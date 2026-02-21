/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ely
 */
public class CustomPicker extends JPanel {

    private final JTextField numberField;
    private final JButton minusBtn;
    private final JButton plusBtn;

    private int minValue = 1;
    private int maxValue = 200;
    private int currentValue = 12;
    private int step = 1;

    private Color borderColor = new Color(20, 20, 20, 30);
    private Color focusBorderColor = new Color(108, 99, 255);
    private int cornerRadius = 16;
    private final SubtitleLabel title;

    public CustomPicker(String labelText, int initialValue) {
        this(labelText, initialValue, 1, 200, 1);
    }

    public CustomPicker(String labelText, int initialValue, int min, int max, int step) {
        this.minValue = min;
        this.maxValue = max;
        this.step = step;
        this.currentValue = Math.max(min, Math.min(max, initialValue));

        setLayout(new BorderLayout(0, 8));
        setOpaque(false);

        title = new SubtitleLabel(labelText); // required
        title.setFontSize(14);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        add(title, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new BorderLayout(0, 0));
        inputPanel.setOpaque(false);

        // Number display field
        numberField = new JTextField(String.valueOf(currentValue));
        numberField.setFont(new Font("Segoe UI", Font.BOLD, 16));
        numberField.setHorizontalAlignment(SwingConstants.CENTER);
        numberField.setEditable(false);
        numberField.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        numberField.setBackground(Color.WHITE);
        numberField.setForeground(new Color(40, 40, 70));
        numberField.setPreferredSize(new Dimension(0, 48));
        numberField.setPreferredSize(new Dimension(80, 48));

        numberField.setUI(new javax.swing.plaf.basic.BasicTextFieldUI() {
            @Override
            protected void paintBackground(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    int w = numberField.getWidth();
                    int h = numberField.getHeight();

                    // Background
                    g2.setColor(numberField.getBackground());
                    g2.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);

                    // Border (3px wide when focused/idle)
                    g2.setColor(numberField.hasFocus() ? focusBorderColor : borderColor);
                    g2.setStroke(new BasicStroke(2.0f));
                    g2.drawRoundRect(1, 1, w - 2, h - 2, cornerRadius, cornerRadius);
                } finally {
                    g2.dispose();
                }
            }
        });

        minusBtn = createControlButton("−");
        minusBtn.addActionListener(e -> decrement());

        plusBtn = createControlButton("+");
        plusBtn.addActionListener(e -> increment());

        addHoverEffect(minusBtn);
        addHoverEffect(plusBtn);

        JPanel controls = new JPanel(new BorderLayout(5,0));
        controls.setOpaque(false);
        controls.add(minusBtn, BorderLayout.WEST);
        controls.add(numberField, BorderLayout.CENTER);
        controls.add(plusBtn, BorderLayout.EAST);

        add(controls, BorderLayout.CENTER);
    }

    private JButton createControlButton(String symbol) {
        JButton btn = new JButton(symbol);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(101, 93, 235));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setPreferredSize(new Dimension(48, 48));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                try {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(btn.getBackground());
                    g2.fillRoundRect(0, 0, btn.getWidth(), btn.getHeight(), 16, 16);
                    super.paint(g2, c);
                } finally {
                    g2.dispose();
                }
            }
        });

        return btn;
    }

    private void addHoverEffect(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(88, 79, 235));
            }
            @Override public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(101, 93, 235));
            }
        });
    }

    private void increment() {
        if (currentValue + step <= maxValue) {
            currentValue += step;
            numberField.setText(String.valueOf(currentValue));
        }
    }

    private void decrement() {
        if (currentValue - step >= minValue) {
            currentValue -= step;
            numberField.setText(String.valueOf(currentValue));
        }
    }

    public int getValue() {
        return currentValue;
    }

    public void setValue(int value) {
        currentValue = Math.max(minValue, Math.min(maxValue, value));
        numberField.setText(String.valueOf(currentValue));
    }
}
