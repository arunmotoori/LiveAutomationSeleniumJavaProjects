package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class AccountPage extends RootPage {
	
	ElementUtils elementUtils;

	public AccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
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
		return elementUtils.getTextOfElement(message);
	}

	public ChangePasswordPage clickOnChangeYourPasswordOption() {
		elementUtils.clickOnElement(changeYourPassword);
		return new ChangePasswordPage(driver);
	}

	public AccountLogoutPage clickOnLogoutOption() {
		elementUtils.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}

	public boolean isUserLoggedIn() {
		return elementUtils.isElementDisplayed(logoutOption);
	}

	public boolean didWenavigateToAccountPage() {
		return elementUtils.isElementDisplayed(editYourAccountInformationOption);
	}

	public NewsletterPage selectSubscribeUnSubscribeNewsletterOption() {
		elementUtils.clickOnElement(subscribeUnsubscribeNewsletterOption);
		return new NewsletterPage(driver);
	}

	public EditAccountInformationPage clickOnEditYourAccountInformationOption() {
		elementUtils.clickOnElement(editYourAccountInformationOption);
		return new EditAccountInformationPage(driver);
	}

}
