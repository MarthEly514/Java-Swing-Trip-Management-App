/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

/**
 *
 * @author ely
 */


public class SearchField extends JPanel{
    private RoundedTextField field;
    private JButton button;

    /**
     * @return the borderRadius
     */
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getBorderRadius() {
        return borderRadius;
    }

    /**
     * @param borderRadius the borderRadius to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    /**
     * @return the borderWidth
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getBorderWidth() {
        return borderWidth;
    }

    /**
     * @param borderWidth the borderWidth to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @return the borderColor
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * @param borderColor the borderColor to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    private int borderRadius = 40;
    private int borderWidth = 2;
    private Color borderColor = Color.LIGHT_GRAY;

    public SearchField(){
        setOpaque(false);
        setFocusable(true);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 40));
        setMaximumSize(new Dimension(300, 40));
        setMinimumSize(new Dimension(300, 0));     
        setForeground(new Color(100, 100, 100));
        setBackground(new Color(161, 117, 255, 30));
        
        //textField
        field = new RoundedTextField();
        field.setBackground(new Color(0, 0, 0, 0));
        field.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 0));
        field.setPlaceholder("Rechercher...");
        field.setPlaceholderColor(borderColor);
        field.setBorderColor(new Color(0, 0, 0, 0));
        field.setBorderWidth(borderWidth);
        field.setFocusedBorderColor(new Color(0, 0, 0, 0));
        field.setHorizontalAlignment(javax.swing.JTextField.LEADING);
        
        //serachButton
        ImageIcon icon = new ImageIcon(getClass().getResource("/trip_manager_app/ressources/icons/searchIcon.png").getFile());
        button = new JButton(icon);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setOpaque(false);
        button.setBackground(new Color(0,0,0,0));
        button.setFocusPainted(false);
        
        
        add(field, BorderLayout.CENTER);
        add(button, BorderLayout.EAST);

    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);           
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getBorderRadius(), getBorderRadius());

        } finally {
            g2.dispose();
        }

        super.paintComponent(grphcs);
    }
    
    @Override 
    protected void paintBorder(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBorderColor());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getBorderRadius(), getBorderRadius());
    }
}
