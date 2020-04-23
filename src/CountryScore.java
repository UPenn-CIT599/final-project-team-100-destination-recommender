import java.util.ArrayList;

public class CountryScore implements Comparable {

	private String name;
	private double siteScore;
	private double costScore;
	private double weatherScore;
	private double totalScore;
	
	/**
	 * Class used to keep track of a countries name and associated scores based on user Input
	 * @param name
	 * @param siteScore
	 * @param costScore
	 * @param weatherScore
	 * @param totalScore
	 */

	public CountryScore(String name, double siteScore, double costScore, double weatherScore, double totalScore) {
		this.name = name;
		this.siteScore = siteScore;
		this.costScore = costScore;
		this.weatherScore = weatherScore;
		this.totalScore = totalScore;
	}

	/**
	 * returns the name of the countyScore object
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns siteScore of the countryScore object
	 * @return
	 */
	public double getSiteScore() {
		return siteScore;
	}
	
	/**
	 * returns costScore of the countryScore object
	 * @return
	 */
	public double getCostScore() {
		return costScore;
	}
	
	/**
	 * returns weatherScore of the countryScore object
	 * @return
	 */
	public double getWeatherScore() {
		return weatherScore;
	}
	
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
	public int compareTo(Object otherCountryScore) {
		CountryScore myOtherCountryScore = (CountryScore) otherCountryScore;
		if (myOtherCountryScore.getTotalScore() == totalScore) {
			return 0;
		}
		else if (myOtherCountryScore.getTotalScore() - totalScore > 0) {
			return -1;
		}
		else {
			return 1;
		}
	}

}
