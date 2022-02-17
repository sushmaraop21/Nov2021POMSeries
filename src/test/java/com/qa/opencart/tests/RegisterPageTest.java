package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		regPage = loginPage.gotoRegisterPage();
	}

	public String getRandomNumber() {
		Random randomGen = new Random();
		String email ="ADh0910"+ randomGen.nextInt(1000) + "@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telePhone, String password,String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomNumber(), telePhone, password, subscribe));
	}
}
