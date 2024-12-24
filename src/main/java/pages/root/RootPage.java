package pages.root;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RootPage {
	
	public WebDriver driver;
	
	public RootPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public String getDomAttributeOfElement(WebElement element,String attribute) {
		String text = "";
		try {
			text = element.getDomAttribute(attribute);
		}catch(NoSuchElementException e){
			text = "";
		}catch(Exception e) {
			text = "";
		}
		return text;
	}
	
	public String getDomPropertyOfElement(WebElement element,String attribute) {
		String text = "";
		try {
			text = element.getDomProperty(attribute);
		}catch(NoSuchElementException e){
			text = "";
		}catch(Exception e) {
			text = "";
		}
		return text;
	}
	
	public int getElementCount(List<WebElement> elements) {
		int n = 0;
		try {
			n = elements.size();
		}catch(NoSuchElementException e) {
			n = 0;
		}catch(Exception e) {
			n = 0;
		}
		return n;
	}
	
	public String getTextOfElement(WebElement element) {
		String text = "";
		try {
			text = element.getText();
		}catch(NoSuchElementException e) {
			text = "";
		}catch(Exception e) {
			text = "";
		}
		return text;
	}
	
	public boolean isElementDisplayed(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		}catch(NoSuchElementException e) {
			b = false;
		}catch(Exception e) {
			b = false;
		}
		return b;
	}
	
	public boolean isElementDisplayedAfterWaiting(By by,int seconds) {
		boolean b = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			b = element.isDisplayed();
		}catch(NoSuchElementException e) {
			b = false;
		}catch(Exception e) {
			b = false;
		}
		return b;
	}
	
	public boolean isElementSelected(WebElement element) {
		boolean b = false;
		try {
			b = element.isSelected();
		}catch(NoSuchElementException e) {
			b = false;
		}catch(Exception e) {
			b = false;
		}
		return b;
	}
	
	public String getCSSPropertyOfPuseudoElement(WebElement element,String property) {
		String text = "";
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			text = (String) jse.executeScript(
					"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('"+property+"');",
					element);
		} catch (NoSuchElementException e) {
			text = "";
		}catch(Exception e) {
			text = "";
		}
		return text;
	}
	
	public String getCSSPropertyOfElement(WebElement element,String property) {
		String text = "";
		try {
			text = element.getCssValue(property);
		} catch (NoSuchElementException e) {
			text = "";
		}catch(Exception e) {
			text = "";
		}
		return text;
	}
	
	public boolean isTextMatching(String expectedText,String actualText) {
		boolean b = false;
		if (actualText.equals(expectedText)) {
			b = true;
		}else {
			b = false;
		}
		return b;
	}

}
