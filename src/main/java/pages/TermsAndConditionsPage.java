package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class TermsAndConditionsPage extends RootPage {

	public TermsAndConditionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Terms & Conditions']")
	private WebElement termsAndConditionsBreadcrumb;

	public boolean didWeNavigateToTermsAndConditionsPage() {
		return isElementDisplayed(termsAndConditionsBreadcrumb);
	}

}
