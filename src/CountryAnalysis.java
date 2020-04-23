import java.text.DecimalFormat;
import java.util.ArrayList;

public class CountryAnalysis {

	private FileReader fr = new FileReader();
	private double siteWeight;
	private double costWeight;
	private double weatherWeight;
	private double userInputTemp;
	private ArrayList<Country> countries;

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

	public ArrayList<CountryScore> applyWeights(double siteWeight, double costWeight, double weatherWeight,
			double userInputTemp, String month) {

		ArrayList<Country> countries = fr.readCSV(month);
		this.countries = countries;
		ArrayList<CountryScore> countryScores = new ArrayList<CountryScore>();

		// loops through arrayList of countries to create countryScore objects
		for (Country country : countries) {
			String name = country.getName(); // name of country and countryScore are the same
			double siteScore = siteWeight * country.getNumSites(); // higher siteScore is preferred
			double costScore = costWeight * country.getCostOfLiving(); // lower costScore is preferred
			double weatherScore = weatherWeight * Math.abs(country.getMonthTemperature() - userInputTemp); // lower weatherScore is preferred
			double totalScore = -siteScore + costScore + weatherScore; // want to return countries with LOWEST total score! Hence the -siteScore 
			CountryScore countryScore = new CountryScore(name, siteScore, costScore, weatherScore, totalScore); 
			countryScores.add(countryScore);
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

	public ArrayList<String> sortCountriesByTotalScore(ArrayList<CountryScore> csArray, int topN) {
		
		ArrayList<String> countryNames = new ArrayList<String>();
		csArray.sort(null); // using Comparator method to sort

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
			for (int j = 0; j < countries.size(); j++) {
				if (csArray.get(i).getName() == countries.get(j).getName()) {
					int index = i + 1;
					System.out.format("%-10s", index + ".");
					System.out.format("%-15s", countries.get(j).getName());
					System.out.format("%-20s", df1.format(countries.get(j).getNumSites()));
					System.out.format("%-20s", df2.format(countries.get(j).getCostOfLiving()));
					System.out.format("%-20s", df3.format(countries.get(j).getMonthTemperature()));
					System.out.println();
				}
			}
		}
		System.out.println();

		return countryNames;
	}

}