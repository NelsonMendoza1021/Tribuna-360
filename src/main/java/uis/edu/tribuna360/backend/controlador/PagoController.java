/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uis.edu.tribuna360.backend.model.PreferenciaRequest;
import uis.edu.tribuna360.backend.model.PreferenciaResponse;
import uis.edu.tribuna360.backend.service.MercadoPagoService;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin("*")
public class PagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/preferencia")
    public ResponseEntity<PreferenciaResponse> crearPreferencia(@RequestBody PreferenciaRequest request) {
        return ResponseEntity.ok(mercadoPagoService.crearPreferencia(request));
    }
}