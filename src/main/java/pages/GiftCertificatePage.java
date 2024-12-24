package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class GiftCertificatePage extends RootPage {

	public GiftCertificatePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Gift Certificate']")
	private WebElement giftCertificatePageBreadcrumb;

	public boolean didWeNavigateToGiftCertificatePage() {
		return isElementDisplayed(giftCertificatePageBreadcrumb);
	}

}
