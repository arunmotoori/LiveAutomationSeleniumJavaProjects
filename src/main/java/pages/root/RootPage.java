package pages.root;

import org.openqa.selenium.WebDriver;

public class RootPage {
	
	public WebDriver driver;
	
	public RootPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
