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
import java.awt.geom.AffineTransform;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicTextFieldUI;

/**
 *
 * @author ely
 */
public class RoundedTextArea extends JTextArea{
    
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
    
    public RoundedTextArea(){
        this("");
    }
    
    /**
     *
     * @param placeholder
     */
    
    public RoundedTextArea(String placeholder)
    {
        this.placeholder = placeholder;
        
        setUI(new NoBackgroundTextFieldUI());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setCaretPosition(0);
        
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
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();  // fresh copy for our custom painting

        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // 1. Draw rounded background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, w, h, radius, radius);

            // 2. Draw border
            float thickness = borderWidth;
            float offset = thickness / 2f;
            g2.setStroke(new BasicStroke(thickness));
            g2.setColor(isFocusOwner() ? focusedBorderColor : borderColor);
            g2.drawRoundRect(
                Math.round(offset),
                Math.round(offset),
                w - Math.round(thickness) - 1,
                h - Math.round(thickness) - 1,
                radius,
                radius
            );

            // IMPORTANT: Now paint the ACTUAL TEXT using ORIGINAL graphics
            // We do NOT use g2 here — we pass the unmodified g from the method parameter
            super.paintComponent(g);

            // 3. Draw placeholder if needed (also on original g2, but with correct insets)
            if (getText().isEmpty() && placeholder != null && !placeholder.isEmpty()) {
                // Use a safe color fallback
                Color phColor = (placeholderColor != null) ? placeholderColor : getForeground().darker();
                g2.setColor(phColor);

                FontMetrics fm = g2.getFontMetrics(getFont());
                int textX = getInsets().left;
                // Align placeholder to top-left like real text (not centered vertically)
                int textY = getInsets().top + fm.getAscent();

                g2.drawString(placeholder, textX, textY);
            }

        } finally {
            g2.dispose();
        }
    }
    
}
