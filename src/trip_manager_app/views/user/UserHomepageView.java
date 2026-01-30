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
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.ui_components.*;
import trip_manager_app.utils.SvgUtils;
import trip_manager_app.views.LoginView;

/**
 *
 * @author ely
 */
public class UserHomepageView extends JPanel{
    private RoundedButton homeButton;
    private RoundedButton destinationButton;
    private RoundedButton reservationButton;
    private RoundedButton userProfileButton;
    public UserHomepageView(){
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
        homeButton = new RoundedButton();
        styleButton(
                homeButton,
                " Accueil",
                "/trip_manager_app/ressources/icons/home_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
        );
                
        //destination button 
        destinationButton = new RoundedButton();
        styleButton(
                destinationButton,
                " Destinations",
                "/trip_manager_app/ressources/icons/travel.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
        
        //reservation button
        reservationButton = new RoundedButton(" Réservations");
        styleButton(
                reservationButton,
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
        
        userProfileButton = new RoundedButton();
        styleButton(
                userProfileButton,
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
        
        JPanel searchBar = new JPanel();
        searchBar.setBackground(Color.red);
        searchBar.setPreferredSize(new Dimension(300, 60));
        searchBar.setMaximumSize(new Dimension(300, 100));
//        searchBar.setMinimumSize(new Dimension(1000, 0));
        searchBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Accueil");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
        topWrapper.add(searchBar);
        topWrapper.add(Box.createVerticalGlue());     
        topWrapper.add(title);
        
        JPanel bottomWrapper = new JPanel();
        bottomWrapper.setBackground(Color.white);
        bottomWrapper.setLayout(new BoxLayout(bottomWrapper, BoxLayout.Y_AXIS));
        bottomWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        //bottomWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
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
        
        
        JPanel row1 = new JPanel();
        row1.setBackground(Color.green);
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row1.setPreferredSize(new Dimension(0, 300));
        row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        row1.setMinimumSize(new Dimension(0, 300));
        
        JScrollPane destinationsWrapper = new JScrollPane(row1);
        destinationsWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        destinationsWrapper.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        destinationsWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
//        for(int i =0; i<3; i++){
//            DestinationCard card = new DestinationCard("Paris", "/logan/sen/lyla.mpeg", "4,5");
//            destinationsWrapper.add(card);
//        }
        
//        DestinationCard card = new DestinationCard("Paris", "/logan/sen/lyla.mpeg", "4,5");
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(300, 200));
        card.setMaximumSize(new Dimension(300, 200));
        card.setMinimumSize(new Dimension(300, 200));
        card.setBackground(Color.PINK);
        destinationsWrapper.add(card);
                
        SubtitleLabel subtitle1 = new SubtitleLabel("Destinations en vedette");

        JPanel row2 = new JPanel();
        row2.setBackground(Color.blue);
        row2.setPreferredSize(new Dimension(0, 400));
        row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        row2.setMinimumSize(new Dimension(0, 400));
        
        SubtitleLabel subtitle2 = new SubtitleLabel("Mes réservations");
        
        
//        bottomWrapper.add(title);
        bottomWrapper.add(subtitle1);
        bottomWrapper.add(Box.createVerticalStrut(20));
//        bottomWrapper.add(card);
//        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(destinationsWrapper);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(subtitle2);
        bottomWrapper.add(Box.createVerticalStrut(20));
        bottomWrapper.add(row2);


        
        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        
        return wrapper;
    }
    
    private void styleButton(RoundedButton button, String text, String iconPath, Color backgroundColor, Color foregroundColor){
        //button

        button.setText(text);
        button.setPreferredSize(new Dimension(250, 50));
        button.setRadius(50);
        button.setBorderWidth(0);
        button.setBorderColor(new Color(0, 0, 0, 0));
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setIcon(SvgUtils.loadSvg(iconPath, 22, 22));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMargin(new Insets(0, 10, 0, 0));
    }
    
    public void addDestinationButtonListener(ActionListener listener){
        destinationButton.addActionListener(listener);
    }
    
    public void addReservationButtonListener(ActionListener listener){
        reservationButton.addActionListener(listener);
    }
    
    public void addUserProfileButtonListener(ActionListener listener){
        userProfileButton.addActionListener(listener);
    }
    

}
