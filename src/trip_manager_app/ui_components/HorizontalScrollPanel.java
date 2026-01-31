/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.Scrollable;

/**
 *
 * @author ely
 */
public class HorizontalScrollPanel extends JPanel implements Scrollable {

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle r, int o, int d) {
        return 20;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle r, int o, int d) {
        return 120;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false; 
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return true;
    }
}