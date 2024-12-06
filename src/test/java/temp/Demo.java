package temp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://omayo.blogspot.com/");
		
		String s1 = driver.findElement(By.id("textbox1")).getDomAttribute("value");
		System.out.println(s1);
		
		driver.findElement(By.id("textbox1")).clear();
		driver.findElement(By.id("textbox1")).sendKeys("Arun Motoori");
		
		String s2 = driver.findElement(By.id("textbox1")).getDomProperty("value");
		System.out.println(s2);
		
		driver.quit();
	}

}
