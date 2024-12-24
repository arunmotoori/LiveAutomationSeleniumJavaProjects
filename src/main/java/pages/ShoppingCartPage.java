package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class ShoppingCartPage extends RootPage {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Shopping Cart']")
	private WebElement shoppingCartBreadcrumb;

	public boolean didWeNaviateToShoppingCartPage() {
		return isElementDisplayed(shoppingCartBreadcrumb);
	}

}
