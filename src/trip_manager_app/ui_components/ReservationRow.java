/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class ReservationRow extends PanelRound{
    int radius = 70;
    UIButton showConfirmDialogButton;
    public ReservationRow(String destination, String date, String state){
        initRow(destination, date, state);
    }
    
    public ReservationRow(String destination, String date, String state, Consumer<String> execAction){
        initRow(destination, date, state);
        showConfirmDialogButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e){
//                if(!active.equals(optionLabel.getText())){
//                    setActive(optionLabel.getText());
//                    optionLabels.forEach(optionLabel->setFocusedOption(optionLabel));
//                    execAction.accept(optionLabel.getName());          
//                }
                    execAction.accept("");          
                
            }
        });
    }

    private void initRow(String destination, String date, String state) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
        setBackground(new Color(101, 93, 235, 20));
        setRoundBottomLeft(radius);
        setRoundBottomRight(radius);
        setRoundTopLeft(radius);
        setRoundTopRight(radius);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
//        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        JLabel destinationLabel = new JLabel(destination);
        destinationLabel.setFont(new Font("SansSerif",Font.BOLD, 18));
        JLabel dateLabel = new JLabel(date);
        JLabel stateLabel = new JLabel(state);
        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setPreferredSize(new Dimension(250, 50));
        buttonContainer.setMaximumSize(new Dimension(250, 50));
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        showConfirmDialogButton = new UIButton(
            "Voir",
            "", 
            new Color(101, 93, 235, 190), 
            new Color(255, 255, 255)      
        );
        showConfirmDialogButton.setHorizontalAlignment(SwingConstants.CENTER);
        buttonContainer.add(showConfirmDialogButton, BorderLayout.CENTER);
        
        // button color change on hover
        
        showConfirmDialogButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                showConfirmDialogButton.setBackground(new Color(101, 93, 235, 250));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                showConfirmDialogButton.setBackground(new Color(101, 93, 235, 190));            
            }
        });

        
        add(destinationLabel);
        add(Box.createHorizontalGlue());
        add(dateLabel);
        add(Box.createHorizontalGlue());
        add(stateLabel);
        add(Box.createHorizontalGlue());
        add(buttonContainer);

    
    }
    }
