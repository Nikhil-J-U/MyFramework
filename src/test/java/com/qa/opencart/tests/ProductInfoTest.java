package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetup() {
		accountsPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@Test
	public void productImagesCountTest() {
		searchResultsPage = accountsPage.doSearch("iMac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
		
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k + ":" +v));
		softAssert.assertEquals(actProductInfoMap.get("name"),"MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actProductInfoMap.get("price"),"$2,000.00");
		softAssert.assertAll();
		
	}

}
