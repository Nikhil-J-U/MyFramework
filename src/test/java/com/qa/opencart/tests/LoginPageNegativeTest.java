package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {
	
	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			{"test111@gmail.com", "abcd"},
			{"naveen111@gmail.com", "abc123"},
			{"niki.uj@gmail.com", "test@123"},
			{"abcd@gmail.com", "test@123"}
		};
	}
	@Test (dataProvider="loginWrongTestData")
	public void loginNegativeTest(String un, String pwd) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(un, pwd));
	}
}
