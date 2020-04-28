import java.awt.Color;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StandardSlider extends JSlider implements ChangeListener {
       
    private double preference;
    
    /**
     * Creates slider with min = 0, max = 100, showing major ticks at
     * 0, 50, and 100. Sets same color as its panel. Sets up unique tick
     * labels based on Strings passed in
     * @param left tick label at min
     * @param mid tick label at mid
     * @param right tick label at max
     */
    public StandardSlider(int min, int max, String left, String mid, String right) {
        super(JSlider.HORIZONTAL, min, max, (min + max) / 2);
        setBackground(TripComponentPanel.getPanelColor());
        
        int tickSpacing = max / 2; // defaults ticks at 0, mid, and max
        if (min + max == 0) tickSpacing = max; // if min and max centered around 0, then shows ticks at min, max, and mid (0)
        setMajorTickSpacing(tickSpacing);
        setPaintTicks(true);
        setPaintLabels(true);
        addChangeListener(this);
        
        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(min), new JLabel(left));
        labelTable.put(new Integer((min + max) / 2), new JLabel(mid));
        labelTable.put(new Integer(max), new JLabel(right));
        setLabelTable(labelTable);
    }
    
    @Override
    /**
     * Once slider stops moving, the preference becomes whatever value
     * the knob is on when it stops
     */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) { // When slider is no longer changing, preference = value of slider
            preference = (double) source.getValue();
        }
        
    }
    
    /**
     * accesser method for preference
     * @return
     */
    public double getPreference() {
        return preference;
    }

}
