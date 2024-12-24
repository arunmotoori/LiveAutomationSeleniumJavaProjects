package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class BrandPage extends RootPage {
	
	ElementUtils elementUtils;

	public BrandPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Brand']")
	private WebElement brandsPageBreadcrumb;

	public boolean didWeNavigateToBrandsPage() {
		return elementUtils.isElementDisplayed(brandsPageBreadcrumb);
	}
}
