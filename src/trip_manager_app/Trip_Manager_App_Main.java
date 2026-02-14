/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trip_manager_app;

import trip_manager_app.controllers.*;
import trip_manager_app.services.AuthService;
import trip_manager_app.views.LoginView;
import trip_manager_app.views.*;
import trip_manager_app.views.user.*;
import trip_manager_app.views.admin.*;

/**
 *
 * @author ely
 */
public class Trip_Manager_App_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        javax.swing.SwingUtilities.invokeLater(()->{
            
            // pages
            MainFrame frame = new MainFrame();
            LoginView loginView = new LoginView();
            SignupView signupView = new SignupView();
            
            //Auth controller is a layer between the main app and the views/controllers
            new AuthService(frame, loginView, signupView);
            
            frame.setVisible(true); 
        });
    }
    
}

