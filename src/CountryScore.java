import java.util.ArrayList;

public class CountryScore implements Comparable{
	
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
		if (myOtherCountryScore.getTotalScore() == totalScore) return 0;
		if (myOtherCountryScore.getTotalScore() - totalScore > 0) return -1;
		return 1;
	}
	
	
	
//	public static void main(String[] args) {
//		CountryScore cs1 = new CountryScore("Belgium", 1,1,1,3);
//		CountryScore cs2 = new CountryScore("Switzerland", 1,1,1,7);
//		CountryScore cs3 = new CountryScore("France", 1,1,1,4);
//		CountryScore cs4 = new CountryScore("UK", 1,1,1,-2);
//		
//		ArrayList<CountryScore> csArray = new ArrayList<CountryScore>();
//		csArray.add(cs1);
//		csArray.add(cs2);
//		csArray.add(cs3);
//		csArray.add(cs4);
//		
//		csArray.sort(null);
//		
//		System.out.println(csArray.get(0).getName());
//		for (CountryScore csScore : csArray) {
//			System.out.println(csScore.getName());
//		}
//		
//	}


}
