package tutorialsninja.tests;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.search.FlagTerm;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import tutorialsninja.base.Base;
import utils.CommonUtils;
import utils.MyXLSReader;

public class Register extends Base {

	public WebDriver driver;
	Properties prop;
	
	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApplication();
		prop = CommonUtils.loadProperties();
		landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccount();
		registerPage = landingPage.selectRegisterOption();

	}

	@AfterMethod
	public void teardown() {
		closeBrowser(driver);
	}

	@Test(priority = 1)
	public void verifyRegisteringWithMandatoryFields() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(accountSuccessPage.getPageHeading(), expectedHeading);
		String expectedProperDetailsOne = "Congratulations! Your new account has been successfully created!";
		String expectedProperDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String expectedProperDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String expectedProperDetailsFour = "contact us";
		String actualProperDetails = accountSuccessPage.getPageContent();
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
		accountPage = accountSuccessPage.clickOnContinueButton();
		Assert.assertTrue(accountPage.didWenavigateToAccountPage());

	}

	@Test(priority = 2)
	public void verifyConfirmationEmail() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		String emailAddress = CommonUtils.generateBrandNewEmail();
		registerPage.enterEmail(emailAddress);
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String email = emailAddress;
		String appPasscode = "gosd duiq gjoe bhqu";

		System.out.println("Halting the program intentionally for 10 seconds.");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Gmail IMAP configuration
		String host = "imap.gmail.com";
		String port = "993";
		String username = email; // Your Gmail address
		String appPassword = appPasscode; // Your app password
		String expectedSubject = "TutorialsNinja - Welcome to your account";
		String expectedFromEmail = "account-update@tn.in>";
		String expectedBodyContent = "Confirm your email to activate your account";
		boolean b = false;
		try {
			// Mail server connection properties
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "imaps");
			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", port);
			properties.put("mail.imap.ssl.enable", "true");

			// Connect to the mail server
			Session emailSession = Session.getDefaultInstance(properties);

			Store store = emailSession.getStore("imaps");

			store.connect(host, username, appPassword); // replace email password with App password

			// Open the inbox folder
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Search for unread emails
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			boolean found = false;
			for (int i = messages.length - 1; i >= 0; i--) {

				Message message = messages[i];

				if (message.getSubject().contains(expectedSubject)) {
					found = true;
					Assert.assertEquals(message.getSubject(), expectedSubject);
					Assert.assertEquals(message.getFrom()[0].toString(), expectedFromEmail);
					String actualEmailBody = CommonUtils.getTextFromMessage(message);
					Assert.assertTrue(actualEmailBody.contains(expectedBodyContent));

					break;
				}
			}

			if (!found) {
				System.out.println("No confirmation email found.");
			}

			// Close the store and folder objects
			inbox.close(false);
			store.close();
			b = true;

		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		
		Assert.assertTrue(b);
	}

	@Test(priority = 3)
	public void verifyRegisterAccountWithAllFields() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

		String expectedProperDetailsOne = "Your Account Has Been Created!";
		String expectedProperDetailsTwo = "Congratulations! Your new account has been successfully created!";
		String expectedProperDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String expectedProperDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String expectedProperDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
		String expectedProperDetailsSix = "contact us";
		String actualProperDetails = accountSuccessPage.getPageContent();
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFive));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsSix));
		accountPage = accountSuccessPage.clickOnContinueButton();
		Assert.assertTrue(accountPage.didWenavigateToAccountPage());

	}

	@Test(priority = 4)
	public void verifyRegistringAccountWithoutFillFields() {

		registerPage.clickOnContinueButton();

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";

		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedFirstNameWarning);
		Assert.assertEquals(registerPage.getLastNameWarning(), expectedLastNameWarning);
		Assert.assertEquals(registerPage.getEmailWarning(), expectedEmailWarning);
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedTelephoneWarning);
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
		Assert.assertEquals(registerPage.getPrivacyPolicyWarning(), expectedPrivacyPolicyWarning);

	}

	@Test(priority = 5)
	public void verifyRegisteringAccountBySubscribingToNewsletter() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		accountPage = accountSuccessPage.clickOnContinueButton();
		newsletterPage = accountPage.selectSubscribeUnSubscribeNewsletterOption();
		Assert.assertTrue(newsletterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsletterPage.isYesNewsletterOptionSelected());

	}

	@Test(priority = 6)
	public void verifyRegisteringAccountBySayingNoToNewsletter() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNoNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		accountPage = accountSuccessPage.clickOnContinueButton();
		newsletterPage = accountPage.selectSubscribeUnSubscribeNewsletterOption();
		Assert.assertTrue(newsletterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsletterPage.isNoNewsletterOptionSelected());

	}

	@Test(priority = 7)
	public void verifyNavigatingToRegisterAccountUsingMultipleWay() {

		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());
		headerOptions = new HeaderOptions(registerPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		loginPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());
		headerOptions = new HeaderOptions(registerPage.getDriver());
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.selectLoginOption();
		loginPage.clickOnRegisterOption();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());

	}

	@Test(priority = 8)
	public void verifyRegisteringAccountByProvidingMismatchingPasswords() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("mismatchingPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		String expectedWarningMessage = "Password confirmation does not match password!";
		Assert.assertEquals(registerPage.getPasswordConfirmWarning(), expectedWarningMessage);

	}

	@Test(priority = 9)
	public void verifyRegistringAccountUsingExistingEmail() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("existingEmail"));
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(registerPage.getExistingEmailWarning(), expectedWarningMessage);

	}

	@Test(priority = 10)
	public void verifyRegisteringAccountWithInvalidEmails() throws InterruptedException, IOException {

		String browserName = prop.getProperty("browserName");
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("invalidEmailOne"));
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		Thread.sleep(3000);

		if (browserName.equals("chrome") || browserName.equals("edge")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(),
					"Please include an '@' in the email address. 'amotoori' is missing an '@'.");
		} else if (browserName.equals("firefox")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(), "Please enter an email address.");
		}

		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailTwo"));
		registerPage.clickOnContinueButton();

		Thread.sleep(2000);

		if (browserName.equals("chrome") || browserName.equals("edge")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(),
					"Please enter a part following '@'. 'amotoori@' is incomplete.");
		} else if (browserName.equals("firefox")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(), "Please enter an email address.");
		}

		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailThree"));
		registerPage.clickOnContinueButton();

		Thread.sleep(2000);

		String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
		Thread.sleep(2000);
		Assert.assertEquals(registerPage.getEmailWarning(), expectedWarningMessage);

		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailFour"));
		registerPage.clickOnContinueButton();

		Thread.sleep(3000);

		if (browserName.equals("chrome") || browserName.equals("edge")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(),
					"'.' is used at a wrong position in 'gmail.'.");
		} else if (browserName.equals("firefox")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(), "Please enter an email address.");
		}
	}

	@Test(priority = 11)
	public void verifyRegisterAccountByProvidingInvalidTelephoneNumber() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("invalidTelephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String expectedWarningMessage = "Telephone number does not appear to be valid";

		boolean state = false;

		try {
			String actualWarningMessage = registerPage.getTelephoneWarning();
			if (actualWarningMessage.equals(expectedWarningMessage)) {
				state = true;
			}
		} catch (NoSuchElementException e) {
			state = false;
		}

		Assert.assertTrue(state);

	}

	@Test(priority = 12)
	public void verifyRegisteringAccountUsingKeyboardKeys() {

		driver = pressKeyMultipleTimes(driver, Keys.TAB, 23);
		driver = enterDetailsIntoRegisterAccountPageFields(driver);
		accountSuccessPage = new AccountSuccessPage(driver);
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

	}

	@Test(priority = 13)
	public void verifyPlaceHoldersOfTextFieldsInRegisterAccountPage() {

		String expectedFirstNamePlaceHolderText = "First Name";
		Assert.assertEquals(registerPage.getPlaceHolderTextFromFirstNameField(), expectedFirstNamePlaceHolderText);

		String expectedLastNamePlaceHolderText = "Last Name";
		Assert.assertEquals(registerPage.getPlaceHolderTextFromLastNameField(), expectedLastNamePlaceHolderText);

		String expectedEmailPlaceHolderText = "E-Mail";
		Assert.assertEquals(registerPage.getPlaceHolderTextFromEmailField(), expectedEmailPlaceHolderText);

		String expectedTelephonePlaceHolderText = "Telephone";
		Assert.assertEquals(registerPage.getPlaceHolderTextFromTelephoneField(), expectedTelephonePlaceHolderText);

		String expectedPasswordPlaceHolderText = "Password";
		Assert.assertEquals(registerPage.getPlaceHolderTextFromPasswordField(), expectedPasswordPlaceHolderText);

		String expectedPasswordConfirmPlaceHolderText = "Password Confirm";
		Assert.assertEquals(registerPage.getPlaceHolderTextFromPasswordConfirmField(),
				expectedPasswordConfirmPlaceHolderText);

	}

	@Test(priority = 14)
	public void verifyMandatoryFieldsSymbolAndColorInRegisterAccountPage() {

		String expectedContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";

		Assert.assertEquals(registerPage.getFirstNameLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getFirstNameLabelColor(driver), expectedColor);
		Assert.assertEquals(registerPage.getLastNameLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getLastNameLabelColor(driver), expectedColor);
		Assert.assertEquals(registerPage.getEmailLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getEmailLabelColor(driver), expectedColor);
		Assert.assertEquals(registerPage.getTelephoneLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getTelephoneLabelColor(driver), expectedColor);
		Assert.assertEquals(registerPage.getPasswordLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getPasswordLabelColor(driver), expectedColor);
		Assert.assertEquals(registerPage.getPasswordConfirmLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getPasswordConfirmLabelColor(driver), expectedColor);
		Assert.assertEquals(registerPage.getPrivacyPolicyLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getPrivacyPolicyLabelColor(driver), expectedColor);

	}

	// JDBC URL, username, and password of MySQL server
	private static final String URL = "jdbc:mysql://localhost:3306/opencart_db";
	private static final String USER = "root";
	private static final String PASSWORD = null;

	@Test(priority = 15)
	public void verifyDataTestingOfRegisteringAccount() {

		String firstNameInputData = "Arun";
		registerPage.enterFirstName(firstNameInputData);
		String lastNameInputData = "Motoori";
		registerPage.enterLastName(lastNameInputData);
		String emailInputData = CommonUtils.generateBrandNewEmail();
		registerPage.enterEmail(emailInputData);
		String passwordInputData = "12345";
		registerPage.enterPassword(passwordInputData);
		// driver.findElement(By.id("input-newsletter")).click();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String firstNameStoredInDatabase = null;
		String lastNameStoredInDatabase = null;
		String emailStoredInDatabase = null;

		try {
			// Step 1: Establish the connection
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to the database!");

			// Step 2: Create a statement
			statement = connection.createStatement();

			// Step 3: Execute a query
			String sql = "SELECT * FROM oc_customer";
			resultSet = statement.executeQuery(sql);

			// Step 4: Process the result set
			while (resultSet.next()) {
				firstNameStoredInDatabase = resultSet.getString("firstname");
				lastNameStoredInDatabase = resultSet.getString("lastname");
				emailStoredInDatabase = resultSet.getString("email");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Step 5: Clean up the resources
			try {
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		Assert.assertEquals(firstNameStoredInDatabase, firstNameInputData);
		Assert.assertEquals(lastNameStoredInDatabase, lastNameInputData);
		Assert.assertEquals(emailStoredInDatabase, emailInputData);

	}

	@Test(priority = 16)
	public void verifyRegisteringAccountWithOnlySpaces() throws InterruptedException {

		registerPage.enterFirstName(" ");
		registerPage.enterLastName(" ");
		registerPage.enterEmail(" ");
		registerPage.enterTelephoneNumber(" ");
		registerPage.enterPassword(" ");
		registerPage.enterConfirmPassword(" ");
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String firstNameWarning = "First Name must be between 1 and 32 characters!";
		String lastNameWarning = "Last Name must be between 1 and 32 characters!";
		String emailWarning = "E-Mail Address does not appear to be valid!";
		String telephoneWarning = "Telephone must be between 3 and 32 characters!";
		String passwordWarning = "Password must be between 4 and 20 characters!";

		if (prop.getProperty("browserName").equals("chrome") || prop.getProperty("browserName").equals("edge")) {
			Assert.assertEquals(registerPage.getFirstNameWarning(), firstNameWarning);
			Assert.assertEquals(registerPage.getLastNameWarning(), lastNameWarning);
			Assert.assertEquals(registerPage.getEmailWarning(), emailWarning);
			Assert.assertEquals(registerPage.getTelephoneWarning(), telephoneWarning);
			Assert.assertEquals(registerPage.getPasswordWarning(), passwordWarning);
		}else if(prop.getProperty("browserName").equals("firefox")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(),"Please enter an email address.");
		}

	}

	@Test(priority = 17, dataProvider = "passwordSupplier")
	public void verifyRegisteringAccountAndCheckingPasswordComplexityStandards(HashMap<String,String> hMap) {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.enterPassword(hMap.get("Passwords"));
		registerPage.enterConfirmPassword(hMap.get("Passwords"));
		registerPage.clickOnContinueButton();

		String warningMessage = "Password entered is not matching the Complexity standards";

		boolean state = false;

		try {
			String acutalWarningMessage = registerPage.getPasswordWarning();
			if (acutalWarningMessage.equals(warningMessage)) {
				state = true;
			}
		} catch (NoSuchElementException e) {
			state = false;
		}

		Assert.assertTrue(state);
		Assert.assertFalse(registerPage.didWeNavigateToRegisterAccountPage());

	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] supplyPasswords() {
		myXLSReader = new MyXLSReader(System.getProperty("user.dir")+"\\src\\test\\resources\\TutorialsNinja.xlsx");
		Object[][] data = CommonUtils.getTestData(myXLSReader,"RegsiterTestSupplyPasswords","data");
		return data;
	}

	@Test(priority = 18)
	public void verifyRegisteringAccountFieldsHeightWidthAligment() throws IOException {

		String browserName = prop.getProperty("browserName");

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";

		String actualFirstNameFieldHeight = registerPage.getFirstNameFieldHeight();
		String expectedFirstNameFieldWidth = registerPage.getFirstNameFieldWidth();
		Assert.assertEquals(actualFirstNameFieldHeight, expectedHeight);
		Assert.assertEquals(expectedFirstNameFieldWidth, expectedWidth);

		registerPage.enterFirstName("");
		registerPage.clickOnContinueButton();
		String expectedWarning = "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("a");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("ab");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdefghijklmnopq");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnop");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnopq");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedWarning);

		// ---------------------

		registerPage = new RegisterPage(driver);
		String actualLastNameFieldHeight = registerPage.getLastNameFieldHeight();
		String actualLastNameFieldWidth = registerPage.getLastNameFieldWidth();
		Assert.assertEquals(actualLastNameFieldHeight, expectedHeight);
		Assert.assertEquals(actualLastNameFieldWidth, expectedWidth);
		expectedWarning = "Last Name must be between 1 and 32 characters!";
		registerPage.clearLastNameField();
		registerPage.enterLastName("");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getLastNameWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearLastNameField();
		registerPage.enterLastName("a");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isLastNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearLastNameField();
		registerPage.enterLastName("ab");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isLastNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearLastNameField();
		registerPage.enterLastName("abcdefghijklmnopq");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isLastNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearLastNameField();
		registerPage.enterLastName("abcdefghijklmnopabcdefghijklmnop");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isLastNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearLastNameField();
		registerPage.enterLastName("abcdefghijklmnopabcdefghijklmnopq");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getLastNameWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		String actualEmailFieldHeight = registerPage.getEmailFieldHeight();
		String actualEmailFieldWidth = registerPage.getEmailFieldWidth();
		Assert.assertEquals(actualEmailFieldHeight, expectedHeight);
		Assert.assertEquals(actualEmailFieldWidth, expectedWidth);

		registerPage = new RegisterPage(driver);
		registerPage.clearEmailField();
		registerPage.enterEmail("abcdefghijklmnopabcdefghijklmnopqabcdefghijklmnopabcdefghijklmno@gmail.com");
		registerPage.clickOnContinueButton();

		Assert.assertFalse(registerPage.isEmailWarningDisplayed());

		// ----------------------------------------

		registerPage = new RegisterPage(driver);
		String actualTelephoneFieldHeight = registerPage.getTelephoneFieldHeight();
		String actualTelephoneFieldWidth = registerPage.getTelephoneFieldWidth();
		Assert.assertEquals(actualTelephoneFieldHeight, expectedHeight);
		Assert.assertEquals(actualTelephoneFieldWidth, expectedWidth);
		expectedWarning = "Telephone must be between 3 and 32 characters!";
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("a");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("ab");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("abc");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("abcd");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("abcdefghijklmnop");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("abcdefghijklmnopabcdefghijklmnop");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearTelephoneField();
		registerPage.enterTelephoneNumber("abcdefghijklmnopabcdefghijklmnopq");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

		// -----------------------

		registerPage = new RegisterPage(driver);
		String actualPasswordFieldHeight = registerPage.getPasswordFieldHeight();
		String actualPasswordFieldWidth = registerPage.getPasswordFieldWidth();
		Assert.assertEquals(actualPasswordFieldHeight, expectedHeight);
		Assert.assertEquals(actualPasswordFieldWidth, expectedWidth);
		expectedWarning = "Password must be between 4 and 20 characters!";
		registerPage.clearPasswordField();
		registerPage.enterPassword("");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("a");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("ab");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("abc");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("abcd");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isPasswordWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("abcde");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isPasswordWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("abcdefghij");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isPasswordWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("abcdefghijabcdefghi");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isPasswordWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("abcdefghijabcdefghij");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isPasswordWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearPasswordField();
		registerPage.enterPassword("abcdefghijabcdefghijk");
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.isPasswordWarningDisplayedAndMatch(expectedWarning));

		registerPage = new RegisterPage(driver);
		String actualConfirmPasswordFieldHeight = registerPage.getPasswordConfirmFieldHeight();
		String actualConfirmPasswordFieldWidth = registerPage.getPasswordConfirmFieldWidth();
		Assert.assertEquals(actualConfirmPasswordFieldHeight, expectedHeight);
		Assert.assertEquals(actualConfirmPasswordFieldWidth, expectedWidth);
		driver = navigateToRegisterPage(driver, prop.getProperty("registerPageURL"));
		driver = CommonUtils.takeScreenshot(driver, "\\Screenshots\\registerPageActualAligment.png");
		if (browserName.equals("chrome")) {
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\registerPageActualAligment.png",
					System.getProperty("user.dir") + "\\Screenshots\\registerPageChromeExpectedAligment.png"));
		} else if (browserName.equals("firefox")) {
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\registerPageActualAligment.png",
					System.getProperty("user.dir") + "\\Screenshots\\registerPageFirefoxExpectedAligment.png"));
		} else if (browserName.equals("edge")) {
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\registerPageActualAligment.png",
					System.getProperty("user.dir") + "\\Screenshots\\registerPageEdgeExpectedAligment.png"));
		}
	}

	@Test(priority = 19)
	public void verifyLeadingAndTrailingSpacesWhileRegisteringAccount() {

		SoftAssert softAssert = new SoftAssert();

		String enteredFirstName = "        " + prop.getProperty("firstName") + "        ";
		registerPage.enterFirstName(enteredFirstName);
		String enteredLastName = "       " + prop.getProperty("lastName") + "       ";
		registerPage.enterLastName(enteredLastName);
		String enteredEmail = "       " + CommonUtils.generateBrandNewEmail() + "       ";
		registerPage.enterEmail(enteredEmail);
		String enteredTelphone = "       " + prop.getProperty("telephoneNumber") + "       ";
		registerPage.enterTelephoneNumber(enteredTelphone);
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
	
		if(prop.getProperty("browserName").equals("chrome") || prop.getProperty("browserName").equals("edge")) {
			accountPage = accountSuccessPage.clickOnContinueButton();
			editAccountInformationPage = accountPage.clickOnEditYourAccountInformationOption();
			softAssert.assertEquals(editAccountInformationPage.getFirstNameFieldValue(), enteredFirstName.trim());
			softAssert.assertEquals(editAccountInformationPage.getLastNameFieldValue(), enteredLastName.trim());
			softAssert.assertEquals(editAccountInformationPage.getEmailFieldValue(), enteredEmail.trim());
			softAssert.assertEquals(editAccountInformationPage.getTelephoneFieldValue(), enteredTelphone.trim());
			softAssert.assertAll();
		}else if(prop.getProperty("browserName").equals("firefox")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(), "Please enter an email address.");
		}
	}

	@Test(priority = 20)
	public void verifyPrivacyPolicyFieldOnRegisterAccountPage() {

		Assert.assertFalse(registerPage.isPrivacyPolicySelected());

	}

	@Test(priority = 21)
	public void verifyRegisteringAccountWithoutPrivacyPolicySelection() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickOnContinueButton();

		String expectedWarningMessage = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(registerPage.getPrivacyPolicyWarning(), expectedWarningMessage);

	}

	@Test(priority = 22)
	public void verifyVisibiltyTogglineOfPasswordsFieldsOnRegisterAccount() {

		Assert.assertEquals(registerPage.getPasswordFieldType(), "password");
		Assert.assertEquals(registerPage.getPasswordConfirmFieldType(), "password");

	}

	@Test(priority = 23)
	public void verifyWorkingOfEveryLinkOnRegisterAccountPage() {

		driver = registerPage.getDriver();
		headerOptions = new HeaderOptions(driver);
		contactUsPage = headerOptions.selectPhoneIconOption();
		Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());
		loginPage = headerOptions.selectHeartIconOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());
		shoppingCartPage = headerOptions.selectShoppingCartOption();
		Assert.assertTrue(shoppingCartPage.didWeNaviateToShoppingCartPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());
		shoppingCartPage = headerOptions.selectCheckoutOption();
		Assert.assertTrue(shoppingCartPage.didWeNaviateToShoppingCartPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());
		headerOptions.selectLogoOption();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		registerPage = registerPage.clickOnRegisterBreadcrumb();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());

		registerPage = new RegisterPage(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());
		loginPage = headerOptions.clickOnAccountBreadcrumb();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		headerOptions = new HeaderOptions(registerPage.getDriver());
		landingPage = headerOptions.clickOnHomeBreadcrumb();
		Assert.assertEquals(getPageURL(driver), prop.getProperty("landingPageURL"));
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		loginPage = registerPage.clickOnLoginPageLink();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		registerPage.clickOnPrivacyPolicyOption();
		registerPage.waitAndCheckDisplayStatusOfClosePrivacyPolicyOption(driver, 10);
		registerPage.closePrivacyPolicyDialog();

		registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		loginPage = rightColumnOptions.clickOnRightSideLoginOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		registerPage = rightColumnOptions.clickOnRightSideRegisterOption();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		forgottenPasswordPage = rightColumnOptions.clickOnRightSideForgottenPasswordOption();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottendPasswordPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		loginPage = rightColumnOptions.clickOnRightSideMyAccountOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideAddressBookOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideWishListOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideOrderHistoryOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideDownloadsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideRecurringPaymentsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideRewardPointsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideReturnsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideTransactionsOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
		rightColumnOptions.clickOnRightSideNewsletterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		aboutUsPage = footerOptions.clickOnAboutUsFooterOption();
		Assert.assertTrue(aboutUsPage.didWeNavigateToAboutUsBreadcrumb());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		deliveryInformationPage = footerOptions.clickOnDeliveryInformationFooterOption();
		Assert.assertTrue(deliveryInformationPage.didWeNavigateToDeliveryInformationPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		privacyPolicyPage = footerOptions.clickOnPrivacyPolicyFooterOption();
		Assert.assertTrue(privacyPolicyPage.didWeNavigateToPrivacyPolicyPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		termsAndConditionsPage = footerOptions.clickOnTermsAndConditionsFooterOption();
		Assert.assertTrue(termsAndConditionsPage.didWeNavigateToTermsAndConditionsPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		contactUsPage = footerOptions.clickOnContactUsFooterOption();
		Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		productReturnsPage = footerOptions.clickOnReturnsFooterOption();
		Assert.assertTrue(productReturnsPage.didWeNavigateToProductReturnsPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		siteMapPage = footerOptions.clickOnSiteMapFooterOption();
		Assert.assertTrue(siteMapPage.didWeNavigateToSiteMapPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		brandPage = footerOptions.clickOnBrandsFooterOption();
		Assert.assertTrue(brandPage.didWeNavigateToBrandsPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		giftCertificatePage = footerOptions.clickOnGiftCertificateFooterOption();
		Assert.assertTrue(giftCertificatePage.didWeNavigateToGiftCertificatePage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		affiliateLoginPage = footerOptions.clickOnAffiliateFooterOption();
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("affiliateLoginPageURL"));
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		specialOffersPage = footerOptions.clickOnSpecialsFooterOption();
		Assert.assertTrue(specialOffersPage.didWeNavigateToSpecialOffersPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		loginPage = footerOptions.clickOnMyAccountFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		loginPage = footerOptions.clickOnOrderHistoryFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		loginPage = footerOptions.clickOnWishListFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

		registerPage = new RegisterPage(driver);
		footerOptions = new FooterOptions(registerPage.getDriver());
		loginPage = footerOptions.clickOnNewsletterFooterOption();
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		driver = navigateBack(driver);

	}

	@Test(priority = 24)
	public void verifyRegisteringAccountWithoutEnteringPasswordIntoPasswordConfirmField() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String expectedWarning = "Password confirmation does not match password!";
		Assert.assertEquals(registerPage.getPasswordConfirmWarning(), expectedWarning);

	}

	@Test(priority = 25)
	public void verifyBreadcrumbURLHeadingTitleOfRegisterAccountPage() {

		Assert.assertTrue(registerPage.didWeNavigateToRegisterAccountPage());
		Assert.assertEquals(registerPage.getRegisterPageHeading(), prop.getProperty("registerPageHeading"));
		Assert.assertEquals(getPageURL(driver), prop.getProperty("registerPageURL"));
		Assert.assertEquals(getPageTitle(driver), prop.getProperty("registerPageTitle"));

	}

	@Test(priority = 26)
	public void verifyUIOfRegisterAccountPage() {

		// https://drive.google.com/file/d/1X6EPJW-Ojl3Xpv99qrnOV4wU8FuekmtO/view
        
		if(prop.getProperty("browserName").equals("chrome")) {
			CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualRegisterPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png"));
		}else if(prop.getProperty("browserName").equals("edge")) {
			CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualEdgeRegisterPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeRegisterPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeRegisterPageUI.png"));
		}else if(prop.getProperty("browserName").equals("firefox")) {
			CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualFirefoxRegisterPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxRegisterPageUI.png",
					System.getProperty("user.dir") + "\\\\Screenshots\\\\expectedFirefoxRegisterPageUI.png"));
		}
		

	}

	@Test(priority = 27)
	public void verifyRegisteringAccountInDifferentTestEnvironments() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
		registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
		accountSuccessPage.clickOnContinueButton();
		Assert.assertEquals(driver.getTitle(), prop.getProperty("accountPageTitle"));

	}

}
