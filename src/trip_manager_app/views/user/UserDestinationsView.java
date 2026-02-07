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
import java.util.List;
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
public class UserDestinationsView extends JPanel{
    private UIButton homeButton;
    private UIButton destinationButton;
    private UIButton reservationButton;
    private UIButton userProfileButton;
    private List<String> options;
    private JPanel row1;
    private JFrame parentFrame;
    
    public UserDestinationsView(){
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
    public UserDestinationsView(JFrame parentFrame){
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
                " Accueil",
                "/trip_manager_app/ressources/icons/home.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
                
        //destination button 
        destinationButton = new UIButton(
                " Destinations",
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
                destinationButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                destinationButton.setBackground(new Color(108, 99, 255));            
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
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));
        title.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        title.setMinimumSize(new Dimension(Integer.MAX_VALUE, 0));
        
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
        
        options = new ArrayList<>();
        options.add("Populaires");
        options.add("Meilleurs prix");
        options.add("Meilleures notes");

        
        NavBarHorizontal navBar = new NavBarHorizontal(options, optionName-> loadReservations(optionName));
                        
        topWrapper.add(Box.createVerticalGlue());     
        topWrapper.add(title);
        topWrapper.add(Box.createVerticalStrut(10));
        topWrapper.add(navBar);
        
        
        row1 = new JPanel();
        row1.setOpaque(false);
        row1.setLayout(new GridLayout(0, 4, 20, 20));  
        row1.setPreferredSize(null);
        row1.setMaximumSize(null);
        row1.setMinimumSize(new Dimension(1, 600));
        row1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        
        
//        "/home/ely/Downloads/_ - 2026-01-27T002355.176.jpeg"));
//        images.add(new ImageIcon("/home/ely/Downloads/_ - 2026-01-26T235924.487.jpeg"));
//        images.add(new ImageIcon("/home/ely/Downloads/_ - 2026-01-26T235911.754.jpeg"

        showDestinationCards(6);

                
        bottomWrapper.add(row1);


        
        wrapper.add(topWrapper);
        wrapper.add(Box.createVerticalStrut(20));
        wrapper.add(scrollWrapper);
        
        
        return wrapper;
    }
    
    public void loadReservations(String labelName){
//        System.out.println(labelName+"I'm gunna catch u nigger");
        if(labelName.equals(options.get(0))){  
//            System.out.println(labelName+" Option 1 clicked");
            row1.removeAll();       // removes every child component
            row1.revalidate();      // tells the layout manager to recalculate layout
            row1.repaint();
            showDestinationCards(6); 
        }
        else if(labelName.equals(options.get(1))){
//            System.out.println(labelName+" Option 2 clicked");
            row1.removeAll();       
            row1.revalidate();
            row1.repaint();
            showDestinationCards(10); 
        }
        else if(labelName.equals(options.get(2))){
//            System.out.println(labelName+" Option 3 clicked");
            row1.removeAll();       
            row1.revalidate();
            row1.repaint();
            showDestinationCards(5); 
        }
    }
    
    private void showDestinationCards(int n) {
        String[] destinations = {"Paris", "Accra", "Nepal", "Uruguay", "Rome", "LA, Los Angeles", "Utah", "Ares", "Revan", "Java", "Caire", "Jerusalem"};
        for(int i =0; i<n; i++){
            String destinationName = destinations[i];
            DestinationCard card = new DestinationCard(destinationName, "4,5", "/home/ely/Downloads/_ - 2026-01-26T235911.754.jpeg",e -> {
                showDetails(destinationName);
            });
//            cards.add(card);
            row1.add(card);
        }
    }
    
    public void showDetails(String content){
        UserDestinationDetails dialog = new UserDestinationDetails(parentFrame, content);   
        dialog.showDialog();
    }
    
    public void addHomeButtonListener(ActionListener listener){
        homeButton.addActionListener(listener);
    }
    
    public void addReservationButtonListener(ActionListener listener){
        reservationButton.addActionListener(listener);
    }
    
    public void addUserProfileButtonListener(ActionListener listener){
        userProfileButton.addActionListener(listener);
    }

    
}
