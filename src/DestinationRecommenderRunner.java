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
		
	}

}
