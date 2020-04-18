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
	
	/**
	 * Reads in a CSV of cost data with information about different world heritage
	 * sites and stores the information in an ArrayList
	 * 
	 * @param fileName
	 * @return
	 */
	public static ArrayList<Data> readDataCSV() {

		ArrayList<Data> lines = new ArrayList<Data>();
		File dataFile = new File("CombinedData.csv");

		try {
			Scanner fileReader = new Scanner(dataFile);
			fileReader.nextLine();
			while (fileReader.hasNextLine()) {
				String eachLine = fileReader.nextLine();
				String[] lineComponents = eachLine.split(",");
				String country = lineComponents[0];
				int siteCount = Integer.parseInt(lineComponents[1]);
				double costOfLiving = Double.parseDouble(lineComponents[2]);
				double janTemp = Double.parseDouble(lineComponents[3]);
				double febTemp = Double.parseDouble(lineComponents[4]);
				double marchTemp = Double.parseDouble(lineComponents[5]);
				double aprilTemp = Double.parseDouble(lineComponents[6]);
				double mayTemp = Double.parseDouble(lineComponents[7]);
				double juneTemp = Double.parseDouble(lineComponents[8]);
				double julyTemp = Double.parseDouble(lineComponents[9]);
				double augTemp = Double.parseDouble(lineComponents[10]);
				double septTemp = Double.parseDouble(lineComponents[11]);
				double octTemp = Double.parseDouble(lineComponents[12]);
				double novTemp = Double.parseDouble(lineComponents[13]);
				double decTemp = Double.parseDouble(lineComponents[14]);
				Data data = new Data(country, siteCount, costOfLiving, janTemp, febTemp, marchTemp, aprilTemp, mayTemp,
						juneTemp, julyTemp, augTemp, septTemp, octTemp, novTemp, decTemp);
				lines.add(data);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
			e.printStackTrace();
		}

		return lines;

	}
}
