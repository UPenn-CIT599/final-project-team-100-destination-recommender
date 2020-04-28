import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CostPanel extends TripComponentPanel {    

    public CostPanel() {
        super("Cost Preference", 100);
        
        //// First Row /////////////////////////////////

        JLabel label = new JLabel("How important is cost for your trip?");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        add(label, gbc_lblNewLabel);
        
        //// Second Row /////////////////////////////////

        String left = "Strict budget";
        String mid = "Got some wiggle room";
        String right = "Looking to ball out";
        slider = new StandardSlider(-50, 50, left, mid, right); // bounds set to -50 and 50 to make more expensive destinations
                                                                // more likely to come up if user is "looking to ball out"
        
        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.weightx = 1;
        gbc_slider.gridx = 0;
        gbc_slider.gridy = 1;
        add(slider, gbc_slider);
    }

}
