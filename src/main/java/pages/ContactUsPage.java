package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class ContactUsPage extends RootPage {

	public ContactUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Contact Us']")
	private WebElement contactUsBreadcrumb;

	public boolean didWeNavigateToContactUsPage() {
		return isElementDisplayed(contactUsBreadcrumb);
	}

}
