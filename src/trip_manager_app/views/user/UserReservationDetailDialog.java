/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import trip_manager_app.ui_components.PanelRound;

/**
 *
 * @author ely
 */
public class UserReservationDetailDialog extends JDialog{
    private final JFrame parentFrame;
    private int cornerRadius = 30;  
    
    public UserReservationDetailDialog(JFrame parentFrame, String content){
        super(parentFrame, "My Nigga", true); 
        this.parentFrame = parentFrame;
        initDialog(content);
    }
    
    private void initDialog(String content){
        setUndecorated(true);
        //setLayout(new BorderLayout(10, 10));
        setSize(680, 760);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();

        // Content
        PanelRound mainPanel = createPanel(content, "4,5");
        add(mainPanel);
        //add(bottom);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }
    
    private PanelRound createPanel(String destination, String rating){
        int radius = 30;
        PanelRound mainPanel = new PanelRound();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.pink);
        mainPanel.setRoundBottomLeft(radius);
        mainPanel.setRoundBottomRight(radius);
        mainPanel.setRoundTopLeft(radius);
        mainPanel.setRoundTopRight(radius);
        Dimension size = new Dimension(680, 660);
        mainPanel.setPreferredSize(size);
        mainPanel.setMaximumSize(size);
        mainPanel.setMinimumSize(size);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(250, 100));
        topPanel.setMaximumSize(new Dimension(250, 100));
        topPanel.setMinimumSize(new Dimension(250, 100));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton closeBtn = new JButton("X");
        closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeBtn.setForeground(Color.RED);
        closeBtn.setFocusPainted(false);
        closeBtn.setOpaque(false);

        closeBtn.addActionListener(ev -> dispose());

        topPanel.add(closeBtn, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
 
        PanelRound bottomPanel = new PanelRound();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(250, 100));
        bottomPanel.setMaximumSize(new Dimension(250, 100));
        bottomPanel.setMinimumSize(new Dimension(250, 100));
        bottomPanel.setBackground(new Color(20, 20, 20, 50));
        bottomPanel.setBlurred(true);
        bottomPanel.setBlurRadius(8f);
        bottomPanel.setRoundBottomLeft(radius);
        bottomPanel.setRoundBottomRight(radius);
//        bottomPanel.setRoundTopLeft(radius);
        bottomPanel.setRoundTopRight(radius);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        JLabel destinationLabel = new JLabel(destination);
        destinationLabel.setForeground(Color.white);
        destinationLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JLabel ratingLabel = new JLabel("<html>Note:<span style='color:#fff009'>&nbsp;&nbsp;&nbsp;"+rating+"</span></html>");
        ratingLabel.setForeground(Color.white);
        ratingLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        bottomPanel.add(destinationLabel, BorderLayout.NORTH);
        bottomPanel.add(ratingLabel, BorderLayout.SOUTH);
        
        return mainPanel;
    }
    
    public void showDialog(){
        setVisible(true); 
    }
    
    private void applyRoundedShape() {
        SwingUtilities.invokeLater(() -> {
            int w = getWidth();
            int h = getHeight();
            if (w > 0 && h > 0) {
                setShape(new RoundRectangle2D.Double(0, 0, w, h, cornerRadius, cornerRadius));
            }
        });
    }
}
