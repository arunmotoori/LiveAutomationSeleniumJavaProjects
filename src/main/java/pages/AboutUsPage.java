package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class AboutUsPage extends RootPage{
	
	ElementUtils elementUtils;
	
	public AboutUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='About Us']")
	private WebElement aboutUSBreadcrumb;
	
	public boolean didWeNavigateToAboutUsBreadcrumb() {
		return elementUtils.isElementDisplayed(aboutUSBreadcrumb);
	}

}
