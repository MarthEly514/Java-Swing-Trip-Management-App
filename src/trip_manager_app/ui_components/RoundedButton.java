/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

/**
 *
 * @author ely
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author ely
 */

public class RoundedButton extends JButton
{

    /**
     *
     * @return
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public Color getBorderColor() {
        return borderColor;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getBorderWidth() {
        return borderWidth;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = Math.max(1, borderWidth);
        repaint();
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRadius() {
        return radius;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRadius(int radius) {
        this.radius = Math.max(0, radius);
        repaint();
    }
    
   private int radius = 30;
   private int borderWidth = 2;
   private Color borderColor = Color.LIGHT_GRAY;
   public RoundedButton()
   {
       this("");
   }
   
   public RoundedButton(String text)
   {
       super(text);
       setOpaque(false);  
       setContentAreaFilled(false);
       setFocusPainted(false);
       setContentAreaFilled(false);
       setBorderPainted(true);
       setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));   

   }
     
   @Override
   protected void paintComponent(Graphics g){
       int w = getWidth();
       int h = getHeight();
       int r = getRadius();
       
       Graphics2D g2 = (Graphics2D) g.create();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
       g2.setColor(getBackground());
       g2.fillRoundRect(0, 0, w, h, r, r);
       
       super.paintComponent(g2);
       g2.dispose();
   }
   @Override
    protected void paintBorder(Graphics g){ 
       int r = getRadius();
       if (borderWidth <= 0 || borderColor == null || borderColor.getAlpha() == 0) {
            return; 
       }
       
       Graphics2D g2 = (Graphics2D) g.create();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
       g2.setStroke(new java.awt.BasicStroke(borderWidth));
       g2.setColor(borderColor);
       
       float thickness = borderWidth;
       float offset = thickness / 2f;
       
       int x = (int) Math.ceil(offset);
       int y = (int) Math.ceil(offset);
       int w = getWidth()  - (int) Math.ceil(thickness) - 1;
       int h = getHeight() - (int) Math.ceil(thickness) - 1;

        g2.drawRoundRect(x, y, w, h, r, r);
        
       g2.dispose();
   }
}