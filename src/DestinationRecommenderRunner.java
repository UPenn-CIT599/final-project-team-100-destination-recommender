public class DestinationRecommenderRunner {

    /**
     * Main method where the Destination Recommender runs
     *
     */
	public static void main(String[] args) {
		
		
		SiteScoreCalc siteAnalysis = new SiteScoreCalc(FileReader.readCSV());
		FileReader.readCSV();
		System.out.println(siteAnalysis.getCountryWithMostSites(5));
        
        // instance of DestinationRecommender class
        // DestinationRecommender DR = new DestinationRecommender();
		
		WeatherScoreCalc dataAnalysis = new WeatherScoreCalc(FileReader.readDataCSV());
		
		System.out.println(dataAnalysis.WeatherList(FileReader.readDataCSV(), 75, "May"));
		
		
	}

}
