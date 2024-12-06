package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RF_027 {
	
	@Test(dataProvider="environmentsSupplier")
	public void verifyRegisteringAccountInDifferentTestEnvironments(String env) {
		
		String browserName = env;
		
		WebDriver driver = null;
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		Assert.assertEquals(driver.getTitle(),"My Account");
		
		driver.quit();
	}
	
	@DataProvider(name="environmentsSupplier")
	public Object[][] passTestEnvironments() {
		
		Object[][] envs = {{"chrome"},{"firefox"},{"edge"}};
		return envs;
		
	}

}
