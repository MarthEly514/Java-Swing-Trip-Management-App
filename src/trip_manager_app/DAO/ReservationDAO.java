//package Trip_Manager_App.dao;
//
//import trip_manager_app.models.ReservationModel;
//import trip_manager_app.utils.DatabaseConnection;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReservationDAO {
//
//    private Connection connection;
//
//    public ReservationDAO() {
//        connection = DatabaseConnection.getConnection();
//    }
//
//    // CREATE
//    public void addReservation(ReservationModel r) {
//        String sql = "INSERT INTO reservation (date_reservation, statut, id_client, id_voyage) " +
//                     "VALUES (?, ?, ?, ?)";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setDate(1, r.getDateReservation());
//            ps.setString(2, r.getStatut());
//            ps.setInt(3, r.getIdClient());
//            ps.setInt(4, r.getIdVoyage());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // READ ALL
//    public List<ReservationModel> getAllReservations() {
//        List<ReservationModel> reservations = new ArrayList<>();
//        String sql = "SELECT * FROM reservation";
//
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                ReservationModel r = new ReservationModel(
//                        rs.getInt("id_reservation"),
//                        rs.getDate("date_reservation"),
//                        rs.getString("statut"),
//                        rs.getInt("id_client"),
//                        rs.getInt("id_voyage")
//                );
//                reservations.add(r);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return reservations;
//    }
//
//    // READ BY ID
//    public ReservationModel getReservationById(int id) {
//        String sql = "SELECT * FROM reservation WHERE id_reservation = ?";
//        ReservationModel r = null;
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                r = new ReservationModel(
//                        rs.getInt("id_reservation"),
//                        rs.getDate("date_reservation"),
//                        rs.getString("statut"),
//                        rs.getInt("id_client"),
//                        rs.getInt("id_voyage")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return r;
//    }
//
//    // UPDATE
//    public void updateReservation(ReservationModel r) {
//        String sql = "UPDATE reservation SET date_reservation=?, statut=?, id_client=?, id_voyage=? " +
//                     "WHERE id_reservation=?";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setDate(1, r.getDateReservation());
//            ps.setString(2, r.getStatut());
//            ps.setInt(3, r.getIdClient());
//            ps.setInt(4, r.getIdVoyage());
//            ps.setInt(5, r.getIdReservation());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // DELETE
//    public void deleteReservation(int id) {
//        String sql = "DELETE FROM reservation WHERE id_reservation=?";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

