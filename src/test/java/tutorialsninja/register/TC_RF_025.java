package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_025 {
	
	@Test
	public void verifyBreadcrumbURLHeadingTitleOfRegisterAccountPage() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());
		
		String expectedHeading = "Register Account";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']/h1")).getText(), expectedHeading);
		
		String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/register";
		Assert.assertEquals(driver.getCurrentUrl(),expectedURL);
		
		String expectedTitle = "Register Account";
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		
		driver.quit();
		
	}

}
