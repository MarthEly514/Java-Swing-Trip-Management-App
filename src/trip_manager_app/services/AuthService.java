/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.services;

import java.util.Arrays;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.KeyStroke;
import trip_manager_app.DAO.AdminDAO;
import trip_manager_app.DAO.ClientDAO;
import trip_manager_app.controllers.AdminController;
import trip_manager_app.controllers.ClientController;
import trip_manager_app.models.AdminModel;
import trip_manager_app.models.ClientModel;
import trip_manager_app.views.LoginView;
import trip_manager_app.views.MainFrame;
import trip_manager_app.views.SignupView;
import trip_manager_app.views.admin.*;
import trip_manager_app.views.user.*;

/**
 *
 * @author ely
 */
public class AuthService {
    
    private MainFrame frame;
    private LoginView loginView;
    private SignupView signupView;
    private ClientDAO clientDao;
    private AdminDAO adminDao;
    private AdminHomepageView adminHomepageView;
    private AdminReservationsManagementView adminReservationsManagementView;
    private AdminUserManagementView adminUserManagementView;
    private AdminDestinationsView adminDestinationsView;
    private UserHomepageView userHomepageView;
    private UserDestinationsView userDestinationsView;
    private UserReservationsView userReservationsView;
    private UserProfileView userProfileView;
    private AdminTransportManagementView adminTransportManagementView;
    
    public AuthService (MainFrame frame, LoginView loginView, SignupView signupView)
    {
        this.clientDao = new ClientDAO();
        this.adminDao = new AdminDAO();
        this.loginView = loginView;
        this.signupView = signupView;
        
        this.frame = frame;
        frame.addView(loginView, "loginView");
        frame.addView(signupView, "signupView");
        
        signupView.addLoginRedirectButtonListener(e -> frame.showView("loginView"));
        signupView.addSignupButtonListener(e -> saveUser()); //execute ici une fonction qui se chargera de verifier si l'user est un admin ou non puis de l'authentifier
        
        loginView.addSignupRedirectButtonListener(e -> frame.showView("signupView"));
        loginView.addLoginButtonListener(e -> verifyLogin()); //execute ici une fonction qui se chargera de verifier si l'user est un admin ou non puis de l'authentifier
        
//        // START OF USER TESTING BLOCK
//        
//        ClientModel defaultClient = new ClientModel(
//                5,
//                "Eaven",
//                "Sen",
//                "Sen",
//                "Sen",
//                "sen"
//        
//        );
//        userHomepageView = new UserHomepageView(frame, defaultClient);
//        userDestinationsView = new UserDestinationsView(frame, defaultClient);
//        userReservationsView = new UserReservationsView(frame, defaultClient);
//        userProfileView = new UserProfileView(defaultClient);
//        
//        new ClientController(frame, loginView, userHomepageView, userDestinationsView, userReservationsView, userProfileView);
//
//        //END OF TESTING BLOCK

//        // START OF ADMIN TESTING BLOCK
//        
//        AdminModel defaultAdmin = new AdminModel(
//                5,
//                "eaven@admin.com",
//                "#senArin"    
//        );
//
//        adminHomepageView = new AdminHomepageView(frame);
//        adminReservationsManagementView = new AdminReservationsManagementView();
//        adminUserManagementView = new AdminUserManagementView();
//        adminDestinationsView = new AdminDestinationsView(frame);
//        adminTransportManagementView = new AdminTransportManagementView(frame);
//        
//        new AdminController(frame, loginView, adminHomepageView, adminReservationsManagementView, adminUserManagementView, adminDestinationsView, adminTransportManagementView);
//
//        //END OF TESTING BLOCK
        
        //User views
        userHomepageView = null;
        userDestinationsView = null;
        userReservationsView = null;
        userProfileView = null;

        //Admin views
        adminHomepageView = null;
        adminReservationsManagementView = null;
        adminUserManagementView = null;
        adminDestinationsView = null;
        adminTransportManagementView = null;

        //controllers

        
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
//            }6
//        }); destroyZalem

        // default view frame.showView("Login");
        frame.addView(loginView, "loginView");
        frame.showView("loginView");
//        frame.addView(adminHomepageView, "adminHomepageView");
//        frame.showView("adminHomepageView");
//        frame.addView(userHomepageView, "userHomepageView");
//        frame.showView("userHomepageView");
    }
    
    private void saveUser(){
        System.out.println("Checking user registration...");
        System.out.println(signupView.getLastName());
        System.out.println(signupView.getFirstName());
        System.out.println(signupView.getEmail());
        System.out.println(signupView.getPhone());
        System.out.println(signupView.getPassword());
        
        ClientModel client = new ClientModel(
            signupView.getLastName(),
            signupView.getFirstName(),
            signupView.getEmail(),
            signupView.getPhone(),
            new String(signupView.getPassword())
            );
        
        boolean isUserSaved = clientDao.addClient(client);
        
        if(isUserSaved){
            
            //views
            userHomepageView =new UserHomepageView(frame, client);
            userDestinationsView = new UserDestinationsView(frame, client);
            userReservationsView = new UserReservationsView(frame, client);
            userProfileView = new UserProfileView(client);
            
            //controller
            new ClientController(frame, loginView, userHomepageView, userDestinationsView, userReservationsView, userProfileView);
            
            //addding the views
            frame.addView(userHomepageView, "userHomepageView");
            frame.showView("userHomepageView");    
            signupView.emptyFields();
            signupView.hideErrorMessage();
        }else{
            signupView.showErrorMessage("Erreur lors de l'inscription");
        }
            
    }
    
    private boolean checkUserPass(char[] pass, ClientModel user){
//        "entity" tells us if the current user is a simple or an admin user
//        Here we get the credentials from the db
        char[] userPass = user.getMotDePasse().toCharArray();
        boolean authenticated = false;
        
        if(pass.length==userPass.length){
        
            for (int i = 0; i< pass.length; i++){
                authenticated = true;
                if(pass[i] != userPass[i]){
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
    
    private boolean checkAdminPass(char[] pass, AdminModel admin){
//        "entity" tells us if the current user is a simple or an admin user
//        Here we get the credentials from the db
        char[] adminPass = admin.getMotDePasse().toCharArray();
        boolean authenticated = false;
        
        if(pass.length==adminPass.length){
        
            authenticated = true;
            if(!Arrays.equals(pass ,adminPass)){
                authenticated = false;
            }
        }
        
        else{
            authenticated = false;
        }

        
        return authenticated;
    }
    
    private void verifyLogin(){

        System.out.println("Checking connexion...");
        
        ClientModel user = clientDao.getClientByEmail(loginView.getEmail());
        AdminModel admin = adminDao.getAdminByEmail(loginView.getEmail());
        
        if (user != null){
            if(checkUserPass(loginView.getPassword(), user)){
                System.out.println("Checking user connexion...");
                userHomepageView =new UserHomepageView(frame, user);
                //views
                userHomepageView =new UserHomepageView(frame, user);
                userDestinationsView = new UserDestinationsView(frame, user);
                userReservationsView = new UserReservationsView(frame, user);
                userProfileView = new UserProfileView(user);
                
                //controller
                new ClientController(frame, loginView, userHomepageView, userDestinationsView, userReservationsView, userProfileView);

                //addding the views
                frame.addView(userHomepageView, "userHomepageView");
                frame.showView("userHomepageView");
                loginView.emptyFields();
                loginView.hideErrorMessage();
            }
            else{
                loginView.showErrorMessage("Erreur de connexion, mot de passe incorrect");
            }
        }
        else if(admin != null){
            if(checkAdminPass(loginView.getPassword(), admin)){
                System.out.println("Checking admin connexion...");
                adminHomepageView =new AdminHomepageView();
                //Admin views
                adminHomepageView = new AdminHomepageView();
                adminReservationsManagementView = new AdminReservationsManagementView();
                adminUserManagementView = new AdminUserManagementView();
                adminDestinationsView = new AdminDestinationsView(frame);
                adminTransportManagementView = new AdminTransportManagementView(frame);
                
                //controller
                new AdminController(frame, loginView, adminHomepageView, adminReservationsManagementView, adminUserManagementView, adminDestinationsView, adminTransportManagementView);
                
                //views
                frame.addView(adminHomepageView, "adminHomepageView");
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

