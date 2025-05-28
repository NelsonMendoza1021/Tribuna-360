/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.controlador;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uis.edu.tribuna360.backend.model.MercadoPagoWebhook;
import uis.edu.tribuna360.backend.service.AbonoUsuarioService;

@RestController
@RequestMapping("/api/mercadopago")
public class MercadoPagoWebhookController {
    
    @Autowired
    private AbonoUsuarioService abonoUsuarioService;
    
    @PostMapping("/webhook")
    public ResponseEntity<String> recibirWebhook(@RequestBody MercadoPagoWebhook payload) {
        try {
            String tipo = payload.getType(); // Ej: "payment"
            Integer idAbonoUsuario = payload.getData().getId(); // ID del pago
            String id = payload.getId();

            abonoUsuarioService.actualizarPorPago(idAbonoUsuario, "APROBADO", LocalDate.now(), tipo, id);

            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error procesando webhook");
        }
    }
    
}
