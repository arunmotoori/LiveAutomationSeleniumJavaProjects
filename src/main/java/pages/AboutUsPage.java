package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class AboutUsPage extends RootPage{
	
	public AboutUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='About Us']")
	private WebElement aboutUSBreadcrumb;
	
	public boolean didWeNavigateToAboutUsBreadcrumb() {
		return isElementDisplayed(aboutUSBreadcrumb);
	}

}
