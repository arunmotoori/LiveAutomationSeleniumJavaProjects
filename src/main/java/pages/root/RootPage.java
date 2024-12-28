package pages.root;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;


public class RootPage {
	
	public WebDriver driver;
	ElementUtils elementUtils;
	
	public RootPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public boolean isTextMatching(String expectedText,String actualText) {
		boolean b = false;
		if (actualText.equals(expectedText)) {
			b = true;
		}else {
			b = false;
		}
		return b;
	}
	
	public WebDriver pressKeyMultipleTimes(WebDriver driver,Keys key,int times) {
		elementUtils.pressKeyMultipleTimes(driver,key,times);
		return driver;
	}

}
