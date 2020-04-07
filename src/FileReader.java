import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	/**
	 * Reads in a CSV of weather data with Country Name, month, avg temperate and
	 * avg precipitation and stores the information in an ArrayList
	 * 
	 * @param fileName
	 * @return
	 */
	public static ArrayList<Weather> weatherFileReader(String fileName) {

		ArrayList<Weather> weather = new ArrayList<Weather>();
		File file = new File(fileName);

		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); // skips the first line with all the column headers
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lineInfo = line.split(",");
				String country = lineInfo[0];
				String month = lineInfo[1];
				double avgTemp = Double.parseDouble(lineInfo[2]);
				double avgPrecipitation = Double.parseDouble(lineInfo[3]);

				Weather weatherObject = new Weather(country, month, avgTemp, avgPrecipitation);

				weather.add(weatherObject);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return weather;
	}

	/**
	 * Reads in a CSV of cost data with Country Name, and Cost Of Living Index and
	 * stores the information in an ArrayList
	 * 
	 * @param fileName
	 * @return
	 */
	public static ArrayList<Cost> costFileReader(String fileName) {

		ArrayList<Cost> cost = new ArrayList<Cost>();
		File file = new File(fileName);

		return cost;
	}
	
	/**
	 * Reads in a CSV of cost data with information about different world heritage sites and
	 * stores the information in an ArrayList
	 * 
	 * @param fileName
	 * @return
	 */
	public static ArrayList<Site> readCSV() {
		ArrayList<Site> sites = new ArrayList<Site>();
		File siteFile = new File("whc-sites-2019_cleanedUp.csv");
		
		try {
			Scanner fileReader = new Scanner(siteFile);
			fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				String eachLine = fileReader.nextLine();
				String[] lineComponents = eachLine.split(",");
				String name = lineComponents[0];
				String category = lineComponents[1];
				String country = lineComponents[2];
				String region = lineComponents[3];
				Site site = new Site(name, category, country, region);
				sites.add(site);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
			e.printStackTrace();
		}
		
		return sites;
		
	}
}
