package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class DeliveryInformationPage extends RootPage {

	public DeliveryInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Delivery Information']")
	private WebElement deliveryInformationPageBreadcrumb;

	public boolean didWeNavigateToDeliveryInformationPage() {
		return isElementDisplayed(deliveryInformationPageBreadcrumb);
	}

}
