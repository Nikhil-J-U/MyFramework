package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	//1. declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	//2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	//3. private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By register = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	
	//4. actions
	@Step("getting login page tite value...")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitleWithFraction(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting login page URL...")
	public Boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("checking forgot password link...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("checking register password link....")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(register);
	}
	
	@Step("do login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login with : "+un + " : " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
		
	} 
	
	@Step("do login with wrong username: {0} and wrong password: {1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("try to login with wrong credentials: "+un +":" +pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMsg = eleUtil.doGetText(loginErrorMsg);
		System.out.println(errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MSG)) {
			System.out.println("login is not done.....");
			return false;
		}
		return true;
	}
	
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(register);
		return new RegisterPage(driver);
	}

}
