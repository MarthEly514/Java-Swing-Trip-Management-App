/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.models;
import java.time.LocalDateTime;

/**
 *
 * @author estev
 */
public class PaiementModel {
    private int reference;
    private int montant;
    private LocalDateTime datePaiement;
    private EnumModePaiement modePaiement;
    private int idReservation;
     
    public PaiementModel() {
        this.datePaiement = LocalDateTime.now();
    }
    
    public PaiementModel(int montant, EnumModePaiement modePaiement, int idReservation) {
        this();
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.idReservation = idReservation;
    }
    
    public int getReference(){
        return reference;
    }
    public int getMontant(){
        return montant;
    }
    public LocalDateTime getDatePaiement(){
        return datePaiement;
    }
    public EnumModePaiement getModePaiement(){
        return modePaiement;
    }
    public int getIdReservation(){
        return idReservation;
    }
    public void setReference(int reference){
        this.reference=reference;
    }
    public void setMontant(int montant){
        this.montant=montant;
    }
    public void setDatePaiement(LocalDateTime datePaiement){
        this.datePaiement=datePaiement;
    }
    public void setModePaiement(EnumModePaiement modePaiement){
        this.modePaiement=modePaiement;
    }
    public void setIdReservation(int idReservation){
        this.idReservation=idReservation;
    }
}
