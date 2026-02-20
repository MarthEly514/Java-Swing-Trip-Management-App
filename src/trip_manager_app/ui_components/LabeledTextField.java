/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ely
 */
public class LabeledTextField extends JPanel {

    private JPanel fieldPanel;
    private UIButton choiceBtn;

    /**
     * @return the containsButton
     */
    public boolean isContainsButton() {
        return containsButton;
    }

    /**
     * @param containsButton the containsButton to set
     */
    public void setContainsButton(boolean containsButton) {
        this.containsButton = containsButton;
    }

    private String label;
    private String placeholder;
    private RoundedTextField inputField;
    private boolean containsButton;
    
    public LabeledTextField(String label, String placeholder, String buttonText, boolean containsButton){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.containsButton = containsButton;
        initLabel(label, placeholder, buttonText);
        if(containsButton != true){
        } else {
            choiceBtn = new UIButton(
                    buttonText,
                    "",
                    new Color(108, 99, 255),
                    Color.white
                    
            );
            choiceBtn.setPreferredSize(new Dimension(150, 40));
            choiceBtn.setMinimumSize(new Dimension(150, 40));
            choiceBtn.setMaximumSize(new Dimension(150, 40));
            choiceBtn.setRadius(20);
            choiceBtn.setHorizontalAlignment(SwingConstants.CENTER);

            
            choiceBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                choiceBtn.setBackground(new Color(101, 93, 235));            
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                choiceBtn.setBackground(new Color(108, 99, 255));            
            }
        });
            
            fieldPanel.add(Box.createHorizontalStrut(5));
            fieldPanel.add(choiceBtn);          
        }
        
    }
    
    public String getText(){
        return inputField.getText();
    }

    private void initLabel(String label, String placeholder, String buttonText) {
        this.label = label;
        this.placeholder = placeholder;
        setBackground(Color.white);
        
        SubtitleLabel title = new SubtitleLabel(label); // required
        title.setFontSize(14);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        //field panel
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        fieldPanel.setOpaque(false);
        
        //ville field
        inputField = new RoundedTextField();
        inputField.setPlaceholder(placeholder);
        inputField.setBorderWidth(2);
        inputField.setBorderColor(new Color(20, 20, 20, 30));
        inputField.setPlaceholderColor(new Color(20, 20, 20, 70));
        inputField.setFocusedBorderColor(new Color(108, 99, 255));
        
        
        fieldPanel.add(inputField);

        this.add(title);
        this.add(Box.createVerticalStrut(5));
        this.add(fieldPanel);
    }
    
    public void setIsEditable(boolean editable){
        inputField.setEditable(editable);
    }
    
    public void addChoiceButtonListener(ActionListener listener){
        choiceBtn.addActionListener(listener);
    }

    public void setText(String text) {
        inputField.setText(text);
    }
    
    
    
}
