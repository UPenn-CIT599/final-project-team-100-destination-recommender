import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SiteReader {

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
