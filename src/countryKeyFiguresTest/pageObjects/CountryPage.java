// Page Object for country key values page 

package countryKeyFiguresTest.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CountryPage {
	WebDriver driver;
	List<WebElement> GDP_loc;
	List<WebElement> Population_loc;
	List<WebElement> CO2_loc;
	
	@FindBy(how=How.LINK_TEXT, using="Home") WebElement HomeLink;
	
	public CountryPage(WebDriver driver){
		this.driver = driver;	
	}
	
	public void openCountryPage(String CountryURL){
		driver.get(CountryURL);
		GDP_loc = driver.findElements(By.xpath(".//*[@id='block-views-44fcb918e09e0c366853ab1749e6380f']/div/div/div[1]/div/div[2]/span/span/a/span"));//
		
		Population_loc = driver.findElements(By.xpath(".//*[@id='block-views-44fcb918e09e0c366853ab1749e6380f']/div/div/div[2]/div/div/div/div[2]/span/span/a/span"));//
		if (Population_loc.size() == 0) Population_loc = driver.findElements(By.xpath(".//*[@id='block-views-44fcb918e09e0c366853ab1749e6380f']/div/div/div/div/div/div/div[2]/span/span/a/span"));//
		
		CO2_loc = driver.findElements(By.xpath(".//*[@id='boxes-box-country_wdi_block2']/div/div[2]/div/div/div/span[1]"));//
	}
	public String getGDP() {
		if (GDP_loc.size() > 0) return GDP_loc.get(0).getText(); 
		else  return "";
	}	
	public String getPopulation() {
		if (Population_loc.size() > 0) return Population_loc.get(0).getText(); 
		else  return "";
	}	
	public String getCO2() {	
		if (CO2_loc.size() > 0) return CO2_loc.get(0).getText(); 
		else  return "";
	}	
	public void gotoHome() {
		HomeLink.click();
	}			
}
