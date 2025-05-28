/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import java.util.List;
import uis.edu.tribuna360.backend.model.Abono;

public interface IAbonoService {
    List<Abono> getAbonos();
    Abono getAbono(Integer id);
    Abono saveAbono(Abono abono);
    void deleteAbono(Integer id);

    // Métodos adicionales para casos de uso específicos (opcional)
    List<Abono> findAbonosDisponibles(); // ejemplo: abonos en estado "disponible"
}
