
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components.renderers;

import trip_manager_app.models.ClientModel;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ely
 */
public class ClientsCellRenderer extends JPanel implements ListCellRenderer<ClientModel> {

    private static final int ARC = 30;           
    private static final int INSET = 8;        

    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel eMailLabel;
    private JLabel phoneLabel;

    public ClientsCellRenderer() {
        setLayout(new BorderLayout(15, 0));
        setOpaque(false);                       
        setBorder(BorderFactory.createEmptyBorder(INSET, INSET + 10, INSET, INSET + 10));

        // Left side
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        firstNameLabel = new JLabel();
        firstNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        lastNameLabel = new JLabel();
        lastNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        eMailLabel = new JLabel();
        eMailLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        eMailLabel.setForeground(new Color(120, 120, 120));

        leftPanel.add(firstNameLabel);
        leftPanel.add(Box.createVerticalStrut(4));
        leftPanel.add(lastNameLabel);
        leftPanel.add(Box.createVerticalStrut(4));
        leftPanel.add(eMailLabel);

        // Right side
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setOpaque(false);

        phoneLabel = new JLabel();
        phoneLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        phoneLabel.setForeground(new Color(108, 99, 255));

        rightPanel.add(phoneLabel);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, w, h, ARC, ARC);

        // Optional: subtle border
        // g2.setColor(new Color(220, 220, 230));
        // g2.setStroke(new BasicStroke(1f));
        // g2.drawRoundRect(0, 0, w-1, h-1, ARC, ARC);

        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends ClientModel> list,
            ClientModel value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        firstNameLabel.setText(value.getPrenom());
        lastNameLabel.setText(value.getNom());
        eMailLabel.setText(value.getEmail());
        phoneLabel.setText(value.getTelephone());

        if (isSelected) {
            setBackground(new Color(101, 93, 235, 20));    
        } else {
            setBackground(index % 2 == 0 
                ? new Color(250, 250, 255) 
                : new Color(245, 245, 250));
            setBackground(new Color(0, 0, 0, 0));

        }

        return this;
    }
}

