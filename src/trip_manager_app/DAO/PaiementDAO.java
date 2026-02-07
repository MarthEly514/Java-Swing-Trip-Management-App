//package trip_manager_app.dao;
//
//import trip_manager_app.model.PaiementModel;
//import trip_manager_app.utils.DatabaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PaiementDAO {
//
//    // CREATE
//    public void ajouter(PaiementModel paiement) {
//        String sql = "INSERT INTO paiement (montant, date_paiement, mode_paiement, statut, id_reservation) " +
//                     "VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setDouble(1, paiement.getMontant());
//            ps.setDate(2, paiement.getDatePaiement());
//            ps.setString(3, paiement.getModePaiement());
//            ps.setString(4, paiement.getStatut());
//            ps.setInt(5, paiement.getIdReservation());
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // READ by id
//    public PaiementModel trouverParId(int id) {
//        String sql = "SELECT * FROM paiement WHERE id_paiement = ?";
//        PaiementModel paiement = null;
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                paiement = new PaiementModel(
//                        rs.getInt("id_paiement"),
//                        rs.getDouble("montant"),
//                        rs.getDate("date_paiement"),
//                        rs.getString("mode_paiement"),
//                        rs.getString("statut"),
//                        rs.getInt("id_reservation")
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return paiement;
//    }
//
//    // READ all
//    public List<PaiementModel> trouverTous() {
//        List<PaiementModel> liste = new ArrayList<>();
//        String sql = "SELECT * FROM paiement";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                PaiementModel paiement = new PaiementModel(
//                        rs.getInt("id_paiement"),
//                        rs.getDouble("montant"),
//                        rs.getDate("date_paiement"),
//                        rs.getString("mode_paiement"),
//                        rs.getString("statut"),
//                        rs.getInt("id_reservation")
//                );
//                liste.add(paiement);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return liste;
//    }
//
//    // UPDATE
//    public void modifier(PaiementModel paiement) {
//        String sql = "UPDATE paiement SET montant=?, date_paiement=?, mode_paiement=?, statut=?, id_reservation=? " +
//                     "WHERE id_paiement=?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setDouble(1, paiement.getMontant());
//            ps.setDate(2, paiement.getDatePaiement());
//            ps.setString(3, paiement.getModePaiement());
//            ps.setString(4, paiement.getStatut());
//            ps.setInt(5, paiement.getIdReservation());
//            ps.setInt(6, paiement.getIdPaiement());
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // DELETE
//    public void supprimer(int id) {
//        String sql = "DELETE FROM paiement WHERE id_paiement = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
