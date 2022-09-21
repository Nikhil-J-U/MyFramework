package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open Cart App - Login Page")
@Story("US 101: Open Cart Login Design with multiple features")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{
	
	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test (priority=1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("page title: "+actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE,".....login page title is not correct.....");
	}
	
	@Description("login page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority=2)
	public void loginPageUrlTest() {
//		String actUrl = loginPage.getLoginPageUrl();
//		System.out.println("page url: "+ actUrl);
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Description("forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Register password link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=4)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("Register password link test")
	@Severity(SeverityLevel.BLOCKER)
	@Test (priority=5)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
		
	}

}
