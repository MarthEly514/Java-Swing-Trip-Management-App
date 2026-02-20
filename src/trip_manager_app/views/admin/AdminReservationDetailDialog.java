/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import trip_manager_app.DAO.ClientDAO;
import trip_manager_app.models.ClientModel;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.models.ReservationModel;
import trip_manager_app.models.VoyageModel;
import trip_manager_app.ui_components.PanelRound;
import trip_manager_app.utils.CachedImageLoader;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class AdminReservationDetailDialog extends JDialog{
    private final JFrame parentFrame;
    private PanelRound mainPanel;
    private int cornerRadius = 40;  
    private DestinationModel destination;
    private VoyageModel voyage;
    private ClientModel client;
    private ReservationModel reservation;
    private ClientDAO clientDao;
    private CachedImageLoader cachedImageLoader;
    private BufferedImage backgroundImage;    
    private float note;  
    private BigDecimal total;
    private MoyenTransportModel transport;
    
    
    public AdminReservationDetailDialog(JFrame parentFrame, ReservationModel reservation, VoyageModel voyage, DestinationModel destination, BigDecimal total, MoyenTransportModel transport){
        super(parentFrame, "Reservations details", true); 
        this.parentFrame = parentFrame;
        this.reservation = reservation;
        this.clientDao = new ClientDAO();
        this.client = clientDao.getClientById(reservation.getIdClient());
        this.voyage = voyage;
        this.transport = transport;
        this.destination = destination;
        this.cachedImageLoader = CachedImageLoader.getInstance();
        this.total = total;
        this.note = destination.getNote();  
        
        loadAndSetImage(destination.getImageId()); 
        initDialog();
    }
    
    private void initDialog(){
        setUndecorated(true);
        //setLayout(new BorderLayout(10, 10));
        setSize(560, 540);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();

        // Content
        PanelRound reservationPanel = createPanel();
        add(reservationPanel);
        //add(bottom);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }
    
    private PanelRound createPanel(){

        mainPanel = new PanelRound(backgroundImage);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setFitHeight(true);
        mainPanel.setRoundBottomLeft(cornerRadius);
        mainPanel.setRoundBottomRight(cornerRadius);
        mainPanel.setRoundTopLeft(cornerRadius);
        mainPanel.setRoundTopRight(cornerRadius);
        Dimension size = new Dimension(680, 660);
        mainPanel.setPreferredSize(size);
        mainPanel.setMaximumSize(size);
        mainPanel.setMinimumSize(size);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(250, 50));
        topPanel.setMaximumSize(new Dimension(250, 50));
        topPanel.setMinimumSize(new Dimension(250, 50));
        topPanel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        
        JButton closeBtn = new JButton();
        closeBtn.setBorder(BorderFactory.createEmptyBorder());
        closeBtn.setBackground(new Color(60, 230, 0, 0));
        if (!"".equals("/trip_manager_app/ressources/icons/croix_light.svg".trim())){
            closeBtn.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/croix_light.svg", 22, 22));  
        }
        closeBtn.addActionListener(ev -> dispose());

        topPanel.add(closeBtn, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
 
        PanelRound bottomPanel = new PanelRound();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 400));
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        bottomPanel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 400));
        bottomPanel.setBackground(new Color(20, 20, 20, 80));
        bottomPanel.setRoundBottomLeft(cornerRadius);
        bottomPanel.setRoundBottomRight(cornerRadius);
//        bottomPanel.setRoundTopLeft(radius);
        bottomPanel.setRoundTopRight(cornerRadius);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
             
        JLabel destinationLabel = new JLabel(destination.getVille());
        destinationLabel.setForeground(Color.white);
        destinationLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        
        JLabel ratingLabel = new JLabel("<html>Note:<span style='color:#fff009'>&nbsp;&nbsp;&nbsp;"+note+"</span></html>");
        ratingLabel.setForeground(Color.white);
        ratingLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        JPanel destinationPanel = new JPanel();
        destinationPanel.setOpaque(false);
        destinationPanel.setLayout(new BorderLayout());
        
        destinationPanel.add(destinationLabel, BorderLayout.WEST);
        destinationPanel.add(ratingLabel, BorderLayout.EAST);
        
        JPanel informationsPanel = new JPanel();
        informationsPanel.setOpaque(false);
        informationsPanel.setLayout(new BoxLayout(informationsPanel, BoxLayout.Y_AXIS));
        
        JLabel informations = new JLabel(
                "<html><p style='font-size: 12px; margin-top: 20px; margin-left: 20px;'>"
                        + "Informations du client: <span style='font-weight: 400'>&nbsp;&nbsp;&nbsp;"+client.getFullName()+"</span><br/>"
                        + "Lieu de départ: <span style='font-weight: 400'>&nbsp;&nbsp;&nbsp;"+voyage.getVilleDepart()+"</span><br/>"
                        + "Lieu d'arrivée: <span style='font-weight: 400'>&nbsp;&nbsp;&nbsp;"+voyage.getVilleDestination()+"</span><br/>"
                        + "Date de départ: <span style='font-weight: 400'>&nbsp;&nbsp;&nbsp;"+voyage.getDateDepart()+"</span><br/>"
                        + "Date de retour: <span style='font-weight: 400'>&nbsp;&nbsp;&nbsp;"+voyage.getDateRetour()+"</span><br/>"
                        + "Moyen de transport: <span style='font-weight: 400'>&nbsp;&nbsp;&nbsp;"+transport.getDescriptionVehicule()+"</span><br/>"
                + "</p><br/>"
                        + "<p style='font-size: 20px;margin-left: 20px;'>Statut: <span style='font-size:28px;font-weight: 600;'>"+reservation.getStatut().getLibelle()+"</span><br/>"
                        + "Total: <span style='font-size:28px;font-weight: 600;'>"+total+"</span> XOF</p>"
                + "</html>"
        );
        informations.setForeground(new Color(255, 255, 255, 50));
        
        informationsPanel.add(informations);       
        
        bottomPanel.add(destinationPanel, BorderLayout.NORTH);
        bottomPanel.add(informationsPanel, BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    public void showDialog(){
        setVisible(true); 
    }
    
    private void loadAndSetImage(int imageId){
        BufferedImage image = cachedImageLoader.loadImage(imageId);
        if (image != null) {
            // You'll need to add a setter in PanelRound to set the background image
            setBackgroundImage(image);
            repaint();
        }
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

    private void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
    }
}
