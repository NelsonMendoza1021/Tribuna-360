/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.tribuna360.backend.model.Estadio;
import uis.edu.tribuna360.backend.repository.EstadioRepository;

@Service
@Transactional
public class EstadioService implements IEstadioService{
    
    @Autowired
    private EstadioRepository estadioRepo;
    
    @Override
    public List<Estadio> getEstadios(){
        return estadioRepo.findAll();
    }
    
    @Override
    public Estadio getEstadio(Integer id){
        return estadioRepo.findById(id).orElse(null);
    }
    
    @Override
    public Estadio saveEstadio(Estadio estadio){
        return estadioRepo.save(estadio);
    }
    
    @Override
    public void deleteEstadio(Integer id){
        estadioRepo.deleteById(id); 
    }
}
