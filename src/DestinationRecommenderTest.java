import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DestinationRecommenderTest {

	CountryAnalysis countryAnalysis = new CountryAnalysis();
	FileReader fileReader = new FileReader();
	private static final DecimalFormat df2 = new DecimalFormat("#.##");

	@Test
	void readCSVTest1() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = fileReader.readCSV("June");
		Country expectedCountry = new Country("Czech Republic", 14, 46.15, 58.5, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(10).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(10).getNumSites(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(10).getCostOfLiving(),
				"Wrong country name");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(10).getMonthTemperature(),
				"Wrong country name");
	}

	@Test
	void readCSVTest2() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = fileReader.readCSV("March");
		Country expectedCountry = new Country("Ireland", 2, 75.91, 44.3, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(20).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(20).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(20).getCostOfLiving(),
				"Wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(20).getMonthTemperature(),
				"Wrong temperature");
	}

	@Test
	void readCSVTest3() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = fileReader.readCSV("December");
		Country expectedCountry = new Country("Malta", 3, 67.46, 57.0, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(27).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(27).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(27).getCostOfLiving(),
				"Wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(27).getMonthTemperature(),
				"Wrong temperature");
	}
	
	@Test
	void readCSVTest4() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = fileReader.readCSV("January");
		Country expectedCountry = new Country("Italy", 56, 67.26, 42.2, 0);
		expectedAnswer.add(expectedCountry);
		assertEquals(expectedAnswer.get(0).getName(), actualAnswer.get(22).getName(), "Wrong country name");
		assertEquals(expectedAnswer.get(0).getNumSites(), actualAnswer.get(22).getNumSites(), "Wrong number of sites");
		assertEquals(expectedAnswer.get(0).getCostOfLiving(), actualAnswer.get(22).getCostOfLiving(),
				"Wrong cost of living");
		assertEquals(expectedAnswer.get(0).getMonthTemperature(), actualAnswer.get(22).getMonthTemperature(),
				"Wrong temperature");
	}
	
	

	@Test
	void applyWeightsTest1() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		ArrayList<Country> actualAnswer = new ArrayList<Country>();
		Country expectedAzerbaijan = new Country("Azerbaijan", 3, 29.92, 33.8, 4.83);
		expectedAnswer.add(expectedAzerbaijan);
		actualAnswer = fileReader.readCSV("January");
		actualAnswer = countryAnalysis.applyWeights(actualAnswer, 5, 6, 7, 62, "January");
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()), df2.format(actualAnswer.get(3).getTotalScore()),
				"Wrong total score");
	}

	@Test
	void applyWeightsTest2() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		String month = "August";
		ArrayList<Country> actualAnswer = fileReader.readCSV(month);
		Country expectedCountry = new Country("France", 45, 74.14, 66.5, 3.46);
		expectedAnswer.add(expectedCountry);
		actualAnswer = countryAnalysis.applyWeights(actualAnswer, 3, 8, 4, 87, month);
		System.out.println("dkjfdls " + actualAnswer.get(14).getTotalScore());
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()),
				df2.format(actualAnswer.get(14).getTotalScore()), "Wrong total score");
	}

	@Test
	void applyWeightsTest3() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		String month = "September";
		ArrayList<Country> actualAnswer = fileReader.readCSV(month);
		Country expectedCountry = new Country("Macedonia", 1, 31.59, 65, 3.05);
		expectedAnswer.add(expectedCountry);
		
		actualAnswer = countryAnalysis.applyWeights(actualAnswer, 3, 9, 5, 53, month);
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()),
				df2.format(actualAnswer.get(26).getTotalScore()), "Wrong total score");
	}
	
	@Test
	void applyWeightsTest4() {
//		fail("Not yet implemented");	
		ArrayList<Country> expectedAnswer = new ArrayList<Country>();
		String month = "March";
		ArrayList<Country> actualAnswer = fileReader.readCSV(month);
		Country expectedCountry = new Country("Norway", 8, 101.43, 30.2, 5.49); // Need to check totalScore
		expectedAnswer.add(expectedCountry);
		
		actualAnswer = countryAnalysis.applyWeights(actualAnswer, 2, 3, 6, 62, month);
		assertEquals(df2.format(expectedAnswer.get(0).getTotalScore()),
				df2.format(actualAnswer.get(26).getTotalScore()), "Wrong total score");
	}

	@Test
	void sortCountriesByTotalScore1() {
		String month = "January";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Spain"); expectedAnswer.add("Portugal"); expectedAnswer.add("Cyprus");
		ArrayList<Country> actualAnswerCountry = countryAnalysis.applyWeights(fileReader.readCSV(month), 2, 7, 9, 55, month);
		System.out.println(actualAnswerCountry);
		ArrayList<String> actualAnswer = countryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 3);
		assertEquals(expectedAnswer, actualAnswer);

	}
	
	@Test
	void sortCountriesByTotalScore2() {
		String month = "March";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Spain"); expectedAnswer.add("Italy");
		ArrayList<Country> actualAnswerCountry = countryAnalysis.applyWeights(fileReader.readCSV(month), 4, 9, 2, 64, month);
		System.out.println(actualAnswerCountry);
		ArrayList<String> actualAnswer = countryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 2);
		assertEquals(expectedAnswer, actualAnswer);

	}
	
	@Test
	void sortCountriesByTotalScore3() {
		String month = "May";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Armenia"); expectedAnswer.add("Poland"); expectedAnswer.add("Russia"); expectedAnswer.add("Slovakia");
		ArrayList<Country> actualAnswerCountry = countryAnalysis.applyWeights(fileReader.readCSV(month), 1, 5, 8, 50, month);
		System.out.println(actualAnswerCountry);
		ArrayList<String> actualAnswer = countryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 4);
		assertEquals(expectedAnswer, actualAnswer);

	}
	
	@Test
	void sortCountriesByTotalScore4() {
		String month = "July";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Spain"); expectedAnswer.add("Italy"); expectedAnswer.add("Germany"); expectedAnswer.add("Russia"); expectedAnswer.add("France");
		ArrayList<Country> actualAnswerCountry = countryAnalysis.applyWeights(fileReader.readCSV(month), 7, 10, 4, 70, month);
		System.out.println(actualAnswerCountry);
		ArrayList<String> actualAnswer = countryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 5);
		assertEquals(expectedAnswer, actualAnswer);

	}
	
	@Test
	void sortCountriesByTotalScore5() {
		String month = "October";
		ArrayList<String> expectedAnswer = new ArrayList<String>();
		expectedAnswer.add("Russia");
		ArrayList<Country> actualAnswerCountry = countryAnalysis.applyWeights(fileReader.readCSV(month), 3, 8, 9, 30, month);
		System.out.println(actualAnswerCountry);
		ArrayList<String> actualAnswer = countryAnalysis.sortCountriesByTotalScore(actualAnswerCountry, 1);
		assertEquals(expectedAnswer, actualAnswer);

	}

}
