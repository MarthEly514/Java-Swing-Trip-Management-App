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
import java.io.IOException;
import java.io.InputStream;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    public MainFrame(){
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel);
        setTitle("Java-Swing Trip Management App");
        setSize(1512, 982);
        setMinimumSize(new Dimension(1150, 750));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
//        //setting the main font: Google Sans Flex
//        try{
//            InputStream is = getClass().getResourceAsStream("/trip_manager_app/ressources/fonts/GoogleSansFlex-VariableFont_GRAD,ROND,opsz,slnt,wdth,wght_1.ttf");
//            Font googleSans = Font.createFont(Font.TRUETYPE_FONT, is);
//            
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(googleSans);
//            setFont(googleSans.deriveFont(12f));
//            
//        } catch(FontFormatException | IOException e){
//               System.out.println("Font not found or bad format");
//        }
//        
    }
    
    public void addView(JPanel view, String name){
        contentPanel.add(view, name);
    }
    
    public void showView(String name){
        cardLayout.show(contentPanel, name);
    }
}
