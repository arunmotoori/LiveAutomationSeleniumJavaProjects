package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class SiteMapPage extends RootPage {

	public SiteMapPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Site Map']")
	private WebElement siteMapPageBreadcrumb;

	public boolean didWeNavigateToSiteMapPage() {
		return isElementDisplayed(siteMapPageBreadcrumb);
	}

}
