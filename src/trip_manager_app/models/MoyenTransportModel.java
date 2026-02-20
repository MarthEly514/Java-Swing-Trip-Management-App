/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.models;

/**
 *
 * @author estev
 */
public class MoyenTransportModel {

    private String noVehicule;
    private String typeVehicule;
    private String descriptionVehicule;
    private int nombrePlaces;
    
    public MoyenTransportModel() {}
    
    public MoyenTransportModel(String typeVehicule, String descriptionVehicule, int nombrePlaces) {
        this.typeVehicule = typeVehicule;
        this.descriptionVehicule = descriptionVehicule;
        this.nombrePlaces = nombrePlaces;
    }

    public MoyenTransportModel(String noVehicule, String typeVehicule, String descriptionVehicule, int nombrePlaces) {
        this.noVehicule = noVehicule;
        this.typeVehicule = typeVehicule;
        this.descriptionVehicule = descriptionVehicule;
        this.nombrePlaces = nombrePlaces;
    }
    
    public String getNoVehicule(){
        return noVehicule;
    }
    public String getTypeVehicule(){
        return typeVehicule;
    }
    public int getNombrePlaces(){
        return nombrePlaces;
    }
    
    public void setNoVehicule(String noVehicule){
        this.noVehicule= noVehicule;
    }
    
    public void setTypeVehicule(String typeVehicule){
        this.typeVehicule=typeVehicule;
    }
    
    public void setNombrePlaces(int nombrePlaces){
        this.nombrePlaces=nombrePlaces;
    }
    
    public String getDescriptionVehicule() {
        return descriptionVehicule;
    }
    
    public void setDescriptionVehicule(String descriptionVehicule) {
        this.descriptionVehicule = descriptionVehicule;
    }
    
    
     @Override
    public String toString() {
        return typeVehicule + " (No: " + noVehicule + ", Places: " + nombrePlaces + ")";
    }
    
}
