import java.util.ArrayList;

public class CountryRunner {

	public static void main(String[] args) {
//		FileReader r = new FileReader();
//		System.out.println(r.readCSV().get(5).getName());
//		System.out.println(r.readCSV().get(5).getNumSites());
//		System.out.println(r.readCSV().get(5).getCostOfLiving());
//		System.out.println(r.readCSV().get(5).getTemperature());
		
		CountryAnalysis ca = new CountryAnalysis();
//		System.out.println(ca.applyWeights(0.7, 0.3, 0.4, 90).get(5).getCostScore());
//		System.out.println(ca.applyWeights(0.7, 0.3, 0.4, 90).get(5).getSiteScore());
//		System.out.println(ca.applyWeights(0.7, 0.3, 0.4, 20).get(5).getWeatherScore());
//		System.out.println(ca.applyWeights(0.7, 0.3, 0.4, 90).get(5).getTotalScore());
//
//		System.out.println(ca.applyWeights(0.7, 0.3, 0.4, 90).get(11).getTotalScore());
		ArrayList<CountryScore> cs = ca.applyWeights(0.2, 1, 0.2, 72);

		System.out.println(ca.sortCountriesByTotalScore(cs, 5));
		

		

	}

}
