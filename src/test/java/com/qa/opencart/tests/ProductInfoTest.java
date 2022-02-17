package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		resultsPage = accPage.doSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeaderName(), "MacBook Pro");
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "Macbook", "MacBook Pro", Constants.MACBOOKPRO_IMAGES_COUNT },
				{ "Macbook", "MacBook Air", Constants.MACBOOK_IMAGES_COUNT },
				{ "iMac", "iMac", Constants.IMAC_IMAGES_COUNT }, };
	}

	@Test(dataProvider = "productData")
	public void productImagesCountTest(String productName, String mainProductName, int imagesCount) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		int totalImages = productInfoPage.getProductImagesCount();
		System.out.println("Total images for " + mainProductName + ":" + totalImages);
		Assert.assertEquals(imagesCount, imagesCount);
	}

	@Test
	public void productDataTest() {
		resultsPage = accPage.doSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfoMap = productInfoPage.getProductInfo();
		actualProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();// this is mandatory to write
	}



}
