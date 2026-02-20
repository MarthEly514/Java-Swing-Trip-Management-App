/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.controllers;

import trip_manager_app.views.LoginView;
import trip_manager_app.views.MainFrame;
import trip_manager_app.views.admin.*;

/**
 *
 * @author ely
 */
public class AdminController  {
    private MainFrame frame;
    public AdminController(MainFrame frame, LoginView loginView, AdminHomepageView adminHomepageView ,AdminReservationsManagementView adminReservationsManagementView ,AdminUserManagementView adminUserManagementView, AdminDestinationsView adminDestinationsView )
    {
        
        this.frame = frame;
        frame.addView(loginView, "loginView");
        frame.addView(adminHomepageView, "adminHomepageView");
        frame.addView(adminReservationsManagementView, "adminReservationsManagementView");
        frame.addView(adminUserManagementView, "adminUserManagementView");
        frame.addView(adminDestinationsView, "adminDestinationsView");
        
        //dashboard navigation
        adminHomepageView.addClientManagementButtonListener(e -> frame.showView("adminUserManagementView"));
        adminHomepageView.addReservationsManagementButtonListener(e -> frame.showView("adminReservationsManagementView"));
        adminHomepageView.addDestinationsButtonListener(e -> frame.showView("adminDestinationsView"));
        adminHomepageView.addLogoutButtonListener(e -> frame.showView("loginView"));
        
        //user management page navigation
        adminUserManagementView.addHomeButtonListener(e -> frame.showView("adminHomepageView"));
        adminUserManagementView.addReservationsManagementButtonListener(e -> frame.showView("adminReservationsManagementView"));
        adminUserManagementView.addDestinationsButtonListener(e -> frame.showView("adminDestinationsView"));
        adminUserManagementView.addLogoutButtonListener(e -> frame.showView("loginView"));
        
//        //reservation management page navigation
        adminReservationsManagementView.addHomeButtonListener(e -> frame.showView("adminHomepageView"));
        adminReservationsManagementView.addUserManagementButtonListener(e -> frame.showView("adminUserManagementView"));
        adminReservationsManagementView.addDestinationsButtonListener(e -> frame.showView("adminDestinationsView"));
        adminReservationsManagementView.addLogoutButtonListener(e -> frame.showView("loginView"));
        
//        //destinations page navigation
        adminDestinationsView.addHomeButtonListener(e -> frame.showView("adminHomepageView"));
        adminDestinationsView.addUserManagementButtonListener(e -> frame.showView("adminUserManagementView"));
        adminDestinationsView.addReservationButtonListener(e -> frame.showView("adminReservationsManagementView"));
        adminDestinationsView.addLogoutButtonListener(e -> frame.showView("loginView"));

    }
}
