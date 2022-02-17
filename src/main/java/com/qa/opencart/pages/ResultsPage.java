package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchHeader = By.cssSelector("div #content h1");
	private By productResults = By.cssSelector("div.caption a");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public int getProductListCount() {
		int prodCount = eleUtil.waitForElementsVisible(productResults, 20).size();
		System.out.println("Total product count :" + prodCount);
		return prodCount;
	}

	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("Main product name :" + mainProductName);
		List<WebElement> searchList = eleUtil.waitForElementsVisible(productResults, 20);
		for (WebElement e : searchList) {
			String text = e.getText();
			if (text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}



}
