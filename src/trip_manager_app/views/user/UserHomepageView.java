/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
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
        
        homeButton = new RoundedButton(" Accueil");
        homeButton.setPreferredSize(new Dimension(250, 50));
        homeButton.setRadius(50);
        homeButton.setBorderWidth(0);
        homeButton.setBorderColor(new Color(0,0,0,0));
        homeButton.setBackground(new Color(108, 99, 255));
        homeButton.setForeground(Color.white);
        homeButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        homeButton.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/home_light.svg", 22, 22));
        homeButton.setHorizontalAlignment(SwingConstants.LEFT);
        homeButton.setMargin(new Insets(0, 10, 0, 0));
        
        
        //destination button
        
        destinationButton = new RoundedButton(" Destinations");
        destinationButton.setPreferredSize(new Dimension(250, 50));
        destinationButton.setRadius(50);
        destinationButton.setBorderWidth(0);
        destinationButton.setBorderColor(new Color(0,0,0,0));
        destinationButton.setBackground(new Color(0, 0, 0, 0));
        destinationButton.setForeground(new Color(108, 99, 255));
        destinationButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        destinationButton.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/travel.svg", 22, 22));
        destinationButton.setHorizontalAlignment(SwingConstants.LEFT);

        
        //reservation button
        
        reservationButton = new RoundedButton(" Réservations");
        reservationButton.setPreferredSize(new Dimension(250, 50));
        reservationButton.setRadius(50);
        reservationButton.setBorderWidth(0);
        reservationButton.setBorderColor(new Color(0,0,0,0));
        reservationButton.setBackground(new Color(0, 0, 0, 0));
        reservationButton.setForeground(new Color(108, 99, 255));
        reservationButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        reservationButton.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/date_range.svg", 22, 22));
        reservationButton.setHorizontalAlignment(SwingConstants.LEFT);

        
        
        navigationPanel.add(homeButton);
        navigationPanel.add(destinationButton);
        navigationPanel.add(reservationButton);
        
        userProfileButton = new RoundedButton("  Mon Profil");
        userProfileButton.setPreferredSize(new Dimension(250, 50));
        userProfileButton.setRadius(50);
        userProfileButton.setBorderWidth(0);
        userProfileButton.setBorderColor(new Color(0,0,0,0));
        userProfileButton.setBackground(new Color(108, 99, 255));
        userProfileButton.setForeground(Color.white);
        userProfileButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        userProfileButton.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/user.svg", 22, 22));
        userProfileButton.setHorizontalAlignment(SwingConstants.LEFT);
        
        
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
        


        panel.add(Box.createVerticalStrut(100));
        panel.add(navigationPanel);
        panel.add(Box.createVerticalGlue());
        panel.add(userProfileButton);

        return panel;
    }
    
    private JPanel createRightPanel(){
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.white);
        //wrapper.setBackground(new Color(248, 248, 250));
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
}
