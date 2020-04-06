
public class SiteRunner {
	
	public static void main(String[] args) {
		
		SiteAnalysis siteAnalysis = new SiteAnalysis(SiteReader.readCSV());
		System.out.println(SiteReader.readCSV());
		System.out.println(siteAnalysis.getCountryWithMostSites());
		
	}

}
