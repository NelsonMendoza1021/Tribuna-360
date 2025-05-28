/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import uis.edu.tribuna360.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCedula(String cedula);
    boolean existsByEmail(String email);
    boolean existsByCedula(String cedula);
}
