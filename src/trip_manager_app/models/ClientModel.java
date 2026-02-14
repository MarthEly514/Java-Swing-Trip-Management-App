/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.models;

/**
 *
 * @author ely
 */
public class ClientModel {
    private int idClient;
    private String nom;
    private String prenom;
    private String eMail;
    private String telephone;
    private String motDePasse;
    
    
    
     public ClientModel() {}
    
    // Constructeur avec paramètres
    public ClientModel(String nom, String prenom, String eMail, String telephone, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.eMail = eMail;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
    }
    
    // for db fetching
    public ClientModel(int idClient, String nom, String prenom, String eMail, String telephone, String motDePasse) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.eMail = eMail;
        this.telephone = telephone;
        this.motDePasse = motDePasse;
    }
    
    
    
    
    
    
    public int getIdClient(){
        return idClient;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
        
    } 
    
    public String getEmail(){
        return eMail;
    }
    
    public String getMotDePasse(){
        return motDePasse;
    }
    
    public String getTelephone(){
        return telephone;
    }
    
    
    
    public void setIdClient(int idClient){
        this.idClient=idClient;
    }
    
    public void setNom(String nom){
        this.nom=nom;
    }
    
    public void setPrenom(String prenom){
        this.prenom=prenom;
        
    } 
    
    public void setEmail(String eMail){
        this.eMail = eMail;
    }
    
    public void setMotDePasse(String motDePasse){
        this.motDePasse=motDePasse;
    }
    
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    
     @Override
    public String toString() {
        return nom + " " + prenom + " (" + eMail + ")";
    }
    
    
    
   
    
}
