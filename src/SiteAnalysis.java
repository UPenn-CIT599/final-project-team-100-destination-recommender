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
	 * returns an ArrayList of countries with the most number of sites in each
	 * country returns the topN countries
	 * 
	 * @return
	 */

	public ArrayList<String> getCountryWithMostSites(int topN) {

		TreeMap<String, Double> countryToNumberOfSites = new TreeMap<>();

		for (Site site : sites) {
			if (countryToNumberOfSites.containsKey(site.getCountry())) {
				countryToNumberOfSites.put(site.getCountry(), countryToNumberOfSites.get(site.getCountry()) + 1);
			} else {
				countryToNumberOfSites.put(site.getCountry(), 1.0);
			}
		}

		ArrayList<String> sortedCountryList = new ArrayList<String>();

		String maxKey = "placeholder";
		for (int i = 0; i < topN; i++) {
			double maxValue = -1.0;
			for (String key : countryToNumberOfSites.keySet()) {
				if (countryToNumberOfSites.get(key) > maxValue) {
					maxKey = key;
					maxValue = countryToNumberOfSites.get(key);
				} else if (countryToNumberOfSites.get(key) == maxValue) {
					maxKey = key;
					maxValue = countryToNumberOfSites.get(key);
					break;
				}
			}
			sortedCountryList.add(maxKey);
			countryToNumberOfSites.put(maxKey, -2.0);

		}

		ArrayList<String> testArray = new ArrayList<String>();
		return sortedCountryList;
	}

	ArrayList<Country> countryList = new ArrayList<Country>();

	/**
	 * calculates a site score for each country depending on the number of sites it
	 * has
	 * 
	 * @param countryToNumSites
	 * @return
	 */

	public double getSiteScore(TreeMap<String, Double> countryToNumSites) {

		for (String key : countryToNumSites.keySet()) {
			if (countryToNumSites.get(key) > 50) {
				return 10;
			} else if (countryToNumSites.get(key) > 40) {

			}
		}

		return 5;
	}

}
