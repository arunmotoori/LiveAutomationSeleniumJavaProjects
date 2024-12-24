package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class RegisterPage extends RootPage {

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
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
		return getTextOfElement(registerPageHeading);
	}

	public boolean waitAndCheckDisplayStatusOfClosePrivacyPolicyOption(WebDriver driver, int seconds) {
		return isElementDisplayedAfterWaiting(xOptionPrivacyPolicy,seconds);
	}

	public void closePrivacyPolicyDialog() {
		xOption.click();
	}

	public void clickOnPrivacyPolicyOption() {
		privacyPolicyOption.click();
	}

	public LoginPage clickOnLoginPageLink() {
		loginPageOption.click();
		return new LoginPage(driver);
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String passwordText) {
		passwordConfirmField.sendKeys(passwordText);
	}

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsletterOption() {
		yesNewsletterOption.click();
	}

	public void selectNoNewsletterOption() {
		noNewsletterOption.click();
	}

	public String getFirstNameWarning() {
		return getTextOfElement(firstNameWarning);
	}

	public String getLastNameWarning() {
		return getTextOfElement(lastNameWarning);
	}

	public String getEmailWarning() {
		return getTextOfElement(emailWarning);
	}

	public String getTelephoneWarning() {
		return getTextOfElement(telephoneWarning);
	}

	public String getPasswordWarning() {
		return getTextOfElement(passwordWarning);
	}

	public String getPrivacyPolicyWarning() {
		return getTextOfElement(privacyPolicyWarning);
	}

	public boolean didWeNavigateToRegisterAccountPage() {
		return isElementDisplayed(registerBreadcrumb);
	}

	public RegisterPage clickOnRegisterBreadcrumb() {
		registerBreadcrumb.click();
		return new RegisterPage(driver);
	}

	public void clickOnMyAccount() {
		myAccontDropMenu.click();
	}

	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public String getPasswordConfirmWarning() {
		return getTextOfElement(passwordConfirmWarning);
	}

	public String getExistingEmailWarning() {
		return getTextOfElement(existingEmailWarning);
	}

	public String getEmailValidationMessage() {
		return getDomPropertyOfElement(emailField,"validationMessage");
	}

	public void clearEmailField() {
		emailField.clear();
	}

	public String getPlaceHolderTextFromFirstNameField() {
		return getDomAttributeOfElement(firstNameField,"placeholder");
	}

	public String getPlaceHolderTextFromLastNameField() {
		return getDomAttributeOfElement(lastNameField,"placeholder");
	}

	public String getPlaceHolderTextFromEmailField() {
		return getDomAttributeOfElement(emailField,"placeholder");
	}

	public String getPlaceHolderTextFromTelephoneField() {
		return getDomAttributeOfElement(telephoneField,"placeholder");
	}

	public String getPlaceHolderTextFromPasswordField() {
		return getDomAttributeOfElement(passwordField,"placeholder");
	}

	public String getPlaceHolderTextFromPasswordConfirmField() {
		return getDomAttributeOfElement(passwordConfirmField,"placeholder");
	}

	public String getFirstNameLabelContent(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(firstNameLabel,"content");
	}

	public String getFirstNameLabelColor(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(firstNameLabel,"color");
	}

	public String getLastNameLabelContent(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(lastNameLabel,"content");
	}

	public String getLastNameLabelColor(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(lastNameLabel,"color");
	}

	public String getEmailLabelContent(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(emailLabel,"content");
	}

	public String getEmailLabelColor(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(emailLabel,"color");
	}

	public String getTelephoneLabelContent(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(telephoneLabel,"content");
	}

	public String getTelephoneLabelColor(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(telephoneLabel,"color");
	}

	public String getPasswordLabelContent(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(passwordLabel,"content");
	}

	public String getPasswordLabelColor(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(passwordLabel,"color");
	}

	public String getPasswordConfirmLabelContent(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(passwordConfirmLabel,"content");
	}

	public String getPasswordConfirmLabelColor(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(passwordConfirmLabel,"color");
	}

	public String getPrivacyPolicyLabelContent(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(privacyPolicyLabel,"content");
	}

	public String getPrivacyPolicyLabelColor(WebDriver driver) {
		return getCSSPropertyOfPuseudoElement(privacyPolicyLabel,"color");
	}

	public String getFirstNameFieldHeight() {
		return getCSSPropertyOfElement(firstNameField,"height");
	}

	public String getFirstNameFieldWidth() {
		return getCSSPropertyOfElement(firstNameField,"width");
	}

	public void clearFirstNameField() {
		firstNameField.clear();
	}

	public boolean isFirstNameWarningDisplayed() {
		return isElementDisplayed(firstNameWarning);
	}

	public String getLastNameFieldHeight() {
		return getCSSPropertyOfElement(lastNameField,"height");
	}

	public String getLastNameFieldWidth() {
		return getCSSPropertyOfElement(lastNameField,"width");
	}

	public void clearLastNameField() {
		lastNameField.clear();
	}

	public boolean isLastNameWarningDisplayed() {
		return isElementDisplayed(lastNameWarning);
	}

	public String getEmailFieldHeight() {
		return getCSSPropertyOfElement(emailField,"height");
	}

	public String getEmailFieldWidth() {
		return getCSSPropertyOfElement(emailField,"width");
	}

	public boolean isEmailWarningDisplayed() {
		return isElementDisplayed(emailWarning);
	}

	public String getTelephoneFieldHeight() {
		return getCSSPropertyOfElement(telephoneField,"height");
	}

	public String getTelephoneFieldWidth() {
		return getCSSPropertyOfElement(telephoneField,"width");
	}

	public void clearTelephoneField() {
		telephoneField.clear();
	}

	public boolean isTelephoneWarningDisplayed() {
		return isElementDisplayed(telephoneWarning);
	}

	public String getPasswordFieldHeight() {
		return getCSSPropertyOfElement(passwordField,"height");
	}

	public String getPasswordFieldWidth() {
		return getCSSPropertyOfElement(passwordField,"width");
	}

	public void clearPasswordField() {
		passwordField.clear();
	}

	public boolean isPasswordWarningDisplayed() {
		return isElementDisplayed(passwordWarning);
	}

	public boolean isPasswordWarningDisplayedAndMatch(String expectedWarning) {
		return isTextMatching(expectedWarning,getPasswordWarning());
	}

	public String getPasswordConfirmFieldHeight() {
		return getCSSPropertyOfElement(passwordConfirmField,"height");
	}

	public String getPasswordConfirmFieldWidth() {
		return getCSSPropertyOfElement(passwordConfirmField,"width");
	}

	public void clearPasswordConfirmField() {
		passwordConfirmField.clear();
	}

	public boolean isPasswordConfirmWarningDisplayed() {
		return isElementDisplayed(passwordConfirmWarning);
	}

	public boolean isPrivacyPolicySelected() {
		return isElementSelected(privacyPolicyField);
	}

	public String getPasswordFieldType() {
		return getDomAttributeOfElement(passwordField,"type");
	}

	public String getPasswordConfirmFieldType() {
		return getDomAttributeOfElement(passwordConfirmField,"type");
	}

}
