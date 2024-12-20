package tutorialsninja.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AccountLogoutPage;
import pages.HeaderOptions;
import pages.LandingPage;
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
	
	@Test
	public void verifyLoggingOutUsingMyAccountDropMenu() {
		
		landingPage.clickOnMyAccount();
		loginPage = landingPage.selectLoginOption();
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		driver = accountPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		accountLogoutPage = headerOptions.selectLogoutOption();
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		driver = accountLogoutPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionAvailable());
		driver = headerOptions.getDriver();
		accountLogoutPage = new AccountLogoutPage(driver);
		landingPage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageURL(landingPage.getDriver()),prop.getProperty("landingPageURL"));
	
	}

}
