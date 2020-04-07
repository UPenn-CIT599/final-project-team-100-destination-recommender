
public class SiteRunner {
	
	public static void main(String[] args) {
		
		SiteAnalysis siteAnalysis = new SiteAnalysis(FileReader.readCSV());
		System.out.println(FileReader.readCSV());
		System.out.println(siteAnalysis.getCountryWithMostSites(3));
		
	}

}
