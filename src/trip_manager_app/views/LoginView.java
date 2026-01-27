/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class LoginView extends JPanel{
    private JButton signupButton;
    public LoginView(){
        add(new JLabel("Login nigger"));
        signupButton = new JButton("Signup page");
        add(signupButton);
    }
    
    public void addSignupButtonListener(ActionListener listener){
        signupButton.addActionListener(listener);
    }
    
}
