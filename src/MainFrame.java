import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements ActionListener {

    private WeatherPanel weatherPanel;
    private SitePanel sitePanel;
    private CostPanel costPanel;
    private JSeparator horizontalLine;
    private JLabel topN_QuestionStart;
    private JLabel topN_QuestionEnd;
    private JComboBox<Integer> topN_ComboBox;
    private JButton runBtn;
    
    private int topN;

    /**
     * Create the frame.
     */
    public MainFrame(String title) {
        super(title);
        
        // Set layout manager
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        getContentPane().setLayout(gridBagLayout);

        // Create Swing components
        weatherPanel = new WeatherPanel();
        GridBagConstraints gbc_weatherPanel = new GridBagConstraints();
        
        sitePanel = new SitePanel();
        GridBagConstraints gbc_sitesPanel = new GridBagConstraints();

        costPanel = new CostPanel();
        GridBagConstraints gbc_costPanel = new GridBagConstraints();
        
        horizontalLine = new JSeparator(SwingConstants.HORIZONTAL);
        GridBagConstraints gbc_horizontalLine = new GridBagConstraints();
        
        topN_QuestionStart = new JLabel("Show top");
        GridBagConstraints gbc_topN_QuestionStart = new GridBagConstraints();
        
        topN_QuestionEnd = new JLabel("results");
        GridBagConstraints gbc_topN_QuestionEnd = new GridBagConstraints();

        Integer[] topN_choices = { 1, 2, 3, 4, 5 };
        topN_ComboBox = new JComboBox<>(topN_choices);
        topN_ComboBox.addActionListener(this);
        GridBagConstraints gbc_topN_ComboBox = new GridBagConstraints();

        runBtn = new JButton("Run");
        GridBagConstraints gbc_runBtn = new GridBagConstraints();
        runBtn.addActionListener(new ActionListener() {
            
            /**
             * Here is where you pull the user input from
             */
            public void actionPerformed(ActionEvent e) {

                String tripMonth = weatherPanel.getMonthSelected();
                double idealTemp = weatherPanel.getIdealTemp();
                double weatherPreference = weatherPanel.getWeatherPreference();
                double sitePreference = sitePanel.getSitePreference();
                double costPreference = costPanel.getCostPreference();
                
                System.out.println("Trip month: " + tripMonth);
                System.out.println("Ideal temp: " + idealTemp);
                System.out.println("Weather preference: " + weatherPreference);
                System.out.println("Site preference: " + sitePreference);
                System.out.println("Cost preference: " + costPreference);
                System.out.println("topN = " + topN);

                
            }
        });


        // Add Swing components to content pane
        Container c = getContentPane();
        
        // First row: Weather Panel
        
        gbc_weatherPanel.gridx = 0;
        gbc_weatherPanel.gridwidth = 4;
        gbc_weatherPanel.gridy = 0;
        gbc_weatherPanel.weighty = 0.3;
        c.add(weatherPanel, gbc_weatherPanel);
        
        // Second row: Sites Panel
        
        gbc_sitesPanel.gridx = 0;
        gbc_sitesPanel.gridwidth = 4;
        gbc_sitesPanel.gridy = 1;
        gbc_sitesPanel.weighty = 0.25;
        c.add(sitePanel, gbc_sitesPanel);
        
        // Third row: Cost Panel
        
        gbc_costPanel.gridx = 0;
        gbc_costPanel.gridwidth = 4;
        gbc_costPanel.gridy = 2;
        gbc_costPanel.weighty = 0.25;
        c.add(costPanel, gbc_costPanel);
        
        // Fourth row, first column: Question label start
        
        gbc_topN_QuestionStart.gridx = 0;
        gbc_topN_QuestionStart.gridy = 3;
        gbc_topN_QuestionStart.weightx = 0.5;
        gbc_topN_QuestionStart.weighty = 0.2;
        gbc_topN_QuestionStart.anchor = GridBagConstraints.EAST;
        c.add(topN_QuestionStart, gbc_topN_QuestionStart);
        
        // Fourth row, second column: topN ComboBox
        
        gbc_topN_ComboBox.gridx = 1;
        gbc_topN_ComboBox.gridy = 3;
        gbc_topN_ComboBox.weightx = 0.5;
        gbc_topN_ComboBox.weighty = 0.2;
        gbc_topN_ComboBox.anchor = GridBagConstraints.CENTER;
        c.add(topN_ComboBox, gbc_topN_ComboBox);
        
        // Fourth row, third column: Question label end
        
        gbc_topN_QuestionEnd.gridx = 2;
        gbc_topN_QuestionEnd.gridy = 3;
        gbc_topN_QuestionEnd.weightx = 0.5;
        gbc_topN_QuestionEnd.weighty = 0.2;
        gbc_topN_QuestionEnd.anchor = GridBagConstraints.WEST;
        c.add(topN_QuestionEnd, gbc_topN_QuestionEnd);

        
        // Fourth row, fourth column: Run Button
        
        gbc_runBtn.gridx = 3;
        gbc_runBtn.gridy = 3;
        gbc_runBtn.weightx = 2;
        gbc_runBtn.weighty = 0.2;
        gbc_runBtn.anchor = GridBagConstraints.CENTER;
        c.add(runBtn, gbc_runBtn);
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        topN = (int) cb.getSelectedItem();
        
    }

}
