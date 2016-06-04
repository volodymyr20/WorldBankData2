package countryKeyFiguresTest.shared;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InitWebDriver {
	WebDriver driver = null; 
	public InitWebDriver() throws IOException {
		GetConfig properties = new GetConfig();	
		String browser = properties.getPropValue("WebDriver");
		
		if (browser.equals("Internet Explorer")) {
			
			System.setProperty("webdriver.ie.driver",".\\utils\\IEDriverServer.exe");
			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            
            driver = new InternetExplorerDriver(capabilities);
            driver.manage().window().maximize();			
		}
		if (browser.equals("Firefox")) driver = new FirefoxDriver();
	    
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public WebDriver GetDriver(){
		return driver;
	}
}
