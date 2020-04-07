
public class SiteRunner {
	
	public static void main(String[] args) {
		
		SiteScoreCalc siteAnalysis = new SiteScoreCalc(FileReader.readCSV());
		System.out.println(FileReader.readCSV());
		System.out.println(siteAnalysis.getCountryWithMostSites(3));
		
	}

}
