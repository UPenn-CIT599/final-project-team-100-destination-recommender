import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JSlider;

public class WeatherPanel extends JPanel
                          implements ActionListener {
    
    private String monthSelected;
    static final int WEATHER_MIN = 0;
    static final int WEATHER_MAX = 100;
    static final int WEATHER_INIT = 75;
    
    public String getMonthSelected() {
        return monthSelected;
    }

    public WeatherPanel() {
        
        // Set size
        setPreferredSize(new Dimension(400, 150));
        
        // Set title
        setBorder(BorderFactory.createTitledBorder("Weather Preference"));
                
        // Set layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        setLayout(gridBagLayout);
        
        //// First Row /////////////////////////////////
        
        JLabel monthQuestion = new JLabel("What month are you planning to travel?");
        GridBagConstraints gbc_monthQuestion = new GridBagConstraints();
        
        gbc_monthQuestion.insets = new Insets(0, 0, 5, 0);

        gbc_monthQuestion.weightx = 0.5;
        gbc_monthQuestion.weighty = 0.5;

        gbc_monthQuestion.gridx = 0;
        gbc_monthQuestion.gridy = 0;
        add(monthQuestion, gbc_monthQuestion);
        
        //// Second Row /////////////////////////////////
        
        String[] months = { "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December" };
        
        JComboBox<String[]> monthComboBox = new JComboBox(months);
        monthComboBox.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        monthComboBox.addActionListener(this);
        
        
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
       
        //gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 1;
        add(monthComboBox, gbc_comboBox);
        
        //// Third Row /////////////////////////////////
        
        JLabel lblNewLabel = new JLabel("Ideal Temperature:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 2;
        add(lblNewLabel, gbc_lblNewLabel);

        //// Fourth Row /////////////////////////////////
        
        JSlider slider = new JSlider(JSlider.HORIZONTAL,
                WEATHER_MIN, WEATHER_MAX, WEATHER_INIT);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        
        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.weightx = 1;
        gbc_slider.gridx = 0;
        gbc_slider.gridy = 3;
        add(slider, gbc_slider);



        
    }

    public void actionPerformed(ActionEvent e) {
        
        JComboBox cb = (JComboBox)e.getSource();
        monthSelected = (String)cb.getSelectedItem();
        
    }

}
