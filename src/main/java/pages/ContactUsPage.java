package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class ContactUsPage extends RootPage {
	
	ElementUtils elementUtils;

	public ContactUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Contact Us']")
	private WebElement contactUsBreadcrumb;

	public boolean didWeNavigateToContactUsPage() {
		return elementUtils.isElementDisplayed(contactUsBreadcrumb);
	}

}
