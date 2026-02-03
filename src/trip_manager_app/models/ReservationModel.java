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
public class ReservationModel {
    
    private int idReservation;
    private LocalDateTime dateReservation;
    private NewEnumReservation statut;
    private int idClient ;
    private int idVoyage;
    
     // Constructeur pour création
    public ReservationModel(int idClient, int idVoyage, NewEnumReservation statut) {
        this.idClient = idClient;
        this.idVoyage = idVoyage;
        this.statut = statut;
        this.dateReservation = LocalDateTime.now();
    }
    
     // Constructeur pour chargement BDD
    public ReservationModel(int idReservation, LocalDateTime dateReservation,
                           NewEnumReservation statut, int idClient, int idVoyage) {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.idClient = idClient;
        this.idVoyage = idVoyage;
    }
    
    // Constructeur vide
    public ReservationModel() {
    }
    
    
    
    public int getIdReservation(){
        return idReservation;
    }
    
    public int getIdClient(){
        return idClient;
    } 
    
    public int getIdVoyage(){
        return idVoyage;
    }
    
    public LocalDateTime getDateReservation(){
        return dateReservation;
    }
    
    public NewEnumReservation getStatut(){
        return statut;
    }
    
    public void setIdReservation(int idReservation){
        this.idReservation=idReservation;
    }
    
    public void setIdClient(int idClient){
        this.idClient=idClient;
    }
    
    public void setIdVoyage(int idVoyage){
        this.idVoyage=idVoyage;
    }
    
    public void setDateReservation(LocalDateTime dateReservation){
        this.dateReservation=dateReservation;
    }
    
    public void setStatut(NewEnumReservation statut){
        this.statut=statut;
    }
    
      @Override
    public String toString() {
        return "Réservation #" + idReservation + " - Statut: " + statut.getLibelle();
    }
    
    
}
