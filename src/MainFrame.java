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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.sun.org.glassfish.external.statistics.annotations.Reset;


public class MainFrame extends JFrame implements ActionListener {
   
    private int topN;
    private int count;
    private static Color mainFrameColor = new Color(141, 185, 219);

    public MainFrame(String title) {
        super(title);
        
        // Set layout manager
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{1.0};
        getContentPane().setLayout(gridBagLayout);

        // Create Swing components
        JLabel frameTitle = new JLabel("Euro Trip Destination Recommender");
        frameTitle.setFont(new Font("Serif", Font.BOLD, 36));
        GridBagConstraints gbc_frameTitle = new GridBagConstraints();
        
        WeatherPanel weatherPanel = new WeatherPanel();
        GridBagConstraints gbc_weatherPanel = new GridBagConstraints();
        
        SitePanel sitePanel = new SitePanel();
        GridBagConstraints gbc_sitesPanel = new GridBagConstraints();

        CostPanel costPanel = new CostPanel();
        GridBagConstraints gbc_costPanel = new GridBagConstraints();
        
        JLabel topN_QuestionStart = new JLabel("Show top");
//        topN_QuestionStart.setForeground(Color.black);
        GridBagConstraints gbc_topN_QuestionStart = new GridBagConstraints();
        
        JLabel topN_QuestionEnd = new JLabel("results");
        GridBagConstraints gbc_topN_QuestionEnd = new GridBagConstraints();
//        topN_QuestionEnd.setForeground(Color.black);

        Integer[] topN_choices = { 1, 2, 3, 4, 5 };
        JComboBox<Integer> topN_ComboBox = new JComboBox<>(topN_choices);
        topN_ComboBox.addActionListener(this);
        GridBagConstraints gbc_topN_ComboBox = new GridBagConstraints();
        
        JTextArea placeHolder = new JTextArea("Results will show here...");
        placeHolder.setFont(new Font("Dialog", Font.ITALIC, 24));
        placeHolder.setBackground(mainFrameColor);
        placeHolder.setSize(300, 200);
        placeHolder.setEditable(false);
        GridBagConstraints gbc_placeHolder = new GridBagConstraints();
        
        String[] columnNames = { "Rank", "Country", "Number of Sites", "Cost of Living", "Average Temperature" };
        Object[][] data = new Object[5][5];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable resultsTable = new JTable(model);
        resultsTable.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        resultsTable.getColumnModel().getColumn(0).setPreferredWidth(15);
        for (int i = 0; i < 5; i++) {
            resultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        JLabel rec = new JLabel("We recommend the following destinations:");
        GridBagConstraints gbc_rec = new GridBagConstraints();
        rec.setVisible(false);
        
        resultsTable.setPreferredScrollableViewportSize(new Dimension(585, 80));
        resultsTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setVisible(false);
        GridBagConstraints gbc_resultsTable = new GridBagConstraints();

        JButton runBtn = new JButton("Run");
        runBtn.setPreferredSize(new Dimension(70, 25));
        GridBagConstraints gbc_runBtn = new GridBagConstraints();
        
        JButton resetBtn = new JButton("Reset");
        resetBtn.setPreferredSize(new Dimension(70, 25));

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
                double costPreference = -1 * costPanel.getUserPreference();
                if (topN == 0) topN = 1; //in case user does not change topN, defaults to 1
                
                CountryAnalysis ca = new CountryAnalysis();
                ArrayList<Country> topCountries = ca.applyWeights(FileReader.readCSV(tripMonth),
                        sitePreference, costPreference, weatherPreference, idealTemp, tripMonth);
                topCountries.sort(null);
                
                for (int i = 0; i < topN; i++) {
                    data[i][0] = i + 1;                         
                    data[i][1] = topCountries.get(i).getName();
                    new Double((double) (data[i][2] = topCountries.get(i).getNumSites()));
                    data[i][3] = CountryAnalysis.df1.format(topCountries.get(i).getCostOfLiving());
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
                
                rec.setVisible(true);
                scrollPane.setVisible(true);
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
        
        resetBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                rec.setVisible(false);
                scrollPane.setVisible(false);
                
                placeHolder.setVisible(true);
                
                DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
                model.setRowCount(0);
                
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

        // Add Swing components to content pane
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
        gbc_placeHolder.weighty = 1.5;
        c.add(placeHolder, gbc_placeHolder);
        
        // Row 7, Col 1-4: Results Message

        gbc_rec.gridx = 0;
        gbc_rec.gridy = gbc_placeHolder.gridy;
        gbc_rec.gridwidth = 4;
        gbc_rec.weighty = 0.2;
        gbc_rec.anchor = GridBagConstraints.SOUTH;
        c.add(rec, gbc_rec);
        
        // Row 8, Col 1-4: Results Table
        
        gbc_resultsTable.gridx = 0;
        gbc_resultsTable.gridy = 7;
        gbc_resultsTable.gridwidth = 4;
        gbc_resultsTable.weighty = 0.8;
//        gbc_resultsTable.anchor = GridBagConstraints.NORTH;
        c.add(scrollPane, gbc_resultsTable);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cb = (JComboBox) e.getSource();
        topN = (int) cb.getSelectedItem();
        
    }

}