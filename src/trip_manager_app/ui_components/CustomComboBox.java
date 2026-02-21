/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

/**
 *
 * @author ely
 */
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.plaf.basic.ComboPopup;

public class CustomComboBox<T> extends JComboBox<T> {

    private Color borderColor         = new Color(20, 20, 20, 30);   
    private Color focusBorderColor    = new Color(108, 99, 255);     
    private Color backgroundColor     = Color.WHITE;
    private Color hoverBackground     = new Color(245, 245, 255);
    private Color textColor           = new Color(40, 40, 70);
    private int   cornerRadius        = 12;

    public CustomComboBox(T[] items) {
        super(items);
        initStyle();
    }

    public CustomComboBox() {
        super();
        initStyle();
    }

    private void initStyle() {
        setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setForeground(textColor);
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // extra right padding for arrow
        setPreferredSize(new Dimension(280, 40));
        setMaximumSize(new Dimension(400, 40));
        

        // Custom UI
        setUI(new BasicComboBoxUI() {
            @Override protected ComboPopup createPopup() {
                ComboPopup popup = super.createPopup();
//                popup.setBorder(BorderFactory.createLineBorder(borderColor, 1));
//                popup.setBackground(Color.WHITE);
                return popup;
            }

            @Override protected JButton createArrowButton() {
                return new ArrowButton();
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle b, boolean hasFocus) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    int w = getWidth();
                    int h = getHeight();
                    int radius = cornerRadius;
                    int stroke = 2;
                    int inset = stroke;

                    // Fill background
                    g2.setColor(getBackground());
                    g2.fillRoundRect(inset, inset, w - 2*inset, h - 2*inset, radius, radius);

                    // Draw border inside
                    g2.setColor(hasFocus ? focusBorderColor : borderColor);
                    g2.setStroke(new BasicStroke(stroke));
                    g2.drawRoundRect(inset, inset, w - 2*inset - 1, h - 2*inset - 1, radius, radius);
                } finally {
                    g2.dispose();
                }
            }
        });

        // Hover & focus effects
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                if (isEnabled()) {
                    setBackground(hoverBackground);
                }
            }
            @Override public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                repaint();
            }
            @Override public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    // Custom arrow (simple chevron)
    private class ArrowButton extends JButton {
        public ArrowButton() {
            setBorder(null);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(20, 48));
        }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            try {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int size = 10;

                int x = (w - size) / 2;
                int y = (h - size) / 2;

                g2.setColor(isEnabled() ? textColor : new Color(160, 160, 160));
                g2.setStroke(new BasicStroke(2.2f));

                // Draw chevron down
                g2.drawLine(x, y + 2, x + size/2, y + size - 2);
                g2.drawLine(x + size/2, y + size - 2, x + size, y + 2);
            } finally {
                g2.dispose();
            }
        }
    }

    // Optional setters
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    public void setAccentColor(Color color) {
        this.focusBorderColor = color;
        repaint();
    }
}