package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialOffersPage {
	
	WebDriver driver;
	
	public SpecialOffersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Special Offers']")
	private WebElement specialOffersPageBreadcrumb;
	
	public boolean didWeNavigateToSpecialOffersPage() {
		return specialOffersPageBreadcrumb.isDisplayed();
	}

}
