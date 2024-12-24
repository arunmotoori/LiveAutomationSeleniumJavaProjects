package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class ProductReturnsPage extends RootPage {

	public ProductReturnsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Product Returns']")
	private WebElement productReturnsPageBreadcrumb;

	public boolean didWeNavigateToProductReturnsPage() {
		return isElementDisplayed(productReturnsPageBreadcrumb);
	}

}
