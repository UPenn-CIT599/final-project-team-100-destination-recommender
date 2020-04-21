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

		for (int i = 0; i < ryan.size() && i < topN; i++) {
			System.out.println(ryan.get(i).getName() + " " + ryan.get(i).getTotalScore());
			System.out.println(ryan.get(i).getSiteScore());
			System.out.println(ryan.get(i).getCostScore());
			System.out.println(ryan.get(i).getWeatherScore());
			countryNames.add(ryan.get(i).getName());
		}

		return countryNames;
	}

//	public ArrayList<String> getTravelDestinations(ArrayList<CountryScore> totalScoreOfEachCountry, int topN) {
//
//		ArrayList<String> travelRecommendations = new ArrayList<String>();
//
//		for (int i = 0; i < topN; i++) {
//			double maxTotalScore = -5;
//			for (int j = 0; j < totalScoreOfEachCountry.size(); j++) {
//				if (j > maxTotalScore) {
//					maxTotalScore = j;
//				}
//				String countryName = countryScore.getName();
//				travelRecommendations.add(countryName);
//				totalScoreOfEachCountry.remove(countryScore);
//			}
//		}
//
//		return null;
//	}

}
