package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrandPage {
	
	WebDriver driver;
	
	public BrandPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Brand']")
	private WebElement brandsPageBreadcrumb;
	
	public boolean didWeNavigateToBrandsPage() {
		return brandsPageBreadcrumb.isDisplayed();
	}
}
