/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uis.edu.tribuna360.backend.model.AbonoUsuario;
import uis.edu.tribuna360.backend.service.AbonoUsuarioService;

import java.util.List;
import uis.edu.tribuna360.backend.model.PreferenciaRequest;
import uis.edu.tribuna360.backend.model.PreferenciaResponse;
import uis.edu.tribuna360.backend.service.MercadoPagoService;

@RestController
@RequestMapping("/api/abonos-usuario")
@CrossOrigin("*")
public class AbonoUsuarioController {
    @Autowired
    private AbonoUsuarioService abonoUsuarioService;
    
    @Autowired
    private MercadoPagoService mercadoPagoService;

    // Obtener todos los abonos de usuario
    @GetMapping
    public List<AbonoUsuario> getAllAbonoUsuarios() {
        return abonoUsuarioService.getAbonoUsuarios();
    }

    // Obtener abonoUsuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<AbonoUsuario> getAbonoUsuarioById(@PathVariable Integer id) {
        AbonoUsuario abonoUsuario = abonoUsuarioService.getAbonoUsuario(id);
        if (abonoUsuario != null) {
            return ResponseEntity.ok(abonoUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo abonoUsuario (compra de abono)
    @PostMapping
    public AbonoUsuario createAbonoUsuario(@RequestBody AbonoUsuario abonoUsuario) {
        abonoUsuario.setIdAbonoUsuario(null);
        return abonoUsuarioService.saveAbonoUsuario(abonoUsuario);
    }

    // Actualizar abonoUsuario
    @PutMapping("/{id}")
    public ResponseEntity<AbonoUsuario> updateAbonoUsuario(@PathVariable Integer id, @RequestBody AbonoUsuario abonoUsuario) {
        AbonoUsuario existente = abonoUsuarioService.getAbonoUsuario(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setAbono(abonoUsuario.getAbono());
        existente.setUsuario(abonoUsuario.getUsuario());
        existente.setFechaCompra(abonoUsuario.getFechaCompra());
        existente.setEstado(abonoUsuario.getEstado());
        existente.setIdPago(abonoUsuario.getIdPago());
        existente.setFechaPago(abonoUsuario.getFechaPago());
        existente.setMetodoPago(abonoUsuario.getMetodoPago());
        existente.setMontoPagado(abonoUsuario.getMontoPagado());
        existente.setEstadoPago(abonoUsuario.getEstadoPago());
        AbonoUsuario actualizado = abonoUsuarioService.saveAbonoUsuario(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar abonoUsuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbonoUsuario(@PathVariable Integer id) {
        AbonoUsuario existente = abonoUsuarioService.getAbonoUsuario(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        abonoUsuarioService.deleteAbonoUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Consultar abonos de un usuario (por idUsuario)
    @GetMapping("/usuario/{idUsuario}")
    public List<AbonoUsuario> getAbonosByUsuario(@PathVariable Integer idUsuario) {
        return abonoUsuarioService.findByUsuarioId(idUsuario);
    }
    
    @PostMapping("/abonos/iniciar-pago")
    public ResponseEntity<PreferenciaResponse> iniciarPago(@RequestBody PreferenciaRequest request) {
        // 1. Tomar usuarioId directamente del request
        Integer usuarioId = request.usuarioId;

        // 2. Crear AbonoUsuario pendiente
        AbonoUsuario abonoUsuario = abonoUsuarioService.crearPendiente(
            request.abonoId,
            usuarioId,
            request.monto
        );
        request.idAbonoUsuario = abonoUsuario.getIdAbonoUsuario();

        // 3. Crear preferencia de MercadoPago
        PreferenciaResponse resp = mercadoPagoService.crearPreferencia(request);

        // 4. Devolver la URL de pago
        return ResponseEntity.ok(resp);
    }
}