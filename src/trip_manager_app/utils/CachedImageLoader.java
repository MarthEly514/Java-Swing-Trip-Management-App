/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.utils;
import java.awt.Image;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ely
 */
public class CachedImageLoader {
    
    private static CachedImageLoader instance;
    private Map<Integer, BufferedImage> imageCache;
    private Connection conn;

    public CachedImageLoader() {
        imageCache = new HashMap<>();
        conn = DatabaseConnection.getConnection();
    }
    
    public static CachedImageLoader getInstance() {
        if (instance == null) {
            instance = new CachedImageLoader();
        }
        return instance;
    }
    
    public BufferedImage loadImage(int imageId) {
        // Check cache first
        if (imageCache.containsKey(imageId)) {
            System.out.println("Loading image " + imageId + " from cache");
            return imageCache.get(imageId);
        }
        
        System.out.println("Loading image " + imageId + " from database");
        // Load from database
        BufferedImage image = loadImageFromDatabase(imageId);
        
        // Cache it
        if (image != null) {
            imageCache.put(imageId, image);
        }
        
        return image;
    }
    
    // Load and scale image
    public Image loadImageScaled(int imageId, int width, int height) {
        BufferedImage original = loadImage(imageId);
        if (original != null) {
            return original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
        return null;
    }
    
    // Preload multiple images (useful for lists)
    public void preloadImages(int[] imageIds) {
        for (int id : imageIds) {
            if (!imageCache.containsKey(id)) {
                loadImage(id);
            }
        }
    }
    
    // Check if image is in cache
    public boolean isImageCached(int imageId) {
        return imageCache.containsKey(imageId);
    }
    
    // Clear entire cache
    public void clearCache() {
        imageCache.clear();
        System.out.println("Image cache cleared");
    }
    
    // Remove specific image from cache
    public void removeFromCache(int imageId) {
        imageCache.remove(imageId);
        System.out.println("Image " + imageId + " removed from cache");
    }
    
    // Get cache size
    public int getCacheSize() {
        return imageCache.size();
    }
    
    // Load image from database
    private BufferedImage loadImageFromDatabase(int imageId) {
        BufferedImage image = null;
        String query = "SELECT image_data FROM images WHERE id_image = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, imageId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("image_data");
                if (imageBytes != null && imageBytes.length > 0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                    image = ImageIO.read(bis);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading image " + imageId + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        return image;
    }
    
}
