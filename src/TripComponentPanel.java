import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Sets common panel format for Weather, Site, and Cost
 * @author seanb
 *
 */
public class TripComponentPanel extends JPanel {
    
    protected StandardSlider slider = new StandardSlider();
    
    /**
     * Accesses the slider's value to return user preference
     * for this trip component
     * @return integer between 0 and 100
     */
    public double getUserPreference() {        
        return slider.getPreference();
    }
    
    public TripComponentPanel(String title, int height) {
        
        // Set size
        setPreferredSize(new Dimension(600, height));
        
        // Set title
        setBorder(BorderFactory.createTitledBorder(title));
                
        // Set layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        setLayout(gridBagLayout);
        
        // Set color
        setBackground(new Color(245, 233, 67));
        
    }

}
