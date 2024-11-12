package tutorialsninja.register;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RF_018 {
	
	@Test
	public void verifyRegisteringAccountFieldsHeightWidthAligment() throws IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";
		
		WebElement firstNameField = driver.findElement(By.id("input-firstname"));
		String actualFirstNameFieldHeight = firstNameField.getCssValue("height");
		String expectedFirstNameFieldWidth = firstNameField.getCssValue("width");
		
		Assert.assertEquals(actualFirstNameFieldHeight, expectedHeight);
		Assert.assertEquals(expectedFirstNameFieldWidth, expectedWidth);
		
		firstNameField.sendKeys("");
		WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		continueButton.click();
		String expectedWarning = "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), expectedWarning);
		
		firstNameField = driver.findElement(By.id("input-firstname"));
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		firstNameField.clear();
		firstNameField.sendKeys("a");
		continueButton.click();
		
		try {
		  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).isDisplayed());
		}catch(NoSuchElementException e) {
		  Assert.assertTrue(true);
		}

		firstNameField = driver.findElement(By.id("input-firstname"));
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		firstNameField.clear();
		firstNameField.sendKeys("ab");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		firstNameField = driver.findElement(By.id("input-firstname"));
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		firstNameField.clear();
		firstNameField.sendKeys("abcdefghijklmnopq");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		firstNameField = driver.findElement(By.id("input-firstname"));
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		firstNameField.clear();
		firstNameField.sendKeys("abcdefghijklmnopabcdefghijklmnop");
		continueButton.click();
		
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		firstNameField = driver.findElement(By.id("input-firstname"));
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		firstNameField.clear();
		firstNameField.sendKeys("abcdefghijklmnopabcdefghijklmnopq");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), expectedWarning);
		
		//---------------------
		
		WebElement lastNameField = driver.findElement(By.id("input-lastname"));
		String actualLastNameFieldHeight = lastNameField.getCssValue("height");
		String actualLastNameFieldWidth = lastNameField.getCssValue("width");
		
		Assert.assertEquals(actualLastNameFieldHeight, expectedHeight);
		Assert.assertEquals(actualLastNameFieldWidth, expectedWidth);
		
		expectedWarning = "Last Name must be between 1 and 32 characters!";
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField.clear();
		lastNameField.sendKeys("");
		continueButton.click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), expectedWarning);
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("a");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("ab");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("abcdefghijklmnopq");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("abcdefghijklmnopabcdefghijklmnop");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("abcdefghijklmnopabcdefghijklmnopq");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), expectedWarning);
		
		WebElement emailField = driver.findElement(By.id("input-email"));
		
		String actualEmailFieldHeight = emailField.getCssValue("height");
		String actualEmailFieldWidth = emailField.getCssValue("width");
		
		Assert.assertEquals(actualEmailFieldHeight, expectedHeight);
		Assert.assertEquals(actualEmailFieldWidth, expectedWidth);
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		emailField.clear();
		emailField.sendKeys("abcdefghijklmnopabcdefghijklmnopqabcdefghijklmnopabcdefghijklmno@gmail.com");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		//----------------------------------------
		
		WebElement telephoneField = driver.findElement(By.id("input-telephone"));
		String actualTelephoneFieldHeight = telephoneField.getCssValue("height");
		String actualTelephoneFieldWidth = telephoneField.getCssValue("width");
		
		Assert.assertEquals(actualTelephoneFieldHeight, expectedHeight);
		Assert.assertEquals(actualTelephoneFieldWidth, expectedWidth);
		
		expectedWarning = "Telephone must be between 3 and 32 characters!";
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField.clear();
		telephoneField.sendKeys("");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarning);
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("a");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("ab");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarning);
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abc");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcd");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcdefghijklmnop");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcdefghijklmnopabcdefghijklmnop");
		continueButton.click();
		try {
		  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).isDisplayed());
		}catch(NoSuchElementException e) {
			Assert.assertTrue(true);
		}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcdefghijklmnopabcdefghijklmnopq");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarning);
		
		//-----------------------
		WebElement passwordField = driver.findElement(By.id("input-password"));
		String actualPasswordFieldHeight = passwordField.getCssValue("height");
		String actualPasswordFieldWidth = passwordField.getCssValue("width");
		
		Assert.assertEquals(actualPasswordFieldHeight, expectedHeight);
		Assert.assertEquals(actualPasswordFieldWidth, expectedWidth);
		
		expectedWarning = "Password must be between 4 and 20 characters!";
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField.clear();
		passwordField.sendKeys("");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedWarning);
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("a");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("ab");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedWarning);
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abc");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedWarning);
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcd");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcde");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghij");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghijabcdefghi");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghijabcdefghij");
		continueButton.click();
		try {
			  Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
			}catch(NoSuchElementException e) {
			  Assert.assertTrue(true);
			}
		
		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghijabcdefghijk");
		continueButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedWarning);
		
		String actualConfirmPasswordFieldHeight = driver.findElement(By.id("input-confirm")).getCssValue("height");
		String actualConfirmPasswordFieldWidth = driver.findElement(By.id("input-confirm")).getCssValue("width");
		
		Assert.assertEquals(actualConfirmPasswordFieldHeight, expectedHeight);
		Assert.assertEquals(actualConfirmPasswordFieldWidth, expectedWidth);
		
		driver.navigate().to("https://tutorialsninja.com/demo/index.php?route=account/register");
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot,new File(System.getProperty("user.dir")+"\\Screenshots\\registerPageActualAligment.png"));
		
		Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\registerPageActualAligment.png", System.getProperty("user.dir")+"\\Screenshots\\registerPageExpectedAligment.png"));
		
		driver.quit();
		
	}
	

}
