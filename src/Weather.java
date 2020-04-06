
public class Weather {

	private String country;
	private String month;
	private double avgTemp;
	private double avgPrecipitation;

	/**
	 * This is the constructor
	 * 
	 * @param country
	 * @param month
	 * @param avgTemp
	 * @param avgPrecipitation
	 */
	public Weather(String country, String month, double avgTemp, double avgPrecipitation) {

		this.country = country;
		this.month = month;
		this.avgTemp = avgTemp;
		this.avgPrecipitation = avgPrecipitation;
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
	 * Returns all the months from the CSV
	 * 
	 * @return
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Returns all the average temperatures from the CSV
	 * 
	 * @return
	 */
	public double getAvgTemp() {
		return avgTemp;
	}

	/**
	 * Returns all the average precipitation from the CSV
	 * 
	 * @return
	 */
	public double getAvgPrecipitation() {
		return avgPrecipitation;
	}
}
