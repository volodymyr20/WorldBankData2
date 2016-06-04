// Page Object for home page 

package countryKeyFiguresTest.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import countryKeyFiguresTest.shared.*;

public class WorldBankHomePage {

	private final String HOME_URL = "http://www.worldbank.org";

	WebDriver driver;
	
	//@FindBy(how=How.LINK_TEXT, using="Data") WebElement DataLink;
	
	@FindBy(how=How.XPATH, using=".//*[@id='hf_header_wrapper']/div/ul[2]/li[3]/a") WebElement DataLink;
	@FindBy(how=How.XPATH, using=".//*[@id='navigation']/div/ul[1]/li[1]/a") WebElement ByCountryLink;
	@FindBy(how=How.XPATH, using=".//*[@id='block-views-income_levels-block_1']/div/div/div/div/ul/li[1]/div/span/span/a") WebElement HighIncomeLink;

	@FindBy(how=How.XPATH, using = ".//*[@id='block-views-44fcb918e09e0c366853ab1749e6380f']/div/div/div[1]/div/div[2]/span/span/a/span") WebElement GDP_loc;
	@FindBy(how=How.XPATH, using = ".//*[@id='block-views-44fcb918e09e0c366853ab1749e6380f']/div/div/div[2]/div/div/div/div[2]/span/span/a/span") WebElement Population_loc;
	@FindBy(how=How.XPATH, using = ".//*[@id='boxes-box-country_wdi_block2']/div/div[2]/div/div/div/span[1]") WebElement CO2_loc;

	@FindBy(how=How.XPATH, using = ".//*[@id='block-views-income_levels_countries-block_1']/div/div/div/table") WebElement Table_loc;
	
	public WorldBankHomePage(WebDriver driver){
		this.driver = driver;
		driver.get(HOME_URL);
	}

	public void gotoData() {
		DataLink.click();
	}	
	public void gotoByCountry() {
		ByCountryLink.click();
	}	
	public void gotoHighIncome() {
		HighIncomeLink.click();
	}	
	
	public ArrayList<Country> getCountries() {
		
		ArrayList<Country> Countries = new ArrayList<Country>();
		
		List<WebElement> allRows = Table_loc.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    
		    for (WebElement cell : cells) { 
		    	
		    	List<WebElement> childs = cell.findElements(By.xpath(".//*"));
		    	String href;
		    	
		    	for (WebElement child : childs) {	
		    		href = child.getAttribute("href");
		    		if(href != null && !href.isEmpty()){
		    			Country country = new Country(child.getText(), href);
		    			Countries.add(country);	    			
		    		}
		    	}		        
		    }
		}   
		return (Countries);		
	}	
	
	public String getGDP() {	
		return GDP_loc.getText();
	}	
	public String getPopulation() {
		return Population_loc.getText();
	}	
	public String getCO2() {
		return CO2_loc.getText();
	}	
}