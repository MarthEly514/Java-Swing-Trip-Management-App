/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;


import javax.swing.*;
import java.awt.*;


public class LabeledComboBox extends JPanel {

    private final CustomComboBox<String> comboBox;  // or use plain JComboBox if you prefer
    private final SubtitleLabel title;

    public LabeledComboBox(String labelText, String[] items) {
        this(labelText, items, null); // default selected = first item
    }

    public LabeledComboBox(String labelText, String[] items, String defaultValue) {
        setLayout(new BorderLayout(0, 8));
        setOpaque(false);
        
        title = new SubtitleLabel(labelText); // required
        title.setFontSize(14);
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        add(title, BorderLayout.NORTH);


        comboBox = new CustomComboBox<>(items);
        if (defaultValue != null) {
            comboBox.setSelectedItem(defaultValue);
        } else if (items.length > 0) {
            comboBox.setSelectedIndex(0);
        }
        comboBox.setCornerRadius(20);   
        comboBox.setBackground(Color.white); // slightly off-white

        add(comboBox, BorderLayout.CENTER);
    }


    
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public String getSelectedValue() {
        return (String) comboBox.getSelectedItem();
    }

    public void setSelectedValue(String value) {
        comboBox.setSelectedItem(value);
    }

    public void setEnabled(boolean enabled) {
        comboBox.setEnabled(enabled);
    }

}