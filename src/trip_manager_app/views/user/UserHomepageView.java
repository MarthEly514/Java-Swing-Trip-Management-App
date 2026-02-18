/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.DAO.DestinationDAO;
import trip_manager_app.DAO.MoyenTransportDAO;
import trip_manager_app.DAO.ReservationDAO;
import trip_manager_app.DAO.VoyageDAO;
import trip_manager_app.models.ClientModel;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.models.ReservationModel;
import trip_manager_app.models.VoyageModel;
import trip_manager_app.ui_components.*;
import trip_manager_app.utils.SvgUtils;
import trip_manager_app.views.LoginView;

/**
 *
 * @author ely
 */
public class UserHomepageView extends JPanel{
    // dao
    private DestinationDAO destDao;
    
    //buttons
    private UIButton homeButton;
    private UIButton destinationButton;
    private UIButton reservationButton;
    private UIButton userProfileButton;
    private UIButton seeMoreDestinationsButton;
    private UIButton seeMoreReservationsButton;
    private JPanel row1;
    private JPanel row2;
    private ReservationDAO resDao;
    private MoyenTransportDAO transportDao;
    private JFrame parentFrame;
    private VoyageDAO voyageDao;
    private ClientModel client;

    public UserHomepageView(){ 
        destDao = new DestinationDAO();
        voyageDao = new VoyageDAO();
        resDao = new ReservationDAO();
        transportDao = new MoyenTransportDAO();
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
    public UserHomepageView(JFrame parentFrame, ClientModel client){ 
        this.parentFrame = parentFrame;
        this.client = client;
        destDao = new DestinationDAO();
        voyageDao = new VoyageDAO();
        resDao = new ReservationDAO();
        transportDao = new MoyenTransportDAO();
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
//    LEFT PANEL
    
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
                " Accueil",
                "/trip_manager_app/ressources/icons/home_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
        );
                
        //destination button 
        destinationButton = new UIButton(
                " Destinations",
                "/trip_manager_app/ressources/icons/travel.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
        
        //reservation button
        reservationButton = new UIButton(
                " Réservations",
                "/trip_manager_app/ressources/icons/date_range.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
         
        navigationPanel.add(homeButton);
        navigationPanel.add(destinationButton);
        navigationPanel.add(reservationButton);
        
        
        JPanel profileButtonContainer = new JPanel();
        profileButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 18));
        profileButtonContainer.setMaximumSize(new Dimension(300, 50));
        profileButtonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        profileButtonContainer.setOpaque(false);
        
        userProfileButton = new UIButton(
                "  Mon Profil",
                "/trip_manager_app/ressources/icons/account_circle_light.svg", 
                new Color(108, 99, 255, 125), 
                Color.white
                
        );        
        profileButtonContainer.add(userProfileButton);
        
        
        // button color change on hover
        
        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                homeButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                homeButton.setBackground(new Color(108, 99, 255));            
            }
        });
        
        destinationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                destinationButton.setBackground(new Color(101, 93, 235, 20));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                destinationButton.setBackground(new Color(0, 0, 0, 0));            
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
        
        userProfileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                userProfileButton.setBackground(new Color(101, 93, 235, 195));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                userProfileButton.setBackground(new Color(108, 99, 255, 125));            
            }
        });
        


        panel.add(Box.createVerticalStrut(100));
        panel.add(navigationPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(profileButtonContainer);

        return panel;
    }
    
//    RIGHT PANEL
    
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
        
        

        JLabel title = new JLabel("Accueil");
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        String userName =client.getPrenom()+" "+ client.getNom();
        String[] subGreetingList = {"Que réservons-nous aujourd'hui?", "Une destination en tête?", "Toutes les routes mènent a Rome comme on dit...", "Où allons-nous aujourd'hui?"};
        
        JLabel greetings = new JLabel("Salut "+ userName);
        greetings.setFont(new Font("SansSerif", Font.BOLD, 24));
        greetings.setForeground(new Color(50, 50, 50));
        greetings.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        greetings.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        greetings.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        //here I need a random number to show a different message every time the user logs in
        Random rand = new Random();
        
        
        JLabel subGreeting = new JLabel();
        subGreeting.setText(subGreetingList[rand.nextInt(subGreetingList.length)]);
        subGreeting.setFont(new Font("SansSerif", Font.PLAIN, 20));
        subGreeting.setForeground(new Color(50, 50, 50, 150));
        subGreeting.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        subGreeting.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        subGreeting.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        subGreeting.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        topWrapper.add(Box.createVerticalStrut(20));     
        topWrapper.add(title);
        topWrapper.add(Box.createVerticalGlue());     
        topWrapper.add(greetings);
        topWrapper.add(Box.createVerticalStrut(5));     
        topWrapper.add(subGreeting);
        topWrapper.add(Box.createVerticalStrut(5));     
        
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setBackground(Color.white);
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottomWrapper);
        
        row1 = new JPanel();
        row1.setOpaque(false);
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row1.setPreferredSize(new Dimension(0, 300));
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        row1.setMinimumSize(new Dimension(1, 300));
        row1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        
        List<DestinationModel> destinations = destDao.getNDestinations(4);

        for( DestinationModel destination:destinations){
            DestinationCard card = new DestinationCard(destination);
//            cards.add(card);
            System.out.println(destination.getVille());
            row1.add(card);
            row1.add(Box.createHorizontalStrut(20));  
        }
                
        SubtitleLabel subtitle1 = new SubtitleLabel("Destinations en vedette");
        
        JPanel seeMoreBtnCtnr = new JPanel();
        seeMoreBtnCtnr.setOpaque(false);
        
        
        seeMoreDestinationsButton = new UIButton(
            "  Voir plus",
            "/trip_manager_app/ressources/icons/plus.svg", 
            new Color(101, 93, 235, 20), 
            new Color(108, 99, 255)      
        );
        seeMoreDestinationsButton.setHorizontalAlignment(SwingConstants.CENTER);
        
        // button color change on hover
        
        seeMoreDestinationsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                seeMoreDestinationsButton.setBackground(new Color(101, 93, 235, 40));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                seeMoreDestinationsButton.setBackground(new Color(101, 93, 235, 20));            
            }
        });

        seeMoreBtnCtnr.add(seeMoreDestinationsButton);
        
        
        SubtitleLabel subtitle2 = new SubtitleLabel("Mes réservations");
        
        row2 = new JPanel();
        row2.setOpaque(false);
        row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        row2.setMinimumSize(new Dimension(Integer.MAX_VALUE, 300));
        row2.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        row2.setLayout(new BoxLayout(row2, BoxLayout.Y_AXIS));
        
        loadReservations(4);
              
        JPanel seeMoreBtn2Ctnr = new JPanel();
        seeMoreBtn2Ctnr.setOpaque(false);
        
        
        seeMoreReservationsButton = new UIButton(
            "  Voir plus",
            "/trip_manager_app/ressources/icons/plus.svg", 
            new Color(101, 93, 235, 20), 
            new Color(108, 99, 255));
        
        seeMoreReservationsButton.setHorizontalAlignment(SwingConstants.CENTER);
        
        // button color change on hover
        
        seeMoreReservationsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                seeMoreReservationsButton.setBackground(new Color(101, 93, 235, 40));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                seeMoreReservationsButton.setBackground(new Color(101, 93, 235, 20));            
            }
        });

        seeMoreBtn2Ctnr.add(seeMoreReservationsButton);
        
        
        bottomWrapper.add(subtitle1);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(row1);
        bottomWrapper.add(Box.createVerticalStrut(10));
        bottomWrapper.add(seeMoreBtnCtnr);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(subtitle2);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(row2);
        bottomWrapper.add(Box.createVerticalStrut(10));
        bottomWrapper.add(seeMoreBtn2Ctnr);

        
        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        
        return wrapper;
    }
    
    public void loadReservations(int n){
        List<ReservationModel> reservations;
        reservations = resDao.getNReservationsByClientId(n, client.getIdClient());
        row2.removeAll();       // removes every child component
        row2.revalidate();      // tells the layout manager to recalculate layout
        row2.repaint();
        showReservationRows(reservations);
    }
    
    public void showDetails(ReservationModel reservation, VoyageModel voyage, DestinationModel destination){
        MoyenTransportModel transport = transportDao.findByNo(voyage.getNoVehicule());
        UserReservationDetailDialog dialog = new UserReservationDetailDialog(parentFrame, reservation, voyage, destination, voyage.getPrix() ,transport);
        dialog.addConfirmButtonListener(e -> System.out.println(voyage.getVilleDestination() + " Confirmed"));
        dialog.addCancelButtonListener(e -> System.out.println(voyage.getVilleDestination() + " Canceled"));
        dialog.showDialog();
    }

    private void showReservationRows(List<ReservationModel> reservations) {
        if(!reservations.isEmpty()){
            for(ReservationModel reservation:reservations){
                
                //fetch the voyage where id_voyage = reservation.id_voyage then I fetched the actual ville from attributes
                VoyageModel voyage = voyageDao.getVoyageById(reservation.getIdVoyage());
                DestinationModel destination = destDao.getMatchingDestinations(voyage.getVilleDestination()).get(0);

                ListElementRow resRow = new ListElementRow( voyage.getVilleDestination(), "Départ le :"+voyage.getDateDepart(), reservation.getStatut().getLibelle(), e ->{
                    showDetails(reservation, voyage, destination);
                } 
                );
                    row2.add(resRow);
                    row2.add(Box.createVerticalStrut(20));  
                }
        }else{
            // Center the empty state vertically and horizontally
            row2.add(Box.createVerticalGlue()); // Push content to center

            JPanel noResPanel = new JPanel();
            noResPanel.setLayout(new BoxLayout(noResPanel, BoxLayout.Y_AXIS));
            noResPanel.setOpaque(false);
            noResPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel noResMessage1 = new JLabel(">-<");
            noResMessage1.setFont(new Font("SansSerif", Font.BOLD, 48));
            noResMessage1.setForeground(new Color(180, 180, 180));
            noResMessage1.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel noResMessage2 = new JLabel("Aucune réservation pour le moment");
            noResMessage2.setFont(new Font("SansSerif", Font.PLAIN, 16));
            noResMessage2.setForeground(new Color(120, 120, 120));
            noResMessage2.setAlignmentX(Component.CENTER_ALIGNMENT);

            noResPanel.add(noResMessage1);
            noResPanel.add(Box.createVerticalStrut(10));
            noResPanel.add(noResMessage2);

            row2.add(noResPanel);
            row2.add(Box.createVerticalGlue()); 
            
        }  
    }
    
    public void addDestinationButtonListener(ActionListener listener){
        destinationButton.addActionListener(listener);
        seeMoreDestinationsButton.addActionListener(listener);
    }
    
    public void addReservationButtonListener(ActionListener listener){
        reservationButton.addActionListener(listener);
        seeMoreReservationsButton.addActionListener(listener);
    }
    
    public void addUserProfileButtonListener(ActionListener listener){
        userProfileButton.addActionListener(listener);
    }
    

}
