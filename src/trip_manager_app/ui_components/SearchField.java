/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;
import javax.swing.*;

/**
 *
 * @author ely
 */


public class SearchField extends JPanel{
    private RoundedTextField field;
    private JLabel searchIcon;

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
    private int delay = 500;
    private Timer timer;
    private Color borderColor = Color.LIGHT_GRAY;

    public SearchField(Consumer <String> execFunc){
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
        searchIcon = new JLabel(icon);
        searchIcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchIcon.setOpaque(false);
        searchIcon.setBackground(new Color(0,0,0,0));
        
        //listening on the typed keys inside the text Field
        field.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(timer != null && timer.isRunning()){
                    timer.stop();
                }
                
                timer = new Timer(delay, e1 ->{
                    String searchText = field.getText();
                    SwingWorker<String, Void> worker = new SwingWorker <String, Void>(){
                        @Override
                        protected String doInBackground() throws Exception {
                            execFunc.accept(searchText);
                            return searchText;
                        }
                        @Override
                        protected void done(){
                            
                        }
                        
                    };
                    worker.execute();
                });
                timer.setRepeats(false);
                timer.start();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        
        });
        
        
        add(field, BorderLayout.CENTER);
        add(searchIcon, BorderLayout.EAST);

    }
    
    public String getSearchQuery(){
        return field.getText().trim();
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
