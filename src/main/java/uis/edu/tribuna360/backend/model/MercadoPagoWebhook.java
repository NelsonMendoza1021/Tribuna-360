/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MercadoPagoWebhook {
    public String action;
    public String api_version;
    public Data data;
    public String date_created;
    public String id;
    public boolean live_mode;
    public String type;
    public long user_id;

    public static class Data {
        public Integer id;
        
        public Integer getId(){
            return id;
        }
    }
}
