package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	public boolean isElementEnabled(WebElement element) {
		boolean b = false;
		try {
			b = element.isEnabled();
		} catch (NoSuchElementException e) {
			b = false;
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	public boolean isElementSelected(WebElement element) {
		boolean b = false;
		try {
			b = element.isSelected();
		} catch (NoSuchElementException e) {
			b = false;
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	public void clickOnElement(WebElement element) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.click();
		}
	}

	public void enterTextIntoElement(WebElement element, String text) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.clear();
			element.sendKeys(text);
		}
	}

	public String getTextOfElement(WebElement element) {
		String text = "";
		try {
			text = element.getText();
		} catch (NoSuchElementException e) {
			text = "";
		} catch (Exception e) {
			text = "";
		}
		return text;
	}

	public String getDomAttributeOfElement(WebElement element, String attribute) {
		String text = "";
		try {
			text = element.getDomAttribute(attribute);
		} catch (NoSuchElementException e) {
			text = "";
		} catch (Exception e) {
			text = "";
		}
		return text;
	}

	public void clearTextFromElement(WebElement element) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			element.clear();
		}
	}

	public String getDomPropertyOfElement(WebElement element, String attribute) {
		String text = "";
		try {
			text = element.getDomProperty(attribute);
		} catch (NoSuchElementException e) {
			text = "";
		} catch (Exception e) {
			text = "";
		}
		return text;
	}

	public void mouseLeftClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.click(element).perform();
	}

	public void pressAndHoldTheKeyboardKey(Keys key) {
		Actions actions = new Actions(driver);
		actions.keyDown(key).perform();
	}

	public void pressKeyboardKey(String keyText) {
		Actions actions = new Actions(driver);
		actions.sendKeys(keyText).perform();
	}

	public void releaseKeyboardKey(Keys key) {
		Actions actions = new Actions(driver);
		actions.keyUp(key).perform();
	}

	public void pastingTextIntoField(WebElement element) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			Actions actions = new Actions(driver);
			actions.click(element).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
		}
	}

	public void copyTextFromElement(WebElement element) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			Actions actions = new Actions(driver);
			actions.doubleClick(element).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
		}
	}

	public boolean isElementDisplayedAfterWaiting(By by, int seconds) {
		boolean b = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			b = element.isDisplayed();
		} catch (NoSuchElementException e) {
			b = false;
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	public String getCSSPropertyOfPuseudoElement(WebElement element, String property) {
		String text = "";
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			text = (String) jse.executeScript(
					"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('" + property + "');",
					element);
		} catch (NoSuchElementException e) {
			text = "";
		} catch (Exception e) {
			text = "";
		}
		return text;
	}

	public String getCSSPropertyOfElement(WebElement element, String property) {
		String text = "";
		try {
			text = element.getCssValue(property);
		} catch (NoSuchElementException e) {
			text = "";
		} catch (Exception e) {
			text = "";
		}
		return text;
	}

	public void selectOptionInDropDownFieldUsingIndex(WebElement element, int indexNumber) {
		if (isElementDisplayed(element) && isElementEnabled(element)) {
			Select select = new Select(element);
			select.selectByIndex(indexNumber);
		}
	}

	public int getElementCount(List<WebElement> elements) {
		int n = 0;
		try {
			n = elements.size();
		} catch (NoSuchElementException e) {
			n = 0;
		} catch (Exception e) {
			n = 0;
		}
		return n;
	}
}
