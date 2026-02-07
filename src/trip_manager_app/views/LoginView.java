/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views;

import java.awt.*;
import java.awt.event.*;
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
public class LoginView extends JPanel{

    /**
     * @return the emailField
     */
    public RoundedTextField getEmailField() {
        return emailField;
    }

    /**
     * @return the passwordField
     */
    public RoundedPasswordField getPasswordField() {
        return passwordField;
    }
    
    private RoundedTextField emailField;
    private RoundedPasswordField passwordField;
    private JLabel errorLabel;
    private JPanel formPanel;
    
    private RoundedButton signupRedirectButton;
    private RoundedButton loginButton;
    public LoginView(){
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
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
        illustration.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/images/undraw_beach-day_cnsv.svg", 300, 300));
        
        JPanel illustrationWrapper = new JPanel();
        illustrationWrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        illustrationWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        illustrationWrapper.add(illustration);
        illustrationWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        illustrationWrapper.setOpaque(false);
        
        
        JLabel signupInviteText = new JLabel("Vous n'avez pas de compte?");
        signupInviteText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        signupInviteText.setAlignmentX(Component.LEFT_ALIGNMENT);

        signupRedirectButton = new RoundedButton("S'inscrire");
        signupRedirectButton.setPreferredSize(new Dimension(200, 50));
        signupRedirectButton.setRadius(50);
        signupRedirectButton.setBorderWidth(0);
        signupRedirectButton.setBorderColor(new Color(0,0,0,0));
        signupRedirectButton.setBackground(new Color(108, 99, 255));
        signupRedirectButton.setForeground(Color.white);
        signupRedirectButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        // button color change on hover
        
        signupRedirectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                signupRedirectButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                signupRedirectButton.setBackground(new Color(108, 99, 255));            
            }
        });
        
        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonWrapper.setMaximumSize(new Dimension(300, 50));
        buttonWrapper.add(signupRedirectButton);
        buttonWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonWrapper.setOpaque(false);
       
        panel.add(Box.createVerticalStrut(100));
        panel.add(text);
        panel.add(Box.createVerticalGlue());
        panel.add(illustrationWrapper);
        panel.add(Box.createVerticalGlue());
        panel.add(signupInviteText);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonWrapper);

        return panel;
    }
    
    private JPanel createRightPanel(){
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.white);
        //wrapper.setBackground(new Color(248, 248, 250));
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        formPanel.setPreferredSize(new Dimension(450, 450));

        JLabel title = new JLabel("Connexion");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(117, 117, 117));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        
        loginButton = new RoundedButton("Se connecter");
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        loginButton.setRadius(50); 
        loginButton.setBorderWidth(0);
        loginButton.setBorderColor(new Color(0,0,0,0));
        loginButton.setBackground(new Color(108, 99, 255));
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // button color change on hover
        
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                loginButton.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                loginButton.setBackground(new Color(108, 99, 255));            
            }
        });

        
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        formPanel.setOpaque(false);
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(getEmailField());
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(getPasswordField());
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(errorLabel);

        

        wrapper.add(formPanel);
        return wrapper;
    }
    
    public void showErrorMessage(String errorMessage){ 
        errorLabel.setText(errorMessage);
    }
    
    public void hideErrorMessage(){ 
        errorLabel.setText(" ");
    }
    
    public String getEmail(){
        return getEmailField().getText();
    }
    
    public char[] getPassword(){
        return getPasswordField().getPassword();
    }
    
    public void emptyFields(){
        getEmailField().setText("");
        getPasswordField().setText("");
    }
    
    public void addSignupRedirectButtonListener(ActionListener listener){
        signupRedirectButton.addActionListener(listener);
    }
    
    public void addLoginButtonListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
    
   
        
}
