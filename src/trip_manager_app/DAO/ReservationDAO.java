/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.DAO;

import trip_manager_app.models.ReservationModel;
import trip_manager_app.models.NewEnumReservation;
import trip_manager_app.utils.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ely
 */
public class ReservationDAO {
    
    // 1. AJOUTER UNE RÉSERVATION (statut par défaut: EN_ATTENTE)
    public boolean ajouterReservation(ReservationModel reservation) {
        String sql = "INSERT INTO Reservation (idClient, idVoyage, statut) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, reservation.getIdClient());
            pstmt.setInt(2, reservation.getIdVoyage());
            pstmt.setString(3, reservation.getStatut().name());
            
            int rows = pstmt.executeUpdate();
            
            if (rows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    reservation.setIdReservation(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur ajout réservation: " + e.getMessage());
            return false;
        }
    }
    
    // 2. TROUVER PAR ID
    public ReservationModel trouverParId(int idReservation) {
        String sql = "SELECT * FROM Reservation WHERE idReservation = ?";
        ReservationModel reservation = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idReservation);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                reservation = creerReservationDepuisResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur recherche réservation: " + e.getMessage());
        }
        return reservation;
    }
    
    // 3. VALIDER UNE RÉSERVATION (EN_ATTENTE → VALIDE)
    public boolean validerReservation(int idReservation) {
        return changerStatut(idReservation, NewEnumReservation.VALIDE);
    }
    
    // 4. ANNULER UNE RÉSERVATION (→ ANNULE)
    public boolean annulerReservation(int idReservation) {
        return changerStatut(idReservation, NewEnumReservation.ANNULE);
    }
    
    // 5. MODIFIER STATUT (méthode générique)
    private boolean changerStatut(int idReservation, NewEnumReservation nouveauStatut) {
        String sql = "UPDATE Reservation SET statut = ? WHERE idReservation = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nouveauStatut.name());
            pstmt.setInt(2, idReservation);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur modification statut INVALIDE: " + e.getMessage());
            return false;
        }
    }
    
    // 6. SUPPRIMER UNE RÉSERVATION
    public boolean supprimerReservation(int idReservation) {
        String sql = "DELETE FROM Reservation WHERE idReservation = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idReservation);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur suppression réservation: " + e.getMessage());
            return false;
        }
    }
    
    // 7. LISTER TOUTES LES RÉSERVATIONS
    public List<ReservationModel> listerToutesReservations() {
        return executerRequeteAvecFiltre("SELECT * FROM Reservation ORDER BY dateReservation DESC", null);
    }
    
    // 8. TROUVER PAR CLIENT
    public List<ReservationModel> trouverParClient(int idClient) {
        String sql = "SELECT * FROM Reservation WHERE idClient = ? ORDER BY dateReservation DESC";
        return executerRequeteAvecFiltre(sql, idClient);
    }
    
    // 9. TROUVER PAR VOYAGE
    public List<ReservationModel> trouverParVoyage(int idVoyage) {
        String sql = "SELECT * FROM Reservation WHERE idVoyage = ? ORDER BY dateReservation";
        return executerRequeteAvecFiltre(sql, idVoyage);
    }
    
    // 10. TROUVER PAR STATUT
    public List<ReservationModel> trouverParStatut(NewEnumReservation statut) {
        String sql = "SELECT * FROM Reservation WHERE statut = ? ORDER BY dateReservation DESC";
        List<ReservationModel> reservations = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, statut.name());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                reservations.add(creerReservationDepuisResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur réservations par statut: " + e.getMessage());
        }
        return reservations;
    }
    
    // 11. TROUVER RÉSERVATIONS EN ATTENTE D'UN CLIENT
    public List<ReservationModel> trouverEnAttenteParClient(int idClient) {
        String sql = "SELECT * FROM Reservation WHERE idClient = ? AND statut = 'EN_ATTENTE' ORDER BY dateReservation";
        return executerRequeteAvecFiltre(sql, idClient);
    }
    
    // 12. TROUVER RÉSERVATIONS VALIDÉES D'UN CLIENT
    public List<ReservationModel> trouverValideesParClient(int idClient) {
        String sql = "SELECT * FROM Reservation WHERE idClient = ? AND statut = 'VALIDE' ORDER BY dateReservation DESC";
        return executerRequeteAvecFiltre(sql, idClient);
    }
    
    // 13. TROUVER RÉSERVATIONS ANNULÉES D'UN CLIENT
    public List<ReservationModel> trouverAnnuleesParClient(int idClient) {
        String sql = "SELECT * FROM Reservation WHERE idClient = ? AND statut = 'ANNULE' ORDER BY dateReservation DESC";
        return executerRequeteAvecFiltre(sql, idClient);
    }
    
    // 14. COMPTER RÉSERVATIONS PAR CLIENT
    public int compterReservationsClient(int idClient) {
        String sql = "SELECT COUNT(*) FROM Reservation WHERE idClient = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idClient);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur comptage réservations: " + e.getMessage());
        }
        return 0;
    }
    
    // 15. VÉRIFIER SI CLIENT A DÉJÀ RÉSERVÉ CE VOYAGE
    public boolean clientADejaReserve(int idClient, int idVoyage) {
        String sql = "SELECT COUNT(*) FROM Reservation WHERE idClient = ? AND idVoyage = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idClient);
            pstmt.setInt(2, idVoyage);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur vérification réservation existante: " + e.getMessage());
        }
        return false;
    }
    
    // 16. VÉRIFIER SI VOYAGE A DES RÉSERVATIONS
    public boolean voyageADesReservations(int idVoyage) {
        String sql = "SELECT COUNT(*) FROM Reservation WHERE idVoyage = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idVoyage);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur vérification réservations voyage: " + e.getMessage());
        }
        return false;
    }
    
    // MÉTHODES PRIVÉES UTILITAIRES
    
    private ReservationModel creerReservationDepuisResultSet(ResultSet rs) throws SQLException {
        ReservationModel reservation = new ReservationModel();
        reservation.setIdReservation(rs.getInt("idReservation"));
        
        Timestamp timestamp = rs.getTimestamp("dateReservation");
        if (timestamp != null) {
            reservation.setDateReservation(timestamp.toLocalDateTime());
        }
        
        String statutStr = rs.getString("statut");
        reservation.setStatut(NewEnumReservation.fromString(statutStr));
        
        reservation.setIdClient(rs.getInt("idClient"));
        reservation.setIdVoyage(rs.getInt("idVoyage"));
        
        return reservation;
    }
    
    private List<ReservationModel> executerRequeteAvecFiltre(String sql, Integer parametre) {
        List<ReservationModel> reservations = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            if (parametre != null) {
                pstmt.setInt(1, parametre);
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                reservations.add(creerReservationDepuisResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erreur exécution requête: " + e.getMessage());
        }
        return reservations;
    }
    
    
    
}
