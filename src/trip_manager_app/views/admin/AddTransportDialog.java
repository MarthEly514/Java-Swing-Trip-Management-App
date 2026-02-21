/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import trip_manager_app.DAO.MoyenTransportDAO;
import trip_manager_app.models.MoyenTransportModel;
import trip_manager_app.ui_components.CustomPicker;
import trip_manager_app.ui_components.LabeledComboBox;
import trip_manager_app.ui_components.LabeledTextField;
import trip_manager_app.ui_components.PanelRound;
import trip_manager_app.ui_components.ScrollWrapper;
import trip_manager_app.ui_components.SubtitleLabel;
import trip_manager_app.ui_components.UIButton;
import trip_manager_app.utils.SvgUtils;

/**
 *
 * @author ely
 */
public class AddTransportDialog extends JDialog {

    private final JFrame parentFrame;
//    private final DestinationModel destination;
    private final MoyenTransportDAO transportDao;
    private int cornerRadius = 30;
    private AdminTransportManagementView parentView;
    private LabeledTextField descriptionField;
    private LabeledTextField noVehicleField;
    private LabeledComboBox typeCombo;
    private CustomPicker placesPicker;
    private UIButton addTransportButton;
    
    
    public AddTransportDialog(JFrame parentFrame){
        super(parentFrame, "Transports details", true); 
        this.parentFrame = parentFrame;
        this.transportDao = new MoyenTransportDAO();
        initDialog();
    }

    public AddTransportDialog(JFrame parentFrame, AdminTransportManagementView parentView) {
        super(parentFrame, "Transports details", true); 
        this.parentFrame = parentFrame;
        this.parentView = parentView;
        this.transportDao = new MoyenTransportDAO();
        initDialog();
    }
    
    private void initDialog(){
        setUndecorated(true);
        setLayout(new BorderLayout());
        setSize(720, 620);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();
        setBackground(Color.white);
        
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(Integer.MAX_VALUE, 90));
        top.setLayout(new BorderLayout());
        top.setBackground(Color.white);
        
        JLabel title = new JLabel("Ajouter un moyen de transport");
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
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 10));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        titlePanel.setOpaque(false);
        
        closeBtnPanel.add(closeBtn);
        titlePanel.add(title);
        
        top.add(titlePanel, BorderLayout.WEST); 
        top.add(closeBtnPanel, BorderLayout.EAST);
        
        SubtitleLabel subtitle1 = new SubtitleLabel("Informations: ");
        subtitle1.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 10));
        
        
        JPanel informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(4, 0, 10, 0));
        informationsPanel.setPreferredSize(new Dimension(0, 350));
        informationsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
        informationsPanel.setMinimumSize(new Dimension(0, 350));
        informationsPanel.setOpaque(false);
        
        //type field
        typeCombo = new LabeledComboBox(
                "Type de véhicule",
                new String[]{"Avion", "Véhicule", "Bateau", "Bus"},
                "Avion"
        );

        //description field
        descriptionField = new LabeledTextField("Description", "(Acura NSX 2018)", "", false);

        //no places field
        placesPicker = new CustomPicker(
            "Nombre de places",
            12,           
            1,            
            200,          
            1             
        );

        //no vehicle field
        
        noVehicleField = new LabeledTextField("Numero du vehicule", "N045AZ","", false);
        
        
        informationsPanel.add(typeCombo);
        informationsPanel.add(descriptionField);
        informationsPanel.add(placesPicker);        
        
//        
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.white);
        bottom.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottom.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        
        
        ScrollWrapper scrollWrapper = new ScrollWrapper(bottom);
        scrollWrapper.setBackground(Color.white);
        scrollWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.setPreferredSize(new Dimension(600, 70));
        buttonContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonContainer.setOpaque(false);
        
        
        addTransportButton = new UIButton(
            "Ajouter",
            "", 
            new Color(101, 93, 235, 190), 
            new Color(255, 255, 255)      
        );
        addTransportButton.setHorizontalAlignment(SwingConstants.CENTER);
        addTransportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                if(addTransportButton.isEnabled() == true){
                    addTransportButton.setBackground(new Color(101, 93, 235, 250));            
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                if(addTransportButton.isEnabled() == true){
                    addTransportButton.setBackground(new Color(101, 93, 235, 190));            
                }
            }
        });
//        
        activateFinishButton();
        
        //detects if the user is entering anything in the field
        descriptionField.getInputField().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent de) {
                activateFinishButton();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                activateFinishButton();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
        });
        
        // listener to add the reservation on click
        addTransportButton.addActionListener((e)->{
            createAndSaveTransport(); 
        });

        
        buttonContainer.add(addTransportButton, BorderLayout.EAST);
        subtitle1.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
//        
        bottom.add(subtitle1);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(informationsPanel);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(buttonContainer);

        add(top, BorderLayout.NORTH);
        add(scrollWrapper, BorderLayout.CENTER);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }
    
    public void showDialog(){
        setVisible(true); 
    }
    
    private void activateFinishButton(){
        // disables the button when some informations are not entered
        if(descriptionField.getText().isEmpty()){
            addTransportButton.setBackground(new Color(20, 20, 20, 20));
        }else{
            addTransportButton.setBackground(new Color(101, 93, 235, 190)); 
        }
        addTransportButton.setEnabled(!descriptionField.getText().isEmpty()); 
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

    private void createAndSaveTransport() {
        addTransportButton.setText("Chargement...");
        addTransportButton.setBackground(new Color(20, 20, 20, 20));
        addTransportButton.setEnabled(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                MoyenTransportModel transport = new MoyenTransportModel(typeCombo.getSelectedValue(),  descriptionField.getText(),  placesPicker.getValue());
                transportDao.addMoyenTransport(transport);

                Thread.sleep(2500);

                return null;
        }

        @Override
        protected void done() {
            try {
                get(); 
                addTransportButton.setText("Ajouté");
                addTransportButton.setBackground(new Color(101, 93, 235, 90)); 
                addTransportButton.setEnabled(false); 
                dispose();
            } catch (Exception ex) {
                addTransportButton.setText("Error – Try again");
                addTransportButton.setBackground(new Color(220, 50, 50)); // red
                addTransportButton.setEnabled(true); // allow retry
                JOptionPane.showMessageDialog(
                    null,
                    "Échec de l'ajout : " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }.execute();
    }
}
