package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("acc page title: "+actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
//	@Test (priority = 2)
////	public void accPageHeaderTest() {
////		String header = accountsPage.getAccountsPageHeader();
////		System.out.println("acc page header is: "+header);
//		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER, Errors.ACC_PAGE_HEADER_NOT_ERROR_MSG);
////	}
	
	@Test (priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExists());
	}
	
	@Test (priority = 4)
	public void accPageSecsTest() {
		List<String> actAccSecList = accountsPage.getAccountSecList();
		Assert.assertEquals(actAccSecList, Constants.getExpectedAccSecList());
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"MacBook Pro"},
			{"Apple"},
			{"Samsung"},
		};
	}
	
	@Test(priority = 5, dataProvider = "productData")
	public void searchTest(String productName) {
	searchResultsPage = accountsPage.doSearch(productName);
	Assert.assertTrue(searchResultsPage.getProducListCount()>0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"MacBook" , "MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30"}
		};
		}
	
	@Test(priority=6, dataProvider = "productSelectData")
	public void selectProductList(String productName, String mainProductName) {
		searchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(),mainProductName);
	}

}
