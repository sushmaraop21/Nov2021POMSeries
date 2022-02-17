package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.cssSelector("div #logo a");
	private By sections = By.cssSelector("div #content h2");
	private By logOut = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon=By.cssSelector("div #search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageUrl() {
		return eleUtil.waitForURLContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public String getHeader() {
		return eleUtil.dogetText(header);
	}

	public boolean isLogOutLinkExist() {
		return eleUtil.doIsdisplayed(logOut);
	}

	public boolean logOut() { // this method is not ready as v r not handling logOut completely-should give good return-page chaining
		if (isLogOutLinkExist()) {
			eleUtil.doClick(logOut);
			return true;
		} else {
			return false;
		}
	}

	public List<String> getAccPageSections() {
		List<WebElement> sectionsList = eleUtil.waitForElementsVisible(sections, 10);
		List<String> secValList = new ArrayList<String>();
		for (WebElement e : sectionsList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				secValList.add(text);
			}
		}
		return secValList;
	}

	public boolean searchExist() {
		return eleUtil.doIsdisplayed(search);
	}

	public ResultsPage doSearch(String productName) { //this method s not ready as v r not handling search landing page yet(accPage comment)
		if(searchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
			
		}
		return new ResultsPage(driver);
	}
	
	
}
