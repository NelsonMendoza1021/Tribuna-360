/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uis.edu.tribuna360.backend.model.PreferenciaRequest;
import uis.edu.tribuna360.backend.model.PreferenciaResponse;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.access.token}")
    private String accessToken;
    public PreferenciaResponse crearPreferencia(PreferenciaRequest request) {
        try {
            MercadoPago.SDK.setAccessToken(accessToken);
            Preference preference = new Preference();

            Item item = new Item()
                    .setTitle(request.descripcion)
                    .setQuantity(1)
                    .setUnitPrice(request.monto);

            preference.appendItem(item);

            if (request.email != null && !request.email.isEmpty()) {
                Payer payer = new Payer();
                payer.setEmail(request.email);
                preference.setPayer(payer);
            }
            
            // Asegúrate que request.idAbonoUsuario existe y es el ID del AbonoUsuario creado antes
            if(request.idAbonoUsuario != null) {
                preference.setExternalReference(request.idAbonoUsuario.toString());
            } else {
                throw new IllegalArgumentException("El idAbonoUsuario es requerido para relacionar el pago.");
            }

            preference.save();

            PreferenciaResponse resp = new PreferenciaResponse();
            resp.initPoint = preference.getInitPoint();
            return resp;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear preferencia de MercadoPago", e);
        }
    }
}