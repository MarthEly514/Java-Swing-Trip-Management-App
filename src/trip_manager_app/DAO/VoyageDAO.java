package trip_manager_app.dao;

import trip_manager_app.models.VoyageModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class VoyageDAO {

    //CREATE
    public boolean addVoyage(VoyageModel v) {
        String sql = """
        INSERT INTO voyage 
        (ville_depart, ville_destination, date_depart, date_arrive, prix, id_transport, places_disponibles)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getVilleDepart());
            ps.setString(2, v.getVilleDestination());
            ps.setTimestamp(3, Timestamp.valueOf(v.getDateDepart()));
            ps.setTimestamp(4, Timestamp.valueOf(v.getDateArrive()));
            ps.setDouble(5, v.getPrix());
            ps.setInt(6, v.getIdMoyenTransport());
            ps.setInt(7, v.getPlacesDisponibles());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //READ ALL
    public List<VoyageModel> getAllVoyages() {
        List<VoyageModel> voyages = new ArrayList<>();
        String sql = "SELECT * FROM voyage";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
            VoyageModel v = new VoyageModel();

            v.setIdVoyage(rs.getInt("id_voyage"));
            v.setVilleDepart(rs.getString("ville_depart"));
            v.setVilleDestination(rs.getString("ville_destination"));
            v.setDateDepart(
                rs.getTimestamp("date_depart").toLocalDateTime()
            );
            v.setDateArrive(
                rs.getTimestamp("date_arrive").toLocalDateTime()
            );
            v.setPrix(rs.getDouble("prix"));
            v.setIdMoyenTransport(rs.getInt("id_transport"));
            v.setPlacesDisponibles(rs.getInt("places_disponibles"));

            voyages.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyages;
    }
}
