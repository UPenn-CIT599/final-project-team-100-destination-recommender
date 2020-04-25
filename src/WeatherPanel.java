import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class WeatherPanel extends TripComponentPanel
                          implements ActionListener {
    
    /**
     * Instance variables related to month combo box
     */
    private String[] months = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };
    private JComboBox<String[]> monthComboBox;
    private String monthSelected;
        
    private JSpinner tempSpinner;
    private JTextField tempTextField;
    
    public JComboBox<String[]> getMonthComboBox() {
        return monthComboBox;
    }
    
    public String getMonthSelected() {
        if (monthSelected == null) {
            return months[Calendar.getInstance().get(Calendar.MONTH)];
        } 
        
        return monthSelected;
    }
    
    public JSpinner getTempSpinner() {
        return tempSpinner;
    }
    
    public JTextField getTempTextField() {
        return tempTextField;
    }
    
    public double getIdealTemp() {
        return (double) ((Integer) tempSpinner.getValue()).intValue();
    }

    public WeatherPanel() {
        
        super("Weather Preference", 150);
        
        // GRID LAYOUT: 4 rows x 2 columns
        
        //// Row 1, Col 1: Month Question /////////////////////////////////
        
        JLabel monthQuestion = new JLabel("What month are you planning to travel?");
        GridBagConstraints gbc_monthQuestion = new GridBagConstraints();
        
        gbc_monthQuestion.insets = new Insets(0, 0, 5, 0);

        gbc_monthQuestion.weightx = 0.5;
        gbc_monthQuestion.weighty = 0.5;

        gbc_monthQuestion.gridx = 0;
        gbc_monthQuestion.gridy = 0;
        add(monthQuestion, gbc_monthQuestion);
        
        //// Row 1, Col 2: Temperature Question /////////////////////////////////
        
        JLabel tempQuestion = new JLabel("Ideal temperature (ºF):");
        GridBagConstraints gbc_tempQuestion = new GridBagConstraints();
        
        gbc_tempQuestion.insets = new Insets(0, 0, 5, 0);

        gbc_tempQuestion.weightx = 0.5;
        gbc_tempQuestion.weighty = 0.5;

        gbc_tempQuestion.gridx = 1;
        gbc_tempQuestion.gridy = 0;
        add(tempQuestion, gbc_tempQuestion);
        
        // Row 2, Col 1: Month ComboBox /////////////////////////////////
            
        monthComboBox = new JComboBox(months);
        monthComboBox.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        monthComboBox.addActionListener(this);
        
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 1;
        add(monthComboBox, gbc_comboBox);
        
        //// Row 2, Col 2: Temperature Spinner /////////////////////////////////
        
        SpinnerNumberModel tempModel = new SpinnerNumberModel(58, // initial temp, set to avg temp on Earth
                                                              -128, // min temp, set to min temp recorded on Earth
                                                              134, // max temp, set to max temp recorded on Earth
                                                              1); // step size
        tempSpinner = new JSpinner(tempModel);
        tempTextField = ((JSpinner.DefaultEditor) tempSpinner.getEditor()).getTextField();
        tempTextField.setEditable(false);
        tempTextField.setBackground(Color.white);
        
        GridBagConstraints gbc_tempSpinner = new GridBagConstraints();
        gbc_tempSpinner.gridx = 1;
        gbc_tempSpinner.gridy = 1;
        add(tempSpinner, gbc_tempSpinner);
        
        //// Row 3, Col 1-2: Weather Preference Question /////////////////////////////////
        
        JLabel lblNewLabel = new JLabel("How important is weather for your trip?");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 2;
        gbc_lblNewLabel.gridwidth = 2;
        add(lblNewLabel, gbc_lblNewLabel);

        //// Row 4, Col 1-2: Weather Slider /////////////////////////////////
        
        String left = "Not important";
        String mid = "Kinda important";
        String right = "Very important";
        slider = new StandardSlider(left, mid, right);
        
        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.weightx = 1;
        gbc_slider.gridx = 0;
        gbc_slider.gridy = 3;
        gbc_slider.gridwidth = 2;
        add(slider, gbc_slider);
        
    }

    public void actionPerformed(ActionEvent e) {
        
        JComboBox cb = (JComboBox) e.getSource();
        monthSelected = (String) cb.getSelectedItem();
        
    }

}
