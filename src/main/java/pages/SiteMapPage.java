package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class SiteMapPage extends RootPage{

	public SiteMapPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Site Map']")
	private WebElement siteMapPageBreadcrumb;
	
	public boolean didWeNavigateToSiteMapPage() {
		return siteMapPageBreadcrumb.isDisplayed();
	}

}
