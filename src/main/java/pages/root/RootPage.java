package pages.root;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ElementUtils;


public class RootPage {
	
	public WebDriver driver;
	ElementUtils elementUtils;
	
	public RootPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement pageHeading;
	
	public String getPageHeading() {
		return elementUtils.getTextOfElement(pageHeading);
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
