package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrivacyPolicyPage {
	
	WebDriver driver;
	
	public PrivacyPolicyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Privacy Policy']")
	private WebElement privacyPolicyBreadcrumb;
	
	public boolean didWeNavigateToPrivacyPolicyPage() {
		return privacyPolicyBreadcrumb.isDisplayed();
	}

}
