package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPagesetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); // v r creating object
																									// in LoginPage and
																									// returning the
		// object and immediately referring here
	}

	@Test
	public void accPageTitleTest() {
		String accTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(accTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageUrlTest() {
		String accUrl = accPage.getAccountsPageUrl();
		System.out.println("Acc page url.." + accUrl);
		Assert.assertTrue(accUrl.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}

	@Test
	public void accPageHeaderTest() {
		String accHeader = accPage.getHeader();
		System.out.println("Header is " + accHeader);
		Assert.assertEquals(accHeader, Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Test
	public void logOutLinkTest() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}

	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}

	@Test
	public void accPageSections() {
		List<String> accSectionsList = accPage.getAccPageSections();
		System.out.println("Actual sections list :" + accSectionsList);
		Assert.assertEquals(accSectionsList, Constants.ACCOUNTS_PAGE_SECTIONS_LIST);

	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "Macbook" }, { "imac" }, { "Apple" } };
	}

	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		Assert.assertTrue(resultsPage.getProductListCount() > 0); // now v r saying this condition is true if search is
																	// >0 i.e,if at least 1 product exists
	}
	
	//here v need to columns->1 is for main search n one for product u wana click->so 2 columns
	@DataProvider
	public Object[][] productselectData() {
		return new Object[][] { { "Macbook","MacBook Pro" },
								{ "Macbook","MacBook Air" },
								{ "iMac" , "iMac"},
								{ "Apple" , "Apple Cinema 30\""} 
								};
	}
	// for clicking on the product which will pfurther takes u to profinfopage

	@Test(dataProvider = "productselectData")
	public void selectProductTest(String productName,String mainProductName) {
		resultsPage = accPage.doSearch(productName); // this gives u results page
		productInfoPage = resultsPage.selectProduct(mainProductName); // this s giving u productInfo page;
		Assert.assertEquals(productInfoPage.getProductHeaderName(), mainProductName);// v r validating with actual header
																					// name with searched productname
	}

}
