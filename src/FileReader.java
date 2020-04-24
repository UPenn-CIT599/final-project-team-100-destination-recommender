import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	/**
	 * reads CSV and returns a list of countries
	 * 
	 * @param month
	 * @return
	 */
	public static ArrayList<Country> readCSV(String month) {

		ArrayList<Country> countries = new ArrayList<Country>();
		File countryFile = new File("master.csv");

		try {
			Scanner fileReader = new Scanner(countryFile);
			fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				String eachLine = fileReader.nextLine();
				String[] lineComponents = eachLine.split(",");
				String name = lineComponents[0];
				double numSites = Double.parseDouble(lineComponents[1]);
				double costOfLiving = Double.parseDouble(lineComponents[2]);
				double monthTemperature = 100;
				if (month.equals("January")) { // reads in the months temperature based on GUI
					monthTemperature = Double.parseDouble(lineComponents[3]);
				} else if (month.equals("February")) {
					monthTemperature = Double.parseDouble(lineComponents[4]);
				} else if (month.equals("March")) {
					monthTemperature = Double.parseDouble(lineComponents[5]);
				} else if (month.equals("April")) {
					monthTemperature = Double.parseDouble(lineComponents[6]);
				} else if (month.equals("May")) {
					monthTemperature = Double.parseDouble(lineComponents[7]);
				} else if (month.equals("June")) {
					monthTemperature = Double.parseDouble(lineComponents[8]);
				} else if (month.equals("July")) {
					monthTemperature = Double.parseDouble(lineComponents[9]);
				} else if (month.equals("August")) {
					monthTemperature = Double.parseDouble(lineComponents[10]);
				} else if (month.equals("September")) {
					monthTemperature = Double.parseDouble(lineComponents[11]);
				} else if (month.equals("October")) {
					monthTemperature = Double.parseDouble(lineComponents[12]);
				} else if (month.equals("November")) {
					monthTemperature = Double.parseDouble(lineComponents[13]);
				} else if (month.equals("December")) {
					monthTemperature = Double.parseDouble(lineComponents[14]);
				}
				Country country = new Country(name, numSites, costOfLiving, monthTemperature);
				countries.add(country);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found");
		}
		return countries;
	}

}
