/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.controllers;

import trip_manager_app.views.LoginView;
import trip_manager_app.views.MainFrame;

/**
 *
 * @author ely
 */
public class LoginController {
    private MainFrame frame;
    public LoginController(MainFrame frame, LoginView loginView){
        
        this.frame = frame;
        frame.addView(loginView, "loginView");
        
        loginView.addSignupButtonListener(e -> frame.showView("signupView"));

    }
}
