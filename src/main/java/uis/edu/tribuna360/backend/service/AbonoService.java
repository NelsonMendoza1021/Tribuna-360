/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.tribuna360.backend.model.Abono;
import uis.edu.tribuna360.backend.repository.AbonoRepository;

@Service
@Transactional
public class AbonoService implements IAbonoService{
    @Autowired
    private AbonoRepository abonoRepo;
    
    @Override
    public List<Abono> getAbonos(){
        return abonoRepo.findAll();
    }
    
    @Override
    public Abono saveAbono(Abono abono){
        return abonoRepo.save(abono);
    }
    
    @Override
    public Abono getAbono(Integer id){
        return abonoRepo.findById(id).orElse(null);
    }
    
    @Override
    public void deleteAbono(Integer id){
        abonoRepo.deleteById(id);
    }
    
    @Override
    public List<Abono> findAbonosDisponibles(){
        return abonoRepo.findByEstado("disponible");
    }
}
