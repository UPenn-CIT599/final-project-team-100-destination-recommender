
public class Country {
	
	private String name;
	private double siteScore;
	private double costScore;
	private double weatherScore;
	
	/**
	 * creates a country class where the site score, cost score and weather score
	 * will be passed into the parameters for each country
	 * @param name
	 * @param siteScore
	 * @param costScore
	 * @param weatherScore
	 */

	public Country(String name, double siteScore, double costScore, double weatherScore) {
		this.name = name;
		this.siteScore = siteScore;
		this.costScore = costScore;
		this.weatherScore = weatherScore;
	}
	
	/**
	 * list of getters
	 * @return
	 */
	
	public String getCountryName() {
		return name;
	}
	
	public double getSiteScore() {
		return siteScore;
	}
	
	public double getCostScore() {
		return costScore;
	}
	
	public double getWeatherScore() {
		return weatherScore;
	}
	
}
