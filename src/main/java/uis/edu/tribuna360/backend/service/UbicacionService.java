/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.tribuna360.backend.model.Ubicacion;
import uis.edu.tribuna360.backend.repository.UbicacionRepository;

@Service
@Transactional
public class UbicacionService implements IUbicacionService{
    
    @Autowired
    private UbicacionRepository ubicacionRepo;
    
    @Override
    public List<Ubicacion> getUbicaciones(){
        return ubicacionRepo.findAll();
    }
    
    @Override
    public Ubicacion getUbicacion(Integer id){
        return ubicacionRepo.findById(id).orElse(null);
    }
    
    @Override
    public Ubicacion saveUbicacion(Ubicacion ubicacion){
        return ubicacionRepo.save(ubicacion);
    }
    
    @Override
    public void deleteUbicacion(Integer id){
        ubicacionRepo.deleteById(id);
    }
}
