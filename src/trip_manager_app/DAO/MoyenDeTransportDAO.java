//package trip_manager_app.dao;
//
//import trip_manager_app.model.MoyenDeTransportModel;
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
//public class MoyenDeTransportDAO {
//
//    // CREATE
//    public void ajouter(MoyenDeTransportModel transport) {
//        String sql = "INSERT INTO moyen_de_transport (type_transport, description) VALUES (?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, transport.getTypeTransport());
//            ps.setString(2, transport.getDescription());
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // READ by id
//    public MoyenDeTransportModel trouverParId(int id) {
//        String sql = "SELECT * FROM moyen_de_transport WHERE id_transport = ?";
//        MoyenDeTransportModel transport = null;
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                transport = new MoyenDeTransportModel(
//                        rs.getInt("id_transport"),
//                        rs.getString("type_transport"),
//                        rs.getString("description")
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return transport;
//    }
//
//    // READ all
//    public List<MoyenDeTransportModel> trouverTous() {
//        List<MoyenDeTransportModel> liste = new ArrayList<>();
//        String sql = "SELECT * FROM moyen_de_transport";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                MoyenDeTransportModel transport = new MoyenDeTransportModel(
//                        rs.getInt("id_transport"),
//                        rs.getString("type_transport"),
//                        rs.getString("description")
//                );
//                liste.add(transport);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return liste;
//    }
//
//    // UPDATE
//    public void modifier(MoyenDeTransportModel transport) {
//        String sql = "UPDATE moyen_de_transport SET type_transport = ?, description = ? WHERE id_transport = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setString(1, transport.getTypeTransport());
//            ps.setString(2, transport.getDescription());
//            ps.setInt(3, transport.getIdTransport());
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // DELETE
//    public void supprimer(int id) {
//        String sql = "DELETE FROM moyen_de_transport WHERE id_transport = ?";
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
