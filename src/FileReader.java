import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	public ArrayList<Country> readCSV() {

		ArrayList<Country> countries = new ArrayList<Country>();
		File countryFile = new File("master.csv");

		Scanner s = new Scanner(System.in);
		System.out.println("Which month would you like to travel? ");
		String monthInput = s.nextLine();

		try {
			Scanner fileReader = new Scanner(countryFile);
			fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				String eachLine = fileReader.nextLine();
				String[] lineComponents = eachLine.split(",");
				String name = lineComponents[0];
				double numSites = Double.parseDouble(lineComponents[1]);
				double costOfLiving = Double.parseDouble(lineComponents[2]);
//				double monthTemperature = Double.parseDouble(lineComponents[3]);

				double monthTemperature = 100;
				if (monthInput.equals("January")) {
					monthTemperature = Double.parseDouble(lineComponents[3]);
				} else if (monthInput.toUpperCase().equals("FEBRUARY")) {
					monthTemperature = Double.parseDouble(lineComponents[4]);
				} else if (monthInput.equals("March")) {
					monthTemperature = Double.parseDouble(lineComponents[5]);
				} else if (monthInput.equals("April")) {
//					double monthTemperature = 11;
					monthTemperature = Double.parseDouble(lineComponents[6]);
				} else if (monthInput.equals("May")) {
					monthTemperature = Double.parseDouble(lineComponents[7]);
				} else if (monthInput.equals("June")) {
					monthTemperature = Double.parseDouble(lineComponents[8]);
				} else if (monthInput.equals("July")) {
					monthTemperature = Double.parseDouble(lineComponents[9]);
				} else if (monthInput.equals("August")) {
					monthTemperature = Double.parseDouble(lineComponents[10]);
				} else if (monthInput.equals("September")) {
					monthTemperature = Double.parseDouble(lineComponents[11]);
				} else if (monthInput.equals("October")) {
					monthTemperature = Double.parseDouble(lineComponents[12]);
				} else if (monthInput.equals("November")) {
					monthTemperature = Double.parseDouble(lineComponents[13]);
				} else if (monthInput.equals("December")) {
					monthTemperature = Double.parseDouble(lineComponents[14]);
				} 
				Country country = new Country(name, numSites, costOfLiving, monthTemperature);
				countries.add(country);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}

}
