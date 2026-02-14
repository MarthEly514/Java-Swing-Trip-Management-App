/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.models;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author ely
 */
public class VoyageModel {
    private int idVoyage;
    private String villeDepart;
    private String villeDestination;
    private LocalDate dateDepart;
    private LocalDate dateRetour;
    private BigDecimal prix; 
    private int noVehicule;
    
    
    
     public VoyageModel() {
    }
    
    public VoyageModel(String villeDepart, String villeDestination, LocalDate dateDepart, LocalDate dateRetour, BigDecimal prix, int noVehicule) {
        this();
        this.villeDepart = villeDepart;
        this.villeDestination = villeDestination;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.prix = prix;
        this.noVehicule = noVehicule;
    }
    
    public VoyageModel(int idVoyage, String villeDepart, String villeDestination, LocalDate dateDepart, LocalDate dateRetour, BigDecimal prix, int noVehicule) {
        this();
        this.idVoyage = idVoyage;
        this.villeDepart = villeDepart;
        this.villeDestination = villeDestination;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.prix = prix;
        this.noVehicule = noVehicule;
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
      
    public LocalDate getDateDepart(){
        return dateDepart;
    }
       
    public LocalDate getDateRetour(){
        return dateRetour;
    }
    
    public BigDecimal getPrix() { 
        return prix;
    }
    
    public int getNoVehicule() {
        return noVehicule; 
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
    
    public void setDateDepart(LocalDate dateDepart){
        this.dateDepart=dateDepart;
    }
    
    public void setDateRetour(LocalDate dateRetour){
        this.dateRetour=dateRetour;
    }
    
    public void setPrix(BigDecimal prix) { 
        this.prix = prix; 
    }
    
     public void setNoVehicule(int noVehicule) {
         this.noVehicule = noVehicule; 
     }
     
      
       public long getDureeEnHeures() {
        if (dateDepart != null && dateRetour != null) {
            return java.time.Duration.between(dateDepart, dateRetour).toHours();
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return villeDepart + " → " + villeDestination + " (" + dateDepart + ") - " + prix + "€";
    }
    
    
}
