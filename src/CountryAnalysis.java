import java.util.ArrayList;

public class CountryAnalysis {

	FileReader fr = new FileReader();

	public ArrayList<CountryScore> applyWeights(double siteWeight, double costWeight, double weatherWeight,
			double userInputTemp) {
		ArrayList<Country> countries = fr.readCSV();
		ArrayList<CountryScore> countryScores = new ArrayList<CountryScore>();

		for (Country country : countries) {
			String name = country.getName();
			double siteScore = siteWeight * country.getNumSites();
			double costScore = costWeight * country.getCostOfLiving();
			double weatherScore = Math.abs(weatherWeight * country.getTemperature() - userInputTemp);
			double totalScore = siteScore + costScore + weatherScore;
			CountryScore countryScore = new CountryScore(name, siteScore, costScore, weatherScore, totalScore);
			countryScores.add(countryScore);
		}

		return countryScores;

	}

	public ArrayList<String> getTravelDestinations(ArrayList<CountryScore> totalScoreOfEachCountry, int topN) {
		
		ArrayList<String> travelRecommendations = new ArrayList<String>();

		for (int i = 0; i < topN; i++) {
			for (CountryScore countryScore : totalScoreOfEachCountry) {
				double maxTotalScore = -5;
				if (countryScore.getTotalScore() > maxTotalScore) {
					maxTotalScore = countryScore.getTotalScore();
				}
				String countryName = countryScore.getName();
				travelRecommendations.add(countryName);
				totalScoreOfEachCountry.remove(countryScore);
			}

		}

		return null;
	}

}
