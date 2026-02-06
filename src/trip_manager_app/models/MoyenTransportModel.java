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
    private int noVehicule;
    private String typeVehicule;
    private int nombrePlaces;
    
    public MoyenTransportModel() {}
    
    public MoyenTransportModel(String typeVehicule, int nombrePlaces) {
        this.typeVehicule = typeVehicule;
        this.nombrePlaces = nombrePlaces;
    }
    
    public int getNoVehicule(){
        return noVehicule;
    }
    public String getTypeVehicule(){
        return typeVehicule;
    }
    public int getNombrePlaces(){
        return nombrePlaces;
    }
    
    public void setNoVehicule(int noVehicule){
        this.noVehicule= noVehicule;
    }
    public void setTypeVehicule(String typeVehicule){
        this.typeVehicule=typeVehicule;
    }
    public void setNombrePlaces(int nombrePlaces){
        this.nombrePlaces=nombrePlaces;
    }
    
     @Override
    public String toString() {
        return typeVehicule + " (No: " + noVehicule + ", Places: " + nombrePlaces + ")";
    }
    
}
