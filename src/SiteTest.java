import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SiteTest {
	
	SiteScoreCalc testSite = new SiteScoreCalc(FileReader.readCSV());

	/**
	 * tests to see if the countries if the most sites are recorded properly
	 */
	
	@Test
	void test() {
		ArrayList<String> classAnswer = testSite.getCountryWithMostSites(3);
		assertEquals(classAnswer, Arrays.asList("Italy", "Spain", "Germany"));
		
	}

}
