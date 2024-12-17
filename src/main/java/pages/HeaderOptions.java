package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderOptions {
	
	WebDriver driver;
	
	public HeaderOptions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a/i[@class='fa fa-phone']")
	private WebElement phoneIconOption;
	
	@FindBy(xpath="//a/i[@class='fa fa-heart']")
	private WebElement heartIconOption;
	
	@FindBy(xpath="//span[text()='Shopping Cart']")
	private WebElement shoppingCartOption;
	
	@FindBy(xpath="//span[text()='Checkout']")
	private WebElement checkoutOption;
	
	@FindBy(xpath="//div[@id='logo']//a")
	private WebElement logoOption;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Account']")
	private WebElement accountBreadcrumb;
	
	@FindBy(xpath="//ul[@class='breadcrumb']//i[@class='fa fa-home']")
	private WebElement homeBreadcrumb;
	
	public ContactUsPage selectPhoneIconOption() {
		phoneIconOption.click();
		return new ContactUsPage(driver);
	}
	
	public LoginPage selectHeartIconOption() {
		heartIconOption.click();
		return new LoginPage(driver);
	}
	
	public ShoppingCartPage selectShoppingCartOption() {
		shoppingCartOption.click();
		return new ShoppingCartPage(driver);
	}
	
	public ShoppingCartPage selectCheckoutOption() {
		checkoutOption.click();
		return new ShoppingCartPage(driver);
	}
	
	public LandingPage clickOnHomeBreadcrumb() {
		homeBreadcrumb.click();
		return new LandingPage(driver);
	}
	
	public LoginPage clickOnAccountBreadcrumb() {
		accountBreadcrumb.click();
		return new LoginPage(driver);
	}
	
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}

	
	public LandingPage selectLogoOption() {
		logoOption.click();
		return new LandingPage(driver);
	}

}
