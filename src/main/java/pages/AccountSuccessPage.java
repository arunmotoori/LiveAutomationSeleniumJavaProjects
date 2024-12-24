package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class AccountSuccessPage extends RootPage {
	
	ElementUtils elementUtils;

	public AccountSuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
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
		return elementUtils.isElementDisplayed(logoutOption);
	}

	public String getPageHeading() {
		return elementUtils.getTextOfElement(pageHeading);
	}

	public String getPageContent() {
		return elementUtils.getTextOfElement(pageContent);
	}

	public AccountPage clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
		return new AccountPage(driver);
	}

	public boolean didWeNavigateToAccountSuccessPage() {
		return elementUtils.isElementDisplayed(accountSuccessPageBreadcrumb);
	}
}
