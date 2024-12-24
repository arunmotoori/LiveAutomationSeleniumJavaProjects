package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import pages.root.RootPage;

public class AffiliateLoginPage extends RootPage{

	public AffiliateLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

}
