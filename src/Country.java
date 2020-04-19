
public class Country {
	
	private String name;
	private double numSites;
	private double costOfLiving;
	private double temperature;
	
	public Country(String name, double numSites, double costOfLiving, double temperature) {
		this.name = name;
		this.numSites = numSites;
		this.costOfLiving = costOfLiving;
		this.temperature = temperature;
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
	
	public double getTemperature() {
		return temperature;
	}


}
