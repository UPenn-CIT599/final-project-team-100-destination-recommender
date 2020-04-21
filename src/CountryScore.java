import java.util.ArrayList;

public class CountryScore implements Comparable {

	private String name;
	private double siteScore;
	private double costScore;
	private double weatherScore;
	private double totalScore;

	public CountryScore(String name, double siteScore, double costScore, double weatherScore, double totalScore) {
		this.name = name;
		this.siteScore = siteScore;
		this.costScore = costScore;
		this.weatherScore = weatherScore;
		this.totalScore = totalScore;
	}

	public String getName() {
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

	public double getTotalScore() {
		return totalScore;
	}

	@Override
	public int compareTo(Object otherCountryScore) {
		CountryScore myOtherCountryScore = (CountryScore) otherCountryScore;
		if (myOtherCountryScore.getTotalScore() == totalScore)
			return 0;
		if (myOtherCountryScore.getTotalScore() - totalScore > 0)
			return -1;
		return 1;
	}

}
