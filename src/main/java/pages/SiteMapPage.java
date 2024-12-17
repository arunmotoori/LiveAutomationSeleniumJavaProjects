package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SiteMapPage {
	
	WebDriver driver;
	
	public SiteMapPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Site Map']")
	private WebElement siteMapPageBreadcrumb;
	
	public boolean didWeNavigateToSiteMapPage() {
		return siteMapPageBreadcrumb.isDisplayed();
	}

}
