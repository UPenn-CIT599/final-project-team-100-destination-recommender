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

    /**
     * Create the panel.
     */
    public CostPanel() {
        super("Cost Preference", 133);
        
        //// First Row /////////////////////////////////

        JLabel label = new JLabel("How important is cost for your trip?");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        add(label, gbc_lblNewLabel);
        
        //// Second Row /////////////////////////////////

        slider = new StandardSlider();
        String left = "Looking to ball out";
        String mid = "Got some wiggle room";
        String right = "Strict budget";
        slider.setUpSlider(left, mid, right);
        
        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.weightx = 1;
        gbc_slider.gridx = 0;
        gbc_slider.gridy = 1;
        add(slider, gbc_slider);
    }

}
