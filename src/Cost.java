
public class Cost {

	private String country;
	private double costOfLiving;

	/**
	 * This is the constructor
	 * 
	 * @param country
	 * @param costOfLiving
	 */
	public Cost(String country, double costOfLiving) {

		this.country = country;
		this.costOfLiving = costOfLiving;
	}

	/**
	 * Returns all the countries from the CSV
	 * 
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Returns the cost of living from the CSV for each country
	 * 
	 * @return
	 */
	public double getCostOfLiving() {
		return costOfLiving;
	}
}
