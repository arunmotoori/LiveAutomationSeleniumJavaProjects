package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class AccountSuccessPage extends RootPage {

	public AccountSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Logout")
	private WebElement logoutOption;

	@FindBy(xpath = "//div[@id='common-success']//h1")
	private WebElement pageHeading;

	@FindBy(id = "content")
	private WebElement pageContent;

	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
	private WebElement accountSuccessPageBreadcrumb;

	public boolean isUserLoggedIn() {
		return isElementDisplayed(logoutOption);
	}

	public String getPageHeading() {
		return getTextOfElement(pageHeading);
	}

	public String getPageContent() {
		return getTextOfElement(pageContent);
	}

	public AccountPage clickOnContinueButton() {
		continueButton.click();
		return new AccountPage(driver);
	}

	public boolean didWeNavigateToAccountSuccessPage() {
		return isElementDisplayed(accountSuccessPageBreadcrumb);
	}
}
