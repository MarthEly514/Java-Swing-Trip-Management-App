/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.*;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.utils.CachedImageLoader;

/**
 *
 * @author ely
 */
public class DestinationCard extends PanelRound{
    private BufferedImage backgroundImage;
    private CachedImageLoader cachedImageLoader;

    public DestinationCard(DestinationModel destination){
        
        super(); 
        cachedImageLoader = CachedImageLoader.getInstance();
        
        loadAndSetImage(destination.getImageId());        
        initCard(destination.getVille(), destination.getNote());
    }
    
    public DestinationCard(DestinationModel destination, Consumer<String> execAction){
        super(); 
        cachedImageLoader = CachedImageLoader.getInstance();
        
        loadAndSetImage(destination.getImageId());        
        initCard(destination.getVille(), destination.getNote());
        addMouseListener(new MouseAdapter() {
                        @Override
            public void mouseClicked(MouseEvent e){
//                if(!active.equals(optionLabel.getText())){
//                    setActive(optionLabel.getText());
//                    optionLabels.forEach(optionLabel->setFocusedOption(optionLabel));
//                }
                    execAction.accept("");          
                
            }
        });
    
    }
    
    private void initCard(String destination, float rating){
        int radius = 50;
        setLayout(new BorderLayout());
        setBackground(Color.pink);
        setRoundBottomLeft(radius);
        setRoundBottomRight(radius);
        setRoundTopLeft(radius);
        setRoundTopRight(radius);
        Dimension size = new Dimension(250, 250);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
        
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
    
    private void loadAndSetImage(int imageId){
        BufferedImage image = cachedImageLoader.loadImage(imageId);
        if (image != null) {
            // You'll need to add a setter in PanelRound to set the background image
            setBackgroundImage(image);
            repaint();
        }
    }
    
    
    
}
