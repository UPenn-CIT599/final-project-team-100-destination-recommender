import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

    private WeatherPanel weatherPanel;
    private SitesPanel sitesPanel;
    private CostPanel costPanel;
    private JButton runBtn;

    /**
     * Create the frame.
     */
    public MainFrame(String title) {
        super(title);
        
        // Set layout manager
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        setLayout(gridBagLayout);

        // Create Swing components
        weatherPanel = new WeatherPanel();
        GridBagConstraints gbc_weatherPanel = new GridBagConstraints();
        
        sitesPanel = new SitesPanel();
        GridBagConstraints gbc_sitesPanel = new GridBagConstraints();

        costPanel = new CostPanel();
        GridBagConstraints gbc_costPanel = new GridBagConstraints();

        runBtn = new JButton("Run");
        GridBagConstraints gbc_runBtn = new GridBagConstraints();


        // Add Swing components to content pane
        Container c = getContentPane();
        
        gbc_weatherPanel.gridx = 0;
        gbc_weatherPanel.gridy = 0;
        c.add(weatherPanel, gbc_weatherPanel);
        
        gbc_sitesPanel.gridx = 0;
        gbc_sitesPanel.gridy = 1;
        c.add(sitesPanel, gbc_sitesPanel);
        
        gbc_costPanel.gridx = 0;
        gbc_costPanel.gridy = 2;
        c.add(costPanel, gbc_costPanel);
        
        gbc_runBtn.gridx = 0;
        gbc_runBtn.gridy = 3;
        gbc_runBtn.weighty = 1;
        gbc_runBtn.anchor = GridBagConstraints.CENTER;
        c.add(runBtn, gbc_runBtn);
        

    }

}
