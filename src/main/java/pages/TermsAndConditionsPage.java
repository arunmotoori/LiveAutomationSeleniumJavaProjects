package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class TermsAndConditionsPage extends RootPage {
	
	ElementUtils elementUtils;

	public TermsAndConditionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Terms & Conditions']")
	private WebElement termsAndConditionsBreadcrumb;

	public boolean didWeNavigateToTermsAndConditionsPage() {
		return elementUtils.isElementDisplayed(termsAndConditionsBreadcrumb);
	}

}
