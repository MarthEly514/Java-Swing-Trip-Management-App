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

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @param iconPath the iconPath to set
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
    private String text;
    private String iconPath;
    
    public UIButton(String text, String iconPath, Color backgroundColor, Color foregroundColor){
        //button

        setText(text);
        this.text = text;
        this.iconPath = iconPath;
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
