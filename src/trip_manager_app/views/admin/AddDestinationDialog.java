/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.views.admin;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import trip_manager_app.DAO.*;
import trip_manager_app.models.DestinationModel;
import trip_manager_app.models.VoyageModel;
import trip_manager_app.ui_components.*;
import trip_manager_app.utils.*;

/**
 *
 * @author ely
 */
public class AddDestinationDialog extends JDialog {

    private final JFrame parentFrame;
//    private final DestinationModel destination;
    private final DestinationDAO destinationDao;
    private final ImageDAO imageDao;
    private int cornerRadius = 30;
    private LabeledTextField villeField;
    private LabeledTextField paysField;
    private LabeledTextField noteField;
    private LabeledTextField imageField;
    private JTextArea descriptionField;
    private UIButton addDestinationButton;
    private PanelRound descriptionPanel;
    private JFileChooser fileChooser;
    private AdminHomepageView parentView;
    
    
    public AddDestinationDialog(JFrame parentFrame){
        super(parentFrame, "Destination details", true); 
        this.parentFrame = parentFrame;
        this.destinationDao = new DestinationDAO();
        this.imageDao = new ImageDAO();        
        initDialog();
    }

    public AddDestinationDialog(JFrame parentFrame, AdminHomepageView parentView) {
        super(parentFrame, "Destination details", true); 
        this.parentFrame = parentFrame;
        this.parentView = parentView;
        this.destinationDao = new DestinationDAO();
        this.imageDao = new ImageDAO();        
        initDialog();
    }
    
    private void initDialog(){
        setUndecorated(true);
        setLayout(new BorderLayout());
        setSize(1080, 720);
        setLocationRelativeTo(parentFrame);
        applyRoundedShape();
        setBackground(Color.white);
        
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(Integer.MAX_VALUE, 90));
        top.setLayout(new BorderLayout());
        top.setBackground(Color.white);
        
        JLabel title = new JLabel("Ajouter une destination");
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
        informationsPanel.setLayout(new GridLayout(2, 2, 20, 20));
        informationsPanel.setPreferredSize(new Dimension(0, 180));
        informationsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        informationsPanel.setMinimumSize(new Dimension(0, 180));
        informationsPanel.setOpaque(false);
        
        //ville field
        villeField = new LabeledTextField("Ville", "Ville", "", false);

        //pays field
        paysField = new LabeledTextField("Pays", "Pays", "", false);

        //note field
        noteField = new LabeledTextField("Note sur 5", "note", "", false);

        //image field
        
        imageField = new LabeledTextField("Image", "*Obligatoire-Choisissez votre image", "Choisir", true);
        imageField.setIsEditable(false);
        imageField.addChoiceButtonListener(e->{
            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
            int result = fileChooser.showOpenDialog(parentFrame);
            if(result == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                imageField.setText(file.getAbsolutePath());
                
            }
        });
        
        
        informationsPanel.add(villeField);
        informationsPanel.add(paysField);
        informationsPanel.add(noteField);
        informationsPanel.add(imageField);
        
        
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
        
        SubtitleLabel subtitle2 = new SubtitleLabel("Description: ");
        subtitle1.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 10));
        
        descriptionPanel = new PanelRound();
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setPreferredSize(new Dimension(0, 0));
        descriptionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        descriptionPanel.setMinimumSize(new Dimension(200, 40));
        descriptionPanel.setBackground(new Color(20, 20, 20, 5));
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20 ,20));
        descriptionPanel.rounded(30);
        
        
        descriptionField = new JTextArea();
        descriptionField.setOpaque(false);
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        descriptionField.setBackground(Color.WHITE);
        descriptionField.setForeground(Color.BLACK);
        descriptionField.setCaretPosition(0);
//        descriptionField.setPlaceholderColor(new Color(20, 20, 20, 70));

        descriptionPanel.add(descriptionField);

        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.setPreferredSize(new Dimension(600, 70));
        buttonContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonContainer.setOpaque(false);
        
        
        addDestinationButton = new UIButton(
            "Ajouter la destination",
            "", 
            new Color(101, 93, 235, 190), 
            new Color(255, 255, 255)      
        );
        addDestinationButton.setHorizontalAlignment(SwingConstants.CENTER);
        addDestinationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                if(addDestinationButton.isEnabled() == true){
                    addDestinationButton.setBackground(new Color(101, 93, 235, 250));            
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                if(addDestinationButton.isEnabled() == true){
                    addDestinationButton.setBackground(new Color(101, 93, 235, 190));            
                }
            }
        });
//        
        activateFinishButton();
        
        //detects if the user is entering anything in the field
        descriptionField.getDocument().addDocumentListener(new DocumentListener(){
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
        addDestinationButton.addActionListener((e)->{
            createAndSaveDestination(); 
        });

        
        buttonContainer.add(addDestinationButton, BorderLayout.EAST);
        subtitle1.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle2.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionField.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        
//        
        bottom.add(subtitle1);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(informationsPanel);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(subtitle2);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(descriptionPanel);
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
    
    private void activateFinishButton(){
        // disables the button when some informations are not entered
        if(descriptionField.getText().isEmpty()){
            addDestinationButton.setBackground(new Color(20, 20, 20, 20));
        }else{
            addDestinationButton.setBackground(new Color(101, 93, 235, 190)); 
        }
        addDestinationButton.setEnabled(!descriptionField.getText().isEmpty()); 
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

    private void createAndSaveDestination() {
        addDestinationButton.setText("Chargement...");
        addDestinationButton.setBackground(new Color(20, 20, 20, 20));
        addDestinationButton.setEnabled(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                ImageClass image = new ImageClass(fileChooser.getSelectedFile());
                imageDao.addImage(image);
                int imageUsedId = imageDao.getLastImageId();
                DestinationModel destination = new DestinationModel(villeField.getText(), paysField.getText(), descriptionField.getText(),Float.parseFloat(noteField.getText()), imageUsedId );
                destinationDao.addDestination(destination);
                

                Thread.sleep(2500);

                return null;
        }

        @Override
        protected void done() {
            try {
                get(); 
                addDestinationButton.setText("Ajouté");
                addDestinationButton.setBackground(new Color(101, 93, 235, 90)); 
                addDestinationButton.setEnabled(false); 
                dispose();
            } catch (Exception ex) {
                addDestinationButton.setText("Error – Try again");
                addDestinationButton.setBackground(new Color(220, 50, 50)); // red
                addDestinationButton.setEnabled(true); // allow retry
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
