package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class RegisterPage extends RootPage {
	
	ElementUtils elementUtils;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement noNewsletterOption;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
	private WebElement registerBreadcrumb;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccontDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
	private WebElement passwordConfirmWarning;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement existingEmailWarning;

	@FindBy(css = "label[for='input-firstname']")
	private WebElement firstNameLabel;

	@FindBy(css = "label[for='input-lastname']")
	private WebElement lastNameLabel;

	@FindBy(css = "label[for='input-email']")
	private WebElement emailLabel;

	@FindBy(css = "label[for='input-telephone']")
	private WebElement telephoneLabel;

	@FindBy(css = "label[for='input-password']")
	private WebElement passwordLabel;

	@FindBy(css = "label[for='input-confirm']")
	private WebElement passwordConfirmLabel;

	@FindBy(css = "[class='pull-right']")
	private WebElement privacyPolicyLabel;

	@FindBy(linkText = "login page")
	private WebElement loginPageOption;

	@FindBy(xpath = "//a[@class='agree']/b[text()='Privacy Policy']")
	private WebElement privacyPolicyOption;

	@FindBy(xpath = "//button[text()='×']")
	private WebElement closePrivacyPolicyDialogOption;

	@FindBy(xpath = "//button[text()='×']")
	private WebElement xOption;

	private By xOptionPrivacyPolicy = By.xpath("//button[text()='×']");

	@FindBy(xpath = "//div[@id='content']/h1")
	private WebElement registerPageHeading;

	public String getRegisterPageHeading() {
		return elementUtils.getTextOfElement(registerPageHeading);
	}

	public boolean waitAndCheckDisplayStatusOfClosePrivacyPolicyOption(WebDriver driver, int seconds) {
		return elementUtils.isElementDisplayedAfterWaiting(xOptionPrivacyPolicy,seconds);
	}

	public void closePrivacyPolicyDialog() {
		elementUtils.clickOnElement(xOption);
	}

	public void clickOnPrivacyPolicyOption() {
		elementUtils.clickOnElement(privacyPolicyOption);
	}

	public LoginPage clickOnLoginPageLink() {
		elementUtils.clickOnElement(loginPageOption);
		return new LoginPage(driver);
	}

	public void enterFirstName(String firstNameText) {
		elementUtils.enterTextIntoElement(firstNameField, firstNameText);
	}

	public void enterLastName(String lastNameText) {
		elementUtils.enterTextIntoElement(lastNameField, lastNameText);
	}

	public void enterEmail(String emailText) {
		elementUtils.enterTextIntoElement(emailField, emailText);
	}

	public void enterTelephoneNumber(String telephoneText) {
		elementUtils.enterTextIntoElement(telephoneField, telephoneText);
	}

	public void enterPassword(String passwordText) {
		elementUtils.enterTextIntoElement(passwordField, passwordText);
	}

	public void enterConfirmPassword(String passwordText) {
		elementUtils.enterTextIntoElement(passwordConfirmField, passwordText);
	}

	public void selectPrivacyPolicy() {
		elementUtils.clickOnElement(privacyPolicyField);
	}

	public AccountSuccessPage clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsletterOption() {
		elementUtils.clickOnElement(yesNewsletterOption);
	}

	public void selectNoNewsletterOption() {
		elementUtils.clickOnElement(noNewsletterOption);
	}

	public String getFirstNameWarning() {
		return elementUtils.getTextOfElement(firstNameWarning);
	}

	public String getLastNameWarning() {
		return elementUtils.getTextOfElement(lastNameWarning);
	}

	public String getEmailWarning() {
		return elementUtils.getTextOfElement(emailWarning);
	}

	public String getTelephoneWarning() {
		return elementUtils.getTextOfElement(telephoneWarning);
	}

	public String getPasswordWarning() {
		return elementUtils.getTextOfElement(passwordWarning);
	}

	public String getPrivacyPolicyWarning() {
		return elementUtils.getTextOfElement(privacyPolicyWarning);
	}

	public boolean didWeNavigateToRegisterAccountPage() {
		return elementUtils.isElementDisplayed(registerBreadcrumb);
	}

	public RegisterPage clickOnRegisterBreadcrumb() {
		elementUtils.clickOnElement(registerBreadcrumb);
		return new RegisterPage(driver);
	}

	public void clickOnMyAccount() {
		elementUtils.clickOnElement(myAccontDropMenu);
	}

	public LoginPage selectLoginOption() {
		elementUtils.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	public String getPasswordConfirmWarning() {
		return elementUtils.getTextOfElement(passwordConfirmWarning);
	}

	public String getExistingEmailWarning() {
		return elementUtils.getTextOfElement(existingEmailWarning);
	}

	public String getEmailValidationMessage() {
		return elementUtils.getDomPropertyOfElement(emailField,"validationMessage");
	}

	public void clearEmailField() {
		elementUtils.clearTextFromElement(emailField);
	}

	public String getPlaceHolderTextFromFirstNameField() {
		return elementUtils.getDomAttributeOfElement(firstNameField,"placeholder");
	}

	public String getPlaceHolderTextFromLastNameField() {
		return elementUtils.getDomAttributeOfElement(lastNameField,"placeholder");
	}

	public String getPlaceHolderTextFromEmailField() {
		return elementUtils.getDomAttributeOfElement(emailField,"placeholder");
	}

	public String getPlaceHolderTextFromTelephoneField() {
		return elementUtils.getDomAttributeOfElement(telephoneField,"placeholder");
	}

	public String getPlaceHolderTextFromPasswordField() {
		return elementUtils.getDomAttributeOfElement(passwordField,"placeholder");
	}

	public String getPlaceHolderTextFromPasswordConfirmField() {
		return elementUtils.getDomAttributeOfElement(passwordConfirmField,"placeholder");
	}

	public String getFirstNameLabelContent(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(firstNameLabel,"content");
	}

	public String getFirstNameLabelColor(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(firstNameLabel,"color");
	}

	public String getLastNameLabelContent(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(lastNameLabel,"content");
	}

	public String getLastNameLabelColor(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(lastNameLabel,"color");
	}

	public String getEmailLabelContent(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(emailLabel,"content");
	}

	public String getEmailLabelColor(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(emailLabel,"color");
	}

	public String getTelephoneLabelContent(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(telephoneLabel,"content");
	}

	public String getTelephoneLabelColor(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(telephoneLabel,"color");
	}

	public String getPasswordLabelContent(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(passwordLabel,"content");
	}

	public String getPasswordLabelColor(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(passwordLabel,"color");
	}

	public String getPasswordConfirmLabelContent(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(passwordConfirmLabel,"content");
	}

	public String getPasswordConfirmLabelColor(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(passwordConfirmLabel,"color");
	}

	public String getPrivacyPolicyLabelContent(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(privacyPolicyLabel,"content");
	}

	public String getPrivacyPolicyLabelColor(WebDriver driver) {
		return elementUtils.getCSSPropertyOfPuseudoElement(privacyPolicyLabel,"color");
	}

	public String getFirstNameFieldHeight() {
		return elementUtils.getCSSPropertyOfElement(firstNameField,"height");
	}

	public String getFirstNameFieldWidth() {
		return elementUtils.getCSSPropertyOfElement(firstNameField,"width");
	}

	public void clearFirstNameField() {
		elementUtils.clearTextFromElement(firstNameField);
	}

	public boolean isFirstNameWarningDisplayed() {
		return elementUtils.isElementDisplayed(firstNameWarning);
	}

	public String getLastNameFieldHeight() {
		return elementUtils.getCSSPropertyOfElement(lastNameField,"height");
	}

	public String getLastNameFieldWidth() {
		return elementUtils.getCSSPropertyOfElement(lastNameField,"width");
	}

	public void clearLastNameField() {
		elementUtils.clearTextFromElement(lastNameField);
	}

	public boolean isLastNameWarningDisplayed() {
		return elementUtils.isElementDisplayed(lastNameWarning);
	}

	public String getEmailFieldHeight() {
		return elementUtils.getCSSPropertyOfElement(emailField,"height");
	}

	public String getEmailFieldWidth() {
		return elementUtils.getCSSPropertyOfElement(emailField,"width");
	}

	public boolean isEmailWarningDisplayed() {
		return elementUtils.isElementDisplayed(emailWarning);
	}

	public String getTelephoneFieldHeight() {
		return elementUtils.getCSSPropertyOfElement(telephoneField,"height");
	}

	public String getTelephoneFieldWidth() {
		return elementUtils.getCSSPropertyOfElement(telephoneField,"width");
	}

	public void clearTelephoneField() {
		elementUtils.clearTextFromElement(telephoneField);
	}

	public boolean isTelephoneWarningDisplayed() {
		return elementUtils.isElementDisplayed(telephoneWarning);
	}

	public String getPasswordFieldHeight() {
		return elementUtils.getCSSPropertyOfElement(passwordField,"height");
	}

	public String getPasswordFieldWidth() {
		return elementUtils.getCSSPropertyOfElement(passwordField,"width");
	}

	public void clearPasswordField() {
		elementUtils.clearTextFromElement(passwordField);
	}

	public boolean isPasswordWarningDisplayed() {
		return elementUtils.isElementDisplayed(passwordWarning);
	}

	public boolean isPasswordWarningDisplayedAndMatch(String expectedWarning) {
		return isTextMatching(expectedWarning,getPasswordWarning());
	}

	public String getPasswordConfirmFieldHeight() {
		return elementUtils.getCSSPropertyOfElement(passwordConfirmField,"height");
	}

	public String getPasswordConfirmFieldWidth() {
		return elementUtils.getCSSPropertyOfElement(passwordConfirmField,"width");
	}

	public void clearPasswordConfirmField() {
		elementUtils.clearTextFromElement(passwordConfirmField);
	}

	public boolean isPasswordConfirmWarningDisplayed() {
		return elementUtils.isElementDisplayed(passwordConfirmWarning);
	}

	public boolean isPrivacyPolicySelected() {
		return elementUtils.isElementSelected(privacyPolicyField);
	}

	public String getPasswordFieldType() {
		return elementUtils.getDomAttributeOfElement(passwordField,"type");
	}

	public String getPasswordConfirmFieldType() {
		return elementUtils.getDomAttributeOfElement(passwordConfirmField,"type");
	}

}
