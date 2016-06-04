// Country name & URL pairs 

package countryKeyFiguresTest.shared;

public class Country {
	private String CountryName;
	private String CountryURL;
	
	public Country(String CountryName, String CountryURL){
		this.CountryName=CountryName;
		this.CountryURL=CountryURL;
	}
	
	public String getCountryName(){
		return CountryName;
	}
	public String getCountryURL(){
		return CountryURL;
	}
}
