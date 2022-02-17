package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMessage = By.cssSelector("div #content h1");

	// once u register n create an account ,u wont see the register link,so to
	// create another user, v need to logout and click on register link again
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	// v will pass pwd->cnfm pwd also holds the same value ,so v wont pass
	public boolean accountRegistration(String firstName,String lastName,String email,String telePhone,String password,String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName); //this - is by locator
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telePhone, telePhone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password); // by locators will b different->so v will again pass the password
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		//this approach here will help us in achieving negative test
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueBtn);
		
		String successMesg=eleUtil.dogetText(successMessage);
		System.out.println(successMesg);
		
		if(successMesg.contains(Constants.REGISTER_SUCCESS_MESG)){
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);			
			return true;
		}
			
		return false;
	}
}
