/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author ely
 */
public class UserDestinationDetails extends JDialog{
    private final JFrame parentFrame;
    private int cornerRadius = 30; 
    private String content;
    
    public UserDestinationDetails(JFrame parentFrame, String content){
        super(parentFrame, "Destination details", true); 
        this.parentFrame = parentFrame;
        this.content = content;
        initDialog();
    }
    
    private void initDialog(){
        setUndecorated(true);
        setLayout(new BorderLayout(10, 10));
        setSize(450, 320);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();

        // Content
        JLabel message = new JLabel(content, SwingConstants.CENTER);
        message.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(message, BorderLayout.CENTER);

        JButton closeBtn = new JButton("X  Close");
        closeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeBtn.setForeground(Color.RED);
        closeBtn.setFocusPainted(false);

        closeBtn.addActionListener(ev -> dispose());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        bottom.add(closeBtn);
        add(bottom, BorderLayout.SOUTH);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

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