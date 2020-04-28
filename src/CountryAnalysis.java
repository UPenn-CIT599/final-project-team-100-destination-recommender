import java.text.DecimalFormat;
import java.util.ArrayList;

public class CountryAnalysis {

	/**
	 * applies the weights given the user Input from the GUI
	 * 
	 * @param siteWeight
	 * @param costWeight
	 * @param weatherWeight
	 * @param userInputTemp
	 * @param month
	 * @return a sorted arrayList of countryScores
	 */

	public static ArrayList<Country> applyWeights(ArrayList<Country> countries, double siteWeight, double costWeight,
			double weatherWeight, double userInputTemp, String month) {
		ArrayList<Country> countryScores = new ArrayList<Country>();
		double maxNumSites = -1;
		double maxCostOfLiving = -1;
		double maxMonthTemperature = -100;
		for (Country country : countries) {
			if (country.getNumSites() > maxNumSites) {
				maxNumSites = country.getNumSites();
			}
			if (country.getCostOfLiving() > maxCostOfLiving) {
				maxCostOfLiving = country.getCostOfLiving();
			}
			if (country.getMonthTemperature() > maxMonthTemperature) {
				maxMonthTemperature = country.getMonthTemperature();
			}
		}
		for (Country country : countries) {
			double siteScore = siteWeight * country.getNumSites() / maxNumSites; // higher siteScore is preferred
			double costScore = costWeight * country.getCostOfLiving() / maxCostOfLiving; // lower costScore is preferred
			double weatherScore = weatherWeight * Math.abs(country.getMonthTemperature() - userInputTemp)
					/ maxMonthTemperature; // lower weatherScore is preferred
			double totalScore = -siteScore + costScore + weatherScore; // want to return countries with LOWEST total
																	   // score! Hence the -siteScore
			country.setTotalScore(totalScore);
			countryScores.add(country);
		}
        countryScores.sort(null);
		return countryScores;
	}

	/**
	 * takes in the arrayList of country scores sorts them by total score determined
	 * by user preference displays suggestions
	 * 
	 * @param csArray
	 * @param topN
	 * @return
	 */

	public ArrayList<String> sortCountriesByTotalScore(ArrayList<Country> countries, int topN) {
		ArrayList<String> countryNames = new ArrayList<String>();
		countries.sort(null); // using Comparator method to sort
		for (int i = 0; i < topN; i++) {
			countryNames.add(countries.get(i).getName());
		}
		return countryNames;
	}
}