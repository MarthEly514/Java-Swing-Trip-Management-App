/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.SwingUtilities;
import trip_manager_app.DAO.MoyenTransportDAO;
import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.ui_components.PanelRound;
import trip_manager_app.ui_components.UIButton;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class AdminTransportDetails  extends JDialog {

    private final MoyenTransportDAO transportDao;
    private final MoyenTransportModel transport;
    private final JFrame parentFrame;
    private UIButton deleteBtn;

    public AdminTransportDetails(JFrame parentFrame, MoyenTransportModel transport) {
        super(parentFrame, "Détails du Moyen de Transport", true);
        this.parentFrame = parentFrame;
        this.transport = transport;
        this.transportDao = new MoyenTransportDAO();

        initDialog();
    }

    private void initDialog() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(700, 500);
        setLocationRelativeTo(parentFrame);

        // Apply rounded shape to the window
        applyRoundedShape();

        // Main content panel
        PanelRound mainPanel = new PanelRound();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.rounded(30);
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout(0, 20));

        // Title
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(Integer.MAX_VALUE, 90));
        top.setLayout(new BorderLayout());
        top.setBackground(Color.white);
        
        JLabel title = new JLabel("Détails du Moyen de Transport");
        title.setFont(new Font("SansSerif", Font.BOLD, 34));
        title.setForeground(new Color(50, 50, 50));
        
        
        JButton closeBtn = new JButton();
        closeBtn.setBorder(BorderFactory.createEmptyBorder());
        closeBtn.setBackground(new Color(60, 230, 0, 0));
        if (!"".equals("/trip_manager_app/ressources/icons/croix_light.svg".trim())){
            closeBtn.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/croix_red.svg", 22, 22));  
        }
        closeBtn.addActionListener(ev -> dispose());
        
        JPanel closeBtnPanel = new JPanel();
        closeBtnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        closeBtnPanel.setOpaque(false);

        
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        titlePanel.setOpaque(false);
        
        closeBtnPanel.add(closeBtn);
        titlePanel.add(title);
        
        top.add(titlePanel, BorderLayout.WEST); 
        top.add(closeBtnPanel, BorderLayout.EAST);

        // Content panel
        JPanel content = new JPanel(new GridLayout(0, 2, 12, 16));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        addInfoRow(content, "Type de véhicule", transport.getTypeVehicule());
        addInfoRow(content, "Description", transport.getDescriptionVehicule());
        addInfoRow(content, "Nombre de places", String.valueOf(transport.getNombrePlaces()));
        addInfoRow(content, "Numéro du véhicule", ""+transport.getNoVehicule());

        // Delete button (bottom right)
        deleteBtn = new UIButton(
            "Supprimer ce moyen de transport",
            "/trip_manager_app/ressources/icons/trash_light.svg",
            new Color(220, 53, 69),
            Color.WHITE
        );
        deleteBtn.setRadius(32);
        deleteBtn.setPreferredSize(new Dimension(280, 52));
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));

        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                deleteBtn.setBackground(new Color(200, 35, 51));
            }
            @Override public void mouseExited(MouseEvent e) {
                deleteBtn.setBackground(new Color(220, 53, 69));
            }
        });

        deleteBtn.addActionListener(e -> confirmDeleteTransport());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(deleteBtn);

        // Assemble
        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(content, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void addInfoRow(JPanel panel, String key, String value) {
        JLabel keyLabel = new JLabel(key + " :");
        keyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        keyLabel.setForeground(new Color(100, 100, 120));
        keyLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        valueLabel.setForeground(new Color(40, 40, 70));

        panel.add(keyLabel);
        panel.add(valueLabel);
    }

    private void confirmDeleteTransport() {
        JDialog confirmDialog = new JDialog(parentFrame, true);
        confirmDialog.setUndecorated(true);
        confirmDialog.setBackground(new Color(0, 0, 0, 0));

        PanelRound confirmPanel = new PanelRound();
        confirmPanel.setBackground(new Color(250, 250, 255));
        confirmPanel.setRoundTopLeft(20);
        confirmPanel.setRoundTopRight(20);
        confirmPanel.setRoundBottomLeft(20);
        confirmPanel.setRoundBottomRight(20);
        confirmPanel.setOpaque(false);
        confirmPanel.setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));
        confirmPanel.setLayout(new BorderLayout(0, 20));

        JLabel question = new JLabel("Voulez-vous vraiment supprimer ce moyen de transport ?");
        question.setFont(new Font("Segoe UI", Font.BOLD, 17));
        question.setForeground(new Color(50, 50, 70));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        JLabel warning = new JLabel("⚠️");
        warning.setFont(new Font("Segoe UI", Font.BOLD, 48));
        warning.setForeground(new Color(255, 140, 0));
        warning.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttons.setOpaque(false);

        UIButton cancelBtn = new UIButton(
            "Annuler",
            "",
            new Color(220, 220, 230),
            new Color(80, 80, 100)
        );
        cancelBtn.setRadius(44);
        cancelBtn.setPreferredSize(new Dimension(140, 44));
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelBtn.addActionListener(e -> confirmDialog.dispose());
        cancelBtn.setHorizontalAlignment(SwingConstants.CENTER);


        UIButton deleteBtn = new UIButton(
            "Supprimer",
            "/trip_manager_app/ressources/icons/trash_light.svg",
            new Color(220, 53, 69),
            Color.WHITE
        );
        deleteBtn.setRadius(44);
        deleteBtn.setPreferredSize(new Dimension(140, 44));
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteBtn.addActionListener(e -> {
            transportDao.supprimer(transport.getNoVehicule());
            confirmDialog.dispose();
            dispose(); 
        });
        deleteBtn.setHorizontalAlignment(SwingConstants.CENTER);


        // Hover effects
        cancelBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { cancelBtn.setBackground(new Color(200, 200, 210)); }
            public void mouseExited(MouseEvent e)  { cancelBtn.setBackground(new Color(220, 220, 230)); }
        });

        deleteBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { deleteBtn.setBackground(new Color(200, 35, 51)); }
            public void mouseExited(MouseEvent e)  { deleteBtn.setBackground(new Color(220, 53, 69)); }
        });

        buttons.add(cancelBtn);
        buttons.add(deleteBtn);

        JPanel center = new JPanel(new BorderLayout(0, 16));
        center.setOpaque(false);
        center.add(warning, BorderLayout.NORTH);
        center.add(question, BorderLayout.CENTER);

        confirmPanel.add(center, BorderLayout.CENTER);
        confirmPanel.add(buttons, BorderLayout.SOUTH);

        confirmDialog.setContentPane(confirmPanel);
        confirmDialog.pack();

        // Round the confirmation dialog
        SwingUtilities.invokeLater(() -> {
            int w = confirmDialog.getWidth();
            int h = confirmDialog.getHeight();
            if (w > 0 && h > 0) {
                confirmDialog.setShape(new RoundRectangle2D.Double(0, 0, w, h, 20, 20));
            }
        });

        confirmDialog.setLocationRelativeTo(parentFrame);
        confirmDialog.setVisible(true);
    }
    
    public void showDialog(){
        setVisible(true); 
    }

    private void applyRoundedShape() {
        SwingUtilities.invokeLater(() -> {
            int w = getWidth();
            int h = getHeight();
            if (w > 0 && h > 0) {
                setShape(new RoundRectangle2D.Double(0, 0, w, h, 24, 24));
            }
        });
    }
}
