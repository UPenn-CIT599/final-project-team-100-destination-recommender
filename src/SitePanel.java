import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Insets;
import java.util.Hashtable;

public class SitePanel extends TripComponentPanel {

    /**
     * Create the panel.
     */
    public SitePanel() {
        
        super("Sites Preference", 100);
        
        //// First Row /////////////////////////////////

        JLabel label = new JLabel("How important are historic sites for your trip?");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 0);
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        add(label, gbc_label);
        
        //// Second Row /////////////////////////////////

        String left = "What is culture?";
        String mid = "I guess I should care about culture";
        String right = "Culture nerd and proud!";
        slider = new StandardSlider(0, 100, left, mid, right);
        
        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.weightx = 1;
        gbc_slider.gridx = 0;
        gbc_slider.gridy = 1;
        add(slider, gbc_slider);

    }

}
