package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class AccountPage extends RootPage {

	public AccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformationOption;

	@FindBy(linkText = "Subscribe / unsubscribe to newsletter")
	private WebElement subscribeUnsubscribeNewsletterOption;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	private WebElement logoutOption;

	@FindBy(linkText = "Change your password")
	private WebElement changeYourPassword;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement message;

	public String getMessage() {
		return getTextOfElement(message);
	}

	public ChangePasswordPage clickOnChangeYourPasswordOption() {
		changeYourPassword.click();
		return new ChangePasswordPage(driver);
	}

	public AccountLogoutPage clickOnLogoutOption() {
		logoutOption.click();
		return new AccountLogoutPage(driver);
	}

	public boolean isUserLoggedIn() {
		return isElementDisplayed(logoutOption);
	}

	public boolean didWenavigateToAccountPage() {
		return isElementDisplayed(editYourAccountInformationOption);
	}

	public NewsletterPage selectSubscribeUnSubscribeNewsletterOption() {
		subscribeUnsubscribeNewsletterOption.click();
		return new NewsletterPage(driver);
	}

	public EditAccountInformationPage clickOnEditYourAccountInformationOption() {
		editYourAccountInformationOption.click();
		return new EditAccountInformationPage(driver);
	}

}
