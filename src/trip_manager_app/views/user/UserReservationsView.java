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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.DAO.*;
import trip_manager_app.models.ClientModel;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.models.ReservationModel;
import trip_manager_app.models.VoyageModel;
import trip_manager_app.ui_components.*;
import trip_manager_app.views.LoginView;

/**
 *
 * @author ely
 */
public class UserReservationsView extends JPanel{
    private UIButton homeButton;
    private UIButton destinationButton;
    private UIButton reservationButton;
    private UIButton userProfileButton;
    private List<String> options;
    private JPanel row1;
    private JFrame parentFrame;
    private DestinationDAO destDao;
    private ReservationDAO resDao;
    private VoyageDAO voyageDao;
    private ClientModel user;
    
    public UserReservationsView(){
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    public UserReservationsView(JFrame parentFrame){
        this.parentFrame = parentFrame;
        this.destDao = new DestinationDAO();
        this.resDao = new ReservationDAO();
        this.voyageDao = new VoyageDAO();

        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
    public UserReservationsView(JFrame parentFrame, ClientModel user){
        this.parentFrame = parentFrame;
        this.destDao = new DestinationDAO();
        this.resDao = new ReservationDAO();
        this.voyageDao = new VoyageDAO();
        this.user = user;

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
                "/trip_manager_app/ressources/icons/travel.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
        
        //reservation button
        reservationButton = new UIButton(
                " Réservations",
                "/trip_manager_app/ressources/icons/date_range_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
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
                reservationButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                reservationButton.setBackground(new Color(108, 99, 255));            
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

        JLabel title = new JLabel("Mes réservations");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        options = new ArrayList<>();
        options.add("En attente");
        options.add("Validées");

        
        NavBarHorizontal navBar = new NavBarHorizontal(options, optionName-> loadReservations(optionName));
        
        topWrapper.add(Box.createVerticalGlue());     
        topWrapper.add(title);
        topWrapper.add(Box.createVerticalStrut(10));
        topWrapper.add(navBar);
        
        
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setBackground(Color.white);
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottomWrapper);
        
        row1 = new JPanel();
        row1.setOpaque(false);
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        row1.setMinimumSize(new Dimension(Integer.MAX_VALUE, 600));
        row1.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        row1.setLayout(new BoxLayout(row1, BoxLayout.Y_AXIS));
        
        loadReservations("*"); 
        
        bottomWrapper.add(row1);

        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        
        return wrapper;
    }
    
    public void loadReservations(String labelName){
        List<ReservationModel> reservations;

        
        if(labelName.equals("*")){
            reservations = resDao.getAllReservations();
            row1.removeAll();       // removes every child component
            row1.revalidate();      // tells the layout manager to recalculate layout
            row1.repaint();
            showReservationRows(reservations); 
        }
        else if(labelName.equals(options.get(0))){  
            reservations = resDao.getReservationsByStatut(labelName);
            row1.removeAll();       // removes every child component
            row1.revalidate();      // tells the layout manager to recalculate layout
            row1.repaint();
            showReservationRows(reservations); 
        }
        else if(labelName.equals(options.get(1))){
            reservations = resDao.getReservationsByStatut(labelName);
            row1.removeAll();       
            row1.revalidate();
            row1.repaint();
            showReservationRows(reservations); 
        }
    }
    
    public void addHomeButtonListener(ActionListener listener){
        homeButton.addActionListener(listener);
    }
    
    public void addDestinationButtonListener(ActionListener listener){
        destinationButton.addActionListener(listener);
    }
    
    public void addUserProfileButtonListener(ActionListener listener){
        userProfileButton.addActionListener(listener);
    }
    
    public void showDetails(ReservationModel reservation, VoyageModel voyage, DestinationModel destination){
        UserReservationDetailDialog dialog = new UserReservationDetailDialog(parentFrame, reservation, voyage, destination, voyage.getPrix() );
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
}
