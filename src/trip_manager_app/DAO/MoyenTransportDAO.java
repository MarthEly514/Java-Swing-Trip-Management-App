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
        String sql = "INSERT INTO moyen_de_transport (noVehicule, typeVehicule, descriptionVehicule, nombrePlaces) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transport.getNoVehicule());
            ps.setString(2, transport.getTypeVehicule());
            ps.setString(3, transport.getDescriptionVehicule());
            ps.setInt(4, transport.getNombrePlaces());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ by id
    public MoyenTransportModel findByNo(int noVehicule) {
        String sql = "SELECT * FROM moyen_de_transport WHERE no_vehicule = ?";
        MoyenTransportModel transport = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noVehicule);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                transport = new MoyenTransportModel(
                        rs.getInt("no_vehicule"),
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
        String sql = "SELECT * FROM moyen_de_transport";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MoyenTransportModel transport = new MoyenTransportModel(
                        rs.getInt("no_vehicule"),
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
        String sql = "UPDATE moyen_de_transport SET type_transport = ?, description = ?, nombre_places = ? WHERE no_vehicule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transport.getTypeVehicule());
            ps.setString(2, transport.getDescriptionVehicule());
            ps.setInt(3, transport.getNombrePlaces());
            ps.setInt(4, transport.getNoVehicule());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void supprimer(int noVehicule) {
        String sql = "DELETE FROM moyen_de_transport WHERE no_vehicule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, noVehicule);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
