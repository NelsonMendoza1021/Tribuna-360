/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uis.edu.tribuna360.backend.model.Usuario;
import uis.edu.tribuna360.backend.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*") // Permite peticiones desde cualquier origen (útil para pruebas front-end)
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    
    //Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getUsuarios();
    }
    
    
    //Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id){
        Usuario usuario = usuarioService.getUsuario(id);
        if (usuario!=null){return ResponseEntity.ok(usuario);}
        else {return ResponseEntity.notFound().build();}
    }
    
    
    //Crear nuevo usuario
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        usuario.setIdUsuario(null);
        return usuarioService.saveUsuario(usuario);
    }
    
    //Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario){
        Usuario existente = usuarioService.getUsuario(id);
        if (existente == null){
            return ResponseEntity.notFound().build();
        }
        
        existente.setCedula(usuario.getCedula());
        existente.setContrasena(usuario.getContrasena());
        existente.setEmail(usuario.getEmail());
        existente.setFechaRegistro(usuario.getFechaRegistro());
        existente.setNombre(usuario.getNombre());
        existente.setRol(usuario.getRol());
        existente.setTelefono(usuario.getTelefono());
        Usuario actualizado = usuarioService.saveUsuario(existente);
        return ResponseEntity.ok(actualizado);
    }
    
    //Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id){
        Usuario existente = usuarioService.getUsuario(id);
        if (existente == null){return ResponseEntity.notFound().build();}
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
    
    
    
}
