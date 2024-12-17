package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutUsPage {
	
	WebDriver driver;
	
	public AboutUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='About Us']")
	private WebElement aboutUSBreadcrumb;
	
	public boolean didWeNavigateToAboutUsBreadcrumb() {
		return aboutUSBreadcrumb.isDisplayed();
	}

}
