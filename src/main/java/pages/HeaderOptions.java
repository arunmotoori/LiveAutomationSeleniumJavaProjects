package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class HeaderOptions extends RootPage {
	
	ElementUtils elementUtils;

	public HeaderOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a/i[@class='fa fa-phone']")
	private WebElement phoneIconOption;

	@FindBy(xpath = "//a/i[@class='fa fa-heart']")
	private WebElement heartIconOption;

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCartOption;

	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkoutOption;

	@FindBy(xpath = "//div[@id='logo']//a")
	private WebElement logoOption;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountBreadcrumb;

	@FindBy(xpath = "//ul[@class='breadcrumb']//i[@class='fa fa-home']")
	private WebElement homeBreadcrumb;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Logout")
	private WebElement logoutOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	public String getPlaceHolderTextOfSearchBoxField() {
		return elementUtils.getDomAttributeOfElement(searchBoxField,"placeholder");
	}

	public void enterProductIntoSearchBoxField(String productName) {
		elementUtils.enterTextIntoElement(searchBoxField, productName);
	}

	public boolean isLogoutOptionAvaialable() {
		return elementUtils.isElementDisplayed(logoutOption);
	}

	public boolean isLoginOptionAvailable() {
		return elementUtils.isElementDisplayed(loginOption);
	}

	public AccountLogoutPage selectLogoutOption() {
		elementUtils.clickOnElement(logoutOption);
		return new AccountLogoutPage(driver);
	}

	public LoginPage selectLoginOption() {
		elementUtils.clickOnElement(loginOption);
		return new LoginPage(driver);
	}

	public void clickOnMyAccountDropMenu() {
		elementUtils.clickOnElement(myAccountDropMenu);
	}

	public ContactUsPage selectPhoneIconOption() {
		elementUtils.clickOnElement(phoneIconOption);
		return new ContactUsPage(driver);
	}

	public LoginPage selectHeartIconOption() {
		elementUtils.clickOnElement(heartIconOption);
		return new LoginPage(driver);
	}

	public ShoppingCartPage selectShoppingCartOption() {
		elementUtils.clickOnElement(shoppingCartOption);
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectCheckoutOption() {
		elementUtils.clickOnElement(checkoutOption);
		return new ShoppingCartPage(driver);
	}

	public LandingPage clickOnHomeBreadcrumb() {
		elementUtils.clickOnElement(homeBreadcrumb);
		return new LandingPage(driver);
	}

	public LoginPage clickOnAccountBreadcrumb() {
		elementUtils.clickOnElement(accountBreadcrumb);
		return new LoginPage(driver);
	}

	public SearchPage clickOnSearchButton() {
		elementUtils.clickOnElement(searchButton);
		return new SearchPage(driver);
	}

	public LandingPage selectLogoOption() {
		elementUtils.clickOnElement(logoOption);
		return new LandingPage(driver);
	}

}
