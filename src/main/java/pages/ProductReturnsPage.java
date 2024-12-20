package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class ProductReturnsPage extends RootPage{
	
	public ProductReturnsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Product Returns']")
	private WebElement productReturnsPageBreadcrumb;
	
	public boolean didWeNavigateToProductReturnsPage() {
		return productReturnsPageBreadcrumb.isDisplayed();
	}

}
