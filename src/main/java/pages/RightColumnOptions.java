package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class RightColumnOptions extends RootPage{
	
	ElementUtils elementUtils;
	
	public RightColumnOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Login']")
	private WebElement rightSideLoginOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Register']")
	private WebElement rightSideRegisterOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Forgotten Password']")
	private WebElement rightSideForgottenPasswordOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='My Account']")
	private WebElement rightSideMyAccountOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Address Book']")
	private WebElement rightSideAddressBookOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Wish List']")
	private WebElement rightSideWishListOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Order History']")
	private WebElement rightSideOrderHistoryOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Downloads']")
	private WebElement rightSideDownloadsOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Recurring payments']")
	private WebElement rightSideRecurringPaymentsOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Reward Points']")
	private WebElement rightSideRewardPointsOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Returns']")
	private WebElement rightSideReturnsOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Transactions']")
	private WebElement rightSideTransactionsOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Newsletter']")
	private WebElement rightSideNewsletterOption;
	
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']")
	private WebElement logoutOption;
	
	public boolean isLogoutRightColumnOptionAvailable() {
		return elementUtils.isElementDisplayed(logoutOption);
	}
	
	public AccountLogoutPage clickOnRightSideLogoutOption() {
		elementUtils.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}
	
	public LoginPage clickOnRightSideNewsletterOption() {
		elementUtils.clickOnElement(rightSideNewsletterOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideTransactionsOption() {
		elementUtils.clickOnElement(rightSideTransactionsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideReturnsOption() {
		elementUtils.clickOnElement(rightSideReturnsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideRewardPointsOption() {
		elementUtils.clickOnElement(rightSideRewardPointsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideRecurringPaymentsOption() {
		elementUtils.clickOnElement(rightSideRecurringPaymentsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideDownloadsOption() {
		elementUtils.clickOnElement(rightSideDownloadsOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideOrderHistoryOption() {
		elementUtils.clickOnElement(rightSideOrderHistoryOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideWishListOption() {
		elementUtils.clickOnElement(rightSideWishListOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideAddressBookOption() {
		elementUtils.clickOnElement(rightSideAddressBookOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideMyAccountOption() {
		elementUtils.clickOnElement(rightSideMyAccountOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideLoginOption() {
		elementUtils.clickOnElement(rightSideLoginOption);
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRightSideRegisterOption() {
		elementUtils.clickOnElement(rightSideRegisterOption);
		return new RegisterPage(driver);
	}
	
	public ForgottenPasswordPage clickOnRightSideForgottenPasswordOption() {
		elementUtils.clickOnElement(rightSideForgottenPasswordOption);
		return new ForgottenPasswordPage(driver);
	}

}
