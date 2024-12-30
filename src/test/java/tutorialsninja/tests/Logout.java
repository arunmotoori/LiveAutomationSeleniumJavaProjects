package tutorialsninja.tests;

import java.util.Properties;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import tutorialsninja.base.Base;
import utils.CommonUtils;

public class Logout extends Base {
	
	public WebDriver driver;
	Properties prop;
	
	@AfterMethod
	public void tearDown() {
		closeBrowser(driver);
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = openBrowserAndApplication();
		prop = CommonUtils.loadProperties();
		landingPage = new LandingPage(driver);
		
	}
	
	@Test(priority = 1)
	public void verifyLoggingOutUsingMyAccountDropMenu() {
		
		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(accountPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		headerOptions = new HeaderOptions(accountLogoutPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailable());
		accountLogoutPage = new AccountLogoutPage(headerOptions.getDriver());
		landingPage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageURL(landingPage.getDriver()),prop.getProperty("landingPageURL"));
	
	}
	
	@Test(priority = 2)
	public void verifyLoggintOutUsingLogoutRightColumnOption() {
		
		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		rightColumnOptions = new RightColumnOptions(accountPage.getDriver());
		accountLogoutPage = rightColumnOptions.clickOnRightSideLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		headerOptions = new HeaderOptions(accountLogoutPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailable());
		accountLogoutPage = new AccountLogoutPage(headerOptions.getDriver());
		landingPage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageURL(landingPage.getDriver()),prop.getProperty("landingPageURL"));
		
	}
	
	@Test(priority = 3)
	public void verifyLoggingOutAndBrowingBack() {
		
		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"), prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(accountPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		driver = navigateBack(accountLogoutPage.getDriver());
		driver = refreshPage(driver);
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		
	}
	
	@Test(priority = 4)
	public void verifyThereIsNoLogoutOptionBeforeLogin() {
		
		headerOptions = new HeaderOptions(landingPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertFalse(headerOptions.isLogoutOptionAvaialable());
		
	}
	
	@Test(priority = 5)
	public void verifyThereIsNoRightColumnLogoutOptionBeforeLogin() {
		
		registerPage = landingPage.navigateToRegisterPage();
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		Assert.assertFalse(rightColumnOptions.isLogoutRightColumnOptionAvailable());
	
	}
	
	@Test(priority = 6)
	public void verifyLoginAfterLogout() {
		
		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(accountPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		headerOptions = new HeaderOptions(accountLogoutPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		Assert.assertTrue(accountPage.isUserLoggedIn());
		
	}
	
	@Test(priority = 7)
	public void verifyBreadcrumbTitleHeadingAndURLOfAccountLogoutPage() {
		
		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(accountPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), prop.getProperty("accountLogoutPageTitle"));
		Assert.assertEquals(getPageURL(accountLogoutPage.getDriver()),prop.getProperty("accountLogoutPageURL"));
		Assert.assertEquals(accountLogoutPage.getPageHeading(),prop.getProperty("accountLogoutPageHeading"));
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		
	}
	
	@Test(priority = 8)
	public void verifyUIOfLogoutOptionAndAccountLogoutPage() {
		
		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(accountPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		if(prop.getProperty("browserName").equals("firefox")) {
			CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualFirefoxLogoutOption.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualFirefoxLogoutOption.png",System.getProperty("user.dir")+"\\Screenshots\\expectedFirefoxLogoutOption.png"));
		}else if(prop.getProperty("browserName").equals("chrome")) {
			CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualChromeLogoutOption.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualChromeLogoutOption.png",System.getProperty("user.dir")+"\\Screenshots\\expectedChromeLogoutOption.png"));
		}else if(prop.getProperty("browserName").equals("edge")) {
			CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualEdgeLogoutOption.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualEdgeLogoutOption.png",System.getProperty("user.dir")+"\\Screenshots\\expectedEdgeLogoutOption.png"));
		}
		accountLogoutPage = headerOptions.selectLogoutOption();
		if(prop.getProperty("browserName").equals("firefox")) {
			CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualFirefoxAccountLogoutPage.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualFirefoxAccountLogoutPage.png",System.getProperty("user.dir")+"\\Screenshots\\expectedFirefoxAccountLogoutPage.png"));
		}else if(prop.getProperty("browserName").equals("chrome")) {
			CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualChromeAccountLogoutPage.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualChromeAccountLogoutPage.png",System.getProperty("user.dir")+"\\Screenshots\\expectedChromeAccountLogoutPage.png"));
		}else if(prop.getProperty("browserName").equals("edge")) {
			CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualEdgeAccountLogoutPage.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualEdgeAccountLogoutPage.png",System.getProperty("user.dir")+"\\Screenshots\\expectedEdgeAccountLogoutPage.png"));
		}
		
	}
	
	@Test(priority = 9)
	public void verifyAccountLogoutFunctionality() {
		
		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(accountPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		landingPage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageURL(landingPage.getDriver()),prop.getProperty("landingPageURL"));
		headerOptions = new HeaderOptions(landingPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailable());
		
	}
	
}
