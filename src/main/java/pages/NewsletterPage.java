package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class NewsletterPage extends RootPage {

	public NewsletterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Newsletter']")
	private WebElement newsletterBreadcrumb;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement noNewsletterOption;

	public boolean didWeNavigateToNewsletterPage() {
		return isElementDisplayed(newsletterBreadcrumb);
	}

	public boolean isYesNewsletterOptionSelected() {
		return isElementSelected(yesNewsletterOption);
	}

	public boolean isNoNewsletterOptionSelected() {
		return isElementSelected(noNewsletterOption);
	}

}
