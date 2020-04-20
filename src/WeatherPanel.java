import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WeatherPanel extends JPanel
                          implements ActionListener,
                                     ChangeListener {
    
    private String[] months = { "January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December" };
    private String monthSelected;
    private double idealTemp;
    private double weatherPreference;
    static final int WEATHER_MIN = 0;
    static final int WEATHER_MAX = 100;
    static final int WEATHER_INIT = 0;
    
    JTextField tempTextField;
    
    public String getMonthSelected() {
        if (monthSelected == null) {
            return months[Calendar.getInstance().get(Calendar.MONTH)];
        } 
        
        return monthSelected;
    }
    
    public double getWeatherPreference() {        
        return weatherPreference;
    }
    
    public double getIdealTemp() {
        return Double.parseDouble(tempTextField.getText()); // need to add try catch for number format exception
    }

    public WeatherPanel() {
        
        // Set size
        setPreferredSize(new Dimension(500, 150));
        
        // Set title
        setBorder(BorderFactory.createTitledBorder("Weather Preference"));
                
        // Set layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        setLayout(gridBagLayout);
        
        //// First Row, First Column /////////////////////////////////
        
        JLabel monthQuestion = new JLabel("What month are you planning to travel?");
        GridBagConstraints gbc_monthQuestion = new GridBagConstraints();
        
        gbc_monthQuestion.insets = new Insets(0, 0, 5, 0);

        gbc_monthQuestion.weightx = 0.5;
        gbc_monthQuestion.weighty = 0.5;

        gbc_monthQuestion.gridx = 0;
        gbc_monthQuestion.gridy = 0;
        add(monthQuestion, gbc_monthQuestion);
        
        //// First Row, Second Column /////////////////////////////////
        
        JLabel tempQuestion = new JLabel("Ideal temperature (ºF):");
        GridBagConstraints gbc_tempQuestion = new GridBagConstraints();
        
        gbc_tempQuestion.insets = new Insets(0, 0, 5, 0);

        gbc_tempQuestion.weightx = 0.5;
        gbc_tempQuestion.weighty = 0.5;

        gbc_tempQuestion.gridx = 1;
        gbc_tempQuestion.gridy = 0;
        add(tempQuestion, gbc_tempQuestion);
        
        //// Second Row, First Column /////////////////////////////////
            
        JComboBox<String[]> monthComboBox = new JComboBox(months);
        monthComboBox.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        monthComboBox.addActionListener(this);
        
        
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
       
        //gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 1;
        add(monthComboBox, gbc_comboBox);
        
        //// Second Row, Second Column /////////////////////////////////

        tempTextField = new JTextField(3);
        GridBagConstraints gbc_tempTextField = new GridBagConstraints();
        gbc_tempTextField.gridx = 1;
        gbc_tempTextField.gridy = 1;
        add(tempTextField, gbc_tempTextField);
        
        //// Third Row /////////////////////////////////
        
        JLabel lblNewLabel = new JLabel("How important is weather for your trip?");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 2;
        gbc_lblNewLabel.gridwidth = 2;
        add(lblNewLabel, gbc_lblNewLabel);

        //// Fourth Row /////////////////////////////////
        
        JSlider slider = new JSlider(JSlider.HORIZONTAL,
                WEATHER_MIN, WEATHER_MAX, WEATHER_INIT);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        
        
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

    @Override
    public void stateChanged(ChangeEvent e) {
        
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            weatherPreference = (double) source.getValue();
        }
    }

}
