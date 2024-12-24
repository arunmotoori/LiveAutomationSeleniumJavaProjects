package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class GiftCertificatePage extends RootPage {
	
	ElementUtils elementUtils;

	public GiftCertificatePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Gift Certificate']")
	private WebElement giftCertificatePageBreadcrumb;

	public boolean didWeNavigateToGiftCertificatePage() {
		return elementUtils.isElementDisplayed(giftCertificatePageBreadcrumb);
	}

}
