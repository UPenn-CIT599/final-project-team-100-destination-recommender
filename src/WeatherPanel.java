import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;

public class WeatherPanel extends JPanel {
    
    public WeatherPanel() {
        
        // Set size
        setPreferredSize(new Dimension(327, 133));
        
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
        
        JComboBox comboBox = new JComboBox();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
       
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 1;
        add(comboBox, gbc_comboBox);



        
    }

}