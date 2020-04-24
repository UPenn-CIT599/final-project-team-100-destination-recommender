import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MainFrame extends JFrame implements ActionListener {
   
    private int topN;

    public MainFrame(String title) {
        super(title);
        
        // Set layout manager
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        getContentPane().setLayout(gridBagLayout);

        // Create Swing components
        WeatherPanel weatherPanel = new WeatherPanel();
        GridBagConstraints gbc_weatherPanel = new GridBagConstraints();
        
        SitePanel sitePanel = new SitePanel();
        GridBagConstraints gbc_sitesPanel = new GridBagConstraints();

        CostPanel costPanel = new CostPanel();
        GridBagConstraints gbc_costPanel = new GridBagConstraints();
        
        JLabel topN_QuestionStart = new JLabel("Show top");
        GridBagConstraints gbc_topN_QuestionStart = new GridBagConstraints();
        
        JLabel topN_QuestionEnd = new JLabel("results");
        GridBagConstraints gbc_topN_QuestionEnd = new GridBagConstraints();

        Integer[] topN_choices = { 1, 2, 3, 4, 5 };
        JComboBox<Integer> topN_ComboBox = new JComboBox<>(topN_choices);
        topN_ComboBox.addActionListener(this);
        GridBagConstraints gbc_topN_ComboBox = new GridBagConstraints();
        
        JTextArea recDisplay = new JTextArea("Results will show here");
        GridBagConstraints gbc_recDisplay = new GridBagConstraints();
        
        String[] columnNames = { "Rank", "Country", "Number of Sites", "Cost of Living", "Average Temperature" };
        Object[][] data = new Object[5][5];
        JTable resultsTable = new JTable(data, columnNames);
        resultsTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        
        resultsTable.setPreferredScrollableViewportSize(new Dimension(585, 80));
        resultsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setVisible(false);
        GridBagConstraints gbc_resultsTable = new GridBagConstraints();


        JButton runBtn = new JButton("Run");
        GridBagConstraints gbc_runBtn = new GridBagConstraints();
        runBtn.addActionListener(new ActionListener() {
            
            /**
             * Here is where you pull the user input from
             */
            public void actionPerformed(ActionEvent e) {

                String tripMonth = weatherPanel.getMonthSelected();
                double idealTemp = weatherPanel.getIdealTemp();
                double weatherPreference = weatherPanel.getUserPreference();
                double sitePreference = sitePanel.getUserPreference();
                double costPreference = costPanel.getUserPreference();
                if (topN == 0) topN = 1; //in case user does not change topN, defaults to 1
                
                CountryAnalysis ca = new CountryAnalysis();
                ArrayList<CountryScore> cs = ca.applyWeights(sitePreference, costPreference, weatherPreference, idealTemp, tripMonth);
                ca.sortCountriesByTotalScore(cs, topN);
                ArrayList<Country> countries = FileReader.readCSV(tripMonth);
                
                for (int i = 0; i < topN; i++) {
                    int index = i + 1;
                    data[i][0] = index + ".";
                    for (int j = 0; j < countries.size(); j++) {
                        if (cs.get(i).getName().equals(countries.get(j).getName())) {                          
                            data[i][1] = cs.get(i).getName();
                            new Double((double) (data[i][2] = countries.get(j).getNumSites()));
                            new Double((double) (data[i][3] = countries.get(j).getCostOfLiving()));
                            data[i][4] = countries.get(j).getMonthTemperature() + " ºF";
                        }
                    }
                }
                
                scrollPane.setVisible(true);
                recDisplay.setVisible(false);
                
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
        
        // Row 1, Col 1-4: Weather Panel
        
        gbc_weatherPanel.gridx = 0;
        gbc_weatherPanel.gridwidth = 4;
        gbc_weatherPanel.gridy = 0;
        gbc_weatherPanel.weighty = 0.3;
        c.add(weatherPanel, gbc_weatherPanel);
        
        // Row 2, Col 1-4: Sites Panel
        
        gbc_sitesPanel.gridx = 0;
        gbc_sitesPanel.gridwidth = 4;
        gbc_sitesPanel.gridy = 1;
        gbc_sitesPanel.weighty = 0.25;
        c.add(sitePanel, gbc_sitesPanel);
        
        // Row 3, Col 1-4: Cost Panel
        
        gbc_costPanel.gridx = 0;
        gbc_costPanel.gridwidth = 4;
        gbc_costPanel.gridy = 2;
        gbc_costPanel.weighty = 0.25;
        c.add(costPanel, gbc_costPanel);
        
        // Row 4, Col 1: Question label start
        
        gbc_topN_QuestionStart.gridx = 0;
        gbc_topN_QuestionStart.gridy = 3;
        gbc_topN_QuestionStart.weightx = 0.5;
        gbc_topN_QuestionStart.weighty = 0.2;
        gbc_topN_QuestionStart.anchor = GridBagConstraints.EAST;
        c.add(topN_QuestionStart, gbc_topN_QuestionStart);
        
        // Row 4, Col 2: topN ComboBox
        
        gbc_topN_ComboBox.gridx = 1;
        gbc_topN_ComboBox.gridy = 3;
        gbc_topN_ComboBox.weightx = 0.5;
        gbc_topN_ComboBox.weighty = 0.2;
        gbc_topN_ComboBox.anchor = GridBagConstraints.CENTER;
        c.add(topN_ComboBox, gbc_topN_ComboBox);
        
        // Row 4, Col 3: Question label end
        
        gbc_topN_QuestionEnd.gridx = 2;
        gbc_topN_QuestionEnd.gridy = 3;
        gbc_topN_QuestionEnd.weightx = 0.5;
        gbc_topN_QuestionEnd.weighty = 0.2;
        gbc_topN_QuestionEnd.anchor = GridBagConstraints.WEST;
        c.add(topN_QuestionEnd, gbc_topN_QuestionEnd);

        
        // Row 4, Col 4: Run Button
        
        gbc_runBtn.gridx = 3;
        gbc_runBtn.gridy = 3;
        gbc_runBtn.weightx = 2;
        gbc_runBtn.weighty = 0.2;
        gbc_runBtn.anchor = GridBagConstraints.CENTER;
        c.add(runBtn, gbc_runBtn);
        
        // Row 5, Col 1-4: Display Place Holder
        
        gbc_recDisplay.gridx = 0;
        gbc_recDisplay.gridy = 4;
        gbc_recDisplay.gridwidth = 4;
        gbc_recDisplay.weighty = 0.5;
        c.add(recDisplay, gbc_recDisplay);
        
        // Row 6, Col 1-4: Results Table
        
        gbc_resultsTable.gridx = 0;
        gbc_resultsTable.gridy = 5;
        gbc_resultsTable.gridwidth = 4;
        gbc_resultsTable.weighty = 0.5;
        c.add(scrollPane, gbc_recDisplay);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        topN = (int) cb.getSelectedItem();
        
    }

}
