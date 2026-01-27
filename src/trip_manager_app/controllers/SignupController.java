/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.controllers;

import trip_manager_app.views.MainFrame;
import trip_manager_app.views.SignupView;

/**
 *
 * @author ely
 */
public class SignupController {
    private MainFrame frame; 
    public SignupController(MainFrame frame, SignupView signupView){
        
        this.frame = frame;
        frame.addView(signupView, "signupView");
        
        signupView.addLoginButtonListener(e -> frame.showView("loginView"));

    }
}
