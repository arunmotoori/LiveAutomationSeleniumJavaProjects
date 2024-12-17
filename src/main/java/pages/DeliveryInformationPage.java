package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryInformationPage {
	
	WebDriver driver;
	
	public DeliveryInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Delivery Information']")
	private WebElement deliveryInformationPageBreadcrumb;
	
	public boolean didWeNavigateToDeliveryInformationPage() {
		return deliveryInformationPageBreadcrumb.isDisplayed();
	}

}
