package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;


public class BaseTest {

	WebDriver driver; // v can have 2references for the same object->2 driver references for 1 chrome object
	Properties prop;
	DriverFactory df;
	
	LoginPage loginPage;
	AccountsPage accPage;
	RegisterPage regPage;
	ResultsPage resultsPage;
	ProductInfoPage productInfoPage;
	ShoppingCartPage shoppingCartPage;
	
	SoftAssert softAssert;
	
	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setUp(String browser,String browserVersion) {
		df = new DriverFactory();
		prop=df.init_prop();
		
		if(browser!=null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserVersion", browserVersion);
		}
			
		
		
		driver = df.init_driver(prop); //null pointer exception-chrome11		
		loginPage = new LoginPage(driver); //v will never initialize page classes inside the best test except LoginPage
		softAssert=new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
