/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components.renderers;

/**
 *
 * @author ely
 */
import trip_manager_app.models.ReservationModel;
import trip_manager_app.models.NewEnumReservation;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ReservationCellRenderer extends JPanel implements ListCellRenderer<ReservationModel> {
    private JLabel idLabel;
    private JLabel dateLabel;
    private JLabel statusLabel;
    private JLabel actionLabel;
    
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    public ReservationCellRenderer() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        
        // Reservation ID
        idLabel = new JLabel();
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.WEST;
        add(idLabel, gbc);
        
        // Date
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(dateLabel, gbc);
        
        // Status
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        statusLabel.setOpaque(true);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        gbc.gridx = 2;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(statusLabel, gbc);
        
        // Action
        actionLabel = new JLabel("Voir détails >");
        actionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        actionLabel.setForeground(new Color(108, 99, 255));
        gbc.gridx = 3;
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.EAST;
        add(actionLabel, gbc);
    }
    
    @Override
    public Component getListCellRendererComponent(
            JList<? extends ReservationModel> list,
            ReservationModel value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        
        // Format data
        idLabel.setText("Réservation #" + value.getIdReservation());
        dateLabel.setText(value.getDateReservation().format(DATE_FORMATTER));
        
        // Status with color
        NewEnumReservation status = value.getStatut();
        statusLabel.setText(status.getLibelle());
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBackground(getStatusColor(status));
        
        // Row background
        if (isSelected) {
            setBackground(new Color(101, 93, 235, 60));
        } else {
            setBackground(new Color(101, 93, 235, 20));
        }
        
        return this;
    }
    
    private Color getStatusColor(NewEnumReservation status) {
        return switch (status) {
            case EN_ATTENTE -> new Color(255, 152, 0);
            case VALIDE -> new Color(76, 175, 80);
            case ANNULE -> new Color(244, 67, 54);
            default -> new Color(158, 158, 158);
        }; 
    }
}
