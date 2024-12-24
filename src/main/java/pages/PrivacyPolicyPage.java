package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class PrivacyPolicyPage extends RootPage {
	
	ElementUtils elementUtils;

	public PrivacyPolicyPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Privacy Policy']")
	private WebElement privacyPolicyBreadcrumb;

	public boolean didWeNavigateToPrivacyPolicyPage() {
		return elementUtils.isElementDisplayed(privacyPolicyBreadcrumb);
	}

}
