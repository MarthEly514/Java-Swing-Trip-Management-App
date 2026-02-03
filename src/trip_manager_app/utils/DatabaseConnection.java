/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ely
 */
public class DatabaseConnection {
    
     // CONFIGURATION MYSQL
    private static final String URL = "jdbc:mysql://localhost:3306/trip_manager_db";
    private static final String USER = "root"; // ou ton utilisateur MySQL
    private static final String PASSWORD = ""; // ton mot de passe MySQL
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Driver MySQL (tu as déjà le JAR)
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Connexion avec login/mot de passe
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                
                System.out.println("✅ Connexion MySQL réussie");
                
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Driver MySQL non trouvé!");
                System.err.println("Ajoute mysql-connector-java.jar aux bibliothèques");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Erreur connexion MySQL: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connexion MySQL fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
}
