import java.awt.Color;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StandardSlider extends JSlider implements ChangeListener {
    
    static final int SLIDER_MIN = 0;
    static final int SLIDER_MAX = 100;
    static final int SLIDER_INIT = 0;
    
    private double preference;
    
    public StandardSlider() {
        super(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
        setBackground(new Color(245, 233, 67));
    }
    
    /**
     * Sets up slider with min = 0, max = 100, showing major ticks at
     * 0, 50, and 100. Sets up unique tick labels based on Strings passed in
     * @param left tick label at 0
     * @param mid tick label at 50
     * @param right tick label at 100
     */
    public void setUpSlider(String left, String mid, String right) {
        setMajorTickSpacing(SLIDER_MAX / 2);
        setPaintTicks(true);
        setPaintLabels(true);
        addChangeListener(this);
        
        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(SLIDER_MIN), new JLabel(left));
        labelTable.put(new Integer(SLIDER_MAX / 2), new JLabel(mid));
        labelTable.put(new Integer(SLIDER_MAX), new JLabel(right));
        setLabelTable(labelTable);

    }

    
    @Override
    /**
     * Once slider stops moving, the preference becomes whatever value
     * the knob is on when it stops
     */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            preference = (double) source.getValue();
        }
        
    }
    
    public double getPreference() {
        return preference;
    }

}
