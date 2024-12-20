package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class BrandPage extends RootPage{
	
	public BrandPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Brand']")
	private WebElement brandsPageBreadcrumb;
	
	public boolean didWeNavigateToBrandsPage() {
		return brandsPageBreadcrumb.isDisplayed();
	}
}
