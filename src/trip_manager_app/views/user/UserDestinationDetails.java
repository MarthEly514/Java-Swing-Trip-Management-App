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
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import trip_manager_app.DAO.DestinationDAO;
import trip_manager_app.DAO.MoyenTransportDAO;
import trip_manager_app.DAO.ReservationDAO;
import trip_manager_app.DAO.VoyageDAO;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.models.MoyenTransportModel;
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

    private JPanel optionsPanel;

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
    private RoundedTextField departureField;
    private String villeArrivee;
    private String description;
    private DatePickerField departureDatePicker;
    private DatePickerField returnDatePicker;
//    private LocalDate dateDepart;
//    private LocalDate dateRetour;
    private UIButton bookingBtn;
    private CachedImageLoader cachedImageLoader;
    private BufferedImage backgroundImage;
    private VoyageDAO voyageDao;
    private MoyenTransportDAO transportDao;
    private ReservationDAO reservationDao;
    private DestinationModel destination;
    private int idClient;
    private List<RadioButton> radioButtons = new ArrayList<>(); 
    private ButtonGroup radioGroup;
    private List<MoyenTransportModel> transports ;



    
    public UserDestinationDetails(JFrame parentFrame, DestinationModel destination, int idClient){
        super(parentFrame, "Destination details", true); 
        this.parentFrame = parentFrame;
        this.villeArrivee = destination.getVille();
        this.destination = destination;
        this.description = destination.getDescription();
        this.idClient = idClient;
        cachedImageLoader = CachedImageLoader.getInstance();
        reservationDao = new ReservationDAO();
        voyageDao = new VoyageDAO();
        transportDao = new MoyenTransportDAO();
        
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
        
        SubtitleLabel subtitle1 = new SubtitleLabel("Ville de départ: ");
        subtitle1.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 10));
        
        departureField = new RoundedTextField();
        departureField.setPlaceholder("Londres");
        departureField.setBorderWidth(2);
        departureField.setBorderColor(new Color(20, 20, 20, 70));
        departureField.setPlaceholderColor(new Color(20, 20, 20, 70));
        departureField.setFocusedBorderColor(new Color(108, 99, 255));
        departureField.setPreferredSize(new Dimension(200, 40));
        departureField.setMaximumSize(new Dimension(200, 40));
        departureField.setMinimumSize(new Dimension(200, 40));
        
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
        
        optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));    
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        
//        List<MoyenTransportModel> vehicules = transportDao.getAllByType("Voiture");
        transports = transportDao.getAll();

        showTransports(transports);

        
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
                if(bookingBtn.isEnabled() == true){
                    bookingBtn.setBackground(new Color(101, 93, 235, 250));            
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                if(bookingBtn.isEnabled() == true){
                    bookingBtn.setBackground(new Color(101, 93, 235, 190));            
                }
            }
        });
        
        //detects if the user is entering anything in the field
        activateBookingButton();
        departureField.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                activateBookingButton();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                activateBookingButton();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        departureField.setAlignmentX(Component.LEFT_ALIGNMENT);
//        descriptionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        datesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionText.setAlignmentX(Component.LEFT_ALIGNMENT); 
        buttonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        bottom.add(subtitle1);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(departureField);
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
    
    public void showTransports(List<MoyenTransportModel> transports){
        
        radioGroup = new ButtonGroup();
        radioButtons.clear();
        optionsPanel.removeAll();
        for (MoyenTransportModel transport : transports) {
            RadioButton option = new RadioButton(transport.getDescriptionVehicule()+"- Nombre de places: "+transport.getNombrePlaces());
            option.setOpaque(false);
            radioGroup.add(option);
            optionsPanel.add(option);
            radioButtons.add(option);
            
        }
        optionsPanel.revalidate();
        optionsPanel.repaint();
    }
    
    public MoyenTransportModel getSelectedTransportModel(List<MoyenTransportModel> transports) {

        for (int i = 0; i < radioButtons.size(); i++) {
            if (radioButtons.get(i).isSelected()) {
                MoyenTransportModel selected = transports.get(i);
                
                return selected;
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
    
    private void activateBookingButton(){
        // disables the button when some informations are not entered
        if(departureField.getText().isEmpty()){
            bookingBtn.setBackground(new Color(20, 20, 20, 20));
        }else{
            bookingBtn.setBackground(new Color(101, 93, 235, 190)); 
        }
        bookingBtn.setEnabled(!departureField.getText().isEmpty()); 
    }
    
    private BigDecimal calculatePrice(String typeVhc){
        BigDecimal price = new BigDecimal("0");
        
        if(typeVhc.toLowerCase().equals("véhicule")){
            price = new BigDecimal("1000");
        }
        else if(typeVhc.toLowerCase().equals("bateau")){
            price = new BigDecimal("1800");
            
        } else if(typeVhc.toLowerCase().equals("bus")){
            price = new BigDecimal("1500");
     
        } else if(typeVhc.toLowerCase().equals("avion")){
            price = new BigDecimal("300000");
            
        }
    
        return price;
    }
    
    private void createAndSaveReservation() {
        bookingBtn.setText("Chargement...");
        bookingBtn.setBackground(new Color(20, 20, 20, 20));
        bookingBtn.setEnabled(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                VoyageModel voyageSaved = new VoyageModel(
                    departureField.getText(), 
                    villeArrivee, 
                    departureDatePicker.getLocalDate(), 
                    returnDatePicker.getLocalDate(), 
                    new BigDecimal("1500"),
//                    calculatePrice(getSelectedTransportModel(transports).getTypeVehicule()),
                    getSelectedTransportModel(transports).getNoVehicule()
                    
                );
                
                System.out.println(getSelectedTransportModel(transports).getNoVehicule());
                voyageDao.addVoyage(voyageSaved);

                VoyageModel voyageUsed = voyageDao.getLastVoyage();

                ReservationModel reservation = new ReservationModel(
                    idClient, 
                    voyageUsed.getIdVoyage(), 
                    NewEnumReservation.fromString("En attente")
                );
                reservationDao.addReservation(reservation);
                

                Thread.sleep(2500);

                return null;
        }

        @Override
        protected void done() {
            try {
                get(); 
                bookingBtn.setText("Réservé");
                bookingBtn.setBackground(new Color(208, 255, 207)); 
                bookingBtn.setEnabled(false); 
            } catch (Exception ex) {
                bookingBtn.setText("Error – Try again");
                bookingBtn.setBackground(new Color(220, 50, 50)); 
                bookingBtn.setEnabled(true); 
                JOptionPane.showMessageDialog(
                    null,
                    "Échec de la réservation : " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }.execute();
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