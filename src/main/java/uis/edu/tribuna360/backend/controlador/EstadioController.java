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
import uis.edu.tribuna360.backend.model.Estadio;
import uis.edu.tribuna360.backend.service.EstadioService;

@RestController
@RequestMapping("/api/estadios")
@CrossOrigin("*") // Permite peticiones desde cualquier origen (útil para pruebas front-end)
public class EstadioController {
    @Autowired
    private EstadioService estadioService;
    
    
    //Obtener todos los estadios
    @GetMapping
    public List<Estadio> getAllEstadios(){
        return estadioService.getEstadios();
    }
    
    //Buscar estadio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estadio> getEstadioById(@PathVariable Integer id){
        Estadio estadio = estadioService.getEstadio(id);
        if (estadio!=null){return ResponseEntity.ok(estadio);}
        else {return ResponseEntity.notFound().build();}
    }
    
    //Crear un nuevo estadio
    @PostMapping
    public Estadio createEstadio(@RequestBody Estadio estadio){
        estadio.setIdEstadio(null);
        return estadioService.saveEstadio(estadio);
    }
    
    //Actualizar estadio
    @PutMapping("/{id}")
    public ResponseEntity<Estadio> updateEstadio(@PathVariable Integer id, @RequestBody Estadio estadio){
        Estadio existente = estadioService.getEstadio(id);
        if (existente == null){
            return ResponseEntity.notFound().build();
        }
        existente.setNombre(estadio.getNombre());
        existente.setUbicacion(estadio.getUbicacion());
        existente.setCapacidad(estadio.getCapacidad());
        Estadio actualizado = estadioService.saveEstadio(existente);
        return ResponseEntity.ok(actualizado);
    }
    
    //Eliminar estadio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadio(@PathVariable Integer id){
        Estadio existente = estadioService.getEstadio(id);
        if (existente == null){return ResponseEntity.notFound().build();}
        estadioService.deleteEstadio(id);
        return ResponseEntity.noContent().build();
    }
}
