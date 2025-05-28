/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uis.edu.tribuna360.backend.model.Abono;
import uis.edu.tribuna360.backend.service.AbonoService;

import java.util.List;

@RestController
@RequestMapping("/api/abonos")
@CrossOrigin("*")
public class AbonoController {
    @Autowired
    private AbonoService abonoService;

    // Obtener todos los abonos
    @GetMapping
    public List<Abono> getAllAbonos() {
        return abonoService.getAbonos();
    }

    // Obtener abono por ID
    @GetMapping("/{id}")
    public ResponseEntity<Abono> getAbonoById(@PathVariable Integer id) {
        Abono abono = abonoService.getAbono(id);
        if (abono != null) {
            return ResponseEntity.ok(abono);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo abono
    @PostMapping
    public Abono createAbono(@RequestBody Abono abono) {
        abono.setIdAbono(null);
        return abonoService.saveAbono(abono);
    }

    // Actualizar abono
    @PutMapping("/{id}")
    public ResponseEntity<Abono> updateAbono(@PathVariable Integer id, @RequestBody Abono abono) {
        Abono existente = abonoService.getAbono(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setClub(abono.getClub());
        existente.setEstadio(abono.getEstadio());
        existente.setUbicacion(abono.getUbicacion());
        existente.setTipo(abono.getTipo());
        existente.setDetalle(abono.getDetalle());
        existente.setPrecio(abono.getPrecio());
        existente.setEstado(abono.getEstado());
        existente.setFechaInicio(abono.getFechaInicio());
        existente.setFechaFin(abono.getFechaFin());
        Abono actualizado = abonoService.saveAbono(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar abono por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbono(@PathVariable Integer id) {
        Abono existente = abonoService.getAbono(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        abonoService.deleteAbono(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener abonos disponibles (estado = "disponible")
    @GetMapping("/disponibles")
    public List<Abono> getAbonosDisponibles() {
        return abonoService.findAbonosDisponibles();
    }
}