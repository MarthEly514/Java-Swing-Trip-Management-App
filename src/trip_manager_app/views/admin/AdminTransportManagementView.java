/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import trip_manager_app.DAO.DestinationDAO;
import trip_manager_app.DAO.MoyenTransportDAO;
import trip_manager_app.DAO.ReservationDAO;
import trip_manager_app.DAO.VoyageDAO;
import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.ui_components.ListElementRow;
import trip_manager_app.ui_components.NavBarHorizontal;
import trip_manager_app.ui_components.ScrollWrapper;
import trip_manager_app.ui_components.SearchField;
import trip_manager_app.ui_components.UIButton;
import trip_manager_app.views.LoginView;

/**
 *
 * @author ely
 */
public class AdminTransportManagementView extends JPanel{
    private UIButton homeButton;
    private UIButton clientsButton;
    private UIButton reservationButton;
    private UIButton logoutButton;
    private List<String> options;
    private JPanel row1;
    private JFrame parentFrame;
    private String currentTab;
    private ReservationDAO resDao;
    private VoyageDAO voyageDao;
    private DestinationDAO destDao;
    private UIButton destinationButton;
    private MoyenTransportDAO transportDao;
    private UIButton transportButton;
    private SearchField searchBar;
    private UIButton addTransportButton;
    
    public AdminTransportManagementView(){
        resDao = new ReservationDAO();
        voyageDao = new VoyageDAO();
        destDao = new DestinationDAO();
        transportDao = new MoyenTransportDAO();
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    public AdminTransportManagementView(JFrame parentFrame){
        this.parentFrame = parentFrame;
        resDao = new ReservationDAO();
        voyageDao = new VoyageDAO();
        destDao = new DestinationDAO();
        transportDao = new MoyenTransportDAO();
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
        destinationButton = new UIButton(
                " Destinations",
                "/trip_manager_app/ressources/icons/public.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
                
        //destination button 
        clientsButton = new UIButton(
                " Gestion des clients",
                "/trip_manager_app/ressources/icons/users.svg", 
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
        //transports button
        transportButton = new UIButton(
                " Moyens de transport",
                "/trip_manager_app/ressources/icons/car_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
        );
         
        navigationPanel.add(homeButton);
        navigationPanel.add(destinationButton);
        navigationPanel.add(clientsButton);
        navigationPanel.add(reservationButton);
        navigationPanel.add(transportButton);
        
        
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
                clientsButton.setBackground(new Color(101, 93, 235, 20));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                clientsButton.setBackground(new Color(0, 0, 0, 0));            
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
                reservationButton.setBackground(new Color(0, 0 ,0 ,0));            
            }
        });
        transportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                transportButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                transportButton.setBackground(new Color(108, 99, 255));            
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

        JLabel title = new JLabel("Destinations");
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        addTransportButton = new UIButton(
            "  Ajouter",
            "/trip_manager_app/ressources/icons/plus.svg", 
            new Color(101, 93, 235, 20), 
            new Color(108, 99, 255)      
        );
        
        addTransportButton.addActionListener(e->{
            showAddDetails();
            reloadValues();
        });
        
        addTransportButton.setHorizontalAlignment(SwingConstants.CENTER);
        
        // button color change on hover
        
        addTransportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                addTransportButton.setBackground(new Color(101, 93, 235, 40));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                addTransportButton.setBackground(new Color(101, 93, 235, 20));            
            }
        });

        
        JPanel titleCtnr = new JPanel();
        titleCtnr.setOpaque(false);
        titleCtnr.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        titleCtnr.setLayout(new BorderLayout());
        titleCtnr.setPreferredSize(new Dimension(0, 50));
        titleCtnr.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        titleCtnr.add(title, BorderLayout.WEST);
        titleCtnr.add(addTransportButton, BorderLayout.EAST);
        
        
        JPanel searchBarContainer = new JPanel();
        searchBarContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 20));
        searchBarContainer.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        searchBarContainer.setOpaque(false); 
        
        searchBar = new SearchField(e-> searchTransport(searchBar.getSearchQuery()));
        searchBar.setBorderColor(new Color(161, 117, 255, 30));
                
        
        searchBarContainer.add(searchBar);
        
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setBackground(Color.white);
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottomWrapper);
        
        options = new ArrayList<>();
        options.add("Tous");
        options.add("Vehicules");
        options.add("Bus");
        options.add("Avions");
        options.add("Bateaux");

        
        NavBarHorizontal navBar = new NavBarHorizontal(options, optionName-> loadTransports(optionName));
        
        topWrapper.add(searchBarContainer);         
//        topWrapper.add(Box.createVerticalGlue());     
        topWrapper.add(titleCtnr);
        topWrapper.add(Box.createVerticalStrut(5));
        topWrapper.add(navBar);
        
        
        row1 = new JPanel();
        row1.setOpaque(false);
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        row1.setMinimumSize(new Dimension(Integer.MAX_VALUE, 600));
        row1.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        row1.setLayout(new BoxLayout(row1, BoxLayout.Y_AXIS));
        

        loadTransports("*");
        
        bottomWrapper.add(row1);
 
        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        
        return wrapper;
    }
    
   public void loadTransports(String labelName){
        List<MoyenTransportModel> transports;

        
        if(labelName.equals("*")){
            transports = transportDao.getAll();
            row1.removeAll();       // removes every child component
            row1.revalidate();      // tells the layout manager to recalculate layout
            row1.repaint();
            showTransportRows(transports); 
        }
        else if(labelName.equals(options.get(0))){  
            transports = transportDao.getAll();
            row1.removeAll();       // removes every child component
            row1.revalidate();      // tells the layout manager to recalculate layout
            row1.repaint();
            showTransportRows(transports); 
            currentTab = options.get(0);
            System.out.println(currentTab);
        }
        else if(labelName.equals(options.get(1))){  
            transports = transportDao.getAllByType("Vehicule");
            row1.removeAll();       // removes every child component
            row1.revalidate();      // tells the layout manager to recalculate layout
            row1.repaint();
            showTransportRows(transports); 
            currentTab = options.get(1);
            System.out.println(currentTab);
        }
        else if(labelName.equals(options.get(2))){
            transports = transportDao.getAllByType("Bus");
            row1.removeAll();       
            row1.revalidate();
            row1.repaint();
            showTransportRows(transports); 
            currentTab = options.get(2);
            System.out.println(currentTab);
        }
        else if(labelName.equals(options.get(3))){
            transports = transportDao.getAllByType("Avion");
            row1.removeAll();       
            row1.revalidate();
            row1.repaint();
            showTransportRows(transports); 
            currentTab = options.get(3);
            System.out.println(currentTab);
        }
        else if(labelName.equals(options.get(4))){
            transports = transportDao.getAllByType("Bateau");
            row1.removeAll();       
            row1.revalidate();
            row1.repaint();
            showTransportRows(transports); 
            currentTab = options.get(4);
            System.out.println(currentTab);
        }
    }
    
    public void addHomeButtonListener(ActionListener listener){
        homeButton.addActionListener(listener);
    }
    
    public void addUserManagementButtonListener(ActionListener listener){
        clientsButton.addActionListener(listener);
    }
    
    public void addLogoutButtonListener(ActionListener listener){
        logoutButton.addActionListener(listener);
    }
    
    public void addDestinationsButtonListener(ActionListener listener){
        destinationButton.addActionListener(listener);
    }

    public void addReservationsManagementButtonListener(ActionListener listener){
        reservationButton.addActionListener(listener);
    }

    public void showAddDetails(){
        AddTransportDialog dialog = new AddTransportDialog(parentFrame);   
        dialog.showDialog();
    }
    
    public void showDetails(MoyenTransportModel transport){
//        AdminTransportDetails dialog = new AdminTransportDetails(parentFrame, transport);   
//        dialog.showDialog();
    }
    
    private void searchTransport(String searchText){
        SwingUtilities.invokeLater(()->{
            List<MoyenTransportModel> transports;
            if(searchText.trim().equals("")){
                transports = transportDao.getNTransports(5);
                row1.removeAll();       // removes every child component
                row1.revalidate();      // tells the layout manager to recalculate layout
                row1.repaint();
                System.out.println("Searching all..."+searchText);
                showTransportRows(transports); 

            } else {
                transports = transportDao.getMatchingTransports(searchText);
                row1.removeAll();       // removes every child component
                row1.revalidate();      // tells the layout manager to recalculate layout
                row1.repaint();
                System.out.println("Searching..."+searchText);
                showTransportRows(transports); 

            }
        });
    }
    
    public void reloadValues() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); 
                    loadTransports("*");


                } catch (Exception ex) {
                    System.err.println("Error refreshing counts: " + ex.getMessage());
                }
            }
        };

        worker.execute();
    }

    private void showTransportRows(List<MoyenTransportModel> transports) {
        if(!transports.isEmpty()){
            for(MoyenTransportModel transport : transports){
                
                ListElementRow resRow = new ListElementRow( transport.getDescriptionVehicule(), transport.getTypeVehicule(), transport.getNoVehicule(), e ->{
                    showDetails(transport);
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
