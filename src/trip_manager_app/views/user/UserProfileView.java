/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
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
public class UserProfileView extends JPanel{
    private UIButton homeButton;
    private UIButton destinationButton;
    private UIButton reservationButton;
    private UIButton userProfileButton;
    private UIButton logoutButton;
    private JPanel row1;
    private ClientModel user;
    private MoyenTransportDAO transportDao;
    private ReservationDAO resDao;
    private DestinationDAO destDao;
    private VoyageDAO voyageDao;
    
    public UserProfileView(){
        setLayout(new BorderLayout());
        transportDao = new MoyenTransportDAO();
        resDao = new ReservationDAO();
        destDao = new DestinationDAO();
        voyageDao = new VoyageDAO();
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
    public UserProfileView(ClientModel user){
        this.user = user;
        transportDao = new MoyenTransportDAO();
        resDao = new ReservationDAO();
        destDao = new DestinationDAO();
        voyageDao = new VoyageDAO();
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
                " Accueil",
                "/trip_manager_app/ressources/icons/home.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
                
        //destination button 
        destinationButton = new UIButton(
                " Destinations",
                "/trip_manager_app/ressources/icons/public.svg", 
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
        profileButtonContainer.setLayout(new BorderLayout());
        profileButtonContainer.setMaximumSize(new Dimension(300, 120));
        profileButtonContainer.setPreferredSize(new Dimension(300, 120));
        profileButtonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        profileButtonContainer.setOpaque(false);
        profileButtonContainer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        logoutButton = new UIButton(
                "  Se deconnecter",
                "/trip_manager_app/ressources/icons/logout_light.svg", 
                new Color(255, 100, 100), 
                Color.white
        );
        logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
        
        userProfileButton = new UIButton(
                "  Mon Profil",
                "/trip_manager_app/ressources/icons/account_circle_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
        );        
        profileButtonContainer.add(logoutButton, BorderLayout.NORTH);
        profileButtonContainer.add(userProfileButton, BorderLayout.SOUTH);
        
        
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
                userProfileButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                userProfileButton.setBackground(new Color(108, 99, 255));            
            }
        });
        
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                logoutButton.setBackground(new Color(255, 70, 70));            
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
    
    private JPanel createRightPanel(){
        JPanel wrapper = new JPanel();
        wrapper.setBackground(Color.white);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 45, 20, 45));
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JPanel topWrapper = new JPanel();
        topWrapper.setLayout(new BoxLayout(topWrapper, BoxLayout.Y_AXIS));
        topWrapper.setBackground(Color.white);
        topWrapper.setPreferredSize(new Dimension(Integer.MAX_VALUE, 400));
        topWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        topWrapper.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        topWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel title = new JLabel("Profil");
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));

        
        JPanel informationsPanel = new JPanel();
        informationsPanel.setOpaque(false);
        informationsPanel.setLayout(new BorderLayout());    
        informationsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        JLabel nameLabel = new JLabel(user.getPrenom()+" "+user.getNom());
        JLabel emailLabel = new JLabel("Adresse mail:   "+user.getEmail());
        JLabel phoneLabel = new JLabel("Téléphone:   "+user.getTelephone());
        
        Font font1 = new Font("SansSerif", Font.BOLD, 28);
        Font font2 = new Font("SansSerif", Font.PLAIN, 18);
        nameLabel.setFont(font1);
        emailLabel.setFont(font2);
        phoneLabel.setFont(font2);
        
        JPanel profilePicturePanel = new JPanel();
        profilePicturePanel.setOpaque(false);
        profilePicturePanel.setLayout(new BoxLayout( profilePicturePanel, BoxLayout.Y_AXIS));   
        profilePicturePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        PanelRound profilePicture = new PanelRound("/trip_manager_app/ressources/images/profile.jpeg");
        profilePicture.setOpaque(false);
        profilePicture.setPreferredSize(new Dimension(200, 200));
        profilePicture.setMaximumSize(new Dimension(200, 200));
        profilePicture.setMinimumSize(new Dimension(200, 200));
        profilePicture.setBackground(new Color(0, 0, 0, 0));
        profilePicture.setFitWidth(true);
        profilePicture.rounded(200);
        
        profilePicturePanel.add(profilePicture);
        
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setOpaque(false);
        detailsPanel.setLayout(new BoxLayout( detailsPanel, BoxLayout.Y_AXIS));    
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        
        detailsPanel.add(Box.createVerticalGlue());
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(emailLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(phoneLabel);
        detailsPanel.add(Box.createVerticalGlue());
        
        informationsPanel.add(profilePicturePanel, BorderLayout.WEST);
        informationsPanel.add(detailsPanel, BorderLayout.CENTER);
        
        topWrapper.add(Box.createVerticalStrut(20));
        topWrapper.add(title);
        topWrapper.add(Box.createVerticalStrut(20));
        topWrapper.add(informationsPanel);
        topWrapper.add(Box.createVerticalGlue());     
        
        
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomWrapper.setBackground(Color.white);
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottomWrapper);
        

        
        SubtitleLabel subtitle1 = new SubtitleLabel("Mes réservations");

        row1 = new JPanel();
        row1.setOpaque(false);
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        row1.setMinimumSize(new Dimension(Integer.MAX_VALUE, 600));
        row1.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        row1.setLayout(new BoxLayout(row1, BoxLayout.Y_AXIS));
        
        SubtitleLabel subtitle2 = new SubtitleLabel("Mes paiements");
        
        subtitle1.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle2.setAlignmentX(Component.LEFT_ALIGNMENT);
        informationsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        row1.setAlignmentX(Component.LEFT_ALIGNMENT);

        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        loadReservations(6);
        
        bottomWrapper.add(subtitle1);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(row1);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(subtitle2);
        bottomWrapper.add(Box.createVerticalStrut(20));

        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        
        return wrapper;
    }
    
    public void loadReservations(int n){
        java.util.List<ReservationModel> reservations;
        reservations = resDao.getNReservationsByClientId(n, user.getIdClient());
        row1.removeAll();       // removes every child component
        row1.revalidate();      // tells the layout manager to recalculate layout
        row1.repaint();
        showReservationRows(reservations);
    }
    
    public void showDetails(ReservationModel reservation, VoyageModel voyage, DestinationModel destination){
        MoyenTransportModel transport = transportDao.findByNo(voyage.getNoVehicule());
//        UserReservationDetailDialog dialog = new UserReservationDetailDialog(parentFrame, reservation, voyage, destination, voyage.getPrix() ,transport);
//        dialog.addConfirmButtonListener(e -> System.out.println(voyage.getVilleDestination() + " Confirmed"));
//        dialog.addCancelButtonListener(e -> System.out.println(voyage.getVilleDestination() + " Canceled"));
//        dialog.showDialog();
    }

    private void showReservationRows(java.util.List<ReservationModel> reservations) {
        if(!reservations.isEmpty()){
            for(ReservationModel reservation:reservations){
                
                //fetch the voyage where id_voyage = reservation.id_voyage then I fetched the actual ville from attributes
                VoyageModel voyage = voyageDao.getVoyageById(reservation.getIdVoyage());
                DestinationModel destination = destDao.getMatchingDestinations(voyage.getVilleDestination()).get(0);

                ListElementRow resRow = new ListElementRow( voyage.getVilleDestination(), "Départ le :"+voyage.getDateDepart(), reservation.getStatut().getLibelle(), e ->{
                    showDetails(reservation, voyage, destination);
                } 
                );
                    row1.add(resRow);
                    row1.add(Box.createVerticalStrut(20));  
                }
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

            JLabel noResMessage2 = new JLabel("Aucune réservation pour le moment");
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
    
    public void addHomeButtonListener(ActionListener listener){
        homeButton.addActionListener(listener);
    }
    
    public void addDestinationButtonListener(ActionListener listener){
        destinationButton.addActionListener(listener);
    }
    
    public void addReservationButtonListener(ActionListener listener){
        reservationButton.addActionListener(listener);
    }
    
    public void addlogoutButtonListener(ActionListener listener){
        logoutButton.addActionListener(listener);
    }
    
}
