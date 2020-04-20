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

public class CostPanel extends JPanel 
                       implements ChangeListener {

    private double costPreference;
    private static int SLIDER_MIN = 0;
    private static int SLIDER_MAX = 100;
    private static int SLIDER_INIT = 0;
    
    public double getCostPreference() {
        return costPreference;
    }

    /**
     * Create the panel.
     */
    public CostPanel() {
        
        // Set size
        setPreferredSize(new Dimension(500, 133));
        
        // Set title
        setBorder(BorderFactory.createTitledBorder("Cost Preference"));
                
        // Set layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{0.0};
        setLayout(gridBagLayout);
        
        //// First Row /////////////////////////////////

        JLabel label = new JLabel("How important is cost for your trip?");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        add(label, gbc_lblNewLabel);
        
        //// Second Row /////////////////////////////////

        JSlider slider = new JSlider(JSlider.HORIZONTAL,
                SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        
        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.weightx = 1;
        gbc_slider.gridx = 0;
        gbc_slider.gridy = 1;
        add(slider, gbc_slider);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            costPreference = (double) source.getValue();
        }
        
    }
}
