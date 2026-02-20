/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.DAO.ClientDAO;
import trip_manager_app.DAO.DestinationDAO;
import trip_manager_app.DAO.MoyenTransportDAO;
import trip_manager_app.DAO.ReservationDAO;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.ui_components.*;
import trip_manager_app.views.LoginView;
import trip_manager_app.views.user.UserDestinationDetails;

/**
 *
 * @author ely
 */
public class AdminHomepageView extends JPanel{
//    private List<JPanel> cards = new ArrayList<>();
    private UIButton homeButton;
    private UIButton clientsButton;
    private UIButton reservationButton;
    private UIButton logoutButton;
    private UIButton addDestinationsButton;
    private final ClientDAO clientDao;
    private final DestinationDAO destinationDao;
    private final ReservationDAO reservationDao;
    private JPanel row2;
    private JPanel row1;
    private JFrame parentFrame;
    private long usersCount;
    private long destinationsCount;
    private long reservationsCount;
    private long transportsCount;
    private JLabel usersCountLabel;
    private JLabel destinationsCountLabel;
    private JLabel reservationsCountLabel;
    private UIButton destinationButton;
    private UIButton transportButton;
    private MoyenTransportDAO transportDao;
    private JLabel transportsCountLabel;

    public AdminHomepageView(){    
        this.clientDao = new ClientDAO();
        this.destinationDao = new DestinationDAO();
        this.reservationDao = new ReservationDAO();
        this.transportDao = new MoyenTransportDAO();
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
        reloadValues();
    }
    
    public AdminHomepageView(JFrame parentFrame){    
        this.parentFrame = parentFrame;
        this.clientDao = new ClientDAO();
        this.destinationDao = new DestinationDAO();
        this.reservationDao = new ReservationDAO();
        this.transportDao = new MoyenTransportDAO();
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
        reloadValues();
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
                " Dashboard",
                "/trip_manager_app/ressources/icons/analytics_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
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
                "/trip_manager_app/ressources/icons/car.svg", 
                new Color(0, 0, 0, 0),
                new Color(108, 99, 255)
                
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
                homeButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                homeButton.setBackground(new Color(108, 99, 255));            
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
                reservationButton.setBackground(new Color(0, 0, 0, 0));            
            }
        });
        
        transportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                transportButton.setBackground(new Color(101, 93, 235, 20));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                transportButton.setBackground(new Color(0, 0, 0, 0));            
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
        topWrapper.setPreferredSize(new Dimension(Integer.MAX_VALUE, 210));
        topWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 210));
        topWrapper.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        topWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        topWrapper.add(Box.createVerticalGlue());     
        topWrapper.add(title);
        
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setBackground(Color.white);
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
        JScrollPane scrollWrapper = new JScrollPane(bottomWrapper);
        scrollWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JScrollBar scrollBar = scrollWrapper.getVerticalScrollBar();
        
        // customizing the scrollbar aspect to match the design
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void paintThumb(Graphics grphcs, JComponent c, Rectangle thumbBounds){
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(thumbColor);
               g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(101, 93, 235, 40);
                this.trackColor = Color.white;
                this.scrollBarWidth = 10;
            }
        });
        
        
        row1 = new JPanel();
        row1.setOpaque(false);
        row1.setLayout(new BorderLayout());
        row1.setPreferredSize(new Dimension(0, 300));
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        row1.setMinimumSize(new Dimension(1, 300));
        row1.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
                
        SubtitleLabel subtitle1 = new SubtitleLabel("   Statistiques generales");
        
//        avec une commande de recuperation de longueur de table, on recupere le nombre d'elements dans la table clients, reservations et dans la table destinations
        usersCount = clientDao.count();
        destinationsCount = destinationDao.count();
        reservationsCount = reservationDao.count();
        transportsCount = transportDao.count();
        
//        long users = clientDao.count();
//        long destinations = destinationDao.count();
//        long reservations = reservationDao.count();
//        long transports = transportDao.count();
        
        usersCountLabel = new JLabel(formatCount(usersCount, "Clients"));
        destinationsCountLabel = new JLabel(formatCount(destinationsCount, "Destinations disponibles"));
        reservationsCountLabel = new JLabel(formatCount(reservationsCount, "Reservations faites"));
        transportsCountLabel = new JLabel(formatCount(transportsCount, "Moyens de transport disponibles"));
        
        JPanel statsContainer = new JPanel();
        statsContainer.setLayout(new BoxLayout(statsContainer, BoxLayout.Y_AXIS));
        statsContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        statsContainer.setOpaque(false);
        
        statsContainer.add(usersCountLabel);
        statsContainer.add(destinationsCountLabel);
        statsContainer.add(reservationsCountLabel);
        statsContainer.add(transportsCountLabel);
        
        row1.add(statsContainer);
        
        SubtitleLabel subtitle2 = new SubtitleLabel("   Destinations disponibles");
        
        row2 = new JPanel();
        row2.setOpaque(false);
        row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        row2.setMinimumSize(new Dimension(Integer.MAX_VALUE, 300));
        row2.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        row2.setLayout(new BoxLayout(row2, BoxLayout.Y_AXIS));
        
        loadDestinations("*");
        
        JPanel addMoreBtnCtnr = new JPanel();
        addMoreBtnCtnr.setOpaque(false);
        
        
        addDestinationsButton = new UIButton(
            "  Ajouter destination",
            "/trip_manager_app/ressources/icons/plus.svg", 
            new Color(101, 93, 235, 20), 
            new Color(108, 99, 255)      
        );
        
        addDestinationsButton.addActionListener(e->{
            showDetails();
            reloadValues();
        });
        
        addDestinationsButton.setHorizontalAlignment(SwingConstants.CENTER);
        
        // button color change on hover
        
        addDestinationsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                addDestinationsButton.setBackground(new Color(101, 93, 235, 40));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                addDestinationsButton.setBackground(new Color(101, 93, 235, 20));            
            }
        });

        addMoreBtnCtnr.add(addDestinationsButton);
        
        bottomWrapper.add(subtitle1);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(row1);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(subtitle2);
        bottomWrapper.add(Box.createVerticalStrut(10));
        bottomWrapper.add(addMoreBtnCtnr);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(row2);
        
        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        new Timer(5000, e -> reloadValues()).start();
        
        return wrapper;
    }
    
    private String formatCount(long count, String labelText) {
    String countStr = (count >= 10) ? String.valueOf(count) : "0" + count;
    return "<html><span style='font-size:32px; color:#a175ff;'>" + countStr +
           "</span>   <span style='font-size:25px;'>" + labelText + "</span></html>";
}
    
    public void showDetails(){
        AddDestinationDialog dialog = new AddDestinationDialog(parentFrame, this);   
        dialog.showDialog();
    }
    
    public void loadDestinations(String labelName){
        java.util.List<DestinationModel> destinations;
        if(labelName.equals("*")){
            destinations = destinationDao.getNDestinations(4);
            row2.removeAll();       // removes every child component
            row2.revalidate();      // tells the layout manager to recalculate layout
            row2.repaint();
            showDestinationRows(destinations); 
        }
 
    }
    
    private void showDestinationRows(java.util.List<DestinationModel> destinations) {

        if(!destinations.isEmpty()){
            for(DestinationModel destination:destinations){

//                    DestinationModel dest = new DestinationModel(destination.getDestinationId(), destination.getVille(), destination.getPays(),  destination.getDescription(), destination.getNote(), destination.getImageId());

                    ListElementRow destRow = new ListElementRow(destination.getVille(), "", "Note: " + destination.getNote(), "/trip_manager_app/ressources/icons/star.svg", e ->showDetails(destination));
                    row2.add(destRow);
                    row2.add(Box.createVerticalStrut(20));  
                }
        }else{
            // Center the empty state vertically and horizontally
            row2.add(Box.createVerticalGlue());

            JPanel noResPanel = new JPanel();
            noResPanel.setLayout(new BoxLayout(noResPanel, BoxLayout.Y_AXIS));
            noResPanel.setOpaque(false);
            noResPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel noResMessage1 = new JLabel(">-<");
            noResMessage1.setFont(new Font("SansSerif", Font.BOLD, 48));
            noResMessage1.setForeground(new Color(180, 180, 180));
            noResMessage1.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel noResMessage2 = new JLabel("Aucune destination repondant a ces critères");
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
    
    private void searchDestination(String searchText){
        SwingUtilities.invokeLater(()->{
            java.util.List<DestinationModel> destinations;
            if(searchText.trim().equals("")){
                destinations = destinationDao.getNDestinations(5);
                row1.removeAll();       // removes every child component
                row1.revalidate();      // tells the layout manager to recalculate layout
                row1.repaint();
                System.out.println("Searching all..."+searchText);
                showDestinationRows(destinations); 

            } else {
                destinations = destinationDao.getMatchingDestinations(searchText);
                row1.removeAll();       // removes every child component
                row1.revalidate();      // tells the layout manager to recalculate layout
                row1.repaint();
                System.out.println("Searching..."+searchText);
                showDestinationRows(destinations); 

            }
        });
    }
    
   public void reloadValues() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            private long users;
            private long destinations;
            private long reservations;

            @Override
            protected Void doInBackground() throws Exception {
                users        = clientDao.count();
                destinations = destinationDao.count();
                reservations = reservationDao.count();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); 

                    usersCountLabel.setText(formatCount(users, "Clients"));
                    destinationsCountLabel.setText(formatCount(destinations, "Destinations disponibles"));
                    reservationsCountLabel.setText(formatCount(reservations, "Reservations faites"));
                    loadDestinations("*");


                } catch (Exception ex) {
                    System.err.println("Error refreshing counts: " + ex.getMessage());
                }
            }
        };

        worker.execute();
    }

    public void showDetails(DestinationModel destination){
        AdminDestinationDetails dialog = new AdminDestinationDetails(parentFrame, destination);   
        dialog.showDialog();
    }
    
    public void addClientManagementButtonListener(ActionListener listener){
        clientsButton.addActionListener(listener);
    }
    
    public void addReservationsManagementButtonListener(ActionListener listener){
        reservationButton.addActionListener(listener);
    }
    
    public void addLogoutButtonListener(ActionListener listener){
        logoutButton.addActionListener(listener);
    }
    
    public void addDestinationsButtonListener(ActionListener listener){
        destinationButton.addActionListener(listener);
    }
    
    public void addTransportsManagementButtonListener(ActionListener listener){
        transportButton.addActionListener(listener);
    }
    

}
