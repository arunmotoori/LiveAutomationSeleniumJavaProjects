package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class DeliveryInformationPage extends RootPage{
	
	public DeliveryInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Delivery Information']")
	private WebElement deliveryInformationPageBreadcrumb;
	
	public boolean didWeNavigateToDeliveryInformationPage() {
		return deliveryInformationPageBreadcrumb.isDisplayed();
	}

}
