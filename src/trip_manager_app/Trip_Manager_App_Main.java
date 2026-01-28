/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trip_manager_app;

import trip_manager_app.controllers.LoginController;
import trip_manager_app.controllers.SignupController;
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
            MainFrame frame = new MainFrame();
            LoginView loginView = new LoginView();
            SignupView signupView = new SignupView();
            UserHomepageView userHomepageView = new UserHomepageView();
            new LoginController(frame, loginView, userHomepageView);
            new SignupController(frame, signupView, userHomepageView);

            // default view frame.showView("Login");
            frame.addView(userHomepageView, "userHomepageView");
            frame.showView("userHomepageView");
            frame.setVisible(true); 
        });
    }
    
}

