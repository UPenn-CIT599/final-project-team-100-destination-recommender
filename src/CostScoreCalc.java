import java.util.ArrayList;
import java.util.HashMap;

public class CostScoreCalc {

	ArrayList<Cost> cost = new ArrayList<Cost>();

	/**
	 * This is the constructor
	 * 
	 * @param cost
	 */
	public CostScoreCalc(ArrayList<Cost> cost) {
		this.cost = cost;

	}

	/**
	 * Returns the ArrayList cost
	 * 
	 * @return
	 */
	public ArrayList<Cost> getcost() {
		return cost;
	}

	/**
	 * Returns a HashMap with the Cost Score by country as a double. Assigning a
	 * score based on the Cost of Living Index as follows: 1 if cost of living is
	 * less than 30, 2 if cost of living is 30-40, 3 if cost of living is 40-50, 4
	 * if cost of living is 50-60, 5 if cost of living is 60-70, 6 if cost of living
	 * is 70-80, 7 if cost of living is 80-90, 8 if cost of living is 90-100, 9 if
	 * cost of living is 100-110, 10 if cost of living is greater than or equal to
	 * 110,
	 * 
	 * @param cost
	 * @return
	 */
	public HashMap<String, Double> CostScore(ArrayList<Cost> cost) {

		HashMap<String, Double> costScore = new HashMap<String, Double>();

		for (Cost costOfLiving : cost) {

			double col = costOfLiving.getCostOfLiving();

			if (col < 30) {
				costScore.put(costOfLiving.getCountry(), 1.0);
			}

			else if (col < 110) {

				for (int i = 2; i < 10; i++) {

					if (col >= 30 + (i - 2) * 10 && col < 40 + (i - 2) * 10) {
						costScore.put(costOfLiving.getCountry(), ((double) i));
					}

				}

			}

			else {
				costScore.put(costOfLiving.getCountry(), 10.0);
			}
		}

		return costScore;

	}

	/**
	 * Returns an ArrayList of countries based on cost of living in ascending order
	 * 
	 * @param lines
	 * @return
	 */
	public ArrayList<String> CostList(ArrayList<Data> lines) {

		HashMap<String, Double> costOfLiving = new HashMap<String, Double>();
		
		for (Data line : lines) {
			costOfLiving.put(line.getCountry(), line.getCostOfLiving());
		}
		
		// Need to sort in order
		
		return null;
	}
}
