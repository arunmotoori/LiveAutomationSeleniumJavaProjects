package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class LoginPage extends RootPage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[@class='btn btn-primary'][text()='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Register']")
	private WebElement registerOption;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadcrumb;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(linkText="Forgotten Password")
	private WebElement forgottenPasswordLink;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='My Account']")
	private WebElement myAccountRightColumnOption;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement warningMessage;
	
	@FindBy(xpath="(//div[@id='content']//h2)[1]")
	private WebElement headingOne;
	
	@FindBy(xpath="(//div[@id='content']//h2)[2]")
	private WebElement headingTwo;
	
	public String getPageHeadingOne() {
		return headingOne.getText();
	}
	
	public String getPageHeadingTwo() {
		return headingTwo.getText();
	}

	public LoginPage clickOnLoginBreadcrumb() {
		loginBreadcrumb.click();
		return new LoginPage(driver);
	}
	
	public void clearPassword() {
		passwordField.clear();
	}
	
	public String getTextCopiedIntoEmailField() {
		return emailField.getDomProperty("value");
	}
	
	public WebDriver pasteCopiedPasswordTextIntoEmailField(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.click(emailField).keyDown(Keys.CONTROL)
		.sendKeys("v").keyUp(Keys.CONTROL);
		return driver;
	}
		
	public WebDriver selectPasswordFieldTextAndCopy(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.doubleClick(passwordField).keyDown(Keys.CONTROL)
		.sendKeys("c").keyUp(Keys.CONTROL);
		return driver;
	}
	
	public String getPasswordFieldType() {
		return passwordField.getDomAttribute("type");
	}
	
	public AccountPage clickOnMyAccountRightColumnOption() {
		myAccountRightColumnOption.click();
		return new AccountPage(driver);
	}
	
	public String getEmailPlaceholder() {
		return emailField.getDomAttribute("placeholder");
	}
	
	public String getPasswordPlaceholder() {
		return passwordField.getDomAttribute("placeholder");
	}
	
	public boolean availabilityOfForgottenPasswordLink() {
		return forgottenPasswordLink.isDisplayed();
	}
	
	public ForgottenPasswordPage clickOnForgottenPasswordLink() {
		forgottenPasswordLink.click();
		return new ForgottenPasswordPage(driver);
	}
	
	
	
	public String getWarningMessage() {
		return warningMessage.getText();
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public RegisterPage clickOnContinueButton() {
		continueButton.click();
		return new RegisterPage(driver);
	}
	
	public void clickOnRegisterOption() {
		registerOption.click();
	}
	
	public boolean didWeNaviateToLoginPage() {
		return loginBreadcrumb.isDisplayed();
	}

}