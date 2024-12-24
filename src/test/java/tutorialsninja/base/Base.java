package tutorialsninja.base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import pages.AboutUsPage;
import pages.AccountLogoutPage;
import pages.AccountPage;
import pages.AccountSuccessPage;
import pages.AffiliateLoginPage;
import pages.BrandPage;
import pages.ChangePasswordPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.EditAccountInformationPage;
import pages.FooterOptions;
import pages.ForgottenPasswordPage;
import pages.GiftCertificatePage;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.LoginPage;
import pages.NewsletterPage;
import pages.PrivacyPolicyPage;
import pages.ProductReturnsPage;
import pages.RegisterPage;
import pages.RightColumnOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialOffersPage;
import pages.TermsAndConditionsPage;
import utils.CommonUtils;
import utils.MyXLSReader;

public class Base {
	
	WebDriver driver;
	Properties prop;
	public MyXLSReader myXLSReader;
	
	public LandingPage landingPage;
	public RegisterPage registerPage;
	public AccountSuccessPage accountSuccessPage;
	public AccountPage accountPage;
	public NewsletterPage newsletterPage;
	public LoginPage loginPage;
	public EditAccountInformationPage editAccountInformationPage;
	public ContactUsPage contactUsPage;
	public ShoppingCartPage shoppingCartPage;
	public SearchPage searchPage;
	public ForgottenPasswordPage forgottenPasswordPage;
	public AboutUsPage aboutUsPage;
	public DeliveryInformationPage deliveryInformationPage;
	public PrivacyPolicyPage privacyPolicyPage;
	public TermsAndConditionsPage termsAndConditionsPage;
	public ProductReturnsPage productReturnsPage;
	public SiteMapPage siteMapPage;
	public BrandPage brandPage;
	public GiftCertificatePage giftCertificatePage;
	public AffiliateLoginPage affiliateLoginPage;
	public SpecialOffersPage specialOffersPage;
	public HeaderOptions headerOptions;
	public RightColumnOptions rightColumnOptions;
	public FooterOptions footerOptions;
	public ChangePasswordPage changePasswordPage;
	public AccountLogoutPage accountLogoutPage;

	public WebDriver openBrowserAndApplication() {

		prop = CommonUtils.loadProperties();
		String browserName = prop.getProperty("browserName");

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appURL"));
		
		return driver;

	}
	
	public WebDriver navigateToRegisterPage(WebDriver driver,String URL) {
		driver.navigate().to(URL);
		return driver;
	}
	
	public WebDriver navigateBack(WebDriver driver) {
		driver.navigate().back();
		return driver;
	}
	
	public WebDriver pressKeyMultipleTimes(WebDriver driver,Keys keyName,int count) {
		
		Actions actions = new Actions(driver);
		
		for(int i=1;i<=count;i++) {
			actions.sendKeys(keyName).perform();
		}
		
		return driver;
		
	}
	
	public WebDriver enterDetailsIntoRegisterAccountPageFields(WebDriver driver) {
		
		prop = CommonUtils.loadProperties();
		Actions actions = new Actions(driver);
		actions.sendKeys(prop.getProperty("firstName")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
		.pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("lastName")).sendKeys(Keys.TAB)
		.pause(Duration.ofSeconds(1)).sendKeys(CommonUtils.generateBrandNewEmail()).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("telephoneNumber"))
		.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
		.sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
		.pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.LEFT).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.ENTER).pause(Duration.ofSeconds(3)).build().perform();
		return driver;
		
	}
	
	public WebDriver enterDetailsIntoLoginPageFields(WebDriver driver) {
		
		prop = CommonUtils.loadProperties();
		Actions actions = new Actions(driver);
		actions.sendKeys(prop.getProperty("existingEmail")).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword"))
		.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
		.sendKeys(Keys.ENTER).pause(Duration.ofSeconds(2)).build().perform();
		
		return driver;
		
	}
	
	public String getHTMLCodeOfThePage() {
		return driver.getPageSource();
	}
	
	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public void closeBrowser(WebDriver driver) {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	public WebDriver refreshPage(WebDriver driver) {
		driver.navigate().refresh();
		return driver;
	}

}
