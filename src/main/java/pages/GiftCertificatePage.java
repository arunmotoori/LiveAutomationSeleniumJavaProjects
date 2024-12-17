package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GiftCertificatePage {
	
	WebDriver driver;
	
	public GiftCertificatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Gift Certificate']")
	private WebElement giftCertificatePageBreadcrumb;

	public boolean didWeNavigateToGiftCertificatePage() {
		return giftCertificatePageBreadcrumb.isDisplayed();
	}

}
