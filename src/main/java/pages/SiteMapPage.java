package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class SiteMapPage extends RootPage {
	
	ElementUtils elementUtils;

	public SiteMapPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Site Map']")
	private WebElement siteMapPageBreadcrumb;

	public boolean didWeNavigateToSiteMapPage() {
		return elementUtils.isElementDisplayed(siteMapPageBreadcrumb);
	}

}
