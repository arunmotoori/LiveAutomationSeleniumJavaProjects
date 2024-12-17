package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RightColumnOptions {
	
	WebDriver driver;
	
	public RightColumnOptions(WebDriver driver) {
		this.driver = driver;
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
	
	public LoginPage clickOnRightSideNewsletterOption() {
		rightSideNewsletterOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideTransactionsOption() {
		rightSideTransactionsOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideReturnsOption() {
		rightSideReturnsOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideRewardPointsOption() {
		rightSideRewardPointsOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideRecurringPaymentsOption() {
		rightSideRecurringPaymentsOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideDownloadsOption() {
		rightSideDownloadsOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideOrderHistoryOption() {
		rightSideOrderHistoryOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideWishListOption() {
		rightSideWishListOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideAddressBookOption() {
		rightSideAddressBookOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideMyAccountOption() {
		rightSideMyAccountOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnRightSideLoginOption() {
		rightSideLoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRightSideRegisterOption() {
		rightSideRegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public ForgottenPasswordPage clickOnRightSideForgottenPasswordOption() {
		rightSideForgottenPasswordOption.click();
		return new ForgottenPasswordPage(driver);
	}

}
