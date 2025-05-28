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
import uis.edu.tribuna360.backend.model.Ubicacion;
import uis.edu.tribuna360.backend.service.UbicacionService;

@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin("*") // Permite peticiones desde cualquier origen (útil para pruebas front-end)

public class UbicacionController {
    @Autowired
    private UbicacionService ubicacionService;
    
    //Obtener todos los estadios
    @GetMapping
    public List<Ubicacion> getAllUbicaciones(){
        return ubicacionService.getUbicaciones();
    }
    
    //Buscar estadio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getUbicacionById(@PathVariable Integer id){
        Ubicacion ubicacion = ubicacionService.getUbicacion(id);
        if (ubicacion!=null){return ResponseEntity.ok(ubicacion);}
        else {return ResponseEntity.notFound().build();}
    }
    
    //Crear un nuevo estadio
    @PostMapping
    public Ubicacion createUbicacion(@RequestBody Ubicacion ubicacion){
        ubicacion.setIdUbicacion(null);
        return ubicacionService.saveUbicacion(ubicacion);
    }
    
    //Actualizar estadio
    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateUbicacion(@PathVariable Integer id, @RequestBody Ubicacion ubicacion){
        Ubicacion existente = ubicacionService.getUbicacion(id);
        if (existente == null){
            return ResponseEntity.notFound().build();
        }
        existente.setZona(ubicacion.getZona());
        Ubicacion actualizado = ubicacionService.saveUbicacion(existente);
        return ResponseEntity.ok(actualizado);
    }
    
    //Eliminar estadio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Integer id){
        Ubicacion existente = ubicacionService.getUbicacion(id);
        if (existente == null){return ResponseEntity.notFound().build();}
        ubicacionService.deleteUbicacion(id);
        return ResponseEntity.noContent().build();
    }
}
