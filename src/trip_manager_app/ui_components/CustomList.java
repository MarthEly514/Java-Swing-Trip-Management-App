package trip_manager_app.ui_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import javax.swing.border.Border;

public class CustomList<T> extends JPanel {
    private JList<T> list;
    private DefaultListModel<T> listModel;
    private Consumer<T> clickListener;
    private ListCellRenderer<T> cellRenderer;
    
    public CustomList(ListCellRenderer<T> renderer, Consumer<T> clickListener) {
        this.cellRenderer = renderer;
        this.clickListener = clickListener;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        // Create list model
        listModel = new DefaultListModel<>();
        
        // Create JList
        list = new JList<>(listModel);
        list.setCellRenderer(cellRenderer);
        list.setFixedCellHeight(100);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBackground(Color.WHITE);
        
        // Add click listener
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = list.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        T item = listModel.getElementAt(index);
                        if (clickListener != null) {
                            clickListener.accept(item);
                        }
                    }
                }
            }
        });
        
        list.setBorder(new RoundedBorder(50, new Color(220, 220, 235), 1));

        add(list, BorderLayout.CENTER);
    }
    
    static class RoundedBorder implements Border {
        private int radius;
        private Color color;
        private int thickness;

        public RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c) { return new Insets(20,20,20,20); }
        @Override public boolean isBorderOpaque() { return false; }
    }
    
    // Public methods
    public void addItem(T item) {
        listModel.addElement(item);
    }
    
    public void setItems(java.util.List<T> items) {
        listModel.clear();
        for (T item : items) {
            listModel.addElement(item);
        }
    }
    
    public void clearItems() {
        listModel.clear();
    }
    
    public void removeItem(int index) {
        if (index >= 0 && index < listModel.size()) {
            listModel.remove(index);
        }
    }
    
    public T getItem(int index) {
        return listModel.getElementAt(index);
    }
    
    public int getItemCount() {
        return listModel.size();
    }
    
    public void setFixedCellHeight(int height) {
        list.setFixedCellHeight(height);
    }
}