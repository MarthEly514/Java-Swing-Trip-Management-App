package Trip_Manager_App.dao;

import trip_manager_app.utils.DatabaseConnection;
import trip_manager_app.models.ClientModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private Connection connection;

    public ClientDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addClient(ClientModel client) {
        String sql = "INSERT INTO client (nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getTelephone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ 
    public List<ClientModel> getAllClients() {
        List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ClientModel c = new ClientModel(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
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
        String sql = "SELECT * FROM client WHERE id_client = ?";
        ClientModel client = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new ClientModel(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    // UPDATE
    public void updateClient(ClientModel client) {
        String sql = "UPDATE client SET nom=?, prenom=?, email=?, telephone=? WHERE id_client=?";

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
        String sql = "DELETE FROM client WHERE id_client=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
