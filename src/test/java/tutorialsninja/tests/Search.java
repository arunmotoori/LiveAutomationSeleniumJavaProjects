package tutorialsninja.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.FooterOptions;
import pages.HeaderOptions;
import pages.LandingPage;
import pages.SearchPage;
import tutorialsninja.base.Base;
import utils.CommonUtils;

public class Search extends Base {

	public WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void setup() {

		driver = openBrowserAndApplication();
		prop = CommonUtils.loadProperties();
		landingPage = new LandingPage(driver);

	}

	@AfterMethod
	public void tearDown() {

		closeBrowser(driver);

	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("existingProduct"));
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());

	}

	@Test(priority = 2)
	public void verifySearchWithNonExistingProduct() {

		landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("nonExistingProduct"));
		searchPage = landingPage.clickOnSearchButton();
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getNoProductMessage(), expectedMessage);

	}

	@Test(priority = 3)
	public void verifySearchWithoutEnteringAnyProduct() {

		searchPage = landingPage.clickOnSearchButton();
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getNoProductMessage(), expectedMessage);

	}

	@Test(priority = 4)
	public void verifySearchAfterLogin() {

		loginPage = landingPage.navigateToLoginPage();
		accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = new HeaderOptions(accountPage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());

	}

	@Test(priority = 5)
	public void verifySearchResultingInMultipleProducts() {

		landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("searchTermResultMultipleProducts"));
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);

	}

	@Test(priority = 6)
	public void verifySearchFieldsPlaceholders() {

		headerOptions = new HeaderOptions(driver);
		String exptedSearchBoxPlaceHolderText = "Search";
		Assert.assertEquals(headerOptions.getPlaceHolderTextOfSearchBoxField(), exptedSearchBoxPlaceHolderText);
		searchPage = headerOptions.clickOnSearchButton();
		String exptdSearchCriteriaFieldPlaceHolderText = "Keywords";
		Assert.assertEquals(searchPage.getPlaceHolderTextOfSearchCriteriaField(),
				exptdSearchCriteriaFieldPlaceHolderText);

	}

	@Test(priority = 7)
	public void verifySearchingForProductUsingSearchCriteriaField() {

		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("existingProduct"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());

	}

	@Test(priority = 8)
	public void verifySearchingForProductUsingSomeTextInProductDescription() {

		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("termInProductDescription"));
		searchPage.selectSearchInProductDescriptionCheckboxBoxField();
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductHavingDescriptionTextDisplayedInSearchResults());

	}

	@Test(priority = 9)
	public void verifySearchBySelectingSubCategory() {

		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("exitingProuductInSubCategory"));
		searchPage.selectOptionFromCategoryDropdownFieldUsingIndex(3);
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());

	}

	@Test(priority = 10)
	public void verifySearchByUsingParentCategoryAndSearchInSubCategoriesOption() {

		headerOptions = new HeaderOptions(driver);
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.enterIntoSearchCriteriaField(prop.getProperty("exitingProuductInSubCategory"));
		searchPage.selectOptionFromCategoryDropdownFieldUsingIndex(1);
		searchPage.clickOnSearchButton();
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getNoProductMessage(), expectedMessage);
		searchPage.selectSearchInSubCategoriesCheckboxField();
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());

	}

	@Test(priority = 11)
	public void verifyProductInSearchResultsInListViewAndGridView() {

		headerOptions = new HeaderOptions(driver);
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exitingProuductInSubCategory"));
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.selectListViewOption();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
		searchPage.selectAddToCartOption();
		String expectedMessage = "Success: You have added iMac to your shopping cart!";
		Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
		searchPage.selectAddToWishListOption();
		expectedMessage = "You must login or create an account to save iMac to your wish list!";
		Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
		searchPage.selectCompareThisProductOption();
		expectedMessage = "Success: You have added iMac to your product comparison!";
		Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
		productDisplayPage = searchPage.selectProductImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		driver = navigateBack(driver);
		productDisplayPage = searchPage.selectProductUsingName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		driver = navigateBack(driver);
		headerOptions = new HeaderOptions(driver);
		landingPage = headerOptions.clickOnHomeBreadcrumb();
		headerOptions = new HeaderOptions(landingPage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("exitingProuductInSubCategory"));
		searchPage = headerOptions.clickOnSearchButton();
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
		searchPage.selectAddToCartOption();
		expectedMessage = "Success: You have added iMac to your shopping cart!";
		Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
		searchPage.selectAddToWishListOption();
		expectedMessage = "You must login or create an account to save iMac to your wish list!";
		Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
		searchPage.selectCompareThisProductOption();
		expectedMessage = "Success: You have added iMac to your product comparison!";
		Assert.assertEquals(searchPage.getSuccessMessage(), expectedMessage);
		productDisplayPage = searchPage.selectProductImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		driver = navigateBack(driver);
		productDisplayPage = searchPage.selectProductUsingName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		driver = navigateBack(driver);

	}

	@Test(priority = 12)
	public void verifyMultipleProductsInSearchResultsInListViewAndGridView() {

		headerOptions = new HeaderOptions(driver);
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);
		searchPage.selectListViewOption();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);

	}

	@Test(priority = 13)
	public void verifyNavigationToProductComparePage() {

		headerOptions = new HeaderOptions(driver);
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		productComparisonPage = searchPage.selectProductCompareLink();
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());

	}

	@Test(priority = 14)
	public void verifySortingProductsInSearchResultsPage() {

		headerOptions = new HeaderOptions(driver);
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() > 1);
		searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionOne"));
		Assert.assertEquals(searchPage.getFirstProductName(), "iMac");
		Assert.assertEquals(searchPage.getSecondProductName(), "MacBook");
		Assert.assertEquals(searchPage.getThirdProductName(), "MacBook Air");
		Assert.assertEquals(searchPage.getFourthProductName(), "MacBook Pro");
		searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionTwo"));
		Assert.assertEquals(searchPage.getFirstProductName(), "MacBook Pro");
		Assert.assertEquals(searchPage.getSecondProductName(), "MacBook Air");
		Assert.assertEquals(searchPage.getThirdProductName(), "MacBook");
		Assert.assertEquals(searchPage.getFourthProductName(), "iMac");
		searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionThree"));
		Assert.assertEquals(searchPage.getFirstProductName(), "iMac");
		Assert.assertEquals(searchPage.getSecondProductName(), "MacBook");
		Assert.assertEquals(searchPage.getThirdProductName(), "MacBook Air");
		Assert.assertEquals(searchPage.getFourthProductName(), "MacBook Pro");
		searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionFour"));
		Assert.assertEquals(searchPage.getFirstProductName(), "MacBook Pro");
		Assert.assertEquals(searchPage.getSecondProductName(), "MacBook Air");
		Assert.assertEquals(searchPage.getThirdProductName(), "MacBook");
		Assert.assertEquals(searchPage.getFourthProductName(), "iMac");

	}

	@Test(priority = 15)
	public void verifyNavigatingToSearchPageFromSiteMapPage() {

		footerOptions = new FooterOptions(landingPage.getDriver());
		siteMapPage = footerOptions.clickOnSiteMapFooterOption();
		searchPage = siteMapPage.clickOnSearchLink();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());

	}

	@Test(priority = 16)
	public void verifySearchPageBreadcrumb() {

		headerOptions = new HeaderOptions(landingPage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
		searchPage = headerOptions.clickOnSearchButton();
		searchPage = searchPage.selectSearchBreadcrumbOption();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		headerOptions = new HeaderOptions(searchPage.getDriver());
		landingPage = headerOptions.clickOnHomeBreadcrumb();
		Assert.assertEquals(getPageURL(landingPage.getDriver()), prop.getProperty("landingPageURL"));

	}

	@Test(priority = 17)
	public void verifySearchFunctionalityUsageByUsingKeyboardKeys() throws InterruptedException {

		searchPage = landingPage.searchForAProductUsingKeyboardKeys(prop.getProperty("exitingProuductInSubCategory"));
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
		searchPage = searchPage.searchUsingSearchCriteriaFieldInSearchResultsPageUsingKeyboardKeys(
				prop.getProperty("existingSampleTermResultingInMultipleProducts"));
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 4);
		searchPage = searchPage.verifySearchingByCategoryUsingKeyboardKeys();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 2);
		searchPage = searchPage.verifySearchingInSubcategoriesUsingKeyboardKeys();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 3);
		searchPage = searchPage
				.verifySearchingUsingDescriptionUsingKeyboardKeys(prop.getProperty("termInProductDescription"));
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
		searchPage = searchPage.verifySearchingInListViewUsingKeyboardKeys();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
		searchPage = searchPage.verifySearchingInGridViewUsingKeyboardKeys();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
		productComparisonPage = searchPage.verifyNavigatingToProductComparedPageUsingKeyboardKeys();
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
		driver = navigateBack(productComparisonPage.getDriver());
		searchPage = new SearchPage(driver);
		searchPage = searchPage.verifySortInSearchPageUsingKeyboardKeys();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);
		searchPage = searchPage.verifyProductsCountInSearchPageUsingKeyboardKeys();
		Assert.assertTrue(searchPage.getNumberOfProductsDisplayedInSearchResults() == 1);

	}

	@Test(priority = 18)
	public void verifySearchFunctionalityHeadingURLAndTitle() {

		searchPage = landingPage.clickOnSearchButton();
		Assert.assertEquals(searchPage.getPageHeading(), prop.getProperty("searchPageHeading"));
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), prop.getProperty("searchPageHeading"));
		Assert.assertEquals(getPageURL(searchPage.getDriver()), prop.getProperty("searchPageURL"));

	}

	@Test(priority=19)
	public void verifySearchFunctionalityUI() {
		
		searchPage = landingPage.clickOnSearchButton();
		if(prop.getProperty("browserName").equals("chrome")) {
			CommonUtils.takeScreenshot(searchPage.getDriver(),"\\Screenshots\\actualChromeSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualChromeSearchPageUI.png","\\Screenshots\\expectedChromeSearchPageUI.png"));
		}else if(prop.getProperty("browserName").equals("firefox")) {
			CommonUtils.takeScreenshot(searchPage.getDriver(),"\\Screenshots\\actualFirefoxSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualFirefoxSearchPageUI.png","\\Screenshots\\expectedFirefoxSearchPageUI.png"));
		}else if(prop.getProperty("browserName").equals("edge")) {
			CommonUtils.takeScreenshot(searchPage.getDriver(),"\\Screenshots\\actualEdgeSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots("\\Screenshots\\actualEdgeSearchPageUI.png","\\Screenshots\\expectedEdgeSearchPageUI.png"));
		}
	}
	
	@Test(priority=20)
	public void verifySearchInAllEnviornments() {
		
		landingPage.enterProductNameIntoIntoSearchBoxFiled(prop.getProperty("existingProduct"));
		searchPage = landingPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
		Assert.assertTrue(searchPage.isExistingProductDisplayedInSearchResults());
		
	}


}


