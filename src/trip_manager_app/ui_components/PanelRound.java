package trip_manager_app.ui_components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanelRound extends JPanel {

    private int roundTopLeft = 0;    
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
    private int borderRadius = 0;
    private int borderWidth = 2;
    private BufferedImage backgroundImage;
    private Color borderColor = Color.LIGHT_GRAY;
    private Color gradientStartColor;
    private Color gradientEndColor; 
    private boolean scaleToFit = false;
    private boolean blurred = false;
    private float blurRadius = 10f;
    private float opacity = 0.3f;
    private BufferedImage cachedBlur = null;
    private boolean isCapturing = false; // Prevent infinite recursion
    
    public PanelRound() {
        setOpaque(false);
    }  
    
    public PanelRound(String imagePath) {
        setOpaque(false);
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public PanelRound(boolean blurred) {
        setOpaque(false);
        this.blurred = blurred;
    }
    
    public PanelRound(String imagePath, boolean blurred) {
        setOpaque(false);
        this.blurred = blurred;
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public boolean isBlurred() {
        return blurred;
    }
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBlurred(boolean blurred) {
        this.blurred = blurred;
        cachedBlur = null; // Clear cache
        repaint();
    }
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public float getBlurRadius() {
        return blurRadius;
    }
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBlurRadius(float blurRadius) {
        this.blurRadius = Math.max(1f, Math.min(50f, blurRadius));
        cachedBlur = null; // Clear cache
        repaint();
    }
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public float getOpacity() {
        return opacity;
    }
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setOpacity(float opacity) {
        this.opacity = Math.max(0f, Math.min(1f, opacity));
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (blurred && !isCapturing) {
            paintBackdropBlur(g);
        } else {
            paintNormal(g);
        }
        
        super.paintComponent(g);
    }
    
    private void paintBackdropBlur(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            Shape area = createRoundedShape();
            g2.setClip(area);
            
            BufferedImage background = captureBackgroundSafe();
            
            if (background != null) {
                BufferedImage blurredBg = applyGaussianBlur(background, blurRadius);
                
                g2.drawImage(blurredBg, 0, 0, null);
                
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                g2.setColor(getBackground());
                g2.fill(area);
            } else {
                g2.setColor(getBackground());
                g2.fill(area);
            }
            
        } catch (Exception e) {
            paintNormal(g);
        } finally {
            g2.dispose();
        }
    }
    
    private BufferedImage captureBackgroundSafe() {
        try {
            if (isCapturing) return null; 
            if (getParent() == null) return null;
            
            isCapturing = true;
            
            int width = getWidth();
            int height = getHeight();
            
            if (width <= 0 || height <= 0) return null;
            
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = img.createGraphics();
            
            // Get bounds
            Rectangle bounds = getBounds();
            
            // Translate and clip
            g2.translate(-bounds.x, -bounds.y);
            g2.setClip(bounds.x, bounds.y, width, height);
            
            Component parent = getParent();
            if (parent instanceof JComponent) {
                boolean wasVisible = isVisible();
                setVisible(false);
                
                parent.paint(g2);
                
                setVisible(wasVisible);
            }
            
            g2.dispose();
            isCapturing = false; 
            
            return img;
            
        } catch (Exception e) {
            isCapturing = false;
            return null;
        }
    }
    
    private void paintNormal(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape area = createRoundedShape();

            g2.setColor(getBackground());
            g2.fill(area);
            g2.setClip(area);
            
            if (backgroundImage != null) {
                drawBackgroundImage(g2);
            }

        } finally {
            g2.dispose();
        }
    }
    
    private BufferedImage applyGaussianBlur(BufferedImage source, float radius) {
        try {
            int size = (int) Math.ceil(radius);
            if (size % 2 == 0) size++;
            if (size < 3) size = 3;
            
            float[] matrix = createGaussianKernel(size, radius);
            
            BufferedImage temp = new BufferedImage(
                source.getWidth(), 
                source.getHeight(), 
                BufferedImage.TYPE_INT_RGB
            );
            
            BufferedImage result = new BufferedImage(
                source.getWidth(), 
                source.getHeight(), 
                BufferedImage.TYPE_INT_RGB
            );
            
            // Horizontal blur
            Kernel kernelH = new Kernel(size, 1, matrix);
            ConvolveOp opH = new ConvolveOp(kernelH, ConvolveOp.EDGE_NO_OP, null);
            opH.filter(source, temp);
            
            // Vertical blur
            Kernel kernelV = new Kernel(1, size, matrix);
            ConvolveOp opV = new ConvolveOp(kernelV, ConvolveOp.EDGE_NO_OP, null);
            opV.filter(temp, result);
            
            return result;
        } catch (Exception e) {
            return source; // Return original if blur fails
        }
    }
    
    private float[] createGaussianKernel(int size, float radius) {
        float[] kernel = new float[size];
        float sigma = radius / 3f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        int middle = size / 2;
        
        for (int i = 0; i < size; i++) {
            float distance = i - middle;
            kernel[i] = (float) Math.exp(-distance * distance / twoSigmaSquare) / sigmaRoot;
            total += kernel[i];
        }
        
        // Normalize
        for (int i = 0; i < size; i++) {
            kernel[i] /= total;
        }
        
        return kernel;
    }
    
    private void drawBackgroundImage(Graphics2D g2) {
        if (scaleToFit) {
            double panelRatio = (double) getWidth() / getHeight();
            double imageRatio = (double) backgroundImage.getWidth() / backgroundImage.getHeight();

            int drawWidth, drawHeight, x = 0, y = 0;

            if (panelRatio > imageRatio) {
                drawHeight = getHeight();
                drawWidth = (int) (drawHeight * imageRatio);
                x = (getWidth() - drawWidth) / 2;
            } else {
                drawWidth = getWidth();
                drawHeight = (int) (drawWidth / imageRatio);
                y = (getHeight() - drawHeight) / 2;
            }

            g2.drawImage(backgroundImage, x, y, drawWidth, drawHeight, this);
        } else {
            int x = (getWidth() - backgroundImage.getWidth()) / 2;
            int y = (getHeight() - backgroundImage.getHeight()) / 2;
            g2.drawImage(backgroundImage, x, y, this);
        }
    }
    
    // All your existing getters/setters...
    
    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getBorderRadius() {
        return borderRadius;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundTopRight() {
        return roundTopRight;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    @java.beans.BeanProperty(preferred = true, visualUpdate = true)
    public int getRoundBottomRight() {
        return roundBottomRight;
    }

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
    
    private Shape createRoundedShape() {
        Area area = new Area(createRoundTopLeft());
        if (roundTopRight > 0) area.intersect(new Area(createRoundTopRight()));
        if (roundBottomLeft > 0) area.intersect(new Area(createRoundBottomLeft()));
        if (roundBottomRight > 0) area.intersect(new Area(createRoundBottomRight()));
        return area;
    }
    
    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY/2,  width, height-roundY/2)));      
        return area;     
    }
    
    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width-roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY/2,  width, height-roundY/2)));      
        return area;     
    }
    
    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0,  width, height-roundY/2)));      
        return area;     
    }
        
    private Shape createRoundBottomRight() {
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