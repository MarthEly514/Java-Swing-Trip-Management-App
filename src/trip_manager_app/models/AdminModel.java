/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.models;

/**
 *
 * @author ely
 */
public class AdminModel {
    private int idAdmin;
    private String eMail;
    private String motDePasse;
    
    
    
     public AdminModel() {}
    
    // Constructeur avec paramètres
    public AdminModel(String eMail, String motDePasse) {
        this.eMail = eMail;
        this.motDePasse = motDePasse;
    }
    
    // for db fetching
    public AdminModel(int idAdmin, String eMail, String motDePasse) {
        this.idAdmin = idAdmin;
        this.eMail = eMail;
        this.motDePasse = motDePasse;
    }
    
    
    
    
    
    
    public int getIdAdmin(){
        return idAdmin;
    }
    
    public String getEmail(){
        return eMail;
    }
    
    public String getMotDePasse(){
        return motDePasse;
    }
    
    
    
    public void setIdAdmin(int idAdmin){
        this.idAdmin=idAdmin;
    }
    
    public void setEmail(String eMail){
        this.eMail = eMail;
    }
    
    public void setMotDePasse(String motDePasse){
        this.motDePasse=motDePasse;
    }
     
}

