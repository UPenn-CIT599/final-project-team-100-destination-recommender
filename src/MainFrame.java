import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

import com.sun.org.glassfish.external.statistics.annotations.Reset;


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
        topN_QuestionStart.setForeground(Color.white);
        GridBagConstraints gbc_topN_QuestionStart = new GridBagConstraints();
        
        JLabel topN_QuestionEnd = new JLabel("results");
        GridBagConstraints gbc_topN_QuestionEnd = new GridBagConstraints();
        topN_QuestionEnd.setForeground(Color.white);

        Integer[] topN_choices = { 1, 2, 3, 4, 5 };
        JComboBox<Integer> topN_ComboBox = new JComboBox<>(topN_choices);
        topN_ComboBox.addActionListener(this);
        GridBagConstraints gbc_topN_ComboBox = new GridBagConstraints();
        
        JTextArea recDisplay = new JTextArea("Results will show here...");
        recDisplay.setSize(300, 200);
        GridBagConstraints gbc_recDisplay = new GridBagConstraints();
        
        String[] columnNames = { "Rank", "Country", "Number of Sites", "Cost of Living", "Average Temperature" };
        Object[][] data = new Object[5][5];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable resultsTable = new JTable(model);
        resultsTable.setModel(model);
        resultsTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        
        resultsTable.setPreferredScrollableViewportSize(new Dimension(585, 80));
        resultsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setVisible(false);
        GridBagConstraints gbc_resultsTable = new GridBagConstraints();

        JButton runBtn = new JButton("Run");
        GridBagConstraints gbc_runBtn = new GridBagConstraints();
        
        JButton resetBtn = new JButton("Reset");
        GridBagConstraints gbc_resetBtn = new GridBagConstraints();
        resetBtn.setEnabled(false);
        
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
                ArrayList<Country> topCountries = ca.applyWeights(FileReader.readCSV(tripMonth),
                        sitePreference, costPreference, weatherPreference, idealTemp, tripMonth);
                
                for (int i = 0; i < topN; i++) {
                    int index = i + 1;
                    data[i][0] = index + ".";                         
                    data[i][1] = topCountries.get(i).getName();
                    new Double((double) (data[i][2] = topCountries.get(i).getNumSites()));
                    new Double((double) (data[i][3] = topCountries.get(i).getCostOfLiving()));
                    data[i][4] = topCountries.get(i).getMonthTemperature() + " ºF";   
                }
                
                if (model.getRowCount() == 0) {
                    model.setRowCount(5);
                }
                
                for (int i = 0; i < topN; i++) {
                    for (int j = 0; j < 5; j++) {
                        resultsTable.setValueAt(data[i][j], i, j);
                    }
                }
                
                scrollPane.setVisible(true);
                recDisplay.setVisible(false);
                runBtn.setEnabled(false);
                resetBtn.setEnabled(true);
                
                System.out.println("Trip month: " + tripMonth);
                System.out.println("Ideal temp: " + idealTemp);
                System.out.println("Weather preference: " + weatherPreference);
                System.out.println("Site preference: " + sitePreference);
                System.out.println("Cost preference: " + costPreference);
                System.out.println("topN = " + topN);

                for (int i = 0; i < topN; i++) {
                    System.out.println(i + ". " + topCountries.get(i).getTotalScore());
                }
                
            }
        });
        
        resetBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                
                if (scrollPane.isVisible()) {
                    scrollPane.setVisible(false);
                    DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
                    model.setRowCount(0);
                }
                
                runBtn.setEnabled(true);
                resetBtn.setEnabled(false);
                
            }
            
        });

        // Add Swing components to content pane
        Container c = getContentPane();
        c.setBackground(new Color(24, 42, 206));
        
        // Row 1, Col 1-4: Weather Panel
        
        gbc_weatherPanel.gridx = 0;
        gbc_weatherPanel.gridwidth = 4;
        gbc_weatherPanel.gridy = 0;
        gbc_weatherPanel.weighty = 0.2;
        c.add(weatherPanel, gbc_weatherPanel);
        
        // Row 2, Col 1-4: Sites Panel
        
        gbc_sitesPanel.gridx = 0;
        gbc_sitesPanel.gridwidth = 4;
        gbc_sitesPanel.gridy = 1;
        gbc_sitesPanel.weighty = 0.15;
        c.add(sitePanel, gbc_sitesPanel);
        
        // Row 3, Col 1-4: Cost Panel
        
        gbc_costPanel.gridx = 0;
        gbc_costPanel.gridwidth = 4;
        gbc_costPanel.gridy = 2;
        gbc_costPanel.weighty = 0.15;
        c.add(costPanel, gbc_costPanel);
        
        // Row 4-5, Col 1: Question label start
        
        gbc_topN_QuestionStart.gridx = 0;
        gbc_topN_QuestionStart.gridy = 3;
        gbc_topN_QuestionStart.gridheight = 2;
        gbc_topN_QuestionStart.weightx = 0.5;
        gbc_topN_QuestionStart.weighty = 0.1;
        gbc_topN_QuestionStart.anchor = GridBagConstraints.EAST;
        c.add(topN_QuestionStart, gbc_topN_QuestionStart);
        
        // Row 4-5, Col 2: topN ComboBox
        
        gbc_topN_ComboBox.gridx = 1;
        gbc_topN_ComboBox.gridy = 3;
        gbc_topN_ComboBox.gridheight = 2;
        gbc_topN_ComboBox.weightx = 0.5;
        gbc_topN_ComboBox.weighty = 0.1;
        gbc_topN_ComboBox.anchor = GridBagConstraints.CENTER;
        c.add(topN_ComboBox, gbc_topN_ComboBox);
        
        // Row 4-5, Col 3: Question label end
        
        gbc_topN_QuestionEnd.gridx = 2;
        gbc_topN_QuestionEnd.gridy = 3;
        gbc_topN_QuestionEnd.gridheight = 2;
        gbc_topN_QuestionEnd.weightx = 0.5;
        gbc_topN_QuestionEnd.weighty = 0.1;
        gbc_topN_QuestionEnd.anchor = GridBagConstraints.WEST;
        c.add(topN_QuestionEnd, gbc_topN_QuestionEnd);
        
        // Row 4, Col 4: Run Button
        
        gbc_runBtn.gridx = 3;
        gbc_runBtn.gridy = 3;
        gbc_runBtn.weightx = 2;
        gbc_runBtn.weighty = 0.05;
        gbc_runBtn.anchor = GridBagConstraints.CENTER;
        c.add(runBtn, gbc_runBtn);
        
        // Row 5, Col 4: Reset Button

        gbc_resetBtn.gridx = 3;
        gbc_resetBtn.gridy = 4;
        gbc_resetBtn.weighty = 0.05;
        gbc_resetBtn.anchor = GridBagConstraints.CENTER;
        c.add(resetBtn, gbc_resetBtn);
        
        // Row 6, Col 1-4: Display Place Holder
        
        gbc_recDisplay.gridx = 0;
        gbc_recDisplay.gridy = 5;
        gbc_recDisplay.gridwidth = 4;
        gbc_recDisplay.weighty = 1.3;
        c.add(recDisplay, gbc_recDisplay);
        
        // Row 6, Col 1-4: Results Table
        
        gbc_resultsTable.gridx = 0;
        gbc_resultsTable.gridy = 5;
        gbc_resultsTable.gridwidth = 4;
        gbc_resultsTable.weighty = 1.3;
        c.add(scrollPane, gbc_resultsTable);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        topN = (int) cb.getSelectedItem();
        
    }

}