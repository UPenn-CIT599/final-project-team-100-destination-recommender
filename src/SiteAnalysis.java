import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class SiteAnalysis {

	ArrayList<Site> sites = new ArrayList<Site>();

	public SiteAnalysis(ArrayList<Site> sites) {
		this.sites = sites;
	}
	
	
	/**
	 * returns an ArrayList of countries with the most number of sites in each country
	 * 
	 * @return
	 */

	public ArrayList<String> getCountryWithMostSites() {

		TreeMap<String, Double> countryToNumberOfSites = new TreeMap<>();

		for (Site site : sites) {
			if (countryToNumberOfSites.containsKey(site.getCountry())) {
				countryToNumberOfSites.put(site.getCountry(), countryToNumberOfSites.get(site.getCountry()) + 1);
			} else {
				countryToNumberOfSites.put(site.getCountry(), 1.0);
			}
		}

		ArrayList<String> sortedCountryList = new ArrayList<String>();

		double maxValue = -1;
		String maxKey = "placeholder";
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (String key : countryToNumberOfSites.keySet()) {
				System.out.println(key);
				System.out.println(countryToNumberOfSites.get(key));
				if (countryToNumberOfSites.get(key) > maxValue) {
					maxKey = key;
					System.out.println("maxKey:  is " + maxKey);
					maxValue = countryToNumberOfSites.get(key);
					count++;
				}
			}
			System.out.println(maxKey);
			sortedCountryList.add(maxKey);
			System.out.println(countryToNumberOfSites);
			System.out.println(countryToNumberOfSites.size());
			System.out.println(sortedCountryList);	
			countryToNumberOfSites.remove(maxKey);

		}

		
		ArrayList<String> testArray = new ArrayList<String>();
//		testArray.add("Italy");
//		testArray.add("Germany");
//		testArray.add("Spain");
//		return testArray;
		return sortedCountryList;

	}
}
