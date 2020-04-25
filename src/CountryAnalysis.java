import java.text.DecimalFormat;
import java.util.ArrayList;

public class CountryAnalysis {

	private FileReader fr = new FileReader();
	private ArrayList<Country> countryArray;

	/**
	 * df1, df2, df3 used to format decimals in output
	 */
	private static final DecimalFormat df1 = new DecimalFormat("#.##");
	private static final DecimalFormat df2 = new DecimalFormat("#.00");
	private static final DecimalFormat df3 = new DecimalFormat("#.0");

	/**
	 * applies the weights given the user Input from the GUI
	 * 
	 * @param siteWeight
	 * @param costWeight
	 * @param weatherWeight
	 * @param userInputTemp
	 * @param month
	 * @return an arrayList of countryScores
	 */


	public ArrayList<Country> applyWeights(ArrayList<Country> countries, double siteWeight, double costWeight,
			double weatherWeight, double userInputTemp, String month) {

//		ArrayList<Country> countries = fr.readCSV(month);
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
					/ maxMonthTemperature; // lower
			// weatherScore
			// is
			// preferred
			double totalScore = -siteScore + costScore + weatherScore; // want to return countries with LOWEST total
																		// score! Hence the -siteScore
			country.setTotalScore(totalScore);
			countryScores.add(country);
		

		}

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

		System.out.format("%-10s", "Rank");
		System.out.format("%-15s", "Country");
		System.out.format("%-20s", "Number of Sites");
		System.out.format("%-20s", "Cost Of Living");
		System.out.format("%-20s", "Average Temperature");
		System.out.println();
		System.out.format("%-75s",
				"-------------------------------------------------------------------------------------");
		System.out.println();

		for (int i = 0; i < topN; i++) {
			int index = i + 1;
			System.out.format("%-10s", index + ".");
			System.out.format("%-15s", countries.get(i).getName());
			System.out.format("%-20s", df1.format(countries.get(i).getNumSites()));
			System.out.format("%-20s", df2.format(countries.get(i).getCostOfLiving()));
			System.out.format("%-20s", df3.format(countries.get(i).getMonthTemperature()));
			System.out.println();
			countryNames.add(countries.get(i).getName());
		}
		System.out.println();
		
		return countryNames;
	}

}