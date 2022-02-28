package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.ShoppingCartPage;
import com.qa.opencart.utils.Constants;

public class ShoppingCartPageTest extends BaseTest {
	@BeforeClass
	public void ShoppingCartPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void getShoppingCartHeaderTest() {
		String header = shoppingCartPage.getShoppingCartPageHeader();

		Assert.assertTrue(header.contains(Constants.SHOPPING_PAGE_HEADER));
	}

	@Test
	public void addToCartTest() {
		resultsPage = accPage.doSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductInfoMap = productInfoPage.getProductInfo();
		actualProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		shoppingCartPage = productInfoPage.addToCart(productInfoPage, 2);

	}

}
