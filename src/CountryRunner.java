import java.util.ArrayList;
import java.util.Scanner;

public class CountryRunner {

	public static void main(String[] args) {

		CountryAnalysis ca = new CountryAnalysis();

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
