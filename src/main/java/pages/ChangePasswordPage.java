package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;
import utils.ElementUtils;

public class ChangePasswordPage extends RootPage{
	
	ElementUtils elementUtils;

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	public void enterPassword(String passwordText) {
		elementUtils.enterTextIntoElement(passwordField, passwordText);
	}
	
	public void enterConfirmPassword(String passwordText) {
		elementUtils.enterTextIntoElement(passwordConfirmField, passwordText);
	}
	
	public AccountPage clickOnContinueButton() {
		elementUtils.clickOnElement(continueButton);
		return new AccountPage(driver);
	}
	
	

}
