package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class FooterOptions extends RootPage{
	
	ElementUtils elementUtils;
	
	public FooterOptions(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="About Us")
	private WebElement aboutUsFooterOption;
	
	@FindBy(linkText="Delivery Information")
	private WebElement deliveryInfomrationFooterOption;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Privacy Policy']")
	private WebElement privacyPolicyFooterOption;
	
	@FindBy(linkText="Terms & Conditions")
	private WebElement termsAndConditionsFooterOption;
	
	@FindBy(linkText="Contact Us")
	private WebElement contactUsFooterOption;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Returns']")
	private WebElement returnsFooterOption;
	
	@FindBy(linkText="Site Map")
	private WebElement siteMapFooterOption;
	
	@FindBy(linkText="Brands")
	private WebElement brandsFooterOption;
	
	@FindBy(linkText="Gift Certificates")
	private WebElement giftCertificateFooterOption;
	
	@FindBy(linkText="Affiliate")
	private WebElement affiliateFooterOption;
	
	@FindBy(linkText="Specials")
	private WebElement specialsFooterOption;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='My Account']")
	private WebElement myAccountFooterOption;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Order History']")
	private WebElement orderHistoryFooterOption;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Wish List']")
	private WebElement wishListFooterOption;
	
	@FindBy(xpath="//ul[@class='list-unstyled']//a[text()='Newsletter']")
	private WebElement newsletterFooterOption;
	
	public LoginPage clickOnNewsletterFooterOption() {
		elementUtils.clickOnElement(newsletterFooterOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnWishListFooterOption() {
		elementUtils.clickOnElement(wishListFooterOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnOrderHistoryFooterOption() {
		elementUtils.clickOnElement(orderHistoryFooterOption);
		return new LoginPage(driver);
	}
	
	public LoginPage clickOnMyAccountFooterOption() {
		elementUtils.clickOnElement(myAccountFooterOption);
		return new LoginPage(driver);
	}
	
	public SpecialOffersPage clickOnSpecialsFooterOption() {
		elementUtils.clickOnElement(specialsFooterOption);
		return new SpecialOffersPage(driver);
	}
	
	public AffiliateLoginPage clickOnAffiliateFooterOption() {
		elementUtils.clickOnElement(affiliateFooterOption);
		return new AffiliateLoginPage(driver);
	}
	
	public GiftCertificatePage clickOnGiftCertificateFooterOption() {
		elementUtils.clickOnElement(giftCertificateFooterOption);
		return new GiftCertificatePage(driver);
	}
	
	public BrandPage clickOnBrandsFooterOption() {
		elementUtils.clickOnElement(brandsFooterOption);
		return new BrandPage(driver);
	}
	
	public SiteMapPage clickOnSiteMapFooterOption() {
		elementUtils.clickOnElement(siteMapFooterOption);
		return new SiteMapPage(driver);
	}
	
	public ProductReturnsPage clickOnReturnsFooterOption() {
		elementUtils.clickOnElement(returnsFooterOption);
		return new ProductReturnsPage(driver);
	}
	
	public ContactUsPage clickOnContactUsFooterOption() {
		elementUtils.clickOnElement(contactUsFooterOption);
		return new ContactUsPage(driver);
	}
	
	public TermsAndConditionsPage clickOnTermsAndConditionsFooterOption() {
		elementUtils.clickOnElement(termsAndConditionsFooterOption);
		return new TermsAndConditionsPage(driver);
	}
	
	public PrivacyPolicyPage clickOnPrivacyPolicyFooterOption() {
		elementUtils.clickOnElement(privacyPolicyFooterOption);
		return new PrivacyPolicyPage(driver);
	}
	
	public DeliveryInformationPage clickOnDeliveryInformationFooterOption() {
		elementUtils.clickOnElement(deliveryInfomrationFooterOption);
		return new DeliveryInformationPage(driver);
	}
	
	public AboutUsPage clickOnAboutUsFooterOption() {
		elementUtils.clickOnElement(aboutUsFooterOption);
		return new AboutUsPage(driver);
	}

}
