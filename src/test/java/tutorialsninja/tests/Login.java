package tutorialsninja.tests;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AboutUsPage;
import pages.AccountPage;
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.LoginPage;
import pages.RightColumnOptions;
import tutorialsninja.base.Base;
import utils.CommonUtils;

public class Login extends Base {

	WebDriver driver;
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
		if (driver != null) {
			driver.quit();
		}
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
	public void verifyLoggingIntoTheApplicationUsingKeyboardKeys() {

		driver = pressKeyMultipleTimes(driver, Keys.TAB, 23);
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
		driver = loginPage.selectPasswordFieldTextAndCopy(driver);
		driver = loginPage.pasteCopiedPasswordTextIntoEmailField(driver);
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
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		loginPage = headerOptions.selectHeartIconOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		shoppingCartPage = headerOptions.selectShoppingCartOption();
		Assert.assertTrue(shoppingCartPage.didWeNaviateToShoppingCartPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		shoppingCartPage = headerOptions.selectCheckoutOption();
		Assert.assertTrue(shoppingCartPage.didWeNaviateToShoppingCartPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		landingPage = headerOptions.selectLogoOption();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		loginPage = loginPage.clickOnLoginBreadcrumb();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		loginPage = headerOptions.clickOnAccountBreadcrumb();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		headerOptions = new HeaderOptions(driver);
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
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		loginPage = rightColumnOptions.clickOnRightSideLoginOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		registerPage = rightColumnOptions.clickOnRightSideRegisterOption();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		forgottenPasswordPage = rightColumnOptions.clickOnRightSideForgottenPasswordOption();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottendPasswordPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		loginPage = rightColumnOptions.clickOnRightSideMyAccountOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideAddressBookOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideWishListOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideOrderHistoryOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideDownloadsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideRecurringPaymentsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideRewardPointsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideReturnsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideTransactionsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		rightColumnOptions = new RightColumnOptions(driver);
		rightColumnOptions.clickOnRightSideNewsletterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		AboutUsPage aboutUsPage = footerOptions.clickOnAboutUsFooterOption();
		Assert.assertTrue(aboutUsPage.didWeNavigateToAboutUsBreadcrumb());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		deliveryInformationPage = footerOptions.clickOnDeliveryInformationFooterOption();
		Assert.assertTrue(deliveryInformationPage.didWeNavigateToDeliveryInformationPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		privacyPolicyPage = footerOptions.clickOnPrivacyPolicyFooterOption();
		Assert.assertTrue(privacyPolicyPage.didWeNavigateToPrivacyPolicyPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		termsAndConditionsPage = footerOptions.clickOnTermsAndConditionsFooterOption();
		Assert.assertTrue(termsAndConditionsPage.didWeNavigateToTermsAndConditionsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		contactUsPage = footerOptions.clickOnContactUsFooterOption();
		Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		productReturnsPage = footerOptions.clickOnReturnsFooterOption();
		Assert.assertTrue(productReturnsPage.didWeNavigateToProductReturnsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		siteMapPage = footerOptions.clickOnSiteMapFooterOption();
		Assert.assertTrue(siteMapPage.didWeNavigateToSiteMapPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		brandPage = footerOptions.clickOnBrandsFooterOption();
		Assert.assertTrue(brandPage.didWeNavigateToBrandsPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		giftCertificatePage = footerOptions.clickOnGiftCertificateFooterOption();
		Assert.assertTrue(giftCertificatePage.didWeNavigateToGiftCertificatePage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		affiliateLoginPage = footerOptions.clickOnAffiliateFooterOption();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("affiliateLoginPageURL"));
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		specialOffersPage = footerOptions.clickOnSpecialsFooterOption();
		Assert.assertTrue(specialOffersPage.didWeNavigateToSpecialOffersPage());
		driver = navigateBack(driver);

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.clickOnMyAccountFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.clickOnOrderHistoryFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.clickOnWishListFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

		loginPage = new LoginPage(driver);
		driver = loginPage.getDriver();
		footerOptions = new FooterOptions(driver);
		loginPage = footerOptions.clickOnNewsletterFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());

	}

}
