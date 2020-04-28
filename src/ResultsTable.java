import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Template for top and bottom results table
 * @author seanb
 *
 */
public class ResultsTable extends JTable {

    final static String[] columnNames = { "Rank", "Country", "Number of Sites",
            "Cost of Living", "Average Temperature" };
    private Object[][] data = new Object[5][5];
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);

    private static DecimalFormat df1 = new DecimalFormat("#.00"); // used to eliminate decimal in number of sites

    public ResultsTable() {

        setModel(model);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 5; i++) {
            getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        } // centers all data
        
        getColumnModel().getColumn(0).setPreferredWidth(15);

        setPreferredScrollableViewportSize(new Dimension(585, 80));
        setFillsViewportHeight(true);

    }

    /**
     * Fills the results table with data based on the user input
     * @param topN pulls from comboBox in MainFrame
     * @param sortedCountries sorted by totalScore, descending order
     * @param topTable true for top table, false for bottom table
     */
    public void fillTable(int topN, ArrayList<Country> sortedCountries, boolean topTable) {

        int indexOfWorstCountry = sortedCountries.size() - 1;

        // Resets row count in case set to 0 (from reset button)
        if (model.getRowCount() == 0) model.setRowCount(5);

        if (topTable) {

            // Stores data objects into the data[][] array to be displayed in table
            // Iterates from 0 to topN, with positive increments
            for (int i = 0; i < topN; i++) {

                data[i][0] = i + 1;                         
                data[i][1] = sortedCountries.get(i).getName();
                new Double((double) (data[i][2] = sortedCountries.get(i).getNumSites()));
                data[i][3] = df1.format(sortedCountries.get(i).getCostOfLiving());
                data[i][4] = sortedCountries.get(i).getMonthTemperature() + " ºF";

            }

        } else {

            // Iterates from worst to (worst - topN), with negative increments
            for (int i = indexOfWorstCountry; i > indexOfWorstCountry - topN; i--) {

                data[indexOfWorstCountry - i][0] = i + 1;                         
                data[indexOfWorstCountry - i][1] = sortedCountries.get(i).getName();
                new Double((double) (data[indexOfWorstCountry - i][2] = sortedCountries.get(i).getNumSites()));
                data[indexOfWorstCountry - i][3] = df1.format(sortedCountries.get(i).getCostOfLiving());
                data[indexOfWorstCountry - i][4] = sortedCountries.get(i).getMonthTemperature() + " ºF"; 

            }

        }

        // Sets the values of the table as the data stored in the data[][] array
        for (int i = 0; i < topN; i++) {
            for (int j = 0; j < 5; j++) {
                setValueAt(data[i][j], i, j);
            }
        }

    }

    /**
     * Clears data in table by setting row count to 0
     */
    public void clearData() {
        model.setRowCount(0);
    }

}
