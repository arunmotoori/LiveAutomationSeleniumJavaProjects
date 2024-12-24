package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class ForgottenPasswordPage extends RootPage {
	
	ElementUtils elementUtils;

	public ForgottenPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Forgotten Password']")
	private WebElement forgottenPasswordBreacrumb;

	public boolean didWeNavigateToForgottendPasswordPage() {
		return elementUtils.isElementDisplayed(forgottenPasswordBreacrumb);
	}

}
