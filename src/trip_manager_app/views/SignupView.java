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
public class SignupView extends JPanel{
    private JButton loginButton;
    public SignupView(){
        add(new JLabel("Signup nigger"));
        loginButton = new JButton("Login page");
        add(loginButton);
    }
    
    public void addLoginButtonListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
}
