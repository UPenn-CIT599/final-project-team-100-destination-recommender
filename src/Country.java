
public class Country implements Comparable{
	
	private String name;
	private double numSites;
	private double costOfLiving;
	private double monthTemperature;
	
//	private String name;
	private double siteScore;
	private double costScore;
	private double weatherScore;
	private double totalScore;
	
	public Country(String name, double numSites, double costOfLiving, double monthTemperature, double totalScore) {
		this.name = name;
		this.numSites = numSites;
		this.costOfLiving = costOfLiving;
		this.monthTemperature = monthTemperature;

//		
//		this.siteScore = siteScore;
//		this.costScore = costScore;
//		this.weatherScore = weatherScore;
		this.totalScore = totalScore;
	}
	

	public String getName() {
		return name;
	}
	
	public void setNumSites(double numSites) {
		this.numSites = numSites;
	}
	
	public void setCostOfLiving(double costOfLiving) {
		this.costOfLiving = costOfLiving;
	}
	
	public void monthTemperature(double monthTemperature) {
		this.monthTemperature = monthTemperature;
	}
	
	public double getNumSites() {
		return numSites;
	}
	
	public double getCostOfLiving() {
		return costOfLiving;
	}
	
	public double getMonthTemperature() {
		return monthTemperature;
	}
	
//	public void setSiteScore(double siteScore) {
//		this.siteScore = siteScore;
//	}
//
//	public void setCostScore(double costScore) {
//		this.costScore = costScore;
//	}
//
//	public void setWeatherScore(double weatherScore) {
//		this.weatherScore = weatherScore;
//	}

	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	
	/**
	 * returns siteScore of the countryScore object
	 * @return
	 */
//	public double getSiteScore() {
//		return siteScore;
//	}
//	
//	/**
//	 * returns costScore of the countryScore object
//	 * @return
//	 */
//	public double getCostScore() {
//		return costScore;
//	}
//	
//	/**
//	 * returns weatherScore of the countryScore object
//	 * @return
//	 */
//	public double getWeatherScore() {
//		return weatherScore;
//	}
	
	/**
	 * returns the totalScore of the countryScore object
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
		}
		else if (myOtherCountry.getTotalScore() - totalScore > 0) {
			return -1;
		}
		else {
			return 1;
		}
	}

	
	
}
