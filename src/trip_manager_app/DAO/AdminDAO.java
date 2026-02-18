/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import trip_manager_app.models.AdminModel;
import trip_manager_app.utils.DatabaseConnection;

/**
 *
 * @author ely
 */
public class AdminDAO {


   // CREATE
    public void addAdmin(AdminModel admin) {
        String sql = "INSERT INTO admins (e_mail, mot_de_passe) VALUES (?, ?)";

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1,admin.getEmail());
            ps.setString(2,admin.getMotDePasse());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } 
    }
        // READ   
        
    public List<AdminModel> getAllAdmin() {
       List<AdminModel> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
                ) {

            while (rs.next()) {
                AdminModel a = new AdminModel(
                        rs.getString("e_mail"),
                        rs.getString("mot_de_passe")
                );
                admins.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // READ (by id)
    public AdminModel getAdminById(int id) {
        String sql = "SELECT * FROM admins WHERE id_admin = ?";
        AdminModel admin = null;

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                admin = new AdminModel(
                        rs.getInt("id_admin"),
                        rs.getString("e_mail"),
                        rs.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
    
    public AdminModel getAdminByEmail(String email) {
        String sql = "SELECT * FROM admins WHERE e_mail = ?";
        AdminModel admin = null;

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                admin = new AdminModel(
                        rs.getInt("id_admin"),
                        rs.getString("e_mail"),
                        rs.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
    

//    // UPDATE
//    public void updateAdmin(AdminModel admin) {
//        String sql = "UPDATE client SET nom=?, prenom=?, email=?, telephone=? WHERE id_client=?";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, admin.getNom());
//            ps.setString(2, admin.getPrenom());
//            ps.setString(3, admin.getEmail());
//            ps.setString(4, admin.getTelephone());
//            ps.setInt(5, admin.getIdClient());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // DELETE
//    public void deleteClient(int id) {
//        String sql = "DELETE FROM client WHERE id_client=?";
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, id);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}


