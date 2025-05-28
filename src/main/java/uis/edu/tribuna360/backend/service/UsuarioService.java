/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.tribuna360.backend.model.Usuario;
import uis.edu.tribuna360.backend.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepo;
    
    @Override
    public List<Usuario> getUsuarios(){
        return usuarioRepo.findAll();
    }
    
    @Override
    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }
    
    @Override
    public Usuario getUsuario(Integer id){
        return usuarioRepo.findById(id).orElse(null);
    }
    
    @Override
    public void deleteUsuario(Integer id){
        usuarioRepo.deleteById(id);
    }
    
    
}
