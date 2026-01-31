/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class ReservationRow extends PanelRound{
    int radius = 80;
    UIButton showConfirmDialogButton;
    public ReservationRow(String destination, String date, String state){
//        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(new Color(101, 93, 235, 20));
        setRoundBottomLeft(radius);
        setRoundBottomRight(radius);
        setRoundTopLeft(radius);
        setRoundTopRight(radius);
//        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        
        JLabel destinationLabel = new JLabel(destination);
        JLabel dateLabel = new JLabel(date);
        JLabel stateLabel = new JLabel(state);
        
        
        JPanel showConfirmDialogButtonCtnr = new JPanel();
//        showConfirmDialogButtonCtnr.setOpaque(false);
        showConfirmDialogButtonCtnr.setLayout(new BorderLayout());
        showConfirmDialogButton = new UIButton(
            "Voir plus",
            "", 
            new Color(101, 93, 235, 20), 
            new Color(108, 99, 255)      
        );
        showConfirmDialogButton.setHorizontalAlignment(SwingConstants.CENTER);
        
        showConfirmDialogButtonCtnr.add(showConfirmDialogButton, BorderLayout.EAST);
        
        // button color change on hover
        
        showConfirmDialogButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                showConfirmDialogButton.setBackground(new Color(101, 93, 235, 40));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                showConfirmDialogButton.setBackground(new Color(101, 93, 235, 20));            
            }
        });

        
        add(destinationLabel);
        add(Box.createHorizontalGlue());
        add(dateLabel);
        add(Box.createHorizontalGlue());
        add(stateLabel);
        add(Box.createHorizontalGlue());
        add(showConfirmDialogButtonCtnr);

    
    }
}
