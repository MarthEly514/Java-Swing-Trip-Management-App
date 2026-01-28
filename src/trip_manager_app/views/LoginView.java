/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views;

import java.awt.*;
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
public class LoginView extends JPanel{
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

        formPanel.setOpaque(false);
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);

        

        wrapper.add(formPanel);
        return wrapper;
    }
    
    public void addSignupRedirectButtonListener(ActionListener listener){
        signupRedirectButton.addActionListener(listener);
    }
    
    public void addLoginButtonListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
        
}
