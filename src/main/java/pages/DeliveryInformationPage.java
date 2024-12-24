package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class DeliveryInformationPage extends RootPage {
	
	ElementUtils elementUtils;

	public DeliveryInformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Delivery Information']")
	private WebElement deliveryInformationPageBreadcrumb;

	public boolean didWeNavigateToDeliveryInformationPage() {
		return elementUtils.isElementDisplayed(deliveryInformationPageBreadcrumb);
	}

}
