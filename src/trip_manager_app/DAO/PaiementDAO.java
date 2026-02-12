package trip_manager_app.dao;

import trip_manager_app.models.PaiementModel;
import trip_manager_app.utils.DatabaseConnection;
import trip_manager_app.models.EnumModePaiement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;


public class PaiementDAO {

    //CREATE
    public void ajouter(PaiementModel paiement) {
        String sql = """
            INSERT INTO paiement (reference, montant, date_paiement, mode_paiement, id_reservation)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paiement.getReference());
            ps.setDouble(2, paiement.getMontant());
            ps.setTimestamp(3, Timestamp.valueOf(paiement.getDatePaiement()));
            ps.setString(4, paiement.getModePaiement().name());
            ps.setInt(5, paiement.getIdReservation());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


     //READ by id
    public PaiementModel trouverParReference(int reference) {
        String sql = "SELECT * FROM paiement WHERE reference = ?";
        PaiementModel paiement = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reference);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paiement = new PaiementModel();
                paiement.setReference(rs.getInt("reference"));
                paiement.setMontant(rs.getInt("montant"));
                paiement.setDatePaiement(
                    rs.getTimestamp("date_paiement").toLocalDateTime()
                );
                paiement.setModePaiement(
                    EnumModePaiement.valueOf(rs.getString("mode_paiement"))
                );
                paiement.setIdReservation(rs.getInt("id_reservation"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiement;
    }

    //READ ALL
    public List<PaiementModel> trouverTous() {
        List<PaiementModel> liste = new ArrayList<>();
        String sql = "SELECT * FROM paiement";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PaiementModel paiement = new PaiementModel();
                paiement.setReference(rs.getInt("reference"));
                paiement.setMontant(rs.getInt("montant"));
                paiement.setDatePaiement(
                    rs.getTimestamp("date_paiement").toLocalDateTime()
                );
                paiement.setModePaiement(
                    EnumModePaiement.valueOf(rs.getString("mode_paiement"))
                );
                paiement.setIdReservation(rs.getInt("id_reservation"));

                liste.add(paiement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }


    //UPDATE
    public void modifier(PaiementModel paiement) {
        String sql = """
            UPDATE paiement
            SET montant = ?, date_paiement = ?, mode_paiement = ?, id_reservation = ?
            WHERE reference = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, paiement.getMontant());
            ps.setTimestamp(2, Timestamp.valueOf(paiement.getDatePaiement()));
            ps.setString(3, paiement.getModePaiement().name());
            ps.setInt(4, paiement.getIdReservation());
            ps.setInt(5, paiement.getReference());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //DELETE
    public void supprimer(int reference) {
     String sql = "DELETE FROM paiement WHERE reference = ?";

     try (Connection conn = DatabaseConnection.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reference);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
