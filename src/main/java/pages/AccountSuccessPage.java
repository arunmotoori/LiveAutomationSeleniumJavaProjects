package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(linkText="Logout")
	private WebElement logoutOption;
	
	@FindBy(xpath="//div[@id='common-success']//h1")
	private WebElement pageHeading;
	
	@FindBy(id="content")
	private WebElement pageContent;
	
	@FindBy(xpath="//a[text()='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement accountSuccessPageBreadcrumb;
	
	public boolean isUserLoggedIn() {
		return logoutOption.isDisplayed();
	}
	
	public String getPageHeading() {
		return pageHeading.getText();
	}
	
	public String getPageContent() {
		return pageContent.getText();
	}
	
	public AccountPage clickOnContinueButton() {
		continueButton.click();
		return new AccountPage(driver);
	}
	
	public boolean didWeNavigateToAccountSuccessPage() {
		return accountSuccessPageBreadcrumb.isDisplayed();
	}
}
