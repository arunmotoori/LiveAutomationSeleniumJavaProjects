package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class PrivacyPolicyPage extends RootPage {

	public PrivacyPolicyPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Privacy Policy']")
	private WebElement privacyPolicyBreadcrumb;

	public boolean didWeNavigateToPrivacyPolicyPage() {
		return isElementDisplayed(privacyPolicyBreadcrumb);
	}

}
