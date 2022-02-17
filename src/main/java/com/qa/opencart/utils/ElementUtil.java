package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	// the constructor is so important here to initialize the driver and if u dont
	// have the constructor, driver will be null

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
		// driver = new ChromeDriver(); this cannot be written here as this will open a
		// new browser that is, 2 browsers.
	}

	public By getBy(String locatorType, String locatorValue) {

		By locator = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;
		default:
			break;
		}
		return locator;

	}

	public WebElement getElement(By locator) {
		//return waitForElementPresence(locator,10);// v shudnt use this here->this acts global wait ->which is bad code. 	
		WebElement ele= driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
	}	

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(getBy(locatorType, locatorValue)).click();
	}

	//v r updating this method->clear before entering the next serach key(for eg: search keys being entered in some scenariosMacbook imac apple)
	public void doSendKeys(By locator, String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}

	public String dogetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsdisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	// from totallinks class
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// from totallinks class
	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	// from totallinks class
	public void printElementsText(By locator) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				System.out.println(text);
			}
		}
	}

	/**
	 * This method will return the element's text
	 * 
	 * @param locator
	 * @return
	 */
	// from totallinks class
	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	/**
	 * This method will return the list of element's attribute value
	 * 
	 * @param locator
	 * @param attrName
	 * @return
	 */
	// from totallinks class
	public List<String> getElementAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrVal = e.getAttribute(attrName);
			eleAttrList.add(attrVal);
		}
		return eleAttrList;
	}

	// from languagelinks class
	public void clickOnLink(By locator, String linkText) {

		List<WebElement> langList = getElements(locator);
		System.out.println(langList.size());

		for (WebElement e : langList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(linkText)) {
				e.click();
				break;
			}
		}
	}

	/******************* Drop down utils ******************/

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public int getDropDownOptionscount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	public void selectValueFromDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}

	public List<String> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>(); // top casting of arraylist
		System.out.println(optionsList.size());
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;
	}

	/************** Actions methods *********************/

	/**
	 * Equivalent to calling: Actions.click(element).sendKeys(keysToSend).This
	 * method is different from
	 * 
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();

	}

	/**
	 * Clicks in the middle of the given element. Equivalent to:
	 * Actions.moveToElement(onElement).click()
	 * 
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();

	}
	
	/**************wait Utils************************/
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	//this method is not in any other class
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * Coming from WebDriverConcept class
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	
	
	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 *  
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * default polling Time- 500 ms/0.5 sec
	 * @param locator 
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	/**
	 *  
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * Polling time -customized time
	 * @param locator 
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut,int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	//non webelements- title,url,alert
	//wait.until will return webelement here 
	public boolean waitForPageTitle(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleValue));
	}
	//where as wait.until returns string here
	public boolean waitForPageActTitle(String actTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(actTitle));
	}

	public String doGetPageTitle(String titleValue, int timeOut) {
		if (waitForPageTitle(titleValue, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}
	public String doGetPageTitleIs(String titleValue, int timeOut) {
		if (waitForPageActTitle(titleValue, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}
	//From WaitForURL class
	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	public String waitForURLToBe(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlFraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}
	//from waitforalert class
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();

	}
	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}
	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}
	/**
	 * An expectation for checking whether the given frame is available to switch
	 * to. If the frame is available it switches the given driver to the specified
	 * frameIndex.
	 * 
	 * @param timeOut
	 * @param frameIndex
	 * @return
	 */
	public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

	}
	public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}
	//list of webelements from waitforelements class
	public  List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	//from FluentWebdriver 
	
	public WebElement waitForElementPresenceWithFluentWait(By locator,int timeOut,int pollingTime) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
							.ignoring(org.openqa.selenium.NoSuchElementException.class)
							.ignoring(StaleElementReferenceException.class)
							.withMessage(locator+" is not foudn within the time..........");
	 return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	//So here we proved that whatever v can do with fluent wait ,v can do with the webdriver wait
	
	public void waitForElementPresenceWithWait(By locator,int timeOut,int pollingTime) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.pollingEvery(Duration.ofSeconds(pollingTime))
		.ignoring(org.openqa.selenium.NoSuchElementException.class)
		.ignoring(StaleElementReferenceException.class)
		.withMessage(locator+ " is not found in the time given");
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	

}
