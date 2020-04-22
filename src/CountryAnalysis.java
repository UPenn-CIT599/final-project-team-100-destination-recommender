import java.text.DecimalFormat;
import java.util.ArrayList;

public class CountryAnalysis {

	FileReader fr = new FileReader();
	double siteWeight;
	double costWeight;
	double weatherWeight;
	double userInputTemp;

	public static final DecimalFormat df1 = new DecimalFormat("#.00");
	public static final DecimalFormat df2 = new DecimalFormat("#.##");

	public ArrayList<CountryScore> applyWeights(double siteWeight, double costWeight, double weatherWeight,
			double userInputTemp, String month) {
		ArrayList<Country> countries = fr.readCSV(month);
		ArrayList<CountryScore> countryScores = new ArrayList<CountryScore>();

		this.siteWeight = siteWeight;
		this.costWeight = costWeight;
		this.weatherWeight = weatherWeight;
		this.userInputTemp = userInputTemp;

		for (Country country : countries) {
			String name = country.getName();
			double siteScore = siteWeight * country.getNumSites();
			double costScore = costWeight * country.getCostOfLiving();
			double weatherScore = weatherWeight * Math.abs(country.getMonthTemperature() - userInputTemp);
			double totalScore = -siteScore + costScore + weatherScore;
			CountryScore countryScore = new CountryScore(name, siteScore, costScore, weatherScore, totalScore);
			countryScores.add(countryScore);
//			System.out.println(countryScore.getName()+ "  " + countryScore.getTotalScore());
		}
//		System.out.println(countryScores.size());
		return countryScores;
	}

	public ArrayList<String> sortCountriesByTotalScore(ArrayList<CountryScore> ryan, int topN) {
		ArrayList<String> countryNames = new ArrayList<String>();
		ryan.sort(null);

		System.out.format("%-10s", "Rank");
		System.out.format("%-15s", "Country");
		System.out.format("%-20s", "Number of Sites");
		System.out.format("%-20s", "Cost Of Living");
		System.out.format("%-20s", "Average Temperature");
		System.out.println();
		System.out.format("%-75s", "-------------------------------------------------------------------------------------");
		System.out.println();

		for (int i = 0; i < ryan.size() && i < topN; i++) {
			int index = i+1;
			System.out.format("%-10s", index + ".");
			System.out.format("%-15s", ryan.get(i).getName());
			System.out.format("%-20s", df2.format(ryan.get(i).getSiteScore() / siteWeight));
			System.out.format("%-20s", df1.format(ryan.get(i).getCostScore() / costWeight));
			System.out.format("%-20s",
					df1.format(ryan.get(i).getWeatherScore() / weatherWeight + userInputTemp) + " F");
			System.out.println();


			countryNames.add(ryan.get(i).getName());
		}

		return countryNames;
	}

}
