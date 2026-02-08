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
    public void addClient(ClientModel client) {
        String sql = "INSERT INTO client (nom, prenom, email, telephone, mot_de_passe) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEMail());
            ps.setInt(4, client.getTelephone());
            ps.setString(5, client.getMotDePasse());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<ClientModel> getAllClients() {
        List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ClientModel c = new ClientModel();
                c.setIdClient(rs.getInt("id_client"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setEMail(rs.getString("email"));
                c.setTelephone(rs.getInt("telephone"));
                c.setMotDePasse(rs.getString("mot_de_passe"));

                clients.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    // READ BY ID
    public ClientModel getClientById(int id) {
        String sql = "SELECT * FROM client WHERE id_client = ?";
        ClientModel client = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                client = new ClientModel();
                client.setIdClient(rs.getInt("id_client"));
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEMail(rs.getString("email"));
                client.setTelephone(rs.getInt("telephone"));
                client.setMotDePasse(rs.getString("mot_de_passe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    // UPDATE
    public void updateClient(ClientModel client) {
        String sql = "UPDATE client SET nom=?, prenom=?, email=?, telephone=?, mot_de_passe=? WHERE id_client=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getEMail());
            ps.setInt(4, client.getTelephone());
            ps.setString(5, client.getMotDePasse());
            ps.setInt(6, client.getIdClient());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteClient(int id) {
        String sql = "DELETE FROM client WHERE id_client = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
