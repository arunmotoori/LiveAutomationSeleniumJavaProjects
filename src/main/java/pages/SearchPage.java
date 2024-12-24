package pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import pages.root.RootPage;

public class SearchPage extends RootPage {

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement searchBreadcrumb;

	@FindBy(linkText = "HP LP3065")
	private WebElement existingProduct;

	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMessage;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> numberOfProducts;

	@FindBy(id = "input-search")
	private WebElement searchCriteriaField;

	public String getPlaceHolderTextOfSearchCriteriaField() {
		return getDomAttributeOfElement(searchCriteriaField,"placeholder");
	}

	public int getNumberOfProductsDisplayedInSearchResults() {
		return getElementCount(numberOfProducts);
	}

	public String getNoProductMessage() {
		return getTextOfElement(noProductMessage);
	}

	public boolean didWeNavigateToSearchPage() {
		return isElementDisplayed(searchBreadcrumb);
	}

	public boolean isExistingProductDisplayedInSearchResults() {
		return isElementDisplayed(existingProduct);
	}
}
