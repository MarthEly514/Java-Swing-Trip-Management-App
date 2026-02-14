package trip_manager_app.DAO;

import trip_manager_app.models.ClientModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private Connection connection;

    public ClientDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

   // CREATE
    public boolean addClient(ClientModel client) {
        String sql = "INSERT INTO clients (nom, prenom, e_mail, telephone, mot_de_passe) VALUES (?, ?, ?, ?, ?)";
        boolean isUserSaved = false;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
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

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    // READ (by id)
    public ClientModel getClientById(int id) {
        String sql = "SELECT * FROM clients WHERE id_client = ?";
        ClientModel client = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
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
    
    public ClientModel getClientByEmail(String email) {
        String sql = "SELECT * FROM clients WHERE e_mail = ?";
        ClientModel client = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
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
    

    // UPDATE
    public void updateClient(ClientModel client) {
        String sql = "UPDATE clients SET nom=?, prenom=?, e_mail=?, telephone=? WHERE id_client=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
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

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
