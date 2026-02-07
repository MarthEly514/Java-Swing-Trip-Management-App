/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.controllers;

import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.KeyStroke;
import trip_manager_app.views.LoginView;
import trip_manager_app.views.MainFrame;
import trip_manager_app.views.SignupView;
import trip_manager_app.views.admin.AdminHomepageView;
import trip_manager_app.views.admin.AdminReservationsManagementView;
import trip_manager_app.views.admin.AdminUserManagementView;
import trip_manager_app.views.user.UserHomepageView;

/**
 *
 * @author ely
 */
public class AuthController {
    private MainFrame frame;
    private LoginView loginView;
    private SignupView signupView;
    public AuthController(MainFrame frame, LoginView loginView, SignupView signupView, AdminHomepageView adminHomepageView, UserHomepageView userHomepageView )
    {
        this.loginView = loginView;
        this.signupView = signupView;
        
        this.frame = frame;
        frame.addView(loginView, "loginView");
        frame.addView(signupView, "signupView");
        frame.addView(adminHomepageView, "adminHomepageView");
        frame.addView(userHomepageView, "userHomepageView");
        
        signupView.addLoginRedirectButtonListener(e -> frame.showView("loginView"));
        signupView.addSignupButtonListener(e -> saveUser()); //execute ici une fonction qui se chargera de verifier si l'user est un admin ou non puis de l'authentifier
        
        loginView.addSignupRedirectButtonListener(e -> frame.showView("signupView"));
        loginView.addLoginButtonListener(e -> verifyLogin()); //execute ici une fonction qui se chargera de verifier si l'user est un admin ou non puis de l'authentifier
        
//      adding an "Entry-triggered" login method

//        InputMap inputMap = loginView.getEmailField().getInputMap(JComponent.WHEN_FOCUSED);
//        InputMap inputMap = loginView.getPasswordField().getInputMap(JComponent.WHEN_FOCUSED);
//        ActionMap actionMap = loginView.getPasswordField().getActionMap();
//
//        // Bind Enter key
//        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterKeyPressed");
//        actionMap.put("enterKeyPressed", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Enter pressed!");
//                verifyLogin();
//            }
//        }); 


        // default view frame.showView("Login");
        frame.addView(loginView, "loginView");
        frame.showView("loginView");
    }
    
    private void saveUser(){
        System.out.println("Checking user registration...");
        System.out.println(signupView.getLastName());
        System.out.println(signupView.getFirstName());
        System.out.println(signupView.getEmail());
        System.out.println(signupView.getPhone());
        System.out.println(Arrays.toString(signupView.getPassword()));
        
        frame.showView("userHomepageView");
        signupView.emptyFields();
//        signupView.hideErrorMessage();
    }
    
    private boolean checkPass(char[] pass, String entity){
//        "entity" tells us if the current user is a simple or an admin user
//        Here we get the credentials from the db
        char[] userPass = {'n', 'i', 'g', 'g', 'a'};
        char[] adminPass = {'a','d','m','i','n'};
        boolean authenticated = false;
        
        if(pass.length==userPass.length && entity.equals("user")){
        
            for (int i = 0; i< pass.length; i++){
                authenticated = true;
                if(pass[i] != userPass[i]){
                    authenticated = false;
                    break;
                }
            }
        }
        else if(pass.length==adminPass.length && entity.equals("admin")){
        
            for (int i = 0; i< pass.length; i++){
                authenticated = true;
                if(pass[i] != adminPass[i]){
                    authenticated = false;
                    break;
                }
            }
        }
        
        else{
            authenticated = false;
        }

        
        return authenticated;
    }
    
    private void verifyLogin(){
        String userEmail = "nigga@gmail.com";
        String adminEmail = "admin@admin.admin";
        System.out.println("Checking connexion...");
        
        if (loginView.getEmail().equals(userEmail)){
            if(checkPass(loginView.getPassword(), "user")){
                System.out.println("Checking user connexion...");
                frame.showView("userHomepageView");
                loginView.emptyFields();
                loginView.hideErrorMessage();
            }
            else{
                loginView.showErrorMessage("Erreur de connexion, mot de passe incorrect");
            }
        }
        else if(loginView.getEmail().equals(adminEmail)){
            if(checkPass(loginView.getPassword(), "admin")){
                System.out.println("Checking admin connexion...");
                frame.showView("adminHomepageView");
                loginView.emptyFields();
                loginView.hideErrorMessage();
            }
            else{
                loginView.showErrorMessage("Erreur de connexion, mot de passe incorrect");
            }
        }
        else{
            loginView.showErrorMessage("Erreur de connexion, addresse E-mail incorrecte");
        }
    }
}
