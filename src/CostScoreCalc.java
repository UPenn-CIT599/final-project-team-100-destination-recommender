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
			if (costOfLiving.getCostOfLiving() < 30) {
				costScore.put(costOfLiving.getCountry(), 1.0);
			}
			
			else if (costOfLiving.getCostOfLiving() >= 30 && costOfLiving.getCostOfLiving() < 40) {
				costScore.put(costOfLiving.getCountry(), 2.0);
			}
			
			else if (costOfLiving.getCostOfLiving() >= 40 && costOfLiving.getCostOfLiving() < 50) {
				costScore.put(costOfLiving.getCountry(), 3.0);
			}
		
			else if (costOfLiving.getCostOfLiving() >= 50 && costOfLiving.getCostOfLiving() < 60) {
				costScore.put(costOfLiving.getCountry(), 4.0);
			}
		
			else if (costOfLiving.getCostOfLiving() >= 60 && costOfLiving.getCostOfLiving() < 70) {
				costScore.put(costOfLiving.getCountry(), 5.0);
			}
		
			else if (costOfLiving.getCostOfLiving() >= 70 && costOfLiving.getCostOfLiving() < 80) {
				costScore.put(costOfLiving.getCountry(), 6.0);
			}
		
			else if (costOfLiving.getCostOfLiving() >= 80 && costOfLiving.getCostOfLiving() < 90) {
				costScore.put(costOfLiving.getCountry(), 7.0);
			}
		
			else if (costOfLiving.getCostOfLiving() >= 90 && costOfLiving.getCostOfLiving() < 100) {
				costScore.put(costOfLiving.getCountry(), 8.0);
			}
		
			else if (costOfLiving.getCostOfLiving() >= 100 && costOfLiving.getCostOfLiving() < 110) {
				costScore.put(costOfLiving.getCountry(), 9.0);
			}
		
			else {
				costScore.put(costOfLiving.getCountry(), 10.0);
			}
		}
		
		return costScore;
		
	}

}
