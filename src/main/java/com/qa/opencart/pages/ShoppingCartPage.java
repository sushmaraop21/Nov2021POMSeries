package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By shoppingCartHeader = By.xpath("//div[@id='content']/h1");
	
	

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getShoppingCartPageHeader() {
		return eleUtil.dogetText(shoppingCartHeader);
	}

	
//
//	public ShoppingCartPage gotoShoppingCartPage() {
//		
//	}

}
