/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.utils.DatabaseConnection;
import trip_manager_app.utils.ImageClass;

/**
 *
 * @author ely
 */
public class ImageDAO {
    
    public boolean addImage(ImageClass image) throws FileNotFoundException {
        String sql = "INSERT INTO images(image_data) VALUES (?)";

        try (   
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
            ) {
            ps.setBinaryStream(1, new FileInputStream(image.getFile()));
     

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //gets the last image uploaded

    public int getLastImageId() {
        
        String sql = "SELECT id_image FROM images ORDER BY id_image DESC LIMIT 1";
        int idImage = 1;
        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
                ) {

            if (rs.next()) {
                    idImage = rs.getInt("id_image");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idImage;
    }
    
    
    // DELETE
    public void deleteImage(int idImage) {
        String sql = "DELETE FROM images WHERE id_image = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idImage);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
