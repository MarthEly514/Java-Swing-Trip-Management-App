package trip_manager_app.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class CachedImageLoader {
    
    private static CachedImageLoader instance;
    private final Map<Integer, BufferedImage> imageCache;
    
    private CachedImageLoader() {
        imageCache = new HashMap<>();
    }
    
    public static CachedImageLoader getInstance() {
        if (instance == null) {
            synchronized (CachedImageLoader.class) {
                if (instance == null) {
                    instance = new CachedImageLoader();
                }
            }
        }
        return instance;
    }
    
    private Connection getConnection() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null || conn.isClosed() || !conn.isValid(2)) {
            DatabaseConnection.reconnect(); 
            conn = DatabaseConnection.getConnection();
        }
        
        return conn;
    }
    
    public BufferedImage loadImage(int imageId) {
        if (imageCache.containsKey(imageId)) {
            System.out.println("Loading image " + imageId + " from cache");
            return imageCache.get(imageId);
        }
        
        System.out.println("Loading image " + imageId + " from database");
        BufferedImage image = loadImageFromDatabase(imageId);
        
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
    
    // Preload multiple images
    public void preloadImages(int[] imageIds) {
        for (int id : imageIds) {
            if (!imageCache.containsKey(id)) {
                loadImage(id);
            }
        }
    }
    
    public boolean isImageCached(int imageId) {
        return imageCache.containsKey(imageId);
    }
    
    public void clearCache() {
        imageCache.clear();
        System.out.println("Image cache cleared");
    }
    
    public void removeFromCache(int imageId) {
        imageCache.remove(imageId);
        System.out.println("Image " + imageId + " removed from cache");
    }
    
    public int getCacheSize() {
        return imageCache.size();
    }
    
    private BufferedImage loadImageFromDatabase(int imageId) {
        String query = "SELECT image_data FROM images WHERE id_image = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, imageId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("image_data");
                if (imageBytes != null && imageBytes.length > 0) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                    return ImageIO.read(bis);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading image " + imageId + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
}