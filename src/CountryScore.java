
public class CountryScore {
	
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


}
