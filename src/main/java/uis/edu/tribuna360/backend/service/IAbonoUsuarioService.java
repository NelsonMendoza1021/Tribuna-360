/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import java.util.List;
import uis.edu.tribuna360.backend.model.AbonoUsuario;

/**
 *
 * @author Nelson
 */
public interface IAbonoUsuarioService {
    List<AbonoUsuario> getAbonoUsuarios();
    AbonoUsuario getAbonoUsuario(Integer id);
    AbonoUsuario saveAbonoUsuario(AbonoUsuario abono);
    void deleteAbonoUsuario(Integer id);
    
    
    List<AbonoUsuario> findByUsuarioId(Integer idUsuario); // Consultar abonos de un usuario
}
