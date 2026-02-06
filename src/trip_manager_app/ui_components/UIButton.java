/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.SwingConstants;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class UIButton extends RoundedButton{
    public UIButton(String text, String iconPath, Color backgroundColor, Color foregroundColor){
        //button

        setText(text);
        setPreferredSize(new Dimension(250, 50));
        setRadius(50);
        setBorderWidth(0);
        setBorderColor(new Color(0, 0, 0, 0));
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setFont(new Font("SansSerif", Font.BOLD, 16));
        if (!"".equals(iconPath.trim())){
            setIcon(SvgUtils.loadSvg(iconPath, 22, 22));  
        }
        setHorizontalAlignment(SwingConstants.LEFT);
        setMargin(new Insets(0, 10, 0, 0));
    }

    
}
