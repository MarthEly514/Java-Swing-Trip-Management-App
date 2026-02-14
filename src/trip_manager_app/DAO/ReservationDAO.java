package trip_manager_app.DAO;

import trip_manager_app.models.ReservationModel;
import trip_manager_app.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import trip_manager_app.models.NewEnumReservation;

public class ReservationDAO {

    private Connection connection;

    public ReservationDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public void addReservation(ReservationModel reservation) {
        String sql = "INSERT INTO reservations (date_reservation, statut, id_client, id_voyage) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(reservation.getDateReservation()));
            ps.setString(2, reservation.getStatut().getLibelle());
            ps.setInt(3, reservation.getIdClient());
            ps.setInt(4, reservation.getIdVoyage());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<ReservationModel> getAllReservations() {
        List<ReservationModel> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ReservationModel reservation = new ReservationModel(
                        rs.getInt("id_reservation"),
                        rs.getTimestamp("date_reservation").toLocalDateTime(),
                        NewEnumReservation.fromString(rs.getString("statut")),
                        rs.getInt("id_client"),
                        rs.getInt("id_voyage")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    
    public List<ReservationModel> getReservationsByStatut(String statut) {
        List<ReservationModel> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE statut=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, statut);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReservationModel reservation = new ReservationModel(
                        rs.getInt("id_reservation"),
                        rs.getTimestamp("date_reservation").toLocalDateTime(),
                        NewEnumReservation.fromString(rs.getString("statut")),
                        rs.getInt("id_client"),
                        rs.getInt("id_voyage")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    
    public List<ReservationModel> getReservationsByStatutByClientId(String statut, int idClient) {
        List<ReservationModel> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE id_client=? AND statut=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idClient);
            ps.setString(2, statut);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReservationModel reservation = new ReservationModel(
                        rs.getInt("id_reservation"),
                        rs.getTimestamp("date_reservation").toLocalDateTime(),
                        NewEnumReservation.fromString(rs.getString("statut")),
                        rs.getInt("id_client"),
                        rs.getInt("id_voyage")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    
    
    // get a certain number of reservations
    public List<ReservationModel> getNReservations(int n) {
        List<ReservationModel> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        if (n > 0) {
            sql += " LIMIT ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            if (n > 0) {
                ps.setInt(1, n);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReservationModel reservation = new ReservationModel(
                            rs.getInt("id_reservation"),
                            rs.getTimestamp("date_reservation").toLocalDateTime(),
                            NewEnumReservation.fromString(rs.getString("statut")),
                            rs.getInt("id_client"),
                            rs.getInt("id_voyage")
                    );
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    
    // READ BY ID
    public ReservationModel getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        ReservationModel reservation = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reservation = new ReservationModel(
                        rs.getInt("id_reservation"),
                        rs.getTimestamp("date_reservation").toLocalDateTime(),
                        NewEnumReservation.fromString(rs.getString("statut")),
                        rs.getInt("id_client"),
                        rs.getInt("id_voyage")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    // UPDATE
    public void updateReservation(ReservationModel r) {
        String sql = "UPDATE reservations SET date_reservation=?, statut=?, id_client=?, id_voyage=? " +
                     "WHERE id_reservation=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(r.getDateReservation()));
            ps.setString(2, r.getStatut().getLibelle());
            ps.setInt(3, r.getIdClient());
            ps.setInt(4, r.getIdVoyage());
            ps.setInt(5, r.getIdReservation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateReservationStatus(int idReservation, String statut) {
        String sql = "UPDATE reservations SET statut=? " +
                     "WHERE id_reservation=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, statut);
            ps.setInt(2, idReservation);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE id_reservation=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

