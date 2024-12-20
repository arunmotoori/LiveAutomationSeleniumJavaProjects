package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class TermsAndConditionsPage extends RootPage{
	
	public TermsAndConditionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Terms & Conditions']")
	private WebElement termsAndConditionsBreadcrumb;
	
	public boolean didWeNavigateToTermsAndConditionsPage() {
		return termsAndConditionsBreadcrumb.isDisplayed();
	}

}
