/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.Color;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class SubtitleLabel extends JLabel{
    public SubtitleLabel(String text){
        setText(text);
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setForeground(new Color(50, 50, 50));
        setAlignmentX(LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(0, 30));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
    }
}
