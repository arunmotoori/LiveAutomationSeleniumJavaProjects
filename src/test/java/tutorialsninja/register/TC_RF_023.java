package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_023 {
	
	@Test
	public void verifyWorkingOfEveryLinkOnRegisterAccountPage() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.xpath("//a/i[@class='fa fa-phone']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Contact Us']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a/i[@class='fa fa-heart']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//span[text()='Shopping Cart']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Shopping Cart']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//span[text()='Checkout']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Shopping Cart']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//div[@id='logo']//a")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Search']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());
		
		driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='breadcrumb']//i[@class='fa fa-home']")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://tutorialsninja.com/demo/index.php?route=common/home");
		driver.navigate().back();
		
		driver.findElement(By.linkText("login page")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='agree']/b[text()='Privacy Policy']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement xOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='×']")));
		Assert.assertTrue(xOption.isDisplayed());
		xOption.click();
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());
		driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).click();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Login']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Forgotten Password']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Forgotten Password']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='My Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Address Book']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Wish List']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Order History']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Downloads']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Recurring payments']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Reward Points']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Returns']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Transactions']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Newsletter']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("About Us")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='About Us']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("Delivery Information")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Delivery Information']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[text()='Privacy Policy']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Privacy Policy']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("Terms & Conditions")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Terms & Conditions']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("Contact Us")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Contact Us']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[text()='Returns']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Product Returns']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("Site Map")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Site Map']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("Brands")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Brand']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("Gift Certificates")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Gift Certificate']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.linkText("Affiliate")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://tutorialsninja.com/demo/index.php?route=affiliate/login");
		driver.navigate().back();
		
		driver.findElement(By.linkText("Specials")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Special Offers']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[text()='My Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[text()='Order History']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[text()='Wish List']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		driver.navigate().back();
		
		driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[text()='Newsletter']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
		
		driver.quit();
	}

}
