package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPasswordPage {
	
	WebDriver driver;
	
	public ForgottenPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Forgotten Password']")
	private WebElement forgottenPasswordBreacrumb;
	
	public boolean didWeNavigateToForgottendPasswordPage() {
		return forgottenPasswordBreacrumb.isDisplayed();
	}

}
