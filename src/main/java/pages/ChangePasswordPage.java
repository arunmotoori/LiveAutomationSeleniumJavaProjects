package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class ChangePasswordPage extends RootPage{

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String passwordText) {
		passwordConfirmField.sendKeys(passwordText);
	}
	
	public AccountPage clickOnContinueButton() {
		continueButton.click();
		return new AccountPage(driver);
	}
	
	

}
