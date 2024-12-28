package tutorialsninja.tests;

import java.util.Properties;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import tutorialsninja.base.Base;
import utils.CommonUtils;

public class Login extends Base {

	public WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApplication();
		prop = CommonUtils.loadProperties();
		landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccount();
		loginPage = landingPage.selectLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		closeBrowser(driver);
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {

		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertTrue(accountPage.didWenavigateToAccountPage());

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		loginPage.enterEmail(CommonUtils.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		loginPage.enterEmail(CommonUtils.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		loginPage.enterEmail(CommonUtils.validEmailRandomizeGenerator());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {

		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

	}

	@Test(priority = 6)
	public void verifyForgottendPasswordLinkOnLoginPage() {

		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		Assert.assertTrue(loginPage.availabilityOfForgottenPasswordLink());
		forgottenPasswordPage = loginPage.clickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottendPasswordPage());

	}

	@Test(priority = 7)
	public void verifyLoggingIntoTheApplicationUsingKeyboardKeys() throws InterruptedException {

		
		driver = landingPage.pressKeyMultipleTimes(driver, Keys.TAB, 23);
		driver = enterDetailsIntoLoginPageFields(driver);
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertTrue(accountPage.didWenavigateToAccountPage());

	}

	@Test(priority = 8)
	public void verifyLoginFieldsPlaceholders() {

		String expectedEmailPlaceholder = "E-Mail Address";
		String expectedPasswordPlaceholder = "Password";
		Assert.assertEquals(loginPage.getEmailPlaceholder(), expectedEmailPlaceholder);
		Assert.assertEquals(loginPage.getPasswordPlaceholder(), expectedPasswordPlaceholder);

	}

	@Test(priority = 9)
	public void verifyBrowserBackAfterLogin() {

		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		driver = navigateBack(driver);
		loginPage = new LoginPage(driver);
		accountPage = loginPage.clickOnMyAccountRightColumnOption();
		Assert.assertTrue(accountPage.isUserLoggedIn());

	}

	@Test(priority = 10)
	public void verifyBrowserBackAfterLoggingOut() {

		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		accountPage.clickOnLogoutOption();
		driver = navigateBack(driver);
		accountPage = new AccountPage(driver);
		accountPage.clickOnEditYourAccountInformationOption();
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

	}

	@Test(priority = 11)
	public void verifyLoginWithInactiveCredentials() {

		loginPage.enterEmail(prop.getProperty("inactiveEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

	}

	@Test(priority = 12)
	public void verifyNumberOfUnsuccessfulLoginAttemps() throws InterruptedException {

		loginPage.enterEmail(CommonUtils.generateBrandNewEmail());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clickOnLoginButton();
		expectedWarning = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

	}

	@Test(priority = 13)
	public void verifyTextEnteredIntoPasswordFieldIsToggledToHideItsVisibility() {

		String expectedType = "password";
		Assert.assertEquals(loginPage.getPasswordFieldType(), expectedType);

	}

	@Test(priority = 14)
	public void verifyCopyingOfTextEnteredIntoPasswordField() {

		String passwordText = prop.getProperty("samplePassword");
		loginPage.enterPassword(passwordText);
	    loginPage.selectPasswordFieldTextAndCopy();
		loginPage.pasteCopiedPasswordTextIntoEmailField();
		Assert.assertNotEquals(loginPage.getTextCopiedIntoEmailField(), passwordText);

	}

	@Test(priority = 15)
	public void verifyPasswordIsStoredInHTMLCodeOfThePage() {

		String passwordText = prop.getProperty("samplePassword");
		loginPage.enterPassword(passwordText);
		Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
		loginPage.clickOnLoginButton();
		Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));

	}

	@Test(priority = 16)
	public void verifyLoggingIntoApplicationAfterChaningPassword() {

		String oldPassword = null;
		String newPassword = null;
		oldPassword = prop.getProperty("validPasswordTwo");
		newPassword = prop.getProperty("samplePasswordTwo");
		loginPage.enterEmail(prop.getProperty("existingSampleEmailTwo"));
		loginPage.enterPassword(oldPassword);
		accountPage = loginPage.clickOnLoginButton();
		changePasswordPage = accountPage.clickOnChangeYourPasswordOption();
		changePasswordPage.enterPassword(newPassword);
		changePasswordPage.enterConfirmPassword(newPassword);
		accountPage = changePasswordPage.clickOnContinueButton();
		String expectedMessage = "Success: Your password has been successfully updated.";
		Assert.assertEquals(accountPage.getMessage(), expectedMessage);
		accountLogoutPage = accountPage.clickOnLogoutOption();
		accountLogoutPage.clickOnMyAccountDropMenu();
		loginPage = accountLogoutPage.selectLoginOption();
		loginPage.enterEmail(prop.getProperty("existingSampleEmailTwo"));
		loginPage.enterPassword(oldPassword);
		loginPage.clickOnLoginButton();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
		loginPage.clearPassword();
		loginPage.enterPassword(newPassword);
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLoggedIn());
		CommonUtils.setProperties("validPasswordTwo", newPassword, prop);
		CommonUtils.setProperties("samplePasswordTwo", oldPassword, prop);

	}

	@Test(priority = 17)
	public void verifyNavigatingToDifferentPagesFromLoginPage() {

		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		contactUsPage = headerOptions.selectPhoneIconOption();
		Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		headerOptions = new HeaderOptions(loginPage.getDriver());
		loginPage = headerOptions.selectHeartIconOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		headerOptions = new HeaderOptions(loginPage.getDriver());
		shoppingCartPage = headerOptions.selectShoppingCartOption();
		Assert.assertTrue(shoppingCartPage.didWeNaviateToShoppingCartPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		headerOptions = new HeaderOptions(loginPage.getDriver());
		shoppingCartPage = headerOptions.selectCheckoutOption();
		Assert.assertTrue(shoppingCartPage.didWeNaviateToShoppingCartPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		headerOptions = new HeaderOptions(loginPage.getDriver());
		landingPage = headerOptions.selectLogoOption();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		headerOptions = new HeaderOptions(loginPage.getDriver());
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		loginPage = loginPage.clickOnLoginBreadcrumb();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		headerOptions = new HeaderOptions(loginPage.getDriver());
		loginPage = headerOptions.clickOnAccountBreadcrumb();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		headerOptions = new HeaderOptions(loginPage.getDriver());
		landingPage = headerOptions.clickOnHomeBreadcrumb();
		Assert.assertEquals(getPageURL(driver), prop.getProperty("landingPageURL"));
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		registerPage = loginPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		forgottenPasswordPage = loginPage.clickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottendPasswordPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		loginPage = rightColumnOptions.clickOnRightSideLoginOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		registerPage = rightColumnOptions.clickOnRightSideRegisterOption();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		forgottenPasswordPage = rightColumnOptions.clickOnRightSideForgottenPasswordOption();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottendPasswordPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		loginPage = rightColumnOptions.clickOnRightSideMyAccountOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideAddressBookOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideWishListOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideOrderHistoryOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideDownloadsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideRecurringPaymentsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideRewardPointsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideReturnsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideTransactionsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		rightColumnOptions.clickOnRightSideNewsletterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		AboutUsPage aboutUsPage = footerOptions.clickOnAboutUsFooterOption();
		Assert.assertTrue(aboutUsPage.didWeNavigateToAboutUsBreadcrumb());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		deliveryInformationPage = footerOptions.clickOnDeliveryInformationFooterOption();
		Assert.assertTrue(deliveryInformationPage.didWeNavigateToDeliveryInformationPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		privacyPolicyPage = footerOptions.clickOnPrivacyPolicyFooterOption();
		Assert.assertTrue(privacyPolicyPage.didWeNavigateToPrivacyPolicyPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		termsAndConditionsPage = footerOptions.clickOnTermsAndConditionsFooterOption();
		Assert.assertTrue(termsAndConditionsPage.didWeNavigateToTermsAndConditionsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		contactUsPage = footerOptions.clickOnContactUsFooterOption();
		Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		productReturnsPage = footerOptions.clickOnReturnsFooterOption();
		Assert.assertTrue(productReturnsPage.didWeNavigateToProductReturnsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		siteMapPage = footerOptions.clickOnSiteMapFooterOption();
		Assert.assertTrue(siteMapPage.didWeNavigateToSiteMapPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		brandPage = footerOptions.clickOnBrandsFooterOption();
		Assert.assertTrue(brandPage.didWeNavigateToBrandsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		giftCertificatePage = footerOptions.clickOnGiftCertificateFooterOption();
		Assert.assertTrue(giftCertificatePage.didWeNavigateToGiftCertificatePage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		affiliateLoginPage = footerOptions.clickOnAffiliateFooterOption();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("affiliateLoginPageURL"));
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		specialOffersPage = footerOptions.clickOnSpecialsFooterOption();
		Assert.assertTrue(specialOffersPage.didWeNavigateToSpecialOffersPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		loginPage = footerOptions.clickOnMyAccountFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		loginPage = footerOptions.clickOnOrderHistoryFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		loginPage = footerOptions.clickOnWishListFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		footerOptions = new FooterOptions(loginPage.getDriver());
		loginPage = footerOptions.clickOnNewsletterFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

	}

	@Test(priority = 18)
	public void verifyDifferentWaysOfNavigatingToLoginPage() {

		registerPage = loginPage.clickOnContinueButton();
		loginPage = registerPage.clickOnLoginPageLink();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		rightColumnOptions = new RightColumnOptions(loginPage.getDriver());
		loginPage = rightColumnOptions.clickOnRightSideLoginOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		headerOptions = new HeaderOptions(loginPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

	}

	@Test(priority = 19)
	public void verifyBreadCrumbPageHeadingTitleAndPageURLOfLoginPage() {

		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		Assert.assertEquals(getPageTitle(driver), prop.getProperty("loginPageTitle"));
		Assert.assertEquals(getPageURL(driver), prop.getProperty("loginPageURL"));
		Assert.assertEquals(loginPage.getPageHeadingOne(), prop.getProperty("registerPageHeadingOne"));
		Assert.assertEquals(loginPage.getPageHeadingTwo(), prop.getProperty("registerPageHeadingTwo"));

	}

	@Test(priority = 20)
	public void verifyUIOfLoginPage() {

		if (prop.getProperty("browserName").equals("chrome")) {
			CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualLoginPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageUI.png"));

		}else if(prop.getProperty("browserName").equals("edge")) {
			CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualEdgeLoginPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLoginPageUI.png"));
			
		}else if(prop.getProperty("browserName").equals("firefox")) {
			CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualFirefoxLoginPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLoginPageUI.png"));
			
		}
	}

	@Test(priority = 21)
	public void verifyLoginFunctionalityInAllEnvironments() {

		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertTrue(accountPage.didWenavigateToAccountPage());

	}

}
