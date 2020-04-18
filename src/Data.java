
public class Data {

	private String country;
	private int siteCount;
	private double costOfLiving;
	private double janTemp;
	private double febTemp;
	private double marchTemp;
	private double aprilTemp;
	private double mayTemp;
	private double juneTemp;
	private double julyTemp;
	private double augTemp;
	private double septTemp;
	private double octTemp;
	private double novTemp;
	private double decTemp;

	/**
	 * This is the constructor
	 * 
	 * @param country
	 * @param siteCount
	 * @param costOfLiving
	 * @param janTemp
	 * @param febTemp
	 * @param marchTemp
	 * @param aprilTemp
	 * @param mayTemp
	 * @param juneTemp
	 * @param julyTemp
	 * @param augTemp
	 * @param septTemp
	 * @param octTemp
	 * @param novTemp
	 * @param decTemp
	 */
	public Data(String country, int siteCount, double costOfLiving, double janTemp, double febTemp, double marchTemp,
			double aprilTemp, double mayTemp, double juneTemp, double julyTemp, double augTemp, double septTemp,
			double octTemp, double novTemp, double decTemp) {

		this.country = country;
		this.siteCount = siteCount;
		this.costOfLiving = costOfLiving;
		this.janTemp = janTemp;
		this.febTemp = febTemp;
		this.marchTemp = marchTemp;
		this.aprilTemp = aprilTemp;
		this.mayTemp = mayTemp;
		this.juneTemp = juneTemp;
		this.julyTemp = julyTemp;
		this.augTemp = augTemp;
		this.septTemp = septTemp;
		this.octTemp = octTemp;
		this.novTemp = novTemp;
		this.decTemp = decTemp;
	}

	/**
	 * List of getters
	 * 
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	public int getSiteCount() {
		return siteCount;
	}

	public double getCostOfLiving() {
		return costOfLiving;
	}

	public double getJanTemp() {
		return janTemp;
	}

	public double getFebTemp() {
		return febTemp;
	}

	public double getMarchTemp() {
		return marchTemp;
	}

	public double getAprilTemp() {
		return aprilTemp;
	}

	public double getMayTemp() {
		return mayTemp;
	}

	public double getJuneTemp() {
		return juneTemp;
	}

	public double getJulyTemp() {
		return julyTemp;
	}

	public double getAugTemp() {
		return augTemp;
	}

	public double getSeptTemp() {
		return septTemp;
	}

	public double getOctTemp() {
		return octTemp;
	}

	public double getNovTemp() {
		return novTemp;
	}

	public double getDecTemp() {
		return decTemp;
	}

}
