package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class NewsletterPage extends RootPage{
	
	public NewsletterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Newsletter']")
	private WebElement newsletterBreadcrumb;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	private WebElement noNewsletterOption;
	
	public boolean didWeNavigateToNewsletterPage() {
		return newsletterBreadcrumb.isDisplayed();
	}
	
	public boolean isYesNewsletterOptionSelected() {
		return yesNewsletterOption.isSelected();
	}
	
	public boolean isNoNewsletterOptionSelected() {
		return noNewsletterOption.isSelected();
	}

}
