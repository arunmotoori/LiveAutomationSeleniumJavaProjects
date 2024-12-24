package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class LoginPage extends RootPage {
	
	ElementUtils elementUtils;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Register']")
	private WebElement registerOption;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadcrumb;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(linkText = "Forgotten Password")
	private WebElement forgottenPasswordLink;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='My Account']")
	private WebElement myAccountRightColumnOption;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement warningMessage;

	@FindBy(xpath = "(//div[@id='content']//h2)[1]")
	private WebElement headingOne;

	@FindBy(xpath = "(//div[@id='content']//h2)[2]")
	private WebElement headingTwo;

	public AccountPage loginToApplication(String emailText, String passwordText) {
		enterEmail(emailText);
		enterPassword(passwordText);
		return clickOnLoginButton();
	}

	public String getPageHeadingOne() {
		return elementUtils.getTextOfElement(headingOne);
	}

	public String getPageHeadingTwo() {
		return elementUtils.getTextOfElement(headingTwo);
	}

	public LoginPage clickOnLoginBreadcrumb() {
		elementUtils.clickOnElement(loginBreadcrumb);
		return new LoginPage(driver);
	}

	public void clearPassword() {
		elementUtils.clearTextFromElement(passwordField);
	}

	public String getTextCopiedIntoEmailField() {
		return elementUtils.getDomPropertyOfElement(emailField,"value");
	}

	public void pasteCopiedPasswordTextIntoEmailField() {
		elementUtils.pastingTextIntoField(emailField);
	}

	public void selectPasswordFieldTextAndCopy() {
		elementUtils.copyTextFromElement(passwordField);
	}

	public String getPasswordFieldType() {
		return elementUtils.getDomAttributeOfElement(passwordField,"type");
	}

	public AccountPage clickOnMyAccountRightColumnOption() {
		elementUtils.clickOnElement(myAccountRightColumnOption);
		return new AccountPage(driver);
	}

	public String getEmailPlaceholder() {
		return elementUtils.getDomAttributeOfElement(emailField,"placeholder");
	}

	public String getPasswordPlaceholder() {
		return elementUtils.getDomAttributeOfElement(passwordField,"placeholder");
	}

	public boolean availabilityOfForgottenPasswordLink() {
		return elementUtils.isElementDisplayed(forgottenPasswordLink);
	}

	public ForgottenPasswordPage clickOnForgottenPasswordLink() {
		elementUtils.clickOnElement(forgottenPasswordLink);
		return new ForgottenPasswordPage(driver);
	}

	public String getWarningMessage() {
		return elementUtils.getTextOfElement(warningMessage);
	}

	public AccountPage clickOnLoginButton() {
		elementUtils.clickOnElement(loginButton);
		return new AccountPage(driver);
	}

	public void enterPassword(String passwordText) {
		elementUtils.enterTextIntoElement(passwordField, passwordText);
	}

	public void enterEmail(String emailText) {
		elementUtils.enterTextIntoElement(emailField, emailText);
	}

	public RegisterPage clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
		return new RegisterPage(driver);
	}

	public void clickOnRegisterOption() {
		elementUtils.clickOnElement(registerOption);
	}

	public boolean didWeNaviateToLoginPage() {
		return elementUtils.isElementDisplayed(loginBreadcrumb);
	}

}