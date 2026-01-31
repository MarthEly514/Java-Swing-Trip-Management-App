/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class DestinationCard extends PanelRound{
    private BufferedImage backgroundImage;

    public DestinationCard(String destination, String rating, String imagePath){
        super(imagePath); 
        int radius = 50;
        setLayout(new BorderLayout());
        setBackground(Color.pink);
        setRoundBottomLeft(radius);
        setRoundBottomRight(radius);
        setRoundTopLeft(radius);
        setRoundTopRight(radius);
        setPreferredSize(new Dimension(250, 250));
        setMaximumSize(new Dimension(250, 250));
        setMinimumSize(new Dimension(250, 250));
        
        PanelRound bottomPanel = new PanelRound();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(250, 100));
        bottomPanel.setMaximumSize(new Dimension(250, 100));
        bottomPanel.setMinimumSize(new Dimension(250, 100));
        bottomPanel.setBackground(new Color(20, 20, 20, 50));
        bottomPanel.setBlurred(true);
        bottomPanel.setBlurRadius(8f);
        bottomPanel.setRoundBottomLeft(radius);
        bottomPanel.setRoundBottomRight(radius);
//        bottomPanel.setRoundTopLeft(radius);
        bottomPanel.setRoundTopRight(radius);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(bottomPanel, BorderLayout.SOUTH);
        
        JLabel destinationLabel = new JLabel(destination);
        destinationLabel.setForeground(Color.white);
        destinationLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JLabel ratingLabel = new JLabel("<html>Note:<span style='color:#fff009'>&nbsp;&nbsp;&nbsp;"+rating+"</span></html>");
        ratingLabel.setForeground(Color.white);
        ratingLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        bottomPanel.add(destinationLabel, BorderLayout.NORTH);
        bottomPanel.add(ratingLabel, BorderLayout.SOUTH);
    
    }
    
}
