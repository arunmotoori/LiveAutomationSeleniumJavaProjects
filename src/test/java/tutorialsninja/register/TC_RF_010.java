package tutorialsninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_RF_010 {
	
	@Test
	public void verifyRegisteringAccountUsingInvalidEmail() throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys("amotoori");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Thread.sleep(3000);
		
		File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot1,new File(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png"));
		
		BufferedImage acutualBImg = ImageIO.read(new File(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png"));
		BufferedImage expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+"\\Screenshots\\sc1Expected.png"));
		
		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);
		
		Assert.assertFalse(imgDifference.hasDiff());
		
		driver.quit();
		
	}
	

}
