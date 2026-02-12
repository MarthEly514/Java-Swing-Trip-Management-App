package trip_manager_app.dao;

import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoyenTransportDAO {

     //CREATE
    public void ajouter(MoyenTransportModel transport) {
        String sql = "INSERT INTO moyen_de_transport (type_transport, nombre_places) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transport.getTypeVehicule());
            ps.setInt(2, transport.getNombrePlaces());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     //READ by id
    public MoyenTransportModel trouverParId(int id) {
        String sql = "SELECT * FROM moyen_de_transport WHERE id_transport = ?";
        MoyenTransportModel transport = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                transport = new MoyenTransportModel();
                transport.setNoVehicule(rs.getInt("id_transport"));
                transport.setTypeVehicule(rs.getString("type_transport"));
                transport.setNombrePlaces(rs.getInt("nombre_places"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transport;
    }

     //READ all
    public List<MoyenTransportModel> trouverTous() {
        List<MoyenTransportModel> liste = new ArrayList<>();
        String sql = "SELECT * FROM moyen_de_transport";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MoyenTransportModel t = new MoyenTransportModel();
                t.setNoVehicule(rs.getInt("id_transport"));
                t.setTypeVehicule(rs.getString("type_transport"));
                t.setNombrePlaces(rs.getInt("nombre_places"));
                liste.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

     //UPDATE
    public void modifier(MoyenTransportModel transport) {
    String sql = "UPDATE moyen_de_transport SET type_transport = ?, nombre_places = ? WHERE id_transport = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, transport.getTypeVehicule());
        ps.setInt(2, transport.getNombrePlaces());
        ps.setInt(3, transport.getNoVehicule());

        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


     //DELETE
    public void supprimer(int id) {
        String sql = "DELETE FROM moyen_de_transport WHERE id_transport = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
