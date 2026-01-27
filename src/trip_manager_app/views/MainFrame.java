/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views;

/**
 *
 * @author ely
 */
import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    public MainFrame(){
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        add(contentPanel);
        setTitle("Java-Swing Trip Management App");
        setSize(1500, 845);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void addView(JPanel view, String name){
        contentPanel.add(view, name);
    }
    
    public void showView(String name){
        cardLayout.show(contentPanel, name);
    }
}
