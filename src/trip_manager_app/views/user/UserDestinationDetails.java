/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.user;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.DAO.DestinationDAO;
import trip_manager_app.DAO.ReservationDAO;
import trip_manager_app.DAO.VoyageDAO;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.models.NewEnumReservation;
import trip_manager_app.models.ReservationModel;
import trip_manager_app.models.VoyageModel;
import trip_manager_app.ui_components.*;
import trip_manager_app.utils.CachedImageLoader;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class UserDestinationDetails extends JDialog{

    /**
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the departureDatePicker
     */
    public DatePickerField getDepartureDatePicker() {
        return departureDatePicker;
    }

    /**
     * @param departureDatePicker the departureDatePicker to set
     */
    public void setDepartureDatePicker(DatePickerField departureDatePicker) {
        this.departureDatePicker = departureDatePicker;
    }

    /**
     * @return the returnDatePicker
     */
    public DatePickerField getReturnDatePicker() {
        return returnDatePicker;
    }

    /**
     * @param returnDatePicker the returnDatePicker to set
     */
    public void setReturnDatePicker(DatePickerField returnDatePicker) {
        this.returnDatePicker = returnDatePicker;
    }
    private final JFrame parentFrame;
    private int cornerRadius = 30; 
    private String ville;
    private String description;
    private DatePickerField departureDatePicker;
    private DatePickerField returnDatePicker;
//    private LocalDate dateDepart;
//    private LocalDate dateRetour;
    private UIButton bookingBtn;
    private CachedImageLoader cachedImageLoader;
    private BufferedImage backgroundImage;
    private VoyageDAO voyageDao;
    private ReservationDAO reservationDao;


    
    public UserDestinationDetails(JFrame parentFrame, DestinationModel destination){
        super(parentFrame, "Destination details", true); 
        this.parentFrame = parentFrame;
        this.ville = destination.getVille();
        this.description = destination.getDescription();
        cachedImageLoader = new CachedImageLoader();
        reservationDao = new ReservationDAO();
        voyageDao = new VoyageDAO();
        
        loadAndSetImage(destination.getImageId()); 
        initDialog();
    }
    
    private void initDialog(){
        setUndecorated(true);
        setLayout(new BorderLayout());
        setSize(1080, 720);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();
        
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(Integer.MAX_VALUE, 260));
        top.setLayout(new OverlayLayout(top));
        
        PanelRound layer1 = new PanelRound(backgroundImage);
        layer1.setPreferredSize(new Dimension(Integer.MAX_VALUE, 260));
        layer1.setFitWidth(true);
        
        JPanel layer2 = new JPanel();
        layer2.setBackground(new Color(20, 20, 20, 100));
        layer2.setPreferredSize(new Dimension(Integer.MAX_VALUE, 260));
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
        destinationName.setText(getVille());
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
        
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottom);
        scrollWrapper.setBackground(Color.white);
        scrollWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        SubtitleLabel subtitle1 = new SubtitleLabel("Description");
        subtitle1.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));    
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        
        JLabel descriptionText = new JLabel("<html><body style='width: 400px'>"+ getDescription() +"</body></html>");
        
        Font font = new Font("SansSerif", Font.PLAIN, 14);
        descriptionText.setFont(font);
        descriptionText.setForeground(new Color(150, 150, 150, 10));
        
        descriptionPanel.add(descriptionText);

        
        SubtitleLabel subtitle2 = new SubtitleLabel("Dates: ");
        setDepartureDatePicker(new DatePickerField("Date de Départ"));
        setReturnDatePicker(new DatePickerField("Date de Retour"));
        
        JPanel datesPanel = new JPanel();
        datesPanel.setOpaque(false);
        datesPanel.setLayout(new BorderLayout());    
        datesPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        datesPanel.add(getDepartureDatePicker(), BorderLayout.NORTH);
        datesPanel.add(getReturnDatePicker(), BorderLayout.SOUTH);
        datesPanel.setPreferredSize(new Dimension(300, 110));
        datesPanel.setMaximumSize(new Dimension(300, 110));
        
        

        
        SubtitleLabel subtitle3 = new SubtitleLabel("Moyens de transport disponibles: ");
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));    
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
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
        buttonContainer.setPreferredSize(new Dimension(600, 70));
        buttonContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        
        // listener to add the reservation on click
        bookingBtn.addActionListener((e)->{
            createAndSaveReservation();
        });

        
        buttonContainer.add(bookingBtn, BorderLayout.EAST);
        subtitle1.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle2.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle3.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        datesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionText.setAlignmentX(Component.LEFT_ALIGNMENT); 
        buttonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        bottom.add(subtitle1);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(descriptionPanel);
        bottom.add(Box.createVerticalStrut(20));
        bottom.add(subtitle2);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(datesPanel);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(subtitle3);
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
    
    private void loadAndSetImage(int imageId){
        BufferedImage image = cachedImageLoader.loadImage(imageId);
        if (image != null) {
            // I will need to add a setter in PanelRound to set the background image
            setBackgroundImage(image);
            repaint();
        }
    }
    
    private void createAndSaveReservation(){
        VoyageModel voyageSaved = new VoyageModel("Luxembourg", ville,  departureDatePicker.getLocalDate(),  returnDatePicker.getLocalDate(), new BigDecimal(500.34), 15);
        voyageDao.addVoyage(voyageSaved);
        VoyageModel voyageUsed = voyageDao.getVoyageByDate(departureDatePicker.getLocalDate());
        ReservationModel reservation = new ReservationModel(1, voyageUsed.getIdVoyage(),NewEnumReservation.fromString("En attente"));
        reservationDao.addReservation(reservation);
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
        bookingBtn.addActionListener(listener);
    }
    

    private void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
    }
}