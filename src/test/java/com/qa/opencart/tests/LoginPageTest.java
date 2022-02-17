package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC- 100 : Design Login Page for open cart Application") // this wont integrate with JIRA
@Story("US-101 : Login page features ")
public class LoginPageTest extends BaseTest {

	@Description("Login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		//String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Loging page url test")
	@Test
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		System.out.println("Login page url :" + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}

	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		//Assert.assertTrue(false); //here allure gives-> failure ->assert fail
	}
	@Description("Positive test case for Login with correct credentials")
	
	@Severity(SeverityLevel.BLOCKER)	
	@Test
	public void loginTest() {
		//this doLogin method is giving Accnnt page object -which is coming from base test
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim()); // Prop is inherited frrom BaseTest
		Assert.assertTrue(accPage.isLogOutLinkExist()); //u can validate with anything here like title etc
	}
}
