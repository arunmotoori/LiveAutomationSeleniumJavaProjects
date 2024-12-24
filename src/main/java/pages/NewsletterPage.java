package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class NewsletterPage extends RootPage {
	
	ElementUtils elementUtils;

	public NewsletterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Newsletter']")
	private WebElement newsletterBreadcrumb;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement noNewsletterOption;

	public boolean didWeNavigateToNewsletterPage() {
		return elementUtils.isElementDisplayed(newsletterBreadcrumb);
	}

	public boolean isYesNewsletterOptionSelected() {
		return elementUtils.isElementSelected(yesNewsletterOption);
	}

	public boolean isNoNewsletterOptionSelected() {
		return elementUtils.isElementSelected(noNewsletterOption);
	}

}
