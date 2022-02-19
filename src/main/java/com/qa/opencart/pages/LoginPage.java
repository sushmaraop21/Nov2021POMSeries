package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
		private WebDriver driver; // v r not initializing this driver
		private ElementUtil eleUtil;
		
		
	//maintain private by locators and v need driver as entity
	
	private By emailId=By.id("input-email"); //allure gives this as broken->no such element-no locator
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotpwdLink=By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	
	
	//2.page constructor
	
	public LoginPage(WebDriver driver) {
		this.driver=driver; 
		eleUtil=new ElementUtil(driver);
	}
	
	//3.public page actions and methods(just like getter n setter-encapsulation
	
	@Step("TC-01 : getting the login page title")
	public String getLoginPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting the login page url")	
	public String getLoginPageUrl() {
		return eleUtil.waitForURLContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsdisplayed(forgotpwdLink);
	}
	
	@Step("login with username : {0} and password: {1}")
	public AccountsPage doLogin(String userName,String pwd) {
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver); //this is nothgn but redirecting to the AccountsPage which s landing page passing the driver
		
	}
	//page action for register page shud be available on login page
	
	@Step("Navigating to the register page")
	public RegisterPage gotoRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver); // this method shud return the next landing page that is Register time 
	}
	
	
}
