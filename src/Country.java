
public class Country implements Comparable {
	
	private String name;
	private double numSites;
	private double costOfLiving;
	private double monthTemperature;
	private double siteScore;
	private double costScore;
	private double weatherScore;
	private double totalScore;
	
	public Country(String name, double numSites, double costOfLiving, double monthTemperature, double siteScore,
	        double costScore, double weatherScore, double totalScore) {
		this.name = name;
		this.numSites = numSites;
		this.costOfLiving = costOfLiving;
		this.monthTemperature = monthTemperature;
		this.siteScore = siteScore;
		this.costScore = costScore;
		this.weatherScore = weatherScore;
		this.totalScore = totalScore;
		
	}
	
    public String getName() {
		return name;
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

    public void setSiteScore(double siteScore) {
        this.siteScore = siteScore;
    }

    public void setCostScore(double costScore) {
        this.costScore = costScore;
    }

    public void setWeatherScore(double weatherScore) {
        this.weatherScore = weatherScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * Comparator method which sorts the Country objects by the totalScore
     * @param otherCountry
     * @return
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
