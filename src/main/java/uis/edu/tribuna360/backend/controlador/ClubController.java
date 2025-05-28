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
import uis.edu.tribuna360.backend.model.Club;
import uis.edu.tribuna360.backend.service.ClubService;


@RestController
@RequestMapping("/api/clubes")
@CrossOrigin("*") // Permite peticiones desde cualquier origen (útil para pruebas front-end)
public class ClubController {
    @Autowired
    private ClubService clubService;
    
    //Obtener todos los clubes
    @GetMapping
    public List<Club> getAllClubes(){
        return clubService.getClubes();
    }
    
    //Buscar club por ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubByID(@PathVariable Integer id){
        Club club = clubService.getClub(id);
        if (club != null){return ResponseEntity.ok(club);}
        
        else {return ResponseEntity.notFound().build();} 
    }
    
    //Crear un nuevo club
    @PostMapping
    public Club createClub(@RequestBody Club club){
        club.setIdClub(null);
        return clubService.saveClub(club);
    }
    
    //Actualizar club
    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Integer id, @RequestBody Club club){
        Club existente = clubService.getClub(id);
        if (existente == null){
            return ResponseEntity.notFound().build();
        }
        existente.setNombre(club.getNombre());
        Club actualizado = clubService.saveClub(existente);
        return ResponseEntity.ok(actualizado);
    }
    //Eliminar club por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Integer id){
        Club existente = clubService.getClub(id);
        if (existente == null){return ResponseEntity.notFound().build();}
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
    
}
