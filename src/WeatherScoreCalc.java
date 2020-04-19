import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WeatherScoreCalc {

	ArrayList<Data> weather = new ArrayList<Data>();

	/**
	 * This is the constructor
	 * 
	 * @param arrayList
	 */
	public WeatherScoreCalc(ArrayList<Data> arrayList) {
		this.weather = arrayList;

	}

	/**
	 * Returns the ArrayList weather
	 * 
	 * @return
	 */
	public ArrayList<Data> getWeather() {
		return weather;
	}

	/**
	 * Returns a HashMap with the Weather Score by country as a double
	 * @param weather
	 * @return
	 */
	public HashMap<String, Double> WeatherScore(ArrayList<Data> data) {

		HashMap<String, Double> weatherScore = new HashMap<String, Double>();
		
		

		return weatherScore;
	}
	
	public ArrayList<String> WeatherList(ArrayList<Data> lines, double tempPreference, String monthPreference) {
		
		HashMap <String, Double> weatherDifference = new HashMap <String, Double>();

		
		for (Data line : lines) {
			
			double difference = 0;
			
			if (monthPreference.equals("January")) {
				difference = tempPreference - line.getJanTemp();
			}
			else if (monthPreference.equals("February")) {
				difference = tempPreference - line.getFebTemp();
			}
			else if (monthPreference.equals("March")) {
				difference = tempPreference - line.getMarchTemp();
			}
			else if (monthPreference.equals("April")) {
				difference = tempPreference - line.getAprilTemp();
			}
			else if (monthPreference.equals("May")) {
				difference = tempPreference - line.getMayTemp();
			}
			else if (monthPreference.equals("June")) {
				difference = tempPreference - line.getJuneTemp();
			}
			else if (monthPreference.equals("July")) {
				difference = tempPreference - line.getJulyTemp();
			}
			else if (monthPreference.equals("August")) {
				difference = tempPreference - line.getAugTemp();
			}
			else if (monthPreference.equals("September")) {
				difference = tempPreference - line.getSeptTemp();
			}
			else if (monthPreference.equals("October")) {
				difference = tempPreference - line.getOctTemp();
			}
			else if (monthPreference.equals("November")) {
				difference = tempPreference - line.getNovTemp();
			}
			else {
				difference = tempPreference - line.getDecTemp();
			}
			
			double absoluteDifference = Math.abs(difference); // absolute value of the difference
			weatherDifference.put(line.getCountry(), absoluteDifference);
		}
		
		System.out.println(weatherDifference);
		// Collections.sort((List<T>) weatherDifference);
		return null;
	}

}
