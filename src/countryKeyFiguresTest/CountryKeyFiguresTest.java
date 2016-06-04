//////////////////////////////////////////////////////////////////////////////////////////////////////
// This is the main file of the test suite for project evaluation at crossover.com
// 
// Here is the summary of what it does:
// 1) Goto worldbank.org -> Data -> By Country -> High Income:
//	  - get for each country: GDP, Population, CO2
// 2) Go home, close browser
// 3) For top 3 countries:
//    - perform validation 
//	  - log GDP, Population, CO2 per country to stdout
//	  - export GDP, Population, CO2 per country to csv files in .\out sub-dir
//
// How to run: via Eclipse, Run->Run (Ctrl+F11), either this class, or through TestRunner one
//
//////////////////////////////////////////////////////////////////////////////////////////////////////

package countryKeyFiguresTest;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import countryKeyFiguresTest.shared.*; // misc utility classes
import countryKeyFiguresTest.pageObjects.CountryPage; // Page Object for Countries table page  
import countryKeyFiguresTest.pageObjects.WorldBankHomePage; // Page Object for web site's home page 

public class CountryKeyFiguresTest {

	static Logger logger = null;
	static InitWebDriver initWebDriver = null;
	static WorldBankHomePage worldBankHomePage = null;
	static CountryPage countryPage = null;
	ArrayList<Country> Countries = null; // country name & URL pairs, will be used to navigate to key value pairs
	
	static String [][] TopThreeCountries = new String[3][4]; // will store top three country key values
	static String [][] TopThreeCountries_values_expected = { // to store values expected from the config
		{"Andorra", "Andorra_GDP", "Andorra_Population", "Andorra_CO2"},
		{"Antigua_and_Barbuda", "Antigua_and_Barbuda_GDP", "Antigua_and_Barbuda_Population", "Antigua_and_Barbuda_CO2"},
		{"Argentina", "Argentina_GDP", "Argentina_Population", "Argentina_CO2"}
	};	
	
	@BeforeClass
	public static void initialize()  throws IOException {
		logger = new Logger();
		logger.LogMessage("Starting Country Key Figures Test Suite", "stdout");
		
		initWebDriver = new InitWebDriver();
		
		// read country key values expected from the config
		GetConfig properties = new GetConfig();	
		
		for (int i=0;i<3;i++) {
			for (int j=1;j<4;j++){
				TopThreeCountries_values_expected[i][j]=properties.getPropValue(TopThreeCountries_values_expected[i][j]);
			}			
		}	
	}
	
	@Before // test case prerequisites, navigate to high income country list page 
	public void gotoCountryTablePage () {	
		worldBankHomePage = PageFactory.initElements(initWebDriver.GetDriver(), WorldBankHomePage.class); // Test Step #1
		
		worldBankHomePage.gotoData(); // Test Step #2
		worldBankHomePage.gotoByCountry(); // Test Step #3
		worldBankHomePage.gotoHighIncome(); // Test Step #4
	}
	
	@Test
	public void testCountryKeyFigure () {		
		
		Countries = worldBankHomePage.getCountries();
		
		countryPage = PageFactory.initElements(initWebDriver.GetDriver(), CountryPage.class);		
		
		int i=0, j=0;

		// Test Steps #5-8
		for (Country currCountry : Countries) {	
			
			countryPage.openCountryPage(currCountry.getCountryURL()); // navigation back does not work, have to open the page every time
			
			// reading actual values
			if ((i<5)&&((i++%2)==0)){
				TopThreeCountries[j][0] = currCountry.getCountryName();
				TopThreeCountries[j][1] = countryPage.getGDP();
				TopThreeCountries[j][2] = countryPage.getPopulation();
				TopThreeCountries[j++][3] = countryPage.getCO2();
			} else if (!(i<5))  { 
				countryPage.getGDP();
				countryPage.getPopulation();
				countryPage.getCO2();
			}
		}
	    // validation 
		for (i=0;i<3;i++) {
			for (j=1;j<4;j++){
				assertEquals(TopThreeCountries_values_expected[i][j], TopThreeCountries[i][j]);
			}
		}	
	}
	
	@After
	public void FinishTest () {
		countryPage.gotoHome(); // Test Step #9
		initWebDriver.GetDriver().quit(); // Test Step #10		
	}
	
	@AfterClass
	public static void LogTestResults () {
		// Test Steps #11-13
		logger.LogResults(TopThreeCountries, "GDP", "stdout"); 
		logger.LogResults(TopThreeCountries, "Population", "stdout");
		logger.LogResults(TopThreeCountries, "CO2", "stdout");
		
		// Test Step #14
		logger.LogResults(TopThreeCountries, "GDP", "file");
		logger.LogResults(TopThreeCountries, "Population", "file");
		logger.LogResults(TopThreeCountries, "CO2", "file");
		
		logger.LogMessage("Country Key Figures Test Suite finished", "stdout");	
	}				
}

