/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uis.edu.tribuna360.backend.model.Club;
import uis.edu.tribuna360.backend.repository.ClubRepository;

@Service
@Transactional
public class ClubService implements IClubService{
    @Autowired
    private ClubRepository clubRepo;
    
    @Override
    public List<Club> getClubes(){
        return clubRepo.findAll();
    }
    
    @Override
    public Club saveClub(Club club){
        return clubRepo.save(club);
    }
    
    @Override
    public Club getClub(Integer id){
        return clubRepo.findById(id).orElse(null);
    }
    
    @Override
    public void deleteClub(Integer id){
        clubRepo.deleteById(id);
    }
}
