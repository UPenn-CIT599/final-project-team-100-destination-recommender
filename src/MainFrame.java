import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class MainFrame extends JFrame implements ActionListener {
   
    private int topN;
    private int count;
    private static Color mainFrameColor = new Color(141, 185, 219);

    public MainFrame(String title) {
        super(title);
        
        // Set layout manager /////////////////////////////////////////
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        getContentPane().setLayout(gridBagLayout);

        // Create Swing components ////////////////////////////////////
        
        // Title
        
        JLabel frameTitle = new JLabel("Euro Trip Destination Recommender");
        frameTitle.setFont(new Font("Serif", Font.BOLD, 36));
        GridBagConstraints gbc_frameTitle = new GridBagConstraints();
        
        // Weather Panel
        
        WeatherPanel weatherPanel = new WeatherPanel();
        GridBagConstraints gbc_weatherPanel = new GridBagConstraints();
        
        // Site Panel
        
        SitePanel sitePanel = new SitePanel();
        GridBagConstraints gbc_sitesPanel = new GridBagConstraints();

        // Cost Panel
        
        CostPanel costPanel = new CostPanel();
        GridBagConstraints gbc_costPanel = new GridBagConstraints();
        
        // topN Question Label Start
        
        JLabel topN_QuestionStart = new JLabel("Show top");
        GridBagConstraints gbc_topN_QuestionStart = new GridBagConstraints();
        
        // topN Question Label End
        
        JLabel topN_QuestionEnd = new JLabel("results");
        GridBagConstraints gbc_topN_QuestionEnd = new GridBagConstraints();
        
        // topN Question ComboBox
        
        Integer[] topN_choices = { 1, 2, 3, 4, 5 };
        JComboBox<Integer> topN_ComboBox = new JComboBox<>(topN_choices);
        topN_ComboBox.addActionListener(this);
        GridBagConstraints gbc_topN_ComboBox = new GridBagConstraints();
        
        // Place Holder TextArea
        
        JTextArea placeHolder = new JTextArea("Results will show here...");
        placeHolder.setFont(new Font("Dialog", Font.ITALIC, 24));
        placeHolder.setBackground(mainFrameColor);
        placeHolder.setSize(300, 200);
        placeHolder.setEditable(false);
        GridBagConstraints gbc_placeHolder = new GridBagConstraints();
        
        // Top Results Message
        
        JLabel topMessage = new JLabel("We recommend the following destinations:");
        GridBagConstraints gbc_topMessage = new GridBagConstraints();
        topMessage.setVisible(false);
        
        // Bottom Results Message
        
        JLabel bottomMessage = new JLabel("Don't go here!");
        GridBagConstraints gbc_bottomMessage = new GridBagConstraints();
        bottomMessage.setVisible(false);
        
        // Top Results Table
        
        String[] columnNames = { "Rank", "Country", "Number of Sites", "Cost of Living", "Average Temperature" };
        Object[][] topData = new Object[5][5];
        DefaultTableModel topModel = new DefaultTableModel(topData, columnNames);
        JTable topResultsTable = new JTable(topModel);
        topResultsTable.setModel(topModel);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        topResultsTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        for (int i = 0; i < 5; i++) {
            topResultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        topResultsTable.setPreferredScrollableViewportSize(new Dimension(585, 80));
        topResultsTable.setFillsViewportHeight(true);
        JScrollPane topScrollPane = new JScrollPane(topResultsTable);
        topScrollPane.setVisible(false);
        GridBagConstraints gbc_topResultsTable = new GridBagConstraints();
        
        // Bottom Results Table
        
        Object[][] bottomData = new Object[5][5];
        DefaultTableModel bottomModel = new DefaultTableModel(bottomData, columnNames);
        JTable bottomResultsTable = new JTable(bottomModel);
        bottomResultsTable.setModel(bottomModel);
        
        bottomResultsTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        for (int i = 0; i < 5; i++) {
            bottomResultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        bottomResultsTable.setPreferredScrollableViewportSize(new Dimension(585, 80));
        bottomResultsTable.setFillsViewportHeight(true);
        JScrollPane bottomScrollPane = new JScrollPane(bottomResultsTable);
        bottomScrollPane.setVisible(false);
        GridBagConstraints gbc_bottomResultsTable = new GridBagConstraints();
        
        // Run Button

        JButton runBtn = new JButton("Run");
        runBtn.setPreferredSize(new Dimension(70, 25));
        GridBagConstraints gbc_runBtn = new GridBagConstraints();
        
        // Reset Button
        
        JButton resetBtn = new JButton("Reset");
        resetBtn.setPreferredSize(new Dimension(70, 25));

        GridBagConstraints gbc_resetBtn = new GridBagConstraints();
        resetBtn.setEnabled(false);
        
        // Add ActionListeners /////////////////////////////////
        
        // Run Button AL
        
        runBtn.addActionListener(new ActionListener() {
            
            /**
             * Here is where you pull the user input from
             */
            public void actionPerformed(ActionEvent e) {

                String tripMonth = weatherPanel.getMonthSelected();
                double idealTemp = weatherPanel.getIdealTemp();
                double weatherPreference = weatherPanel.getUserPreference();
                double sitePreference = sitePanel.getUserPreference();
                double costPreference = -1 * costPanel.getUserPreference();
                if (topN == 0) topN = 1; //in case user does not change topN, defaults to 1
                
                CountryAnalysis ca = new CountryAnalysis();
                ArrayList<Country> topCountries = ca.applyWeights(FileReader.readCSV(tripMonth),
                        sitePreference, costPreference, weatherPreference, idealTemp, tripMonth);
                topCountries.sort(null);
                
                for (int i = 0; i < topN; i++) {
                    topData[i][0] = i + 1;                         
                    topData[i][1] = topCountries.get(i).getName();
                    new Double((double) (topData[i][2] = topCountries.get(i).getNumSites()));
                    topData[i][3] = CountryAnalysis.df1.format(topCountries.get(i).getCostOfLiving());
                    topData[i][4] = topCountries.get(i).getMonthTemperature() + " ºF";   

                }
                
                if (topModel.getRowCount() == 0) {
                    topModel.setRowCount(5);
                }
                
                for (int i = 0; i < topN; i++) {
                    for (int j = 0; j < 5; j++) {
                        topResultsTable.setValueAt(topData[i][j], i, j);
                    }
                }
                
                for (int i = topCountries.size() - 1; i > topCountries.size() - 1 - topN; i--) {
                    bottomData[topCountries.size() - 1 - i][0] = i + 1;                         
                    bottomData[topCountries.size() - 1 - i][1] = topCountries.get(i).getName();
                    new Double((double) (bottomData[topCountries.size() - 1 - i][2] = topCountries.get(i).getNumSites()));
                    bottomData[topCountries.size() - 1 - i][3] = CountryAnalysis.df1.format(topCountries.get(i).getCostOfLiving());
                    bottomData[topCountries.size() - 1 - i][4] = topCountries.get(i).getMonthTemperature() + " ºF";  
                }
                
                if (bottomModel.getRowCount() == 0) {
                    bottomModel.setRowCount(5);
                }
                
                for (int i = topCountries.size() - 1; i > topCountries.size() - 1 - topN; i--) {
                    for (int j = 0; j < 5; j++) {
                        bottomResultsTable.setValueAt(bottomData[topCountries.size() - 1 - i][j], topCountries.size() - 1 - i, j);
                    }
                }
                
                topMessage.setVisible(true);
                bottomMessage.setVisible(true);
                topScrollPane.setVisible(true);
                bottomScrollPane.setVisible(true);
                placeHolder.setVisible(false);
                runBtn.setEnabled(false);
                resetBtn.setEnabled(true);
                
                System.out.println("Trip month: " + tripMonth);
                System.out.println("Ideal temp: " + idealTemp);
                System.out.println("Weather preference: " + weatherPreference);
                System.out.println("Site preference: " + sitePreference);
                System.out.println("Cost preference: " + costPreference);
                System.out.println("topN = " + topN);
                System.out.println(placeHolder.getFont());

                for (int i = 0; i < topN; i++) {
                    System.out.println((i + 1) + ". " + topCountries.get(i).getTotalScore());
                }
                
                if (count == 3) {
                    placeHolder.setText("Really enjoying this trip recommender, huh?");
                } else if (count == 4) {
                    placeHolder.setText("You should consider giving team 100 a 100 :)");
                } else {
                    placeHolder.setText("                                         ");
                }
                
                count++;
            }
        });
        
        // Reset Button AL
        
        resetBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                topMessage.setVisible(false);
                bottomMessage.setVisible(false);
                topScrollPane.setVisible(false);
                bottomScrollPane.setVisible(false);
                
                placeHolder.setVisible(true);
                
                DefaultTableModel topModel = (DefaultTableModel) topResultsTable.getModel();
                topModel.setRowCount(0);
                
                DefaultTableModel bottomModel = (DefaultTableModel) bottomResultsTable.getModel();
                bottomModel.setRowCount(0);
                
                weatherPanel.getMonthComboBox().setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
                weatherPanel.getTempSpinner().setValue(58);
                weatherPanel.slider.setValue(50);
                sitePanel.slider.setValue(50);
                costPanel.slider.setValue(0);
                topN_ComboBox.setSelectedIndex(0);

                runBtn.setEnabled(true);
                resetBtn.setEnabled(false);
                
            }
            
        });

        // Add Swing components to content pane //////////////////////////////
        Container c = getContentPane();
        c.setBackground(mainFrameColor);
        
        // Row 1, Col 1-4: Title
        
        gbc_frameTitle.gridx = 0;
        gbc_frameTitle.gridwidth = 4;
        gbc_frameTitle.gridy = 0;
        gbc_frameTitle.weighty = 0.40;
        c.add(frameTitle, gbc_frameTitle);
        
        // Row 2, Col 1-4: Weather Panel
        
        gbc_weatherPanel.gridx = 0;
        gbc_weatherPanel.gridwidth = 4;
        gbc_weatherPanel.gridy = 1;
        gbc_weatherPanel.weighty = 0.2;
        c.add(weatherPanel, gbc_weatherPanel);
        
        // Row 3, Col 1-4: Sites Panel
        
        gbc_sitesPanel.gridx = 0;
        gbc_sitesPanel.gridwidth = 4;
        gbc_sitesPanel.gridy = gbc_weatherPanel.gridy + 1;
        gbc_sitesPanel.weighty = 0.15;
        c.add(sitePanel, gbc_sitesPanel);
        
        // Row 4, Col 1-4: Cost Panel
        
        gbc_costPanel.gridx = 0;
        gbc_costPanel.gridwidth = 4;
        gbc_costPanel.gridy = gbc_sitesPanel.gridy + 1;
        gbc_costPanel.weighty = 0.15;
        c.add(costPanel, gbc_costPanel);
        
        // Row 5-6, Col 1: Question label start
        
        gbc_topN_QuestionStart.gridx = 0;
        gbc_topN_QuestionStart.gridy = gbc_costPanel.gridy + 1;
        gbc_topN_QuestionStart.gridheight = 2;
        gbc_topN_QuestionStart.weightx = 0.5;
        gbc_topN_QuestionStart.weighty = 0.1;
        gbc_topN_QuestionStart.anchor = GridBagConstraints.EAST;
        c.add(topN_QuestionStart, gbc_topN_QuestionStart);
        
        // Row 5-6, Col 2: topN ComboBox
        
        gbc_topN_ComboBox.gridx = 1;
        gbc_topN_ComboBox.gridy = gbc_costPanel.gridy + 1;
        gbc_topN_ComboBox.gridheight = 2;
        gbc_topN_ComboBox.weightx = 0.5;
        gbc_topN_ComboBox.weighty = 0.1;
        gbc_topN_ComboBox.anchor = GridBagConstraints.CENTER;
        c.add(topN_ComboBox, gbc_topN_ComboBox);
        
        // Row 5-6, Col 3: Question label end
        
        gbc_topN_QuestionEnd.gridx = 2;
        gbc_topN_QuestionEnd.gridy = gbc_costPanel.gridy + 1;
        gbc_topN_QuestionEnd.gridheight = 2;
        gbc_topN_QuestionEnd.weightx = 0.5;
        gbc_topN_QuestionEnd.weighty = 0.1;
        gbc_topN_QuestionEnd.anchor = GridBagConstraints.WEST;
        c.add(topN_QuestionEnd, gbc_topN_QuestionEnd);
        
        // Row 5, Col 4: Run Button
        
        gbc_runBtn.gridx = 3;
        gbc_runBtn.gridy = gbc_topN_QuestionEnd.gridy;
        gbc_runBtn.weightx = 2;
        gbc_runBtn.weighty = 0.05;
        gbc_runBtn.anchor = GridBagConstraints.CENTER;
        c.add(runBtn, gbc_runBtn);
        
        // Row 6, Col 4: Reset Button

        gbc_resetBtn.gridx = 3;
        gbc_resetBtn.gridy = gbc_runBtn.gridy + 1;
        gbc_resetBtn.weighty = 0.05;
        gbc_resetBtn.anchor = GridBagConstraints.CENTER;
        c.add(resetBtn, gbc_resetBtn);
        
        // Row 7-8, Col 1-4: Display Place Holder
        
        gbc_placeHolder.gridx = 0;
        gbc_placeHolder.gridy = gbc_resetBtn.gridy + 1;
        gbc_placeHolder.gridwidth = 4;
        gbc_placeHolder.gridheight = 2;
        gbc_placeHolder.weighty = 4;
        c.add(placeHolder, gbc_placeHolder);
        
        // Row 7, Col 1-4: Top Results Message

        gbc_topMessage.gridx = 0;
        gbc_topMessage.gridy = gbc_placeHolder.gridy;
        gbc_topMessage.gridwidth = 4;
        gbc_topMessage.weighty = 0.02;
        gbc_topMessage.anchor = GridBagConstraints.SOUTH;
        c.add(topMessage, gbc_topMessage);
        
        // Row 8, Col 1-4: Top Results Table
        
        gbc_topResultsTable.gridx = 0;
        gbc_topResultsTable.gridy = gbc_topMessage.gridy + 1;
        gbc_topResultsTable.gridwidth = 4;
        gbc_topResultsTable.weighty = 0.8;
//        gbc_topResultsTable.anchor = GridBagConstraints.NORTH;
        c.add(topScrollPane, gbc_topResultsTable);
        
        // Row 9, Col 1-4: Bottom Results Message
        
        gbc_bottomMessage.gridx = 0;
        gbc_bottomMessage.gridy = gbc_topResultsTable.gridy + 1;
        gbc_bottomMessage.gridwidth = 4;
        gbc_bottomMessage.weighty = 0.02;
        gbc_bottomMessage.anchor = GridBagConstraints.SOUTH;
        c.add(bottomMessage, gbc_bottomMessage);
        
        // Row 10. Col 1-4: Bottom Results Table
        
        gbc_bottomResultsTable.gridx = 0;
        gbc_bottomResultsTable.gridy = gbc_bottomMessage.gridy + 1;
        gbc_bottomResultsTable.gridwidth = 4;
        gbc_bottomResultsTable.weighty = 0.8;
//        gbc_bottomResultsTable.anchor = GridBagConstraints.NORTH;
        c.add(bottomScrollPane, gbc_bottomResultsTable);
        

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        topN = (int) cb.getSelectedItem();
        
    }

}