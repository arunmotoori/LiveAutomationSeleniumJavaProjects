package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class ForgottenPasswordPage extends RootPage {

	public ForgottenPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Forgotten Password']")
	private WebElement forgottenPasswordBreacrumb;

	public boolean didWeNavigateToForgottendPasswordPage() {
		return isElementDisplayed(forgottenPasswordBreacrumb);
	}

}
