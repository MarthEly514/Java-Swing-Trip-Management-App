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

    /**
     * @return the lastNameField
     */
    public RoundedTextField getLastNameField() {
        return lastNameField;
    }

    /**
     * @return the firstNameField
     */
    public RoundedTextField getFirstNameField() {
        return firstNameField;
    }

    /**
     * @return the emailField
     */
    public RoundedTextField getEmailField() {
        return emailField;
    }

    /**
     * @return the phoneField
     */
    public RoundedTextField getPhoneField() {
        return phoneField;
    }

    /**
     * @return the passwordField
     */
    public RoundedPasswordField getPasswordField() {
        return passwordField;
    }
    
    
    
    private JPanel formPanel;
    private RoundedButton signupButton;
    private RoundedButton loginRedirectButton;
    private RoundedTextField lastNameField;
    private RoundedTextField firstNameField;
    private RoundedTextField emailField;
    private RoundedTextField phoneField;
    private RoundedPasswordField passwordField;
    private JLabel errorLabel;
    
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
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        formPanel.setPreferredSize(new Dimension(450, 550));

        JLabel title = new JLabel("Inscription");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(117, 117, 117));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Lastname Field

        lastNameField = new RoundedTextField();
        getLastNameField().setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        getLastNameField().setRadius(50);
        getLastNameField().setFont(new Font("SansSerif", Font.PLAIN, 16));
        getLastNameField().setForeground(new Color(117, 117, 117));
        getLastNameField().setPlaceholder("Nom");
        getLastNameField().setBorderColor(new Color(214,211,255));
        getLastNameField().setPlaceholderColor(new Color(190, 190, 190));
        getLastNameField().setFocusedBorderColor(new Color(108, 99, 255));
        
        // FirstName Field

        firstNameField = new RoundedTextField();
        getFirstNameField().setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        getFirstNameField().setRadius(50);
        getFirstNameField().setFont(new Font("SansSerif", Font.PLAIN, 16));
        getFirstNameField().setForeground(new Color(117, 117, 117));
        getFirstNameField().setPlaceholder("Prenom");
        getFirstNameField().setBorderColor(new Color(214,211,255));
        getFirstNameField().setPlaceholderColor(new Color(190, 190, 190));
        getFirstNameField().setFocusedBorderColor(new Color(108, 99, 255));


        // Email Field

        emailField = new RoundedTextField();
        getEmailField().setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        getEmailField().setRadius(50);
        getEmailField().setFont(new Font("SansSerif", Font.PLAIN, 16));
        getEmailField().setForeground(new Color(117, 117, 117));
        getEmailField().setPlaceholder("Email");
        getEmailField().setBorderColor(new Color(214,211,255));
        getEmailField().setPlaceholderColor(new Color(190, 190, 190));
        getEmailField().setFocusedBorderColor(new Color(108, 99, 255));
        
        // Phone Number Field

        phoneField = new RoundedTextField();
        getPhoneField().setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        getPhoneField().setRadius(50);
        getPhoneField().setFont(new Font("SansSerif", Font.PLAIN, 16));
        getPhoneField().setForeground(new Color(117, 117, 117));
        getPhoneField().setPlaceholder("Numero de telephone");
        getPhoneField().setBorderColor(new Color(214,211,255));
        getPhoneField().setPlaceholderColor(new Color(190, 190, 190));
        getPhoneField().setFocusedBorderColor(new Color(108, 99, 255));

        // Password Field
        
        passwordField = new RoundedPasswordField();
        getPasswordField().setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        getPasswordField().setRadius(50);
        getPasswordField().setFont(new Font("SansSerif", Font.PLAIN, 16));
        getPasswordField().setForeground(new Color(117, 117, 117));
        getPasswordField().setPlaceholder("Mot de passe");
        getPasswordField().setBorderColor(new Color(214,211,255));
        getPasswordField().setPlaceholderColor(new Color(190, 190, 190));
        getPasswordField().setFocusedBorderColor(new Color(108, 99, 255));

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
        
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.setOpaque(false);
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(getLastNameField());
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(getFirstNameField());
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(getEmailField());
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(getPhoneField());
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(getPasswordField());
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(signupButton);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(errorLabel);

        

        wrapper.add(formPanel);
        return wrapper;
    }
    
    public void addSignupButtonListener(ActionListener listener){
        signupButton.addActionListener(listener);
    }
    
    public void addLoginRedirectButtonListener(ActionListener listener){
        loginRedirectButton.addActionListener(listener);
    }
    
    /**
     * @return the lastNameField content
     */
    public String getLastName() {
        return lastNameField.getText();
    }

    /**
     * @return the firstNameField content
     */
    public String getFirstName() {
        return firstNameField.getText();
    }

    /**
     * @return the emailField content
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * @return the phoneField content
     */
    public String getPhone() {
        return phoneField.getText();
    }

    /**
     * @return the passwordField content
     */
    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public void emptyFields() {
        getLastNameField().setText("");
        getFirstNameField().setText("");
        getEmailField().setText("");
        getPhoneField().setText("");
        getPasswordField().setText("");
    }

    public void hideErrorMessage() {
        errorLabel.setText(" ");
    }

    public void showErrorMessage(String error) {
        errorLabel.setText(error);
    }
}
