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
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class ListElementRow extends PanelRound{
    int radius = 70;
    UIButton showConfirmDialogButton;
    public ListElementRow(String title, String description, String state){
        initRow(title, description, state, "");
    }
    
    public ListElementRow(String title, String description, String state, String iconPath){
        initRow(title, description, state, iconPath);
    }
    
    public ListElementRow(String title, String description, String state, Consumer<String> execAction){
        initRow(title, description, state, "");
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
    public ListElementRow(String title, String description, String state, String iconPath, Consumer<String> execAction){
        initRow(title, description, state, iconPath);
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

    private void initRow(String title, String description, String state, String iconPath) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
        setBackground(new Color(101, 93, 235, 20));
        setRoundBottomLeft(radius);
        setRoundBottomRight(radius);
        setRoundTopLeft(radius);
        setRoundTopRight(radius);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
//        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setPreferredSize(new Dimension(260, 40));
        titleLabel.setMaximumSize(new Dimension(260, 40));

        // Description – takes remaining space
        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setPreferredSize(new Dimension(300, 40));
        descriptionLabel.setMaximumSize(new Dimension(320, 40));
//        add(descriptionLabel);

        // State 
        JLabel stateLabel = new JLabel(" " + state);
        stateLabel.setPreferredSize(new Dimension(100, 40));
        stateLabel.setMaximumSize(new Dimension(100, 40));
        if (!"".equals(iconPath.trim())){
            stateLabel.setIcon(SvgUtils.loadSvg(iconPath, 22, 22));  
        }
//        add(stateLabel);

        // Button 
        JPanel buttonContainer = new JPanel(new BorderLayout());
        buttonContainer.setOpaque(false);
        buttonContainer.setPreferredSize(new Dimension(200, 50));
        buttonContainer.setMaximumSize(new Dimension(200, 50));
        
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

        
        add(titleLabel);
        add(Box.createHorizontalStrut(10));
        add(descriptionLabel);
        add(Box.createHorizontalGlue());
        add(stateLabel);
        add(Box.createHorizontalStrut(10));
        add(buttonContainer);

    
    }
}
