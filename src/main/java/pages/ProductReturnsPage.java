package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductReturnsPage {
	
	WebDriver driver;

	public ProductReturnsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Product Returns']")
	private WebElement productReturnsPageBreadcrumb;
	
	public boolean didWeNavigateToProductReturnsPage() {
		return productReturnsPageBreadcrumb.isDisplayed();
	}

}
