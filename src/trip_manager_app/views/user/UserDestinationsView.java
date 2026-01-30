/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import trip_manager_app.ui_components.RoundedButton;
import trip_manager_app.ui_components.RoundedPasswordField;
import trip_manager_app.ui_components.RoundedTextField;
import trip_manager_app.utils.SvgUtils;
import trip_manager_app.views.LoginView;

/**
 *
 * @author ely
 */
public class UserDestinationsView extends JPanel{
    private RoundedButton homeButton;
    private RoundedButton destinationButton;
    private RoundedButton reservationButton;
    private RoundedButton userProfileButton;
    
    public UserDestinationsView(){
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
        homeButton = new RoundedButton();
        styleButton(
                homeButton,
                " Accueil",
                "/trip_manager_app/ressources/icons/home.svg", 
                new Color(0, 0, 0, 0), 
                new Color(108, 99, 255)
                
        );
                
        //destination button 
        destinationButton = new RoundedButton();
        styleButton(
                destinationButton,
                " Destinations",
                "/trip_manager_app/ressources/icons/travel_light.svg", 
                new Color(108, 99, 255), 
                Color.white
                
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
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.white);
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        formPanel.setPreferredSize(new Dimension(450, 350));

        JLabel title = new JLabel("Connexion");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(117, 117, 117));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email Field

        RoundedTextField emailField = new RoundedTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        emailField.setRadius(50);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        emailField.setForeground(new Color(117, 117, 117));
        emailField.setPlaceholder("Email");
        emailField.setBorderColor(new Color(214,211,255));
        emailField.setPlaceholderColor(new Color(190, 190, 190));
        emailField.setFocusedBorderColor(new Color(108, 99, 255));

        // Password Field
        
        RoundedPasswordField passwordField = new RoundedPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        passwordField.setRadius(50);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setForeground(new Color(117, 117, 117));
        passwordField.setPlaceholder("Mot de passe");
        passwordField.setBorderColor(new Color(214,211,255));
        passwordField.setPlaceholderColor(new Color(190, 190, 190));
        passwordField.setFocusedBorderColor(new Color(108, 99, 255));

        formPanel.setOpaque(false);
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(passwordField);
        

        wrapper.add(formPanel);
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
