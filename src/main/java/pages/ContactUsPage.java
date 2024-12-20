package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ContactUsPage extends RootPage{
	
	public ContactUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Contact Us']")
	private WebElement contactUsBreadcrumb;
	
	public boolean didWeNavigateToContactUsPage() {
		return contactUsBreadcrumb.isDisplayed();
	}

}
