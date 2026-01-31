package trip_manager_app.dao;

import trip_manager_app.models.VoyageModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoyageDAO {

    private Connection conn;

    public VoyageDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // CREATE
    public boolean addVoyage(VoyageModel v) {
        String sql = "INSERT INTO voyage(destination, date_depart, date_retour, prix) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getDestination());
            ps.setDate(2, new java.sql.Date(v.getDateDepart().getTime()));
            ps.setDate(3, new java.sql.Date(v.getDateRetour().getTime()));
            ps.setDouble(4, v.getPrix());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // READ (tous les voyages)
    public List<VoyageModel> getAllVoyages() {
        List<VoyageModel> voyages = new ArrayList<>();
        String sql = "SELECT * FROM voyage";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                VoyageModel v = new VoyageModel(
                    rs.getInt("id_voyage"),
                    rs.getString("destination"),
                    rs.getDate("date_depart"),
                    rs.getDate("date_retour"),
                    rs.getDouble("prix")
                );
                voyages.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyages;
    }
}
