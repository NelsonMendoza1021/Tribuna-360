/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.tribuna360.backend.model.Abono;
import uis.edu.tribuna360.backend.model.AbonoUsuario;
import uis.edu.tribuna360.backend.model.Usuario;
import uis.edu.tribuna360.backend.repository.AbonoRepository;
import uis.edu.tribuna360.backend.repository.AbonoUsuarioRepository;
import uis.edu.tribuna360.backend.repository.UsuarioRepository;

@Service
@Transactional
public class AbonoUsuarioService implements IAbonoUsuarioService{
    @Autowired
    private AbonoUsuarioRepository abonoUsuarioRepo;
    
    @Autowired
    private AbonoRepository abonoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Override
    public List<AbonoUsuario> getAbonoUsuarios() {
        return abonoUsuarioRepo.findAll();
    }

    @Override
    public AbonoUsuario saveAbonoUsuario(AbonoUsuario abonoUsuario) {
        return abonoUsuarioRepo.save(abonoUsuario);
    }

    @Override
    public AbonoUsuario getAbonoUsuario(Integer id) {
        return abonoUsuarioRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteAbonoUsuario(Integer id) {
        abonoUsuarioRepo.deleteById(id);
    }

    @Override
    public List<AbonoUsuario> findByUsuarioId(Integer idUsuario) {
        return abonoUsuarioRepo.findByUsuarioIdUsuario(idUsuario);
    }
    
    public AbonoUsuario crearPendiente(Integer abonoId, Integer usuarioId, Float monto) {
        // Busca el abono y usuario (ajusta según tus repositorios reales)
        Abono abono = abonoRepo.findById(abonoId).orElseThrow(/* ... */);
        Usuario usuario = usuarioRepo.findById(usuarioId).orElseThrow(/* ... */);

        AbonoUsuario abonoUsuario = AbonoUsuario.builder()
            .abono(abono)
            .usuario(usuario)
            .fechaCompra(LocalDate.now())
            .estado("pendiente")
            .estadoPago("pendiente")
            .montoPagado(BigDecimal.valueOf(monto))
            .build();

        return abonoUsuarioRepo.save(abonoUsuario);
    }
    
    public void actualizarPorPago(Integer idAbonoUsuario, String nuevoEstadoPago, LocalDate fechaPago, String tipo, String id) {
    AbonoUsuario abono = abonoUsuarioRepo.findByIdAbonoUsuario(idAbonoUsuario).orElseThrow(() -> new RuntimeException("No se encontró el abono para idPago: " + idAbonoUsuario));
    abono.setEstadoPago(nuevoEstadoPago);
    abono.setFechaPago(fechaPago);
    abono.setMetodoPago(tipo);
    abono.setIdPago(id);
    abonoUsuarioRepo.save(abono);
}
}
