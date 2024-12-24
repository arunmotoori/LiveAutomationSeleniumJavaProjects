package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class SpecialOffersPage extends RootPage {
	
	ElementUtils elementUtils;

	public SpecialOffersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Special Offers']")
	private WebElement specialOffersPageBreadcrumb;

	public boolean didWeNavigateToSpecialOffersPage() {
		return elementUtils.isElementDisplayed(specialOffersPageBreadcrumb);
	}

}
