import java.util.ArrayList;
import java.util.HashMap;

public class DestinationRecommender {

	/**
	 * Takes in 3 HashMaps of countries with their costScore, weatherScore and
	 * siteScore and returns a HashMap with a combined score based the weight
	 * provided by the user
	 * 
	 * @param costScore
	 * @param weatherScore
	 * @param siteScore
	 * @return
	 */
	public HashMap<String, Double> combinedScores(HashMap<String, Double> costScore,
			HashMap<String, Double> weatherScore, HashMap<String, Double> siteScore, Double costWeight,
			Double weatherWeight, Double siteWeight) {

		HashMap<String, Double> combinedScoresMap = new HashMap<String, Double>();

		return combinedScoresMap;
	}

	/**
	 * Takes in a HashMap of Countries and their respective scores and an integer
	 * topN and returns an ArrayList of topN Countries
	 * 
	 * @param combinedScores
	 * @return
	 */
	public ArrayList<String> recommendedList(HashMap<String, Double> combinedScores, Integer topN) {

		ArrayList<String> recommendedListArray = new ArrayList<String>();

		return recommendedListArray;
	}
}
