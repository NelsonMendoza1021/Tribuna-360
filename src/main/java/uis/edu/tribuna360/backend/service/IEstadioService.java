/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import java.util.List;
import uis.edu.tribuna360.backend.model.Estadio;

public interface IEstadioService {
    //Listar todos los estadios
    public List<Estadio> getEstadios();
    
    //Crear o actualizar club
    public Estadio saveEstadio(Estadio estadio);
    
    //Buscar club por ID
    public Estadio getEstadio(Integer id);
    
    //Eliminar club por ID
    public void deleteEstadio(Integer id);
}
