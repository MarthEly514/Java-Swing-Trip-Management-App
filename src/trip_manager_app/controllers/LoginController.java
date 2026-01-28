/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.controllers;

import trip_manager_app.views.LoginView;
import trip_manager_app.views.MainFrame;
import trip_manager_app.views.user.UserHomepageView;

/**
 *
 * @author ely
 */
public class LoginController {
    private MainFrame frame;
    public LoginController(MainFrame frame, LoginView loginView, UserHomepageView userHomepageView){
        
        this.frame = frame;
        frame.addView(loginView, "loginView");
        frame.addView(userHomepageView, "userHomepageView");
        
        loginView.addSignupRedirectButtonListener(e -> frame.showView("signupView"));
        loginView.addLoginButtonListener(e -> frame.showView("userHomepageView"));

    }
}
