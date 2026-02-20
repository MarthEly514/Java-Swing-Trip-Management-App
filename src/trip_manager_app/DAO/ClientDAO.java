package trip_manager_app.DAO;

import trip_manager_app.models.ClientModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {


   // CREATE
    public boolean addClient(ClientModel client) {
        String sql = "INSERT INTO clients (nom, prenom, e_mail, telephone, mot_de_passe) VALUES (?, ?, ?, ?, ?)";
        boolean isUserSaved = false;
        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getTelephone());
            ps.setString(5, client.getMotDePasse());
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected >0){
                isUserSaved = true;
            }
            else{
                isUserSaved = false;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } 
       return isUserSaved; 
    }
        // READ   
        
    public List<ClientModel> getAllClients() {
       List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
                ) {

            while (rs.next()) {
                ClientModel c = new ClientModel(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("e_mail"),
                        rs.getString("telephone"),
                        rs.getString("mot_de_passe")
                );
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    public long count() {
        String sql = "SELECT COUNT(*) FROM clients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public long countByNameContaining(String partialName) {
        String sql = "SELECT COUNT(*) FROM clients WHERE nom ILIKE ? OR prenom ILIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            String pattern = "%" + partialName + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
             
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public long countByNameStatus(String status) {
        String sql = "SELECT COUNT(*) FROM clients WHERE statut = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, status);
             
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // READ (by id)
    public ClientModel getClientById(int id) {
        String sql = "SELECT * FROM clients WHERE id_client = ?";
        ClientModel client = null;

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new ClientModel(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("e_mail"),
                        rs.getString("telephone"),
                        rs.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
    
    public List<ClientModel> getNewClients() {
        List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients  ORDER BY id_client DESC LIMIT 10";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
                ) {

            while (rs.next()) {
                ClientModel c = new ClientModel(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("e_mail"),
                        rs.getString("telephone"),
                        rs.getString("mot_de_passe")
                );
                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    
    public ClientModel getClientByEmail(String email) {
        String sql = "SELECT * FROM clients WHERE e_mail = ?";
        ClientModel client = null;

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new ClientModel(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("e_mail"),
                        rs.getString("telephone"),
                        rs.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
    
    public List<ClientModel> getMatchingClients(String keyword){
        List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients ";
        if (!keyword.trim().equals("")) {
            sql += "WHERE nom LIKE ? OR prenom LIKE ? OR e_mail LIKE ? ORDER BY id_client DESC LIMIT 15";
        }

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            
             if (!keyword.trim().equals("")) {
                ps.setString(1, "%"+keyword);
                ps.setString(2, "%"+keyword);
                ps.setString(3, "%"+keyword);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ClientModel destination = new ClientModel(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("e_mail"),
                        rs.getString("telephone"),
                        rs.getString("mot_de_passe")
                    );
                    clients.add(destination);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
    

    // UPDATE
    public void updateClient(ClientModel client) {
        String sql = "UPDATE clients SET nom=?, prenom=?, e_mail=?, telephone=? WHERE id_client=?";

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getTelephone());
            ps.setInt(5, client.getIdClient());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id_client=?";

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
