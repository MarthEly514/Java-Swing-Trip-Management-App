/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.ui_components.*;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class UserDestinationDetails extends JDialog{
    private final JFrame parentFrame;
    private int cornerRadius = 30; 
    private String content;
    private UIButton bookingBtn;
    
    public UserDestinationDetails(JFrame parentFrame, String content){
        super(parentFrame, "Destination details", true); 
        this.parentFrame = parentFrame;
        this.content = content;
        initDialog();
    }
    
    private void initDialog(){
        setUndecorated(true);
        setLayout(new BorderLayout());
        setSize(1080, 720);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();
        
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(Integer.MAX_VALUE, 360));
        top.setLayout(new OverlayLayout(top));
        
        PanelRound layer1 = new PanelRound("/home/ely/Downloads/_ - 2026-01-26T235911.754.jpeg");
        layer1.setPreferredSize(new Dimension(Integer.MAX_VALUE, 360));
        layer1.setFitWidth(true);
        
        JPanel layer2 = new JPanel();
        layer2.setBackground(new Color(20, 20, 20, 100));
        layer2.setPreferredSize(new Dimension(Integer.MAX_VALUE, 360));
        layer2.setLayout(new BorderLayout());
        
        JPanel layer2Sub1 = new JPanel();
        layer2Sub1.setOpaque(false);
        layer2Sub1.setLayout(new BorderLayout());
        layer2Sub1.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 0));
        
        JPanel layer2Sub2 = new JPanel(); 
        layer2Sub2.setOpaque(false);
        layer2Sub2.setLayout(new BorderLayout());
        layer2Sub2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel destinationName = new JLabel();
        destinationName.setText(content);
        destinationName.setFont(new Font("Segoe UI", Font.BOLD, 90));
        destinationName.setForeground(Color.white);
        
        JButton closeBtn = new JButton();
        closeBtn.setBorder(BorderFactory.createEmptyBorder());
        closeBtn.setBackground(new Color(60, 230, 0, 0));
        if (!"".equals("/trip_manager_app/ressources/icons/croix_light.svg".trim())){
            closeBtn.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/croix_light.svg", 22, 22));  
        }

        closeBtn.addActionListener(ev -> dispose());
        
        layer2Sub1.add(destinationName, BorderLayout.SOUTH);
        layer2Sub2.add(closeBtn, BorderLayout.NORTH);
        layer2.add(layer2Sub1, BorderLayout.WEST);
        layer2.add(layer2Sub2, BorderLayout.EAST);
        
        
        top.add(layer2);
        top.add(layer1);
        
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.white);
        bottom.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
        JScrollPane scrollWrapper = new JScrollPane(bottom);
        scrollWrapper.setBackground(Color.white);
        scrollWrapper.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollBar scrollBar = scrollWrapper.getVerticalScrollBar();
        
        // customizing the scrollbar aspect to match the design
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void paintThumb(Graphics grphcs, JComponent c, Rectangle thumbBounds){
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(thumbColor);
               g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = new Color(101, 93, 235, 40);
                this.trackColor = Color.white;
                this.scrollBarWidth = 10;
            }
        });
        
        SubtitleLabel subtitle1 = new SubtitleLabel("Description");
        
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));    
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        
        JLabel descriptionText = new JLabel("<html><body style='width: 300px'>Lorem ipsum dolor sit amet consectetur...Lorem ipsum dolor sit amet consectetur...Lorem ipsum dolor sit amet consectetur...</body></html>");
        
        Font font = new Font("SansSerif", Font.PLAIN, 16);
        descriptionText.setFont(font);
        
        descriptionPanel.add(descriptionText);

        
        SubtitleLabel subtitle2 = new SubtitleLabel("Moyens de transport disponibles: ");
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));    
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));
        
        ButtonGroup radioGroup = new ButtonGroup();
        
        String[] transports = {
            "AirBus 747 - 16 place(s) disponible(s)",
            "Boeing 237 - 08 place(s) disponible(s)",
            "Yatch T500 - 12 place(s) disponible(s)",
            "Boeing 237 - 08 place(s) disponible(s)", 
        };
        
        for (String transport : transports) {
            RadioButton option = new RadioButton(transport);
            option.setOpaque(false);
            radioGroup.add(option);
            optionsPanel.add(option);
            
        }
        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 40));
        buttonContainer.setOpaque(false);
        bookingBtn = new UIButton(
            "Reserver mon voyage",
            "", 
            new Color(101, 93, 235, 190), 
            new Color(255, 255, 255)      
        );
        bookingBtn.setHorizontalAlignment(SwingConstants.CENTER);
        bookingBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                bookingBtn.setBackground(new Color(101, 93, 235, 250));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                bookingBtn.setBackground(new Color(101, 93, 235, 190));            
            }
        });
        
        buttonContainer.add(bookingBtn, BorderLayout.EAST);
        subtitle1.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle2.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionText.setAlignmentX(Component.LEFT_ALIGNMENT); 
        buttonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        bottom.add(subtitle1);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(descriptionPanel);
        bottom.add(Box.createVerticalStrut(20));
        bottom.add(subtitle2);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(optionsPanel);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(buttonContainer);
        bottom.add(Box.createVerticalStrut(20));

        add(top, BorderLayout.NORTH);
        add(scrollWrapper, BorderLayout.CENTER);

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