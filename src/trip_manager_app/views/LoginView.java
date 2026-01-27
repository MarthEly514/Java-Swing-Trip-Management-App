/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import trip_manager_app.ui_components.RoundedButton;
import trip_manager_app.ui_components.RoundedPasswordField;
import trip_manager_app.ui_components.RoundedTextField;

/**
 *
 * @author ely
 */
public class LoginView extends JPanel{
    private RoundedButton signupButton;
    public LoginView(){
        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createRightPanel(), BorderLayout.CENTER);
    }
    
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        panel.setBackground(new Color(233,223, 255));

        JLabel logo = new JLabel("🌍 Trip Manager");
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel text = new JLabel("<html>Manage your trips easily.<br>Plan. Book. Travel.</html>");
        text.setAlignmentX(Component.LEFT_ALIGNMENT);

        signupButton = new RoundedButton("New here? Sign up");
        signupButton.setMaximumSize(new Dimension(200, 50));
        signupButton.setRadius(50);
        signupButton.setBorderWidth(0);
        signupButton.setBorderColor(new Color(0,0,0,0));
        signupButton.setBackground(new Color(161, 117, 255));
        signupButton.setForeground(Color.white);
        signupButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        signupButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(logo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(text);
        panel.add(Box.createVerticalGlue());
        panel.add(signupButton);

        return panel;
    }
    
    private JPanel createRightPanel(){
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(246, 245, 250));
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
        emailField.setBorderColor(new Color(233, 223, 255));
        emailField.setPlaceholderColor(new Color(190, 190, 190));
        emailField.setFocusedBorderColor(new Color(161, 117, 255));

        // Password Field
        
        RoundedPasswordField passwordField = new RoundedPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        passwordField.setRadius(50);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        passwordField.setForeground(new Color(117, 117, 117));
        passwordField.setPlaceholder(".....");
        passwordField.setBorderColor(new Color(233, 223, 255));
        passwordField.setPlaceholderColor(new Color(190, 190, 190));
        passwordField.setFocusedBorderColor(new Color(161, 117, 255));

        // Login Button
        
        RoundedButton loginButton = new RoundedButton("Se connecter");
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        loginButton.setRadius(50);
        loginButton.setBorderWidth(0);
        loginButton.setBorderColor(new Color(0,0,0,0));
        loginButton.setBackground(new Color(161, 117, 255));
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.setOpaque(false);
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginButton);

        wrapper.add(formPanel);
        return wrapper;
    }
    
    public void addSignupButtonListener(ActionListener listener){
        signupButton.addActionListener(listener);
    }
    
}
