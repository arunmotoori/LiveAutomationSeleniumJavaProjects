package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class ProductReturnsPage extends RootPage {
	
	ElementUtils elementUtils;

	public ProductReturnsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Product Returns']")
	private WebElement productReturnsPageBreadcrumb;

	public boolean didWeNavigateToProductReturnsPage() {
		return elementUtils.isElementDisplayed(productReturnsPageBreadcrumb);
	}

}
