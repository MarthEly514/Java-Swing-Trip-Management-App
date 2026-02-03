/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.DAO;

import trip_manager_app.models.ClientModel;
import trip_manager_app.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ely
 */
public class ClientDAO {
    
     // 1. AJOUTER UN CLIENT
    public boolean ajouterClient(ClientModel client) {
        String sql = "INSERT INTO Client (nom, prenom, email, telephone, motDePasse) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, client.getNom());        // nom
            pstmt.setString(2, client.getPrenom());     // prenom  
            pstmt.setString(3, client.getEMail());      // email
            pstmt.setInt(4, client.getTelephone());  // telephone
            pstmt.setString(5, client.getMotDePasse()); // motDePasse
            
            int rows = pstmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur ajout client: " + e.getMessage());
            return false;
        }
    }
    
    // 2. TROUVER PAR ID
    public ClientModel trouverParId(int idClient) {
        String sql = "SELECT * FROM Client WHERE idClient = ?";
        ClientModel client = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idClient);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                client = new ClientModel();
                client.setIdClient(rs.getInt("idClient"));
                client.setNom(rs.getString("nom"));           // minuscule
                client.setPrenom(rs.getString("prenom"));     // minuscule
                client.setEMail(rs.getString("email"));       // minuscule
                client.setTelephone(rs.getInt("telephone")); // minuscule
                client.setMotDePasse(rs.getString("motDePasse")); // exact
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur recherche client: " + e.getMessage());
        }
        return client;
    }
    
    // 3. TROUVER PAR EMAIL
    public ClientModel trouverParEmail(String email) {
        String sql = "SELECT * FROM Client WHERE email = ?";
        ClientModel client = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                client = new ClientModel();
                client.setIdClient(rs.getInt("idClient"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEMail(rs.getString("email"));
                client.setTelephone(rs.getInt("telephone"));
                client.setMotDePasse(rs.getString("motDePasse"));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur recherche par email: " + e.getMessage());
        }
        return client;
    }
    
    // 4. MODIFIER UN CLIENT
    public boolean modifierClient(ClientModel client) {
        String sql = "UPDATE Client SET nom = ?, prenom = ?, email = ?, " +
                     "telephone = ?, motDePasse = ? WHERE idClient = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getPrenom());
            pstmt.setString(3, client.getEMail());
            pstmt.setInt (4, client.getTelephone());
            pstmt.setString(5, client.getMotDePasse());
            pstmt.setInt(6, client.getIdClient());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur modification client: " + e.getMessage());
            return false;
        }
    }
    
    // 5. SUPPRIMER UN CLIENT
    public boolean supprimerClient(int idClient) {
        String sql = "DELETE FROM Client WHERE idClient = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idClient);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur suppression client: " + e.getMessage());
            return false;
        }
    }
    
    // 6. LISTER TOUS LES CLIENTS
    public List<ClientModel> listerTousClients() {
        List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client ORDER BY nom, prenom";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ClientModel client = new ClientModel();
                client.setIdClient(rs.getInt("idClient"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEMail(rs.getString("email"));
                client.setTelephone(rs.getInt("telephone"));
                client.setMotDePasse(rs.getString("motDePasse"));
                clients.add(client);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur liste clients: " + e.getMessage());
        }
        return clients;
    }
    
    // 7. VÉRIFIER CONNEXION
    public ClientModel verifierConnexion(String email, String motDePasse) {
        String sql = "SELECT * FROM Client WHERE email = ? AND motDePasse = ?";
        ClientModel client = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, motDePasse);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                client = new ClientModel();
                client.setIdClient(rs.getInt("idClient"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEMail(rs.getString("email"));
                client.setTelephone(rs.getInt("telephone"));
                client.setMotDePasse(rs.getString("motDePasse"));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur vérification connexion: " + e.getMessage());
        }
        return client;
    }

    
}
