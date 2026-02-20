/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import trip_manager_app.DAO.DestinationDAO;
import trip_manager_app.DAO.MoyenTransportDAO;
import trip_manager_app.DAO.ReservationDAO;
import trip_manager_app.DAO.VoyageDAO;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.models.NewEnumReservation;
import trip_manager_app.models.ReservationModel;
import trip_manager_app.models.VoyageModel;
import trip_manager_app.ui_components.DatePickerField;
import trip_manager_app.ui_components.PanelRound;
import trip_manager_app.ui_components.RadioButton;
import trip_manager_app.ui_components.RoundedTextField;
import trip_manager_app.ui_components.ScrollWrapper;
import trip_manager_app.ui_components.SubtitleLabel;
import trip_manager_app.ui_components.UIButton;
import trip_manager_app.utils.CachedImageLoader;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class AdminDestinationDetails extends JDialog{

    private final DestinationDAO destinationDao;
    private UIButton deleteDestBtn;



    /**
     * @return the villeArrivee
     */
    public String getVille() {
        return villeArrivee;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    private final JFrame parentFrame;
    private final String villeArrivee;
    private int cornerRadius = 30; 
    private String description;
    private CachedImageLoader cachedImageLoader;
    private BufferedImage backgroundImage;
    private DestinationModel destination;
    private VoyageDAO voyageDao;
    private List<RadioButton> radioButtons = new ArrayList<>(); 



    
    public AdminDestinationDetails(JFrame parentFrame, DestinationModel destination){
        super(parentFrame, "Destination details", true); 
        this.parentFrame = parentFrame;
        this.villeArrivee = destination.getVille();
        this.destination = destination;
        this.description = destination.getDescription();
        this.destinationDao = new DestinationDAO();
        this.voyageDao = new VoyageDAO();
        cachedImageLoader = CachedImageLoader.getInstance();
        
        loadAndSetImage(destination.getImageId()); 
        initDialog();
    }
    
    private void initDialog(){
        setUndecorated(true);
        setLayout(new BorderLayout());
        setSize(880, 580);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();
        
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(Integer.MAX_VALUE, 360));
        top.setLayout(new OverlayLayout(top));
        
        PanelRound layer1 = new PanelRound(backgroundImage);
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
        layer2Sub2.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));    
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        JLabel destinationName = new JLabel();
        destinationName.setText("<html><body style='width: 400px'><span style='font-size: 60px'>"+ getVille() +"</span><span style ='font-size: 30px'>, "+destination.getPays()+"</span></body></html>");
        destinationName.setForeground(Color.white);
        
        JLabel descriptionText = new JLabel("<html><body style='width: 400px'>"+ getDescription() +"</body></html>");
        Font font = new Font("SansSerif", Font.PLAIN, 16);
        descriptionText.setFont(font);
        descriptionText.setForeground(new Color(255, 255, 255, 100));
        
        descriptionPanel.add(destinationName);
        descriptionPanel.add(Box.createVerticalStrut(5));
        descriptionPanel.add(descriptionText);
        
        JButton closeBtn = new JButton();
        closeBtn.setBorder(BorderFactory.createEmptyBorder());
        closeBtn.setBackground(new Color(60, 230, 0, 0));
        if (!"".equals("/trip_manager_app/ressources/icons/croix_light.svg".trim())){
            closeBtn.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/croix_light.svg", 22, 22));  
        }

        closeBtn.addActionListener(ev -> dispose());
        
        layer2Sub1.add(descriptionPanel, BorderLayout.SOUTH);
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
        
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottom);
        scrollWrapper.setBackground(Color.white);
        scrollWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        SubtitleLabel subtitle1 = new SubtitleLabel("<html><body>Note: &nbsp;&nbsp;&nbsp;"+"<span style='font-size:16px; color:#F9DB78'>"+destination.getNote()+"</span></body></html>");
        subtitle1.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 10));
        
        SubtitleLabel subtitle2 = new SubtitleLabel("<html><body>Nombre de reservations faites: : &nbsp;&nbsp;&nbsp;"+"<span style='font-size:16px'>"+voyageDao.getVoyageCountByDestination(destination.getVille())+"</span></body></html>");
        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.setPreferredSize(new Dimension(600, 70));
        buttonContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonContainer.setOpaque(false);
        deleteDestBtn = new UIButton(
                "Supprimer destination",
                "/trip_manager_app/ressources/icons/trash_light.svg",
                new Color(255, 100, 100),
                new Color(255, 255, 255)      
        );
        deleteDestBtn.setHorizontalAlignment(SwingConstants.CENTER);
        deleteDestBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                deleteDestBtn.setBackground(new Color(255, 50, 50));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                deleteDestBtn.setBackground(new Color(255, 100, 100));            
            }
        });
        
        // listener to add the reservation on click
        deleteDestBtn.addActionListener((e)->{
            deleteDestination();
        });

        
        buttonContainer.add(deleteDestBtn, BorderLayout.EAST);
        subtitle1.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle2.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionText.setAlignmentX(Component.LEFT_ALIGNMENT); 
        buttonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        bottom.add(subtitle1);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(subtitle2);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(buttonContainer);
        bottom.add(Box.createVerticalStrut(10));

        add(top, BorderLayout.NORTH);
        add(scrollWrapper, BorderLayout.CENTER);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }
    
    public void showDialog(){
        setVisible(true); 
    }
    
    public MoyenTransportModel getSelectedTransportModel(List<MoyenTransportModel> transports) {
    for (int i = 0; i < radioButtons.size(); i++) {
        if (radioButtons.get(i).isSelected()) {
            return transports.get(i);
        }
    }
    return null;
}
    
    private void loadAndSetImage(int imageId){
        BufferedImage image = cachedImageLoader.loadImage(imageId);
        if (image != null) {
            // I will need to add a setter in PanelRound to set the background image
            setBackgroundImage(image);
            repaint();
        }
    }
   
    
    private void deleteDestination() {
        confirmDeleteDestination(destination, () -> {
            destinationDao.deleteDestination(destination.getDestinationId());
        });
        
    }
    
    private void confirmDeleteDestination(DestinationModel destination, Runnable onConfirmed) {
        JDialog dialog = new JDialog(parentFrame, true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        // Main rounded content panel
        PanelRound mainPanel = new PanelRound();
        mainPanel.setBackground(new Color(250, 250, 255)); // very light background
        mainPanel.setRoundTopLeft(20);
        mainPanel.setRoundTopRight(20);
        mainPanel.setRoundBottomLeft(20);
        mainPanel.setRoundBottomRight(20);
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(28, 32, 28, 32));
        mainPanel.setLayout(new BorderLayout(0, 20));

        // Title / Question
        JLabel questionLabel = new JLabel("Voulez-vous vraiment supprimer cette destination ?");
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        questionLabel.setForeground(new Color(50, 50, 70));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        // Warning icon or text (optional but improves UX)
        JLabel warningIcon = new JLabel("⚠️");
        warningIcon.setFont(new Font("Segoe UI", Font.BOLD, 48));
        warningIcon.setForeground(new Color(255, 140, 0)); // orange warning color
        warningIcon.setHorizontalAlignment(SwingConstants.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonsPanel.setOpaque(false);

        // Cancel button
        UIButton cancelBtn = new UIButton(
            " Annuler",
            " ", 
            new Color(220, 220, 230),
            new Color(80, 80, 100)
        );
        cancelBtn.setRadius(44);
        cancelBtn.setPreferredSize(new Dimension(140, 44));
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelBtn.addActionListener(e -> dialog.dispose());
        cancelBtn.setHorizontalAlignment(SwingConstants.CENTER);

        UIButton deleteBtn = new UIButton(
            " Supprimer",
            "/trip_manager_app/ressources/icons/trash_light.svg", // assuming you have a trash icon
            new Color(255, 100, 100),
            Color.WHITE
        );
        deleteBtn.setHorizontalAlignment(SwingConstants.CENTER);
        

        deleteBtn.setRadius(44);
        deleteBtn.setPreferredSize(new Dimension(140, 44));
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteBtn.addActionListener(e -> {
            new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                onConfirmed.run();
                Thread.sleep(2500);

                return null;
        }

        @Override
        protected void done() {
            try {
                get(); 
                dialog.dispose();
                dispose();
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    null,
                    "Échec de la réservation : " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }.execute();
        });

        // Hover effects
        cancelBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { cancelBtn.setBackground(new Color(200, 200, 210)); }
            public void mouseExited(MouseEvent e)  { cancelBtn.setBackground(new Color(220, 220, 230)); }
        });
        
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){ deleteBtn.setBackground(new Color(255, 50, 50));  }    
            @Override
            public void mouseExited(MouseEvent e){ deleteBtn.setBackground(new Color(255, 100, 100)); }
        });

        

        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(deleteBtn);

        // Assemble layout
        JPanel centerPanel = new JPanel(new BorderLayout(0, 16));
        centerPanel.setOpaque(false);
        centerPanel.add(warningIcon, BorderLayout.NORTH);
        centerPanel.add(questionLabel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        dialog.setContentPane(mainPanel);
        dialog.pack();

        // Apply rounded shape to the window itself (all four corners)
        SwingUtilities.invokeLater(() -> {
            int w = dialog.getWidth();
            int h = dialog.getHeight();
            if (w > 0 && h > 0) {
                dialog.setShape(new RoundRectangle2D.Double(0, 0, w, h, 20, 20));
            }
        });

        dialog.setLocationRelativeTo(parentFrame);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
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
    
    public void addBookingButtonListener(ActionListener listener){
        deleteDestBtn.addActionListener(listener);
    }
    

    private void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
    }
}
