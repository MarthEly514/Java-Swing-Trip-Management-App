/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class NavBarHorizontal extends JPanel{
    private List<String> options;
    private List<JLabel> optionLabels;
    private final Consumer<String> execAction;
    private String active;
    public NavBarHorizontal(List<String> options, Consumer<String> execAction){
        setOpaque(false);
        this.options = options;
        this.optionLabels = new ArrayList<>();
        this.active = options.getFirst();
        this.execAction = execAction;
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 60));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        // labels rendering
        options.forEach(option->createOptions(option));
        optionLabels.forEach(optionLabel->setFocusedOption(optionLabel));
    }
    
    private void createOptions(String option){
        JLabel optionLabel = new JLabel(option);
        optionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        optionLabel.setForeground(new Color(0,0,0,60));
        optionLabel.setName(option);
        
        optionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                if(!optionLabel.getText().equals(active)){              
                    optionLabel.setForeground(new Color(101, 93, 235, 100));            
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                if(!optionLabel.getText().equals(active)){       
                    optionLabel.setForeground(new Color(0,0,0,60));       
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                if(!active.equals(optionLabel.getText())){
                    setActive(optionLabel.getText());
                    optionLabels.forEach(optionLabel->setFocusedOption(optionLabel));
                    execAction.accept(optionLabel.getName());          
                }
                
            }
        });
        
        
       
        add(optionLabel);
        optionLabels.add(optionLabel);
    }

    private void setFocusedOption(JLabel optionLabel) {
        if(optionLabel.getText().equals(active)){       
            optionLabel.setForeground(new Color(108, 99, 255, 195));
        }
        else{
            optionLabel.setForeground(new Color(0,0,0,60));       
        }
    }

    /**
     * @return the active
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public String getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setActive(String active) {
        this.active = active;
    }

    
}
