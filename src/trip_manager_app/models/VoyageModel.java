/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.models;
import java.time.LocalDateTime;

/**
 *
 * @author ely
 */
public class VoyageModel {
    private int idVoyage;
    private String villeDepart;
    private String villeDestination;
    private LocalDateTime dateDepart;
    private LocalDateTime dateArrive;
    
    public int getIdVoyage(){
        return idVoyage;
    }
    
    public String getVilleDepart(){
        return villeDepart;
    }
     
    public String getVilleDestination(){
        return villeDestination;
    }
      
    public LocalDateTime getDateDepart(){
        return dateDepart;
    }
       
    public LocalDateTime getDateaArrive(){
        return dateArrive;
    }
    
    public void setIdVoyage(int idVoyage){
        this.idVoyage=idVoyage;
    }
    
    public void setVilleDepart(String villeDepart){
        this.villeDepart=villeDepart;
    }
    
    public void setvilleDestination(String villeDestination){
        this.villeDestination=villeDestination;
    }
    
    public void setDateDepart(LocalDateTime dateDepart){
        this.dateDepart=dateDepart;
    }
    
    public void setDateArrive(LocalDateTime dateArrive){
        this.dateArrive=dateArrive;
    }
    
    
}
