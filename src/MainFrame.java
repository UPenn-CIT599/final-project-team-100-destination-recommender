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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MainFrame extends JFrame {

    private int topN;
    private int count;
    private static Color mainFrameColor = new Color(141, 185, 219);

    /**
     * Main window that houses the GUI
     * @param title
     */
    public MainFrame(String title) {
        super(title);

        // Set layout manager /////////////////////////////////////////
        getContentPane().setLayout(new GridBagLayout());

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
        GridBagConstraints gbc_topN_ComboBox = new GridBagConstraints();

        // Place Holder TextArea

        JTextArea placeHolder = new JTextArea("Results will show here...");
        placeHolder.setFont(new Font("Dialog", Font.ITALIC, 24));
        placeHolder.setBackground(mainFrameColor);
        placeHolder.setEditable(false);
        GridBagConstraints gbc_placeHolder = new GridBagConstraints();

        // Top Results Message

        JLabel topMessage = new JLabel("We recommend the following destinations:");
        topMessage.setVisible(false);
        GridBagConstraints gbc_topMessage = new GridBagConstraints();

        // Bottom Results Message

        JLabel bottomMessage = new JLabel("Don't go here!");
        bottomMessage.setVisible(false);
        GridBagConstraints gbc_bottomMessage = new GridBagConstraints();

        // Top Results Table

        ResultsTable topResultsTable = new ResultsTable();
        JScrollPane topScrollPane = new JScrollPane(topResultsTable);
        topScrollPane.setVisible(false);
        GridBagConstraints gbc_topResultsTable = new GridBagConstraints();

        // Bottom Results Table

        ResultsTable bottomResultsTable = new ResultsTable();
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

        // Add Swing components to content pane //////////////////////////////

        Container c = getContentPane();
        c.setBackground(mainFrameColor);

        // ***GRID X & Y ZERO-INDEXED***

        // Row 1, Col 1-4: Title

        gbc_frameTitle.gridx = 0;
        gbc_frameTitle.gridwidth = 4;
        gbc_frameTitle.gridy = 0;
        gbc_frameTitle.weighty = 0.4;
        c.add(frameTitle, gbc_frameTitle);

        // Row 2, Col 1-4: Weather Panel

        gbc_weatherPanel.gridx = 0;
        gbc_weatherPanel.gridwidth = 4;
        gbc_weatherPanel.gridy = gbc_frameTitle.gridy + 1;
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

        // Row 7-10, Col 1-4: Display Place Holder

        gbc_placeHolder.gridx = 0;
        gbc_placeHolder.gridy = gbc_resetBtn.gridy + 1;
        gbc_placeHolder.gridwidth = 4;
        gbc_placeHolder.gridheight = 4;
        gbc_placeHolder.weighty = 5;
        c.add(placeHolder, gbc_placeHolder);

        // Row 7, Col 1-4: Top Results Message

        gbc_topMessage.gridx = 0;
        gbc_topMessage.gridy = gbc_placeHolder.gridy;
        gbc_topMessage.gridwidth = 4;
        gbc_topMessage.weighty = 0.5;
        gbc_topMessage.anchor = GridBagConstraints.CENTER;
        c.add(topMessage, gbc_topMessage);

        // Row 8, Col 1-4: Top Results Table

        gbc_topResultsTable.gridx = 0;
        gbc_topResultsTable.gridy = gbc_topMessage.gridy + 1;
        gbc_topResultsTable.gridwidth = 4;
        gbc_topResultsTable.weighty = 1.5;
        gbc_topResultsTable.anchor = GridBagConstraints.NORTH;
        c.add(topScrollPane, gbc_topResultsTable);

        // Row 9, Col 1-4: Bottom Results Message

        gbc_bottomMessage.gridx = 0;
        gbc_bottomMessage.gridy = gbc_topResultsTable.gridy + 1;
        gbc_bottomMessage.gridwidth = 4;
        gbc_bottomMessage.weighty = 0.5;
        gbc_bottomMessage.anchor = GridBagConstraints.CENTER;
        c.add(bottomMessage, gbc_bottomMessage);

        // Row 10. Col 1-4: Bottom Results Table

        gbc_bottomResultsTable.gridx = 0;
        gbc_bottomResultsTable.gridy = gbc_bottomMessage.gridy + 1;
        gbc_bottomResultsTable.gridwidth = 4;
        gbc_bottomResultsTable.weighty = 1.5;
        gbc_bottomResultsTable.anchor = GridBagConstraints.NORTH;
        c.add(bottomScrollPane, gbc_bottomResultsTable);

        pack();

        // Add ActionListeners /////////////////////////////////

        // topN ComboBox AL

        topN_ComboBox.addActionListener(new ActionListener() {

            /**
             * action performed for when topN combo box is set
             */
            public void actionPerformed(ActionEvent e) {

                JComboBox<Integer> cb = (JComboBox<Integer>) e.getSource();
                topN = (int) cb.getSelectedItem();

            }
        });

        // Run Button AL

        runBtn.addActionListener(new ActionListener() {

            /**
             * Pulls data from all components and feeds these to CountryAnalysis
             * engine on back-end to in order to generate top suggested countries
             */
            public void actionPerformed(ActionEvent e) {

                // Run Button "instance variables"
                String tripMonth = weatherPanel.getMonthSelected();
                double idealTemp = weatherPanel.getIdealTemp();

                double weatherPreference = weatherPanel.getUserPreference();
                double sitePreference = sitePanel.getUserPreference();
                double costPreference = -1 * costPanel.getUserPreference(); // negative because countries with "lower" cost scores
                // are ranked higher (flipped this because was more
                // intuitive to have "looking to ball out" on right)

                ArrayList<Country> sortedCountries = CountryAnalysis.applyWeights(FileReader.readCSV(tripMonth),
                        sitePreference, costPreference, weatherPreference, idealTemp, tripMonth);
                sortedCountries.sort(null);
                if (topN == 0) topN = 1; //in case user does not change topN, defaults to 1

                // Fill tables with data
                topResultsTable.fillTable(topN, sortedCountries, true);
                bottomResultsTable.fillTable(topN, sortedCountries, false);

                // Hide place holder, show results                 
                placeHolder.setVisible(false);
                topMessage.setVisible(true);
                bottomMessage.setVisible(true);
                topScrollPane.setVisible(true);
                bottomScrollPane.setVisible(true);

                // Disable run button, enable reset button
                runBtn.setEnabled(false);
                resetBtn.setEnabled(true);

                // Fun little easter egg :)
                if (count == 2) {
                    placeHolder.setText("Really enjoying this trip recommender, huh?");
                } else if (count == 3) {
                    placeHolder.setText("You should consider giving team 100 a 100 :)");
                } else {
                    placeHolder.setText("");
                }

                count++;
            }
        });

        // Reset Button AL

        resetBtn.addActionListener(new ActionListener() {

            /**
             * resets all fields to default and returns view to original view
             */
            public void actionPerformed(ActionEvent arg0) {

                // Set all fields to default               
                weatherPanel.getMonthComboBox().setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
                weatherPanel.getTempSpinner().setValue(58);
                weatherPanel.slider.setValue(50);
                sitePanel.slider.setValue(50);
                costPanel.slider.setValue(0);
                topN_ComboBox.setSelectedIndex(0);

                topResultsTable.clearData();
                bottomResultsTable.clearData();

                // Hide results, show placeholder
                topMessage.setVisible(false);
                bottomMessage.setVisible(false);
                topScrollPane.setVisible(false);
                bottomScrollPane.setVisible(false);                
                placeHolder.setVisible(true);

                // Disable reset button, enable run button
                resetBtn.setEnabled(false);
                runBtn.setEnabled(true);

            }

        });

    }

}