import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	public ArrayList<Country> readCSV() {

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
				double temperature = Double.parseDouble(lineComponents[3]);
				Country country = new Country(name, numSites, costOfLiving, temperature);
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
