import java.util.ArrayList;
import java.util.Scanner;

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

		Scanner s = new Scanner(System.in);
		System.out.println("How important are sites?");
		double siteWeight = s.nextDouble();
		System.out.println("How important is cost?");
		double costWeight = s.nextDouble();
		System.out.println("How important is weather?");
		double weatherWeight = s.nextDouble();		
		System.out.println("What is your ideal temperature?");
		double idealTemp = s.nextDouble();

		
		
		ArrayList<CountryScore> cs = ca.applyWeights(siteWeight, costWeight, weatherWeight, idealTemp);
		int number = 5;
		System.out.println("We recommend the following " + number + " destinations");
		System.out.println(ca.sortCountriesByTotalScore(cs, number));
		

		

	}

}
