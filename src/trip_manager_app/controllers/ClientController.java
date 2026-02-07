/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.controllers;

import trip_manager_app.views.LoginView;
import trip_manager_app.views.MainFrame;
import trip_manager_app.views.user.*;

/**
 *
 * @author ely
 */
public class ClientController {
    private MainFrame frame;
    public ClientController(MainFrame frame, LoginView loginView, UserHomepageView userHomepageView, UserDestinationsView userDestinationsView, UserReservationsView userReservationsView, UserProfileView userProfileView){
        
        this.frame = frame;
        frame.addView(loginView, "loginView");
        frame.addView(userHomepageView, "userHomepageView");
        frame.addView(userDestinationsView, "userDestinationsView");
        frame.addView(userReservationsView, "userReservationsView");
        frame.addView(userProfileView, "userProfileView");
        
        //homepage navigation
        userHomepageView.addDestinationButtonListener(e -> frame.showView("userDestinationsView"));
        userHomepageView.addReservationButtonListener(e -> frame.showView("userReservationsView"));
        userHomepageView.addUserProfileButtonListener(e -> frame.showView("userProfileView"));
        
        //destination page navigation
        userDestinationsView.addHomeButtonListener(e -> frame.showView("userHomepageView"));
        userDestinationsView.addReservationButtonListener(e -> frame.showView("userReservationsView"));
        userDestinationsView.addUserProfileButtonListener(e -> frame.showView("userProfileView"));
        
        //reservation page navigation
        userReservationsView.addHomeButtonListener(e -> frame.showView("userHomepageView"));
        userReservationsView.addDestinationButtonListener(e -> frame.showView("userDestinationsView"));
        userReservationsView.addUserProfileButtonListener(e -> frame.showView("userProfileView"));
        
        //user profile page navigation
        userProfileView.addHomeButtonListener(e -> frame.showView("userHomepageView"));
        userProfileView.addDestinationButtonListener(e -> frame.showView("userDestinationsView"));
        userProfileView.addReservationButtonListener(e -> frame.showView("userReservationsView"));
        userProfileView.addlogoutButtonListener(e -> frame.showView("loginView"));

    }
}
