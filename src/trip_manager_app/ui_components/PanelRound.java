/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

/**
 *
 * @author ely
 */

import java.awt.AlphaComposite;
import java.awt.Color;
//import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.JPanel;

/**
 *
 * @author ely
 */
public class PanelRound extends JPanel{

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
     * @return the roundTopLeft
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    /**
     * @param roundTopLeft the roundTopLeft to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    /**
     * @return the roundTopRight
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundTopRight() {
        return roundTopRight;
    }

    /**
     * @param roundTopRight the roundTopRight to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    /**
     * @return the roundBottomLeft
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    /**
     * @param roundBottomLeft the roundBottomLeft to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    /**
     * @return the roundBottomRight
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    /**
     * @param roundBottomRight the roundBottomRight to set
     */
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public Color getGradientStartColor() {
        return gradientStartColor;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setGradientStartColor(Color c) {
        this.gradientStartColor = c != null ? c : Color.WHITE;
        repaint();
}

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public Color getGradientEndColor() {
        return gradientEndColor;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setGradientEndColor(Color c) {
        this.gradientEndColor = c != null ? c : Color.GRAY;
        repaint();
    }
    
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
    
    private int roundTopLeft = 0;    
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
    private int borderRadius = 0;
    private int borderWidth = 2;
    private Color borderColor = Color.LIGHT_GRAY;
    private Color gradientStartColor;
    private Color gradientEndColor;                  
    
    public PanelRound()
    {
        setOpaque(false);
    }    
        /*GradientPaint gradient = new GradientPaint(
        0, 0, gradientStartColor != null ? gradientStartColor: new Color(120, 200, 255),
        getWidth(), getHeight(), gradientEndColor != null ? gradientEndColor : new Color(30, 80, 180)
        );

        if (gradientStartColor != null && gradientEndColor != null )
        {
            g2.setPaint(gradient);
        } 
        else{ 
            g2.setColor(getBackground());
        }*/
        
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            
            Area area = new Area(createRoundTopLeft());
            if (roundTopRight  > 0) area.intersect(new Area(createRoundTopRight()));
            if (roundBottomLeft > 0) area.intersect(new Area(createRoundBottomLeft()));
            if (roundBottomRight > 0) area.intersect(new Area(createRoundBottomRight()));

            g2.setColor(getBackground());
            g2.fill(area);

        } finally {
            g2.dispose();
        }

        super.paintComponent(grphcs);
    }

    /*@Override
    protected void paintBorder(Graphics g) {
        if (borderWidth < 1 || borderColor == null || borderColor.getAlpha() == 0) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            float thickness = borderWidth;
            float offset     = thickness / 2f;

            g2.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(borderColor);

            Area area = new Area(createRoundTopLeft());
            if (roundTopRight   > 0) area.intersect(new Area(createRoundTopRight()));
            if (roundBottomLeft > 0) area.intersect(new Area(createRoundBottomLeft()));
            if (roundBottomRight > 0) area.intersect(new Area(createRoundBottomRight()));

            area.subtract(new Area(
                new RoundRectangle2D.Double(
                    offset, offset,
                    getWidth()  + thickness,
                    getHeight() + thickness,
                    0, 0
                )
            ));

            g2.draw(area);

        } finally {
            g2.dispose();
        }
    }*/
    
    private Shape createRoundTopLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY/2,  width, height-roundY/2)));      
        return area;     
    }
    
    private Shape createRoundTopRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width-roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY/2,  width, height-roundY/2)));      
        return area;     
    }
    
    private Shape createRoundBottomLeft(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0,  width, height-roundY/2)));      
        return area;     
    }
        
    private Shape createRoundBottomRight(){
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width-roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0,  width, height-roundY/2)));      
        return area;     
    }
    
}