/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.repository;


import uis.edu.tribuna360.backend.model.AbonoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AbonoUsuarioRepository extends JpaRepository<AbonoUsuario, Integer> {
    public List<AbonoUsuario> findByUsuarioIdUsuario(Integer idUsuario);

    public Optional<AbonoUsuario> findByIdAbonoUsuario(Integer idPago);
}
