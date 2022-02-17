package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	public static String highlight;// static->v can use DriverFactory.highlight
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the driver using the browser name
	 * 
	 * @param browserName
	 * @return
	 */

	// String browserName - v r replacing with Properties prop->this s called call
	// by reference
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight").trim();

		System.out.println("Browser name is :" + browserName);
		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		} 
		else {
			System.out.println("Please enter the right browser name:" + browserName);
		}
		//getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();

	}
	
	/**
	 * this will return the thread local copy of driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	

	/**
	 * this method is used to initialize the properties( this method will read the
	 * properties and return the prop reference)
	 * 
	 * @return properties class reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(".\\src\\test\\Resources\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	// ThreadLocal -- JDK 8 --> create a local copy of driver
	// set driver with TL
	// getdriver() -- driver
	// dribver null problem
	// u can take ur driver copy anywhere in ur framework
	// better thread management
	// to avoid the dead local conditon -- TL driver copy
	// large test cases count -- 200, 300 TCS --> proper test results

	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		// this driver is null and it is giving pass instead of fail n not attaching
		// screenshot->so v will use thread local concept and get the local copy of driver
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); // here v replaced driver with getdriver method
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
