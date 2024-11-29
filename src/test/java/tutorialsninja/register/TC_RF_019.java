package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RF_019 {
	
	WebDriver driver;
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@Test
	public void  verifyLeadingAndTrailingSpacesWhileRegisteringAccount() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		String enteredFirstName = "        Arun     ";
		driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
		String enteredLastName = "       Motoori    ";
		driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
		String enteredEmail = "      "+CommonUtils.generateBrandNewEmail()+"       ";
		driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
		String enteredTelphone = "   1234567890   ";
		driver.findElement(By.id("input-telephone")).sendKeys(enteredTelphone);
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary'][text()='Continue']")).click();
		driver.findElement(By.linkText("Edit your account information")).click();
		
		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("value"),enteredFirstName.trim());
		Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("value"), enteredLastName.trim());
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("value"), enteredEmail.trim());
		Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("value"), enteredTelphone.trim());
		
	}

}
