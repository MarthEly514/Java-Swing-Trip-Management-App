/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;

import trip_manager_app.ui_components.renderers.ReservationCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.models.ClientModel;
import trip_manager_app.models.NewEnumReservation;
import trip_manager_app.models.ReservationModel;
import trip_manager_app.ui_components.*;
import trip_manager_app.ui_components.renderers.ClientsCellRenderer;
import trip_manager_app.views.LoginView;
import trip_manager_app.views.user.UserReservationDetailDialog;

/**
 *
 * @author ely
 */
public class AdminUserManagementView extends JPanel{
    private UIButton homeButton;
    private UIButton clientsButton;
    private UIButton reservationButton;
    private UIButton logoutButton;
    private List<String> options;
    private JPanel row1;
    private JFrame parentFrame;
    
    public AdminUserManagementView(){
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    public AdminUserManagementView(JFrame parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        panel.setBackground(new Color(240,240,255));
        panel.setPreferredSize(new Dimension(320, 0));

        //loading the logo
        
        Image logoImg;
        try {
            logoImg = ImageIO.read(getClass().getResource("/trip_manager_app/ressources/icons/hdpiGroup.png"));
            //logo.setIcon();
            ImageIcon logoIcon = new ImageIcon(logoImg.getScaledInstance(35, 35, Image.SCALE_SMOOTH));
            JLabel logo = new JLabel("  EasyTrip");
            logo.setIcon(logoIcon);
            logo.setFont(new Font("SansSerif", Font.BOLD, 20));
            logo.setAlignmentX(Component.LEFT_ALIGNMENT);
            logo.setForeground(new Color(108, 99, 255));
            // adding the eft panel logo
            panel.add(logo);
        } catch (IOException ex) {
            System.getLogger(LoginView.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        // home navigation panel
        
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 18));
        navigationPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        navigationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        navigationPanel.setOpaque(false);
        
        //home button
        homeButton = new UIButton(
                " Dashboard",
                "/trip_manager_app/ressources/icons/analytics.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
                
        //destination button 
        clientsButton = new UIButton(
                " Gestion des clients",
                "/trip_manager_app/ressources/icons/travel_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
        );
        
        //reservation button
        reservationButton = new UIButton(
                " Réservations",
                "/trip_manager_app/ressources/icons/date_range.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
 
        );
         
        navigationPanel.add(homeButton);
        navigationPanel.add(clientsButton);
        navigationPanel.add(reservationButton);
        
        
        JPanel profileButtonContainer = new JPanel();
        profileButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 18));
        profileButtonContainer.setMaximumSize(new Dimension(300, 50));
        profileButtonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        profileButtonContainer.setOpaque(false);
        
        logoutButton = new UIButton(
                "  Déconnexion",
                "/trip_manager_app/ressources/icons/logout_light.svg", 
                new Color(255, 100, 100), 
                Color.white
                
        ); 
        
        profileButtonContainer.add(logoutButton);
        
        
        // button color change on hover
        
        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                homeButton.setBackground(new Color(101, 93, 235, 20));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                homeButton.setBackground(new Color(0, 0, 0, 0));            
            }
        });
        
        clientsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                clientsButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                clientsButton.setBackground(new Color(108, 99, 255));            
            }
        });

        reservationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                reservationButton.setBackground(new Color(101, 93, 235, 20));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                reservationButton.setBackground(new Color(0, 0, 0, 0));            
            }
        });
        
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                logoutButton.setBackground(new Color(255, 50, 50));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                logoutButton.setBackground(new Color(255, 100, 100));            
            }
        });
        


        panel.add(Box.createVerticalStrut(100));
        panel.add(navigationPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(profileButtonContainer);    

        return panel;
    }
    
    
//    Right panel
    
    private JPanel createRightPanel(){
        JPanel wrapper = new JPanel();
        wrapper.setBackground(Color.white);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 45, 20, 45));
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JPanel topWrapper = new JPanel();
        topWrapper.setLayout(new BoxLayout(topWrapper, BoxLayout.Y_AXIS));
        topWrapper.setBackground(Color.white);
        topWrapper.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
        topWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        topWrapper.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        topWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JPanel searchBarContainer = new JPanel();
        searchBarContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 20));
        searchBarContainer.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        searchBarContainer.setOpaque(false); 
        
//        SearchField searchBar = new SearchField();
//        searchBar.setBorderColor(new Color(161, 117, 255, 30));
//        
//        searchBarContainer.add(searchBar);

        JLabel title = new JLabel("Gestion des clients");
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        options = new ArrayList<>();
        options.add("Actifs");
        options.add("Inactifs");
        
        

        
        NavBarHorizontal navBar = new NavBarHorizontal(options, optionName-> loadReservations(optionName));
        
        topWrapper.add(searchBarContainer);
//        topWrapper.add(Box.createVerticalGlue());     
        topWrapper.add(title);
        topWrapper.add(Box.createVerticalStrut(10));
        topWrapper.add(navBar);
        
        
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setBackground(Color.white);
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 600));

        row1 = new JPanel();
        row1.setOpaque(false);
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        row1.setMinimumSize(new Dimension(Integer.MAX_VALUE, 600));
        row1.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        row1.setLayout(new BoxLayout(row1, BoxLayout.Y_AXIS));
        
        int n = 40;
        showUsersTable(n); 
        
        bottomWrapper.add(row1);
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottomWrapper);

        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        
        return wrapper;
    }
    
    public void loadReservations(String labelName){
        if(labelName.equals(options.get(0))){  
            row1.removeAll();       // removes every child component
            row1.revalidate();      // tells the layout manager to recalculate layout
            row1.repaint();
            showUsersTable(40); 
        }
        else if(labelName.equals(options.get(1))){
            row1.removeAll();       
            row1.revalidate();
            row1.repaint();
            showUsersTable(3); 
        }
    }
    
    public void addHomeButtonListener(ActionListener listener){
        homeButton.addActionListener(listener);
    }
    
    public void addReservationsManagementButtonListener(ActionListener listener){
        reservationButton.addActionListener(listener);
    }
    
    public void addLogoutButtonListener(ActionListener listener){
        logoutButton.addActionListener(listener);
    }
    
    public void showDetails(String content){
//        UserReservationDetailDialog dialog = new UserReservationDetailDialog(parentFrame, content, 400, "hello");   
//        dialog.showDialog();
    }

    private void showUsersTable(int n) {
        if(n > 0){
        // Create the list
        CustomList<ClientModel> clientList = new CustomList<>(
            new ClientsCellRenderer(),
            client -> {
                showClientDetails(client);
                System.out.println("Clicked client #" + client.getIdClient());
            }
        );
        
        // Add some reservations
        String[] destinations = {"Paris", "Accra", "Nepal", "Uruguay", "Rome", 
                                 "LA, Los Angeles", "Utah", "Ares", "Revan", 
                                 "Java", "Caire", "Jerusalem"};
         
        for (int i = 0; i < n; i++) {
            clientList.addItem(new ClientModel("Logan", "Sen", "nigger@sen.com", "990099898", "Raven"));
            
//            ReservationModel reservation = new ReservationModel(dest, "12 Fevrier 2026", "En attente");
//            reservationList.addReservation(reservation);
        }
//        // Add reservations
//        reservationList.addItem(new ReservationModel(1, LocalDateTime.now(), 
//            NewEnumReservation.EN_ATTENTE, 101, 201));
//        reservationList.addItem(new ReservationModel(2, LocalDateTime.now().minusDays(1), 
//            NewEnumReservation.VALIDE, 102, 202));
//        reservationList.addItem(new ReservationModel(3, LocalDateTime.now().minusDays(5), 
//            NewEnumReservation.ANNULE, 103, 203));

        // Add to your panel
        row1.add(clientList);



//        // Or set all at once
//        List<ReservationModel> reservations = new ArrayList<>();
//        for (String dest : destinations) {
//            reservations.add(new ReservationModel(dest, "12 Fevrier 2026", "En attente"));
//        }
//        reservationList.setReservations(reservations);
            
        }else{
            // Center the empty state vertically and horizontally
            row1.add(Box.createVerticalGlue()); // Push content to center

            JPanel noResPanel = new JPanel();
            noResPanel.setLayout(new BoxLayout(noResPanel, BoxLayout.Y_AXIS));
            noResPanel.setOpaque(false);
            noResPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel noResMessage1 = new JLabel(">-<");
            noResMessage1.setFont(new Font("SansSerif", Font.BOLD, 48));
            noResMessage1.setForeground(new Color(180, 180, 180));
            noResMessage1.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel noResMessage2 = new JLabel("Aucun client pour le moment");
            noResMessage2.setFont(new Font("SansSerif", Font.PLAIN, 16));
            noResMessage2.setForeground(new Color(120, 120, 120));
            noResMessage2.setAlignmentX(Component.CENTER_ALIGNMENT);

            noResPanel.add(noResMessage1);
            noResPanel.add(Box.createVerticalStrut(10));
            noResPanel.add(noResMessage2);

            row1.add(noResPanel);
            row1.add(Box.createVerticalGlue()); 
            
        }  
    }

//      private void showReservationDetails(ReservationModel reservation) {
//        JOptionPane.showMessageDialog(
//            this,
//            "Réservation #" + reservation.getIdReservation() + "\n" +
//            "Client ID: " + reservation.getIdClient() + "\n" +
//            "Voyage ID: " + reservation.getIdVoyage() + "\n" +
//            "Date: " + reservation.getDateReservation() + "\n" +
//            "Statut: " + reservation.getStatut().getLibelle(),
//            "Détails de la réservation",
//            JOptionPane.INFORMATION_MESSAGE
//        );
//      }
    
      private void showClientDetails(ClientModel client) {
        JOptionPane.showMessageDialog(
            this,
            "Client ID: " + client.getIdClient() + "\n" +
            "Nom: " + client.getNom()+ "\n" +
            "Prenom: " + client.getPrenom()+ "\n" +
            "E-mail: " + client.getEmail(),
            "Détails Client",
            JOptionPane.INFORMATION_MESSAGE
        );
      }
      
}