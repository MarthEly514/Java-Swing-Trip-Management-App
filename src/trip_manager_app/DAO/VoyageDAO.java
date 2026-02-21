package trip_manager_app.DAO;

import trip_manager_app.models.VoyageModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoyageDAO {

    // CREATE
    public boolean addVoyage(VoyageModel v) {
        String sql = "INSERT INTO voyages(ville_depart, ville_destination, date_depart, date_retour, prix, no_vehicule) VALUES (?, ?, ?, ?, ?, ?)";

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1, v.getVilleDepart());
            ps.setString(2, v.getVilleDestination());
            ps.setDate(3, Date.valueOf(v.getDateDepart()));
            ps.setDate(4, Date.valueOf(v.getDateRetour()));
            ps.setBigDecimal(5, v.getPrix());
            ps.setInt(6, v.getNoVehicule());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // READ (tous les voyages)
    public List<VoyageModel> getAllVoyages() {
        List<VoyageModel> voyages = new ArrayList<>();
        String sql = "SELECT * FROM voyages ORDER BY id_voyage DESC";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
                ) {

            while (rs.next()) {
                VoyageModel v = new VoyageModel(
                    rs.getInt("id_voyage"),
                    rs.getString("ville_depart"),
                    rs.getString("ville_destination"),
                    rs.getDate("date_depart").toLocalDate(),
                    rs.getDate("date_retour").toLocalDate(),
                    rs.getBigDecimal("prix"),
                    rs.getInt("no_vehicule")
                );
                voyages.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyages;
    }
    
    public VoyageModel getLastVoyage() {
        String sql = "SELECT * FROM voyages ORDER BY id_voyage DESC LIMIT 1";
        VoyageModel voyage = null;

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
                ) {

            if (rs.next()) {
                    voyage = new VoyageModel(
                    rs.getInt("id_voyage"),
                    rs.getString("ville_depart"),
                    rs.getString("ville_destination"),
                    rs.getDate("date_depart").toLocalDate(),
                    rs.getDate("date_retour").toLocalDate(),
                    rs.getBigDecimal("prix"),
                    rs.getInt("no_vehicule")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyage;
    }

    public VoyageModel getVoyageById(int idVoyage) {
        String sql = "SELECT * FROM voyages WHERE id_voyage=?";
        VoyageModel voyage = null;
        
        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setInt(1, idVoyage);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    voyage = new VoyageModel(
                    rs.getInt("id_voyage"),
                    rs.getString("ville_depart"),
                    rs.getString("ville_destination"),
                    rs.getDate("date_depart").toLocalDate(),
                    rs.getDate("date_retour").toLocalDate(),
                    rs.getBigDecimal("prix"),
                    rs.getInt("no_vehicule")
                );
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyage;
    }
    
   public long getVoyageCountByDestination(String villeDestination) {
        if (villeDestination == null || villeDestination.trim().isEmpty()) {
            return 0; // or throw IllegalArgumentException
        }

        String sql = "SELECT COUNT(*) FROM voyages WHERE ville_destination = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, villeDestination.trim()); // trim to avoid whitespace issues

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider: Logger.getLogger(getClass().getName()).severe(...);
        }

        return 0; // safer default than -1
    }
    
    public VoyageModel getVoyageByDate(LocalDate dateDepart) {
        String sql = "SELECT * FROM voyages WHERE date_depart=?";
        VoyageModel voyage = null;
        
        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setDate(1, Date.valueOf(dateDepart));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    voyage = new VoyageModel(
                    rs.getInt("id_voyage"),
                    rs.getString("ville_depart"),
                    rs.getString("ville_destination"),
                    rs.getDate("date_depart").toLocalDate(),
                    rs.getDate("date_retour").toLocalDate(),
                    rs.getBigDecimal("prix"),
                    rs.getInt("no_vehicule")
                );
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voyage;
    }

    public void deleteVoyage(int idVoyage) {
        String sql = "DELETE FROM voyages WHERE id_voyage=?";

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setInt(1, idVoyage);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
