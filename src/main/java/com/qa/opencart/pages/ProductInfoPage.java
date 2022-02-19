package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.netty.handler.codec.string.StringDecoder;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeaderName = By.cssSelector("div #content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]//li");

	private By addquantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	//private By successmainProductNameLink = By.xpath("//div[@class='alert alert-success alert-dismissible']//a");
	// private By successShoppingCartLink = By.xpath("//div[@class='alert// alert-success alert-dismissible']//a//following-sibling::a");
	// private By successMesg = By.xpath("//div[@class='alert alert-success
	// alert-dismissible' and text()='Success: You have added ']");
	private By shoppingCartButton=By.xpath("(//div[@id='top-links']//ul/li)[position()=9]");
	

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderName() {
		return eleUtil.dogetText(productHeaderName);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, 10).size();
	}

	public Map<String, String> getProductInfo() {
		// productMap = new HashMap<String, String>(); // random order
		// productMap = new TreeMap<String, String>(); // sorted order
		productMap = new LinkedHashMap<String, String>(); // order based
		productMap.put("name", getProductHeaderName());
		productMap.put("totalimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}

	// Brand: Apple Product
	// Code: Product 18
	// Reward Points: 800
	// Availability: Out Of Stock

	// dont waant to expose this method to testng class
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
		}
	}

	// $2,000.00
	// Ex Tax: $2,000.00

	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText().trim();
		String exTaxPrice = metaPriceList.get(1).getText().trim();
		productMap.put("price", price);
		productMap.put("ExTaxprice", exTaxPrice);
	}

	public ShoppingCartPage addToCart(ProductInfoPage productInfoPage, int quantity) {

		eleUtil.doSendKeys(addquantity, String.valueOf(quantity));
		eleUtil.doClick(addToCart);
		
		String mesg = eleUtil.dogetText(shoppingCartButton);
		System.out.println(mesg);
		if (isShoppingCartBtnExist()) {
			eleUtil.doClick(shoppingCartButton);
		}

//		String mesg = eleUtil.dogetText(successMesg);
//		System.out.println(mesg);
//		if (mesg.contains(Constants.SHOPCART_SUCCESS_MESSAGE)) {
//			eleUtil.doClick(successShoppingCartLink);
//		}
		return new ShoppingCartPage(driver);
	}

	public boolean isShoppingCartBtnExist() {
		return eleUtil.doIsdisplayed(shoppingCartButton);

	}

}
