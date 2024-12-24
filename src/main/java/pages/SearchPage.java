package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.ElementUtils;

public class SearchPage extends RootPage {
	
	ElementUtils elementUtils;

	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
	private WebElement searchBreadcrumb;

	@FindBy(linkText = "HP LP3065")
	private WebElement existingProduct;

	@FindBy(linkText = "iMac")
	private WebElement iMacProduct;

	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMessage;

	@FindBy(xpath = "//div[@class='product-thumb']")
	private List<WebElement> numberOfProducts;

	@FindBy(id = "input-search")
	private WebElement searchCriteriaField;

	@FindBy(id = "button-search")
	private WebElement searchButton;

	@FindBy(id = "description")
	private WebElement searchInProductDescriptionCheckboxBoxField;
	
	@FindBy(name="category_id")
	private WebElement categoryDropdownField;
	
	public void selectOptionFromCategoryDropdownField(int indexNumber) {
		elementUtils.selectOptionInDropDownFieldUsingIndex(categoryDropdownField, indexNumber);
	}

	public void selectSearchInProductDescriptionCheckboxBoxField() {
		elementUtils.clickOnElement(searchInProductDescriptionCheckboxBoxField);
	}

	public void clickOnSearchButton() {
		elementUtils.clickOnElement(searchButton);
	}

	public void enterIntoSearchCriteriaField(String text) {
		elementUtils.enterTextIntoElement(searchCriteriaField, text);
	}

	public String getPlaceHolderTextOfSearchCriteriaField() {
		return elementUtils.getDomAttributeOfElement(searchCriteriaField, "placeholder");
	}

	public int getNumberOfProductsDisplayedInSearchResults() {
		return elementUtils.getElementCount(numberOfProducts);
	}

	public String getNoProductMessage() {
		return elementUtils.getTextOfElement(noProductMessage);
	}

	public boolean didWeNavigateToSearchPage() {
		return elementUtils.isElementDisplayed(searchBreadcrumb);
	}

	public boolean isExistingProductDisplayedInSearchResults() {
		return elementUtils.isElementDisplayed(existingProduct);
	}

	public boolean isProductHavingDescriptionTextDisplayedInSearchResults() {
		return elementUtils.isElementDisplayed(iMacProduct);
	}
	
	public boolean isProductInCategoryDisplayedInSearchResults() {
		return elementUtils.isElementDisplayed(iMacProduct);
	}
}
