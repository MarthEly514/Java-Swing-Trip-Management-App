/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.controllers;

import trip_manager_app.views.MainFrame;
import trip_manager_app.views.SignupView;
import trip_manager_app.views.user.UserHomepageView;

/**
 *
 * @author ely
 */
public class SignupController {
    private MainFrame frame; 
    public SignupController(MainFrame frame, SignupView signupView, UserHomepageView userHomepageView){
        
        this.frame = frame;
        frame.addView(signupView, "signupView");
        frame.addView(userHomepageView, "userHomepageView");        
        
        signupView.addLoginRedirectButtonListener(e -> frame.showView("loginView"));
        signupView.addSignupButtonListener(e -> frame.showView("userHomepageView"));

    }
}
