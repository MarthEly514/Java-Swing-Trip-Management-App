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
    private double prix; 
    private int idMoyenTransport; 
    private int placesDisponibles; 
    
    
    
     public VoyageModel() {
        this.placesDisponibles = 50; 
    }
    
    public VoyageModel(String villeDepart, String villeDestination, 
                      LocalDateTime dateDepart, LocalDateTime dateArrive,
                      double prix, int idMoyenTransport) {
        this();
        this.villeDepart = villeDepart;
        this.villeDestination = villeDestination;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        this.prix = prix;
        this.idMoyenTransport = idMoyenTransport;
    }
    
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
       
    public LocalDateTime getDateArrive(){
        return dateArrive;
    }
    
    public double getPrix() { 
        return prix;
    }
    
    public int getIdMoyenTransport() {
        return idMoyenTransport; 
    }
    
    public int getPlacesDisponibles() { 
        return placesDisponibles;
    }
    
    public void setIdVoyage(int idVoyage){
        this.idVoyage=idVoyage;
    }
    
    public void setVilleDepart(String villeDepart){
        this.villeDepart=villeDepart;
    }
    
    public void setVilleDestination(String villeDestination){
        this.villeDestination=villeDestination;
    }
    
    public void setDateDepart(LocalDateTime dateDepart){
        this.dateDepart=dateDepart;
    }
    
    public void setDateArrive(LocalDateTime dateArrive){
        this.dateArrive=dateArrive;
    }
    
    public void setPrix(double prix) { 
        this.prix = prix; 
    }
    
     public void setIdMoyenTransport(int idMoyenTransport) {
         this.idMoyenTransport = idMoyenTransport; 
     }
     
      public void setPlacesDisponibles(int placesDisponibles) { 
          this.placesDisponibles = placesDisponibles; 
      }
      
       public long getDureeEnHeures() {
        if (dateDepart != null && dateArrive != null) {
            return java.time.Duration.between(dateDepart, dateArrive).toHours();
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return villeDepart + " → " + villeDestination + " (" + dateDepart.toLocalDate() + ") - " + prix + "€";
    }
    
    
}
