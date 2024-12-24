package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class ShoppingCartPage extends RootPage {
	
	ElementUtils elementUtils;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Shopping Cart']")
	private WebElement shoppingCartBreadcrumb;

	public boolean didWeNaviateToShoppingCartPage() {
		return elementUtils.isElementDisplayed(shoppingCartBreadcrumb);
	}

}
