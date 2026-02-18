package trip_manager_app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = 
            "jdbc:mysql://localhost:3306/trip_manager_app_db?useSSL=false&serverTimezone=UTC&autoReconnect=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection;
    
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed() || !connection.isValid(2)) {
                if (connection != null && !connection.isClosed()) {
                    connection.close(); // Close old connection first
                }
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à la base réussie ✅");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de connexion ❌");
            e.printStackTrace();
            
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Reconnexion réussie ✅");
            } catch (SQLException ex) {
                System.err.println("Échec de reconnexion ❌");
                ex.printStackTrace();
            }
        }
        return connection;
    }
    
    public static void reconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database reconnected successfully ✅");
        } catch (SQLException e) {
            System.err.println("Failed to reconnect ❌: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null; 
                System.out.println("Database connection closed ✅");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}