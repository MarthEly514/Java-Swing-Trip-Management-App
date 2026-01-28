/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import trip_manager_app.ui_components.RoundedButton;
import trip_manager_app.ui_components.RoundedPasswordField;
import trip_manager_app.ui_components.RoundedTextField;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class SignupView extends JPanel{
    private RoundedButton signupButton;
    private RoundedButton loginRedirectButton;
    public SignupView(){
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
    //LEFT PANEL
    
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        panel.setBackground(new Color(240,240,255));
        panel.setPreferredSize(new Dimension(600, 0));

        Image logoImg;
        try {
            logoImg = ImageIO.read(getClass().getResource("/trip_manager_app/ressources/icons/hdpiGroup.png"));
            //logo.setIcon();
            ImageIcon logoIcon = new ImageIcon(logoImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH));
            JLabel logo = new JLabel("  EasyTrip");
            logo.setIcon(logoIcon);
            logo.setFont(new Font("SansSerif", Font.BOLD, 24));
            logo.setAlignmentX(Component.LEFT_ALIGNMENT);
            logo.setForeground(new Color(108, 99, 255));
            // adding the eft panel logo
            panel.add(logo);
        } catch (IOException ex) {
            System.getLogger(LoginView.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        JLabel text = new JLabel("<html>Gérez facilement vos voyages.<br><strong>Plannifiez. Réservez. Voyagez.</strong></html>");
        text.setFont(new Font("SansSerif", Font.PLAIN, 24));
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel illustration = new JLabel();
        illustration.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/images/undraw_travelers_kud9.svg", 300, 300));
        
        JPanel illustrationWrapper = new JPanel();
        illustrationWrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        illustrationWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        illustrationWrapper.add(illustration);
        illustrationWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        illustrationWrapper.setOpaque(false);
        
        
        JLabel signinInviteText = new JLabel("Vous avez déjà un compte?");
        signinInviteText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        signinInviteText.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginRedirectButton = new RoundedButton("Se connecter");
        loginRedirectButton.setPreferredSize(new Dimension(200, 50));
        loginRedirectButton.setRadius(50);
        loginRedirectButton.setBorderWidth(0);
        loginRedirectButton.setBorderColor(new Color(0,0,0,0));
        loginRedirectButton.setBackground(new Color(108, 99, 255));
        loginRedirectButton.setForeground(Color.white);
        loginRedirectButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        // button color change on hover
        
        loginRedirectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                loginRedirectButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                loginRedirectButton.setBackground(new Color(108, 99, 255));            
            }
        });
        
        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonWrapper.setMaximumSize(new Dimension(300, 50));
        buttonWrapper.add(loginRedirectButton);
        buttonWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonWrapper.setOpaque(false);
       
        panel.add(Box.createVerticalStrut(100));
        panel.add(text);
        panel.add(Box.createVerticalGlue());
        panel.add(illustrationWrapper);
        panel.add(Box.createVerticalGlue());
        panel.add(signinInviteText);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonWrapper);

        return panel;
    }
    
    //RIGHT PANEL
    
    private JPanel createRightPanel(){
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.white);
        //wrapper.setBackground(new Color(248, 248, 250));
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        formPanel.setPreferredSize(new Dimension(450, 550));

        JLabel title = new JLabel("Inscription");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(117, 117, 117));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Lastname Field

        RoundedTextField lastNameField = new RoundedTextField();
        lastNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        lastNameField.setRadius(50);
        lastNameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lastNameField.setForeground(new Color(117, 117, 117));
        lastNameField.setPlaceholder("Nom");
        lastNameField.setBorderColor(new Color(214,211,255));
        lastNameField.setPlaceholderColor(new Color(190, 190, 190));
        lastNameField.setFocusedBorderColor(new Color(108, 99, 255));
        
        // FirstName Field

        RoundedTextField firstNameField = new RoundedTextField();
        firstNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        firstNameField.setRadius(50);
        firstNameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        firstNameField.setForeground(new Color(117, 117, 117));
        firstNameField.setPlaceholder("Prenom");
        firstNameField.setBorderColor(new Color(214,211,255));
        firstNameField.setPlaceholderColor(new Color(190, 190, 190));
        firstNameField.setFocusedBorderColor(new Color(108, 99, 255));


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
        
        // Phone Number Field

        RoundedTextField phoneField = new RoundedTextField();
        phoneField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        phoneField.setRadius(50);
        phoneField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        phoneField.setForeground(new Color(117, 117, 117));
        phoneField.setPlaceholder("Email");
        phoneField.setBorderColor(new Color(214,211,255));
        phoneField.setPlaceholderColor(new Color(190, 190, 190));
        phoneField.setFocusedBorderColor(new Color(108, 99, 255));

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

        // Login Button
        
        signupButton = new RoundedButton("S'inscrire");
        signupButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        signupButton.setRadius(50);
        signupButton.setBorderWidth(0);
        signupButton.setBorderColor(new Color(0,0,0,0));
        signupButton.setBackground(new Color(108, 99, 255));
        signupButton.setForeground(Color.white);
        signupButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // button color change on hover
        
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                signupButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                signupButton.setBackground(new Color(108, 99, 255));            
            }
        });

        formPanel.setOpaque(false);
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(lastNameField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(firstNameField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(phoneField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(signupButton);

        

        wrapper.add(formPanel);
        return wrapper;
    }
    
    public void addSignupButtonListener(ActionListener listener){
        signupButton.addActionListener(listener);
    }
    
    public void addLoginRedirectButtonListener(ActionListener listener){
        loginRedirectButton.addActionListener(listener);
    }
}
