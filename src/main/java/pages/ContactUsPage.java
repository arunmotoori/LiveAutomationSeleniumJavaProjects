package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {
	
	WebDriver driver;
	
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Contact Us']")
	private WebElement contactUsBreadcrumb;
	
	public boolean didWeNavigateToContactUsPage() {
		return contactUsBreadcrumb.isDisplayed();
	}

}
