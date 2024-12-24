package pages.root;
import org.openqa.selenium.WebDriver;


public class RootPage {
	
	public WebDriver driver;
	
	public RootPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
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
