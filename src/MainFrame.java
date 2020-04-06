import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    private WeatherPanel weatherPanel;

    /**
     * Create the frame.
     */
    public MainFrame(String title) {
        super(title);
        
        // Set layout manager
        setLayout(new BorderLayout());

        // Create Swing components
        weatherPanel = new WeatherPanel();

        // Add Swing components to content pane
        Container c = getContentPane();
        c.add(weatherPanel, BorderLayout.NORTH);

    }

}
