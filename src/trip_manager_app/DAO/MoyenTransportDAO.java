package trip_manager_app.DAO;

import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoyenTransportDAO {

    // CREATE
    public void addMoyenTransport(MoyenTransportModel transport) {
        String sql = "INSERT INTO moyens_transport (noVehicule, typeVehicule, descriptionVehicule, nombrePlaces) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transport.getNoVehicule());
            ps.setString(2, transport.getTypeVehicule());
            ps.setString(3, transport.getDescriptionVehicule());
            ps.setInt(4, transport.getNombrePlaces());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ by id
    public MoyenTransportModel findByNo(String noVehicule) {
        String sql = "SELECT * FROM moyens_transport WHERE no_vehicule = ?";
        MoyenTransportModel transport = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, noVehicule);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                transport = new MoyenTransportModel(
                        rs.getString("no_vehicule"),
                        rs.getString("type_transport"),
                        rs.getString("description"),
                        rs.getInt("nombre_places")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transport;
    }

    // READ all
    public List<MoyenTransportModel> getAll() {
        List<MoyenTransportModel> liste = new ArrayList<>();
        String sql = "SELECT * FROM moyens_transport";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MoyenTransportModel transport = new MoyenTransportModel(
                        rs.getString("no_vehicule"),
                        rs.getString("type_transport"),
                        rs.getString("description"),
                        rs.getInt("nombre_places")
                );
                liste.add(transport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
    
    public List<MoyenTransportModel> getAllByType(String typeTransport) {
        List<MoyenTransportModel> liste = new ArrayList<>();
        String sql = "SELECT * FROM moyens_transport WHERE type_transport=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, typeTransport);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MoyenTransportModel transport = new MoyenTransportModel(
                        rs.getString("no_vehicule"),
                        rs.getString("type_transport"),
                        rs.getString("description"),
                        rs.getInt("nombre_places")
                );
                liste.add(transport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    // UPDATE
    public void modifier(MoyenTransportModel transport) {
        String sql = "UPDATE moyens_transport SET type_transport = ?, description = ?, nombre_places = ? WHERE no_vehicule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transport.getTypeVehicule());
            ps.setString(2, transport.getDescriptionVehicule());
            ps.setInt(3, transport.getNombrePlaces());
            ps.setString(4, transport.getNoVehicule());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void supprimer(int noVehicule) {
        String sql = "DELETE FROM moyens_transport WHERE no_vehicule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noVehicule);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MoyenTransportModel> getNTransports(int n) {
        List<MoyenTransportModel> liste = new ArrayList<>();
        String sql = "SELECT * FROM moyens_transport LIMIT ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MoyenTransportModel transport = new MoyenTransportModel(
                        rs.getString("no_vehicule"),
                        rs.getString("type_transport"),
                        rs.getString("description"),
                        rs.getInt("nombre_places")
                );
                liste.add(transport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<MoyenTransportModel> getMatchingTransports(String searchText) {
        List<MoyenTransportModel> liste = new ArrayList<>();
        String sql = "SELECT * FROM moyens_transport ";
        if (!searchText.trim().equals("")) {
            sql += "WHERE no_vehicule LIKE ? OR description LIKE ? LIMIT 15";
        }

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            
             if (!searchText.trim().equals("")) {
                ps.setString(1, "%"+searchText);
                ps.setString(2, "%"+searchText);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MoyenTransportModel transport = new MoyenTransportModel(
                            rs.getString("no_vehicule"),
                            rs.getString("type_transport"),
                            rs.getString("description"),
                            rs.getInt("nombre_places")
                    );
                    liste.add(transport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public long count() {
        String sql = "SELECT COUNT(*) FROM moyens_transport";
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
}
