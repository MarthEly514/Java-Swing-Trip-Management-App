/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author ely
 */
public class SubtitleLabel extends JPanel{
    public SubtitleLabel(String text){
        JLabel title = new JLabel(text);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(new Color(0, 0, 0, 160));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 30));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        setMinimumSize(new Dimension(100, 30));
        add(title, BorderLayout.WEST);
        setOpaque(false);
    }
}
