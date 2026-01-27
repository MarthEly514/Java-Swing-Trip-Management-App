/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trip_manager_app;

import trip_manager_app.controllers.LoginController;
import trip_manager_app.controllers.SignupController;
import trip_manager_app.views.LoginView;
import trip_manager_app.views.MainFrame;
import trip_manager_app.views.SignupView;

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
            new LoginController(frame, loginView);
            new SignupController(frame, signupView);

            frame.showView("Login");
            frame.setVisible(true);
        });
    }
    
}

