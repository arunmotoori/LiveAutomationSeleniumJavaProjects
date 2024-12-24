package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class BrandPage extends RootPage {

	public BrandPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Brand']")
	private WebElement brandsPageBreadcrumb;

	public boolean didWeNavigateToBrandsPage() {
		return isElementDisplayed(brandsPageBreadcrumb);
	}
}
