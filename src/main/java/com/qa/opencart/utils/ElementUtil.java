package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
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
		case "cssselector":
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
			System.out.println("please enter correct locator type and value....");
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public WebElement getElement(By locator, int timeOut) {

		return doPresenceOfElementLocated(locator, timeOut);
	}

	public WebElement getElement(String locatorType, String locatorValue) {

		return driver.findElement(getBy(locatorType, locatorValue));
	}
	
	public void doClear(By locator) {
		getElement(locator).clear();
	}

	public void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	public void doSendKeys(By locator, int timeOut, String value) {
		doPresenceOfElementLocated(locator, timeOut).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}

	public void doClick(By locator, int timeOut) {
		doPresenceOfElementLocated(locator, timeOut).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		String text;
		for (WebElement e : eleList) {
			text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}

		return eleTextList;

	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getAttributesList(By locator, String value) {
		List<WebElement> imgList = getElements(locator);
		String attrValue;
		List<String> attrList = new ArrayList<String>();
		for (WebElement e : imgList) {
			attrValue = e.getAttribute(value);
			attrList.add(attrValue);

		}

		return attrList;
	}

	public String getAttributeValue(By locator, String value) {
		String attrValue = getElement(locator).getAttribute(value);
		System.out.println(attrValue);
		return attrValue;
	}

	public boolean isElementExists(By locator) {
		int elementcount = getElementsCount(locator);
		System.out.println("total elements found: " + elementcount);
		if (elementcount >= 1) {
			System.out.println("element is found.. " + locator);
			return true;
		} else {
			System.out.println("element is not found.." + locator);
			return false;
		}
	}

	/************************* DROP DOWN UTILS *****************************/

	public void doDropDownSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doDropDownSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doDropDownSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> countryList = select.getOptions();
		for (WebElement e : countryList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}

		}
	}

	public void doSelectDropDownValueWithoutSelect(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());
		String text;
		for (WebElement e : optionsList) {
			text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/************************** LINKS UTIL ***********************************/

	public List<String> getLinksTextList(By locator) {
		List<WebElement> linksList = getElements(locator);
		List<String> linkTextList = new ArrayList<String>();
		String text;
		for (WebElement e : linksList) {
			text = e.getText();
			linkTextList.add(text);
		}
		return linkTextList;
	}

	public void clickOnElement(By locator, String linkText) {
		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());
		String text;
		for (WebElement e : optionsList) {
			text = e.getText().trim();
			System.out.println(text);
			if (text.equals(linkText)) {
				e.click();
				break;
			}
		}
	}

	/************************* WEBTABLE UTILS *************/

	public void printTable(By rowLocator, By colLocator, String beforeXpath, String afterXpath) {
		int rowCount = getElements(rowLocator).size();
		int colCount = getElements(colLocator).size();
		for (int row = 2; row <= rowCount; row++) {
			for (int col = 1; col <= colCount; col++) {
				String xpath = beforeXpath + row + afterXpath + col + "]";
				String text = doGetText(By.xpath(xpath));
				System.out.print(text + "           |        ");
			}
			System.out.println();
		}

	}

	/*************** ACTION UTIL ******************************/

	public void doMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).perform();
	}

	public void doClickOnChildMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		doMoveToElement(parentMenuLocator);
		Thread.sleep(3000);
		doClick(childMenuLocator);

	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsSendKeysOnActiveElement(By locator, String value) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).sendKeys(value).build().perform();
	}

	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	public void doActionMoveToElementClick(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
	}

	/************************ WAIT UTIL *******************************/

	public WebElement doPresenceOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement doPresenceOfElementLocated(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> textList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.add(text);
		}
		return textList;
	}
	
	/********************* WAIT UTIL FOR NON-WEBELEMENTS***************/
	

	public boolean waitForURLToContains(String urlFraction, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(urlFraction));

	}

	public boolean waitForURLToBe(String url, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlToBe(url));

	}
	

	public String doGetTitleWithFraction(String titleFraction, int timeOut) {
		if(waitForTitleContains(titleFraction, timeOut)) {
			return driver.getTitle();
		}
		else {
			return null;
		}
	}
	
	public String doGetTitle(String titleFraction, int timeOut) {
		if(waitForTitleToBe(titleFraction, timeOut)) {
			return driver.getTitle();
		}
		else {
			return null;
		}
	}
	public boolean waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFraction));
	}
	

	public boolean waitForTitleToBe(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(titleFraction));
	}
	

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	

	public void doAlertAccept(int timeOut) {
		 waitForAlert(timeOut).accept();
	}
	

	public void doAlertDismiss(int timeOut) {
		 waitForAlert(timeOut).dismiss();
	}
	

	public void enterAlertText(String text,int timeOut) {
		 waitForAlert(timeOut).sendKeys(text);
	}
	

	public void waitForFrameByNameOrId(String nameOrID, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
	}
	
	public void waitForFrameByIndex(int index, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}
	
	public void waitForFrameByLocator(By locator, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public void waitForFrameByWebElement(WebElement frameElement, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void clickElementWhenReady(By locator, int timeOut, long interval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(interval));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	

	public WebElement waitForElementpresentUsingFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("element not found...");
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				
	}
	
	public WebElement waitForElementUsingWebDriverWait(By locator, int timeOut, long pollingTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait
			.withTimeout(Duration.ofSeconds(timeOut))
			.pollingEvery(Duration.ofMillis(pollingTime))
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.withMessage("element not found...");
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	

	public WebElement retryingElement(By locator, int timeOut, long pollingTime) {
		WebElement element = null;
		int attempts = 0;
		
		while (attempts<timeOut) {
			try {
			element = getElement(locator);
			break;
			}
			catch (NoSuchElementException e){
				System.out.println("element is not found in attempt " + attempts + ": " + locator);
				try {
					Thread.sleep(pollingTime);
				} catch(InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		
		if(element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			}
			catch(Exception e) {
				System.out.println("element is not found exception.... tried for :" +timeOut+ "with the interval of : "+pollingTime);
			}
		}
		return element;
	}
	


}
