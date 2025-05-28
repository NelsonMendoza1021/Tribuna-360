/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import uis.edu.tribuna360.backend.model.Abono;

public interface AbonoRepository extends JpaRepository<Abono, Integer> {
    List<Abono> findByEstado(String estado);
}
