/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import java.util.List;
import uis.edu.tribuna360.backend.model.Usuario;


public interface IUsuarioService {
    
    //Listar todos los clubes
    public List<Usuario> getUsuarios();
    
    //Crear o actualizar club
    public Usuario saveUsuario(Usuario usuario);
    
    //Buscar club por ID
    public Usuario getUsuario(Integer id);
    
    //Eliminar club por ID
    public void deleteUsuario(Integer id);
}
