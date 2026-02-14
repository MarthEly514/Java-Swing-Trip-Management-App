/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.models;

import java.sql.Date;

/**
 *
 * @author ely
 */
public class DestinationModel{

    private int destinationId;
    private int imageId;
    private String ville;
    private String pays;
    private String description;
    private float note;
    private String imagePath;
    private byte imageByte;

    public DestinationModel(){}

    public DestinationModel(String ville, String pays,  String description, float note,  String imagePath){
        this.ville = ville;
        this.pays = pays;
        this.description = description;
        this.note = note;
        this.imagePath = imagePath;

    }
    //fetching from db constructor
    public DestinationModel(int destinationId, String ville, String pays, String description, float note,  int imageId){
        this.destinationId = destinationId;
        this.ville = ville;
        this.pays = pays;
        this.description = description;
        this.note = note;
        this.imageId = imageId;

    }
    
    //loading into db constructor
    public DestinationModel(int destinationId, String ville, String pays, String description, float note,  byte imageByte){
        this.destinationId = destinationId;
        this.ville = ville;
        this.pays = pays;
        this.description = description;
        this.note = note;
        this.imageByte = imageByte;

    }
    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath.trim();
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the note
     */
    public float getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(float note) {
        this.note = note;
    }

    /**
     * @return the ville
     */
    public String getVille() {
        return ville.trim();
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description.trim();
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPays() {
         return pays.trim();// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the idDestination
     */
    public int getDestinationId() {
        return destinationId;
    }

    /**
     * @return the imageByte
     */
    public byte getImageByte() {
        return imageByte;
    }

    /**
     * @param imageByte the imageByte to set
     */
    public void setImageByte(byte imageByte) {
        this.imageByte = imageByte;
    }

    /**
     * @return the imageId
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * @param imageId the imageId to set
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
