
public class Country implements Comparable {

	private String name;
	private double numSites;
	private double costOfLiving;
	private double monthTemperature;
	private double totalScore;

	/**
	 * This is the constructor
	 * 
	 * @param name
	 * @param numSites
	 * @param costOfLiving
	 * @param monthTemperature
	 * @param totalScore
	 */
	public Country(String name, double numSites, double costOfLiving, double monthTemperature, double totalScore) {
		this.name = name;
		this.numSites = numSites;
		this.costOfLiving = costOfLiving;
		this.monthTemperature = monthTemperature;
		this.totalScore = totalScore;
	}

	/**
	 * gets the country's name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * gets the number of sites in the country
	 * 
	 * @return
	 */
	public double getNumSites() {
		return numSites;
	}

	/**
	 * gets the country's cost of living
	 * 
	 * @return
	 */
	public double getCostOfLiving() {
		return costOfLiving;
	}

	/**
	 * gets the country's temperature for the month
	 * 
	 * @return
	 */
	public double getMonthTemperature() {
		return monthTemperature;
	}

	/**
	 * sets the totalScore of the country object
	 * 
	 * @param totalScore
	 */
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}

	/**
	 * returns the totalScore of the country object
	 * 
	 * @return
	 */
	public double getTotalScore() {
		return totalScore;
	}

	/**
	 * Comparator method which sorts the countryScore objects by the totalScore
	 */
	@Override
	public int compareTo(Object otherCountry) {
		Country myOtherCountry = (Country) otherCountry;
		if (myOtherCountry.getTotalScore() == totalScore) {
			return 0;
		} else if (myOtherCountry.getTotalScore() - totalScore > 0) {
			return -1;
		} else {
			return 1;
		}
	}

}
