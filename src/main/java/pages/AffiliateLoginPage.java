package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import pages.root.RootPage;
import utils.ElementUtils;

public class AffiliateLoginPage extends RootPage{
	
	ElementUtils elementUtils;

	public AffiliateLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
	}

}
