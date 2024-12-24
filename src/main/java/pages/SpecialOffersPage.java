package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class SpecialOffersPage extends RootPage {

	public SpecialOffersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Special Offers']")
	private WebElement specialOffersPageBreadcrumb;

	public boolean didWeNavigateToSpecialOffersPage() {
		return isElementDisplayed(specialOffersPageBreadcrumb);
	}

}
