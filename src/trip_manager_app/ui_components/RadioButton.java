/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class RadioButton extends JRadioButton{
    
    public RadioButton(String text){
        super(text);
        initComponent();
    }
    public RadioButton(String text, Color color){
        super(text);
        initComponent();
        setForeground(color);
    }
    
    private void initComponent(){
        setOpaque(false);
        setFocusPainted(false);
        setForeground(new Color(189, 189, 189));
        Image rawUncheckedIcon;
        ImageIcon uncheckedIcon;
        Image rawCheckedIcon;
        ImageIcon checkedIcon;
        try{
            rawUncheckedIcon = ImageIO.read(getClass().getResource("/trip_manager_app/ressources/icons/check_box_outline_blank.png"));
            uncheckedIcon = new ImageIcon(rawUncheckedIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            rawCheckedIcon = ImageIO.read(getClass().getResource("/trip_manager_app/ressources/icons/select_check_box.png"));
            checkedIcon = new ImageIcon(rawCheckedIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            setIcon(uncheckedIcon);
            setSelectedIcon(checkedIcon);
        }catch(IOException ex){
        }
        
    }
    
}
