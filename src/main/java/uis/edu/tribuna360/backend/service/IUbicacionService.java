/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.service;

import java.util.List;
import uis.edu.tribuna360.backend.model.Ubicacion;

public interface IUbicacionService {
    //Listar todos los estadios
    public List<Ubicacion> getUbicaciones();
    
    //Crear o actualizar club
    public Ubicacion saveUbicacion(Ubicacion ubicacion);
    
    //Buscar club por ID
    public Ubicacion getUbicacion(Integer id);
    
    //Eliminar club por ID
    public void deleteUbicacion(Integer id);
}
