/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import java.util.List;
import uis.edu.tribuna360.backend.model.Club;

public interface IClubService {
    
    //Listar todos los clubes
    public List<Club> getClubes();
    
    //Crear o actualizar club
    public Club saveClub(Club club);
    
    //Buscar club por ID
    public Club getClub(Integer id);
    
    //Eliminar club por ID
    public void deleteClub(Integer id);
}