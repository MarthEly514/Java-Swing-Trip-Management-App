/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTextFieldUI;

/**
 *
 * @author ely
 */
public class RoundedTextField extends JTextField{
    

// ... inside your RoundedTextField class ...

    private static class NoBackgroundTextFieldUI extends BasicTextFieldUI {
        @Override
        protected void paintBackground(Graphics g) {
            // Do NOTHING → prevent the rectangular background fill
        }
    }

    /**
     * @return the placeholderColor
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public Color getPlaceholderColor() {
        return placeholderColor;
    }

    /**
     * @param placeholderColor the placeholderColor to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
    }


    /**
     * @return the placeholder
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * @param placeholder the placeholder to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    /**
     * @return the radius
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
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
        repaint();
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
        repaint();
    }

    /**
     * @return the focusedBorderColor
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public Color getFocusedBorderColor() {
        return focusedBorderColor;
    }

    /**
     * @param focusedBorderColor the focusedBorderColor to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setFocusedBorderColor(Color focusedBorderColor) {
        this.focusedBorderColor = focusedBorderColor;
        repaint();
    }
    
    
    private String placeholder;
    private int radius = 20;
    private int borderWidth = 2;
    private Color borderColor = Color.GRAY;
    private Color focusedBorderColor = Color.BLUE;
    private Color placeholderColor;
    
    public RoundedTextField(){
        this("", 0);
    }
    
    /**
     *
     * @param placeholder
     * @param columns
     */
    
    public RoundedTextField(String placeholder, int columns)
    {
        super(columns);
        this.placeholder = placeholder;
        
        setUI(new NoBackgroundTextFieldUI());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 12));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        
        //change border color on focus
        addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e){
                repaint();
            }
            
            @Override
            public void focusLost(FocusEvent e){
                repaint();
            }
        });
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void update(Graphics g) {
        // Prevent default rectangular background fill
        paint(g);  
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g.create();
        
        try{
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int w = getWidth();
            int h = getHeight();
            
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, w, h, radius, radius);
            
            float thickness = borderWidth;
            float offset = thickness / 2f;
            g2.setStroke(new BasicStroke(thickness));
            g2.setColor(isFocusOwner()? focusedBorderColor : borderColor);
            g2.drawRoundRect(
                    (int) Math.ceil(offset), 
                    (int) Math.ceil(offset), 
                    w - (int) Math.ceil(thickness) - 1, 
                    h - (int) Math.ceil(thickness) - 1, 
                    radius, 
                    radius
            );
            
            super.paintComponent(g2);
            
            //placeholder drawing
            if(getText().isEmpty())
            {
                g2.setColor(getPlaceholderColor());
                //g2.setFont(getFont().deriveFont(Font.ITALIC));
                
                FontMetrics fm = g2.getFontMetrics();
                int x = getInsets().left;
                int y = (h + fm.getAscent() - fm.getDescent()) / 2;
                
                g2.drawString(placeholder, x, y);
            }
        }finally{
            g2.dispose();
        }
    
    }
}
