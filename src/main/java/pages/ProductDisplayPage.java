package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.ElementUtils;

public class ProductDisplayPage extends RootPage {
	
	WebDriver driver;
	ElementUtils elementUtils;
	public ProductDisplayPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(className="thumbnails")
	private WebElement thumbnailsSection;
	
	public boolean didWeNavigateToProductDisplayPage() {
		return elementUtils.isElementDisplayed(thumbnailsSection);
	}

}
