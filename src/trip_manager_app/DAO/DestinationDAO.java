/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.DAO;

import trip_manager_app.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import trip_manager_app.models.DestinationModel;

/**
 *
 * @author ely
 */
public class DestinationDAO {

    private Connection conn;

    public DestinationDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public boolean addDestination(DestinationModel destination) {
        String sql = "INSERT INTO destinations(ville, pays, description, note, id_image) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, destination.getVille());
            ps.setString(2, destination.getPays());
            ps.setString(3, destination.getDescription());
            ps.setFloat(4, destination.getNote());
            ps.setInt(5, destination.getImageId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<DestinationModel> getAllDestinations() {
        List<DestinationModel> destinations = new ArrayList<>();
        String sql = "SELECT * FROM destinations";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DestinationModel destination = new DestinationModel(
                    rs.getInt("id_destination"),
                    rs.getString("ville"),
                    rs.getString("pays"),
                    rs.getString("description"),
                    rs.getFloat("note"),
                    rs.getInt("id_image")
                );
                destinations.add(destination);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }
    
    public List<DestinationModel> getNDestinations(int n) {
        List<DestinationModel> destinations = new ArrayList<>();
        String sql = "SELECT * FROM destinations";
        if (n > 0) {
            sql += " LIMIT ?";
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            if (n > 0) {
                ps.setInt(1, n);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DestinationModel destination = new DestinationModel(
                        rs.getInt("id_destination"),
                        rs.getString("ville"),
                        rs.getString("pays"),
                        rs.getString("description"),
                        rs.getFloat("note"),
                        rs.getInt("id_image")
                    );
                    destinations.add(destination);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    public List<DestinationModel> getNPopularDestinations(int n) {
        List<DestinationModel> destinations = new ArrayList<>();
        String sql = "SELECT * FROM destinations";
        if (n > 0) {
            sql += " LIMIT ?";
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            if (n > 0) {
                ps.setInt(1, n);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DestinationModel destination = new DestinationModel(
                        rs.getInt("id_destination"),
                        rs.getString("ville"),
                        rs.getString("pays"),
                        rs.getString("description"),
                        rs.getFloat("note"),
                        rs.getInt("id_image")
                    );
                    destinations.add(destination);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }
    
    public List<DestinationModel> getMatchingDestinations(String keyword){
        List<DestinationModel> destinations = new ArrayList<>();
        String sql = "SELECT * FROM destinations ";
        if (!keyword.trim().equals("")) {
            sql += "WHERE ville = ? OR pays = ? LIMIT 15";
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set parameter only if needed
            if (!keyword.trim().equals("")) {
                ps.setString(1, keyword);
                ps.setString(2, keyword);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DestinationModel destination = new DestinationModel(
                        rs.getInt("id_destination"),
                        rs.getString("ville"),
                        rs.getString("pays"),
                        rs.getString("description"),
                        rs.getFloat("note"),
                        rs.getInt("id_image")
                    );
                    destinations.add(destination);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }

    public List<DestinationModel> getNBestDestinations(int n) {
        List<DestinationModel> destinations = new ArrayList<>();
        String sql = "SELECT * FROM destinations WHERE note>=3.5";
        if (n > 0) {
            sql += " LIMIT ?";
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set parameter only if needed
            if (n > 0) {
                ps.setInt(1, n);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DestinationModel destination = new DestinationModel(
                        rs.getInt("id_destination"),
                        rs.getString("ville"),
                        rs.getString("pays"),
                        rs.getString("description"),
                        rs.getFloat("note"),
                        rs.getInt("id_image")
                    );
                    destinations.add(destination);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destinations;
    }
}
