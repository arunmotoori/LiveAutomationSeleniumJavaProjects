package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
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
	
	@FindBy(linkText="login page")
	private WebElement loginPageOption;
	
	@FindBy(xpath="//a[@class='agree']/b[text()='Privacy Policy']")
	private WebElement privacyPolicyOption;
	
	@FindBy(xpath="//button[text()='×']")
	private WebElement closePrivacyPolicyDialogOption;
	
	@FindBy(xpath="//button[text()='×']")
	private WebElement xOption;
	
	private By xOptionPrivacyPolicy = By.xpath("//button[text()='×']");
	
	
	
	@FindBy(xpath="//div[@id='content']/h1")
	private WebElement registerPageHeading;
	
	public WebDriver getDriverFromRegisterPage() {
		return driver;
	}
	
	public String getRegisterPageHeading() {
		return registerPageHeading.getText();
	}
	
	public boolean waitAndCheckDisplayStatusOfClosePrivacyPolicyOption(WebDriver driver,int seconds) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(xOptionPrivacyPolicy));
		return xOption.isDisplayed();
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
		return firstNameWarning.getText();
	}

	public String getLastNameWarning() {
		return lastNameWarning.getText();
	}

	public String getEmailWarning() {
		return emailWarning.getText();
	}

	public String getTelephoneWarning() {
		return telephoneWarning.getText();
	}

	public String getPasswordWarning() {
		return passwordWarning.getText();
	}

	public String getPrivacyPolicyWarning() {
		return privacyPolicyWarning.getText();
	}

	public boolean didWeNavigateToRegisterAccountPage() {
		return registerBreadcrumb.isDisplayed();
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
		return passwordConfirmWarning.getText();
	}

	public String getExistingEmailWarning() {
		return existingEmailWarning.getText();

	}

	public String getEmailValidationMessage() {
		return emailField.getDomProperty("validationMessage");
	}

	public void clearEmailField() {
		emailField.clear();
	}

	public String getPlaceHolderTextFromFirstNameField() {
		return firstNameField.getDomAttribute("placeholder");
	}

	public String getPlaceHolderTextFromLastNameField() {
		return lastNameField.getDomAttribute("placeholder");
	}

	public String getPlaceHolderTextFromEmailField() {
		return emailField.getDomAttribute("placeholder");
	}

	public String getPlaceHolderTextFromTelephoneField() {
		return telephoneField.getDomAttribute("placeholder");
	}

	public String getPlaceHolderTextFromPasswordField() {
		return passwordField.getDomAttribute("placeholder");
	}

	public String getPlaceHolderTextFromPasswordConfirmField() {
		return passwordConfirmField.getDomAttribute("placeholder");
	}

	public String getFirstNameLabelContent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String fnContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				firstNameLabel);
		return fnContent;
	}

	public String getFirstNameLabelColor(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String fnColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", firstNameLabel);
		return fnColor;
	}

	public String getLastNameLabelContent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String lnContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", lastNameLabel);
		return lnContent;
	}

	public String getLastNameLabelColor(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String lnColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", lastNameLabel);
		return lnColor;
	}

	public String getEmailLabelContent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String emailContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", emailLabel);
		return emailContent;
	}

	public String getEmailLabelColor(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String emailColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", emailLabel);
		return emailColor;
	}

	public String getTelephoneLabelContent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String telephoneContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				telephoneLabel);
		return telephoneContent;
	}

	public String getTelephoneLabelColor(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String telephoneColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", telephoneLabel);
		return telephoneColor;
	}

	public String getPasswordLabelContent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String passwordContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", passwordLabel);
		return passwordContent;
	}

	public String getPasswordLabelColor(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String passwordColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", passwordLabel);
		return passwordColor;
	}

	public String getPasswordConfirmLabelContent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String passwordConfirmContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				passwordConfirmLabel);
		return passwordConfirmContent;
	}

	public String getPasswordConfirmLabelColor(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String passwordConfirmColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
				passwordConfirmLabel);
		return passwordConfirmColor;
	}

	public String getPrivacyPolicyLabelContent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String privacyPolicyContent = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				privacyPolicyLabel);
		return privacyPolicyContent;
	}

	public String getPrivacyPolicyLabelColor(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String privacyPolicyColor = (String) jse.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
				privacyPolicyLabel);
		return privacyPolicyColor;
	}

	public String getFirstNameFieldHeight() {
		return firstNameField.getCssValue("height");
	}

	public String getFirstNameFieldWidth() {
		return firstNameField.getCssValue("width");
	}

	public void clearFirstNameField() {
		firstNameField.clear();
	}

	public boolean isFirstNameWarningDisplayed() {
		boolean status = false;
		try {
			status = firstNameWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getLastNameFieldHeight() {
		return lastNameField.getCssValue("height");
	}

	public String getLastNameFieldWidth() {
		return lastNameField.getCssValue("width");
	}

	public void clearLastNameField() {
		lastNameField.clear();
	}

	public boolean isLastNameWarningDisplayed() {
		boolean status = false;
		try {
			status = lastNameWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getEmailFieldHeight() {
		return emailField.getCssValue("height");
	}

	public String getEmailFieldWidth() {
		return emailField.getCssValue("width");
	}

	public boolean isEmailWarningDisplayed() {
		boolean status = false;
		try {
			status = emailWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getTelephoneFieldHeight() {
		return telephoneField.getCssValue("height");
	}

	public String getTelephoneFieldWidth() {
		return telephoneField.getCssValue("width");
	}

	public void clearTelephoneField() {
		telephoneField.clear();
	}

	public boolean isTelephoneWarningDisplayed() {
		boolean status = false;
		try {
			status = telephoneWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getPasswordFieldHeight() {
		return passwordField.getCssValue("height");
	}

	public String getPasswordFieldWidth() {
		return passwordField.getCssValue("width");
	}

	public void clearPasswordField() {
		passwordField.clear();
	}

	public boolean isPasswordWarningDisplayed() {
		boolean status = false;
		try {
			status = passwordWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public boolean isPasswordWarningDisplayedAndMatch(String expectedWarning) {
		boolean state = false;
		try {
			String acutalWarning = getPasswordWarning();
			if (acutalWarning.equals(expectedWarning)) {
				state = true;
			}
		} catch (NoSuchElementException e) {
			state = false;
		}
		return state;
	}

	public String getPasswordConfirmFieldHeight() {
		return passwordConfirmField.getCssValue("height");
	}

	public String getPasswordConfirmFieldWidth() {
		return passwordConfirmField.getCssValue("width");
	}

	public void clearPasswordConfirmField() {
		passwordConfirmField.clear();
	}

	public boolean isPasswordConfirmWarningDisplayed() {
		boolean status = false;
		try {
			status = passwordConfirmWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}
	
	public boolean isPrivacyPolicySelected() {
		return privacyPolicyField.isSelected();
	}
	
	public String getPasswordFieldType() {
		return passwordField.getDomAttribute("type");
	}
	
	public String getPasswordConfirmFieldType() {
		return passwordConfirmField.getDomAttribute("type");
	}

}
