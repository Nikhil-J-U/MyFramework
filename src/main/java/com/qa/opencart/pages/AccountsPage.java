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
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By header = By.cssSelector("div#logo a");
	private By accountSections = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");
	private By logout = By.linkText("Logout");
	
	public String getAccountPageTitle() {
		return eleUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountsPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public boolean isLogoutLinkExists() {
		return eleUtil.doIsDisplayed(logout);
	}
	
	public void logout() {
		if(isLogoutLinkExists()) {
			eleUtil.doClick(logout);
		}
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> accList = eleUtil.waitForElementsToBeVisible(accountSections, Constants.DEFAULT_TIME_OUT);
		List<String> accValList = new ArrayList<String>();
		for(WebElement e:accList) {
			String text = e.getText();
			accValList.add(text);
		}
		return accValList;
		
	}
	
	public boolean isSearchExists() {
		return eleUtil.doIsDisplayed(search);
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching the product : "+productName);
		eleUtil.doSendKeys(search, productName);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);

	}

}
