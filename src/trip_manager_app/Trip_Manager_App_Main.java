/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trip_manager_app;

import trip_manager_app.controllers.*;
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
            
            //User views
            UserHomepageView userHomepageView = new UserHomepageView();
            UserDestinationsView userDestinationsView = new UserDestinationsView(frame);
            UserReservationsView userReservationsView = new UserReservationsView(frame);
            UserProfileView userProfileView = new UserProfileView();
            
            //Admin views
            AdminHomepageView adminHomepageView = new AdminHomepageView();
            AdminReservationsManagementView adminReservationsManagementView = new AdminReservationsManagementView();
            AdminUserManagementView adminUserManagementView = new AdminUserManagementView();
            
            //controllers
//            new LoginController(frame, loginView, userHomepageView);
//            new SignupController(frame, signupView, userHomepageView);
            new ClientController(frame, loginView, userHomepageView, userDestinationsView, userReservationsView, userProfileView);
            new AdminController(frame, loginView, adminHomepageView, adminReservationsManagementView, adminUserManagementView);
            new AuthController(frame, loginView, signupView, adminHomepageView, userHomepageView);
            
            frame.setVisible(true); 
        });
    }
    
}

