import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DestinationRecommenderTest {

	private static final DecimalFormat df2 = new DecimalFormat("#.00");

	@Test // 1
	void getCountryNameTest() {
		Country expectedCountry = new Country("Croatia", 10, 49.70, 39.0, 0);
		String actualAnswer = expectedCountry.getName();
		assertEquals("Croatia", actualAnswer);
	}

	@Test // 2
	void getCountryNumSitesTest() {
		Country expectedCountry = new Country("Finland", 7, 70.29, 16.4, 0);
		double actualAnswer = expectedCountry.getNumSites();
		assertEquals(7, actualAnswer);
	}

	@Test // 3
	void getCostOfLivingTest() {
		Country expectedCountry = new Country("Montenegro", 4, 38.23, 39.2, 0);
		double actualAnswer = expectedCountry.getCostOfLiving();
		assertEquals(38.23, actualAnswer);
	}

	@Test // 4
	void getMonthTemperature() {
		Country expectedCountry = new Country("Switzerland", 12, 122.4, 29, 0);
		double actualAnswer = expectedCountry.getMonthTemperature();
		assertEquals(29, actualAnswer);
	}

	@Test // 5
	void getTotalScore() {
		Country expectedCountry = new Country("Slovakia", 7, 44.6, 24.6, 6.5);
		double actualAnswer = expectedCountry.getTotalScore();
		assertEquals(6.5, actualAnswer);
	}

	@Test // 6
	void compareTo() {
		Country expectedCountry = new Country("Slovakia", 7, 44.6, 24.6, 6.5);
		Country otherCountry = new Country("Switzerland", 12, 122.4, 29, 3.2);
		double actualAnswer = expectedCountry.compareTo(otherCountry);
		assertEquals(1, actualAnswer);
	}

	@Test // 7
	void readCSVTest1() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = FileReader.readCSV("June");
		Country expectedCountry = new Country("Czech Republic", 14, 46.15, 58.5, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(10).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(10).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(10).getCostOfLiving(),
				"wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(10).getMonthTemperature(),
				"Wrong temperature");
	}

	@Test // 8
	void readCSVTest2() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = FileReader.readCSV("March");
		Country expectedCountry = new Country("Ireland", 2, 75.91, 44.3, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(20).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(20).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(20).getCostOfLiving(),
				"Wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(20).getMonthTemperature(),
				"Wrong temperature");
	}

	@Test // 9
	void readCSVTest3() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = FileReader.readCSV("December");
		Country expectedCountry = new Country("Malta", 3, 67.46, 57.0, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(27).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(27).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(27).getCostOfLiving(),
				"Wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(27).getMonthTemperature(),
				"Wrong temperature");
	}

	@Test // 10
	void readCSVTest4() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = FileReader.readCSV("January");
		Country expectedCountry = new Country("Italy", 56, 67.26, 42.2, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(22).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(22).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(22).getCostOfLiving(),
				"Wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(22).getMonthTemperature(),
				"Wrong temperature");
	}

	@Test // 11
	void readCSVTest5() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = FileReader.readCSV("August");
		Country expectedCountry = new Country("Bosnia and Herzegovina", 3, 35.97, 66.9, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(6).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(6).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(6).getCostOfLiving(),
				"Wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(6).getMonthTemperature(),
				"Wrong temperature");
	}

	@Test // 12
	void applyWeightsTest1() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = new ArrayList<Country>();
		Country expectedAzerbaijan = new Country("Azerbaijan", 3, 29.92, 33.8, 4.83);
		expectedAnswer.add(expectedAzerbaijan);
		actualAnswer = FileReader.readCSV("January");
		actualAnswer = CountryAnalysis.applyWeights(actualAnswer, 5, 6, 7, 62, "January");
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()), df2.format(actualAnswer.get(3).getTotalScore()),
				"Wrong total score");
	}

	@Test // 13
	void applyWeightsTest2() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		String month = "August";
		ArrayList<Country> actualAnswer = FileReader.readCSV(month);
		Country expectedCountry = new Country("France", 45, 74.14, 66.5, 3.46);
		expectedAnswer.add(expectedCountry);
		actualAnswer = CountryAnalysis.applyWeights(actualAnswer, 3, 8, 4, 87, month);
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()),
				df2.format(actualAnswer.get(14).getTotalScore()), "Wrong total score");
	}

	@Test // 14
	void applyWeightsTest3() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		String month = "September";
		ArrayList<Country> actualAnswer = FileReader.readCSV(month);
		Country expectedCountry = new Country("Macedonia", 1, 31.59, 65, 3.05);
		expectedAnswer.add(expectedCountry);

		actualAnswer = CountryAnalysis.applyWeights(actualAnswer, 3, 9, 5, 53, month);
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()),
				df2.format(actualAnswer.get(26).getTotalScore()), "Wrong total score");
	}

	@Test // 15
	void applyWeightsTest4() {
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		String month = "March";
		ArrayList<Country> actualAnswer = FileReader.readCSV(month);
		Country expectedCountry = new Country("Norway", 8, 101.43, 30.2, 5.5); // Need to check totalScore
		expectedAnswer.add(expectedCountry);
		actualAnswer = CountryAnalysis.applyWeights(actualAnswer, 2, 3, 6, 62, month);
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()),
				df2.format(actualAnswer.get(31).getTotalScore()), "Wrong total score");
	}

	@Test // 16
	void sortCountriesByTotalScore1() {
		String month = "January";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Spain");
		expectedAnswer.add("Portugal");
		expectedAnswer.add("Cyprus");
		ArrayList<Country> actualAnswerCountry = CountryAnalysis.applyWeights(FileReader.readCSV(month), 2, 7, 9, 55,
				month);
		ArrayList<String> actualAnswer = CountryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 3);
		assertEquals(expectedAnswer, actualAnswer);

	}

	@Test // 17
	void sortCountriesByTotalScore2() {
		String month = "March";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Spain");
		expectedAnswer.add("Italy");
		ArrayList<Country> actualAnswerCountry = CountryAnalysis.applyWeights(FileReader.readCSV(month), 4, 9, 2, 64,
				month);
		ArrayList<String> actualAnswer = CountryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 2);
		assertEquals(expectedAnswer, actualAnswer);

	}

	@Test // 18
	void sortCountriesByTotalScore3() {
		String month = "May";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Armenia");
		expectedAnswer.add("Poland");
		expectedAnswer.add("Russia");
		expectedAnswer.add("Slovakia");
		ArrayList<Country> actualAnswerCountry = CountryAnalysis.applyWeights(FileReader.readCSV(month), 1, 5, 8, 50,
				month);
		ArrayList<String> actualAnswer = CountryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 4);
		assertEquals(expectedAnswer, actualAnswer);

	}

	@Test // 19
	void sortCountriesByTotalScore4() {
		String month = "July";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Spain");
		expectedAnswer.add("Italy");
		expectedAnswer.add("Germany");
		expectedAnswer.add("Russia");
		expectedAnswer.add("France");
		ArrayList<Country> actualAnswerCountry = CountryAnalysis.applyWeights(FileReader.readCSV(month), 7, 10, 4, 70,
				month);
		ArrayList<String> actualAnswer = CountryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 5);
		assertEquals(expectedAnswer, actualAnswer);

	}

	@Test // 20
	void sortCountriesByTotalScore5() {
		String month = "October";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Russia");
		ArrayList<Country> actualAnswerCountry = CountryAnalysis.applyWeights(FileReader.readCSV(month), 3, 8, 9, 30,
				month);
		ArrayList<String> actualAnswer = CountryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 1);
		assertEquals(expectedAnswer, actualAnswer);

	}

	@Test // 21
	void sortCountriesByTotalScore6() {
		String month = "November";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Italy");
		ArrayList<Country> actualAnswerCountry = CountryAnalysis.applyWeights(FileReader.readCSV(month), 5, 5, 5, 42,
				month);
		ArrayList<String> actualAnswer = CountryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 1);
		assertEquals(expectedAnswer, actualAnswer);

	}

}