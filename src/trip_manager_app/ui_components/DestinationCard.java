/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class DestinationCard extends PanelRound{
    public DestinationCard(String destination, String imagePath, String rating){
        PanelRound card = new PanelRound();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.pink);
        card.setRoundBottomLeft(20);
        card.setRoundBottomRight(20);
        card.setRoundTopLeft(20);
        card.setRoundTopRight(20);
        card.setPreferredSize(new Dimension(500, 500));
        JLabel destinationLabel = new JLabel(destination);
        JLabel imagePathLabel = new JLabel(imagePath);
        JLabel ratingLabel = new JLabel(rating);
        card.add(destinationLabel);
        card.add(imagePathLabel);
        card.add(ratingLabel);
        
    }
    
}
