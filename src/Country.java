
public class Country {
	
	private String name;
	private double numSites;
	private double costOfLiving;
	private double monthTemperature;
	
	public Country(String name, double numSites, double costOfLiving, double monthTemperature) {
		this.name = name;
		this.numSites = numSites;
		this.costOfLiving = costOfLiving;
		this.monthTemperature = monthTemperature;
		this.monthTemperature = monthTemperature;
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
	
}
