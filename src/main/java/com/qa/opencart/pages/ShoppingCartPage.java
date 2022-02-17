package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By shoppingCartHeader = By.xpath("//div[@id='content']/h1");
	private By addquantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successmainProductNameLink = By.xpath("//div[@class='alert alert-success alert-dismissible']//a");
	private By successShoppingCartLink = By
			.xpath("//div[@class='alert alert-success alert-dismissible']//a//following-sibling::a");
	private By successMesg = By.xpath("//div[@class='alert alert-success alert-dismissible']");

	
	// Assignment

//		public void addToCart(int quantity) {
//			eleUtil.doSendKeys(addquantity, String.valueOf(quantity));
//			eleUtil.doClick(addToCart);		
//			
	//
//		}
	//
//		public boolean isSuccessMessageExist() {
//			return eleUtil.doIsdisplayed(successMesg);
	//
//		}
	//
//		public ShoppingCartPage gotoShoppingCartPage() {
//			String mesg = eleUtil.dogetText(successMesg);
//			System.out.println(mesg);
//			if (mesg.contains(Constants.SHOPCART_SUCCESS_MESSAGE)) {
//				eleUtil.doClick(successShoppingCartLink);
//			}
//			return new ShoppingCartPage(driver);
//		}

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getShoppingCartPageHeader() {
		return eleUtil.dogetText(shoppingCartHeader);
	}

}
