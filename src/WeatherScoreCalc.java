import java.util.ArrayList;
import java.util.HashMap;

public class WeatherScoreCalc {

	ArrayList<Weather> weather = new ArrayList<Weather>();

	/**
	 * This is the constructor
	 * 
	 * @param weather
	 */
	public WeatherScoreCalc(ArrayList<Weather> weather) {
		this.weather = weather;

	}

	/**
	 * Returns the ArrayList weather
	 * 
	 * @return
	 */
	public ArrayList<Weather> getWeather() {
		return weather;
	}

	/**
	 * Returns a HashMap with the Weather Score by country as a double
	 * @param weather
	 * @return
	 */
	public HashMap<String, Double> WeatherScore(ArrayList<Weather> weather) {

		HashMap<String, Double> weatherScore = new HashMap<String, Double>();

		return weatherScore;
	}

}
