/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.ui_components;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import trip_manager_app.utils.SvgUtils;

public class DatePickerField extends PanelRound {

    private final JDateChooser dateChooser;

    /**
     * Creates a date selector with optional label
     * @param labelText label text (can be empty or null for no label)
     */
    
    public DatePickerField() {
        this("Date", null);
    }
    
    public DatePickerField(String labelText) {
        this(labelText, null);
    }

    /**
     * Creates a date selector with initial date and optional label
     * @param labelText label text (can be empty or null)
     * @param initialDate initial date (null = today)
     */
    public DatePickerField(String labelText, Date initialDate) {
        super();
        setLayout(new BorderLayout(10, 0));
        setOpaque(false);
        dateChooser = new JDateChooser();

        setBackground(new Color(245, 245, 250));
        setRoundTopLeft(20);
        setRoundTopRight(20);
        setRoundBottomLeft(20);
        setRoundBottomRight(20);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setPreferredSize(new Dimension(200, 50));
        setMaximumSize(new Dimension(200, 50));

        if (labelText != null && !labelText.trim().isEmpty()) {
            JLabel lbl = new JLabel(labelText + " :");
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            lbl.setForeground(new Color(70, 70, 80));
            add(lbl, BorderLayout.WEST);
        }
        initDateChooser(initialDate);
    }


    /**
     * Returns the selected date (may return null)
     * @return 
     */
    public Date getSelectedDate() {
        return dateChooser.getDate();
    }

    public LocalDate getLocalDate() {
        Date date = dateChooser.getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String formattedDate = localDateTime.format(formatter);

        return localDate;
    }
    /**
     * Set the selected date programmatically
     */
    
    public void setSelectedDate(Date date) {
        dateChooser.setDate(date);
    }

    /**
     * Set minimum selectable date
     */
    public void setMinDate(Date minDate) {
        dateChooser.setMinSelectableDate(minDate);
    }

    /**
     * Set maximum selectable date
     */
    public void setMaxDate(Date maxDate) {
        dateChooser.setMaxSelectableDate(maxDate);
    }

    /**
     * Enable / disable the picker
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        dateChooser.setEnabled(enabled);
    }

    private void initDateChooser(Date initialDate) {
        
        dateChooser.setDateFormatString("dd/MM/yyyy");          
        dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        dateChooser.setOpaque(false);
        dateChooser.setBorder(null);

        // Make inner field transparent so rounded container shows through
        JTextField textField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        textField.setBorder(null);
        textField.setOpaque(false);
        
        JButton calendarButton = dateChooser.getCalendarButton();

        if (calendarButton != null) {
            calendarButton.setText("📅");
            

            if (!"".equals("/trip_manager_app/ressources/icons/date_range.svg".trim())){
                calendarButton.setIcon(SvgUtils.loadSvg("/trip_manager_app/ressources/icons/date_range.svg", 20, 20));  
            }

            calendarButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
//            calendarButton.setForeground(new Color(60, 120, 220));     
            calendarButton.setBackground(new Color(240, 245, 255, 0));    
            calendarButton.setOpaque(true);                            

            calendarButton.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            calendarButton.setFocusPainted(false);
            calendarButton.setBorderPainted(false);

        }

        


        if (initialDate != null) {
            dateChooser.setDate(initialDate);
        } else {
            dateChooser.setDate(new Date());
        }

        add(dateChooser, BorderLayout.CENTER);
    }
}