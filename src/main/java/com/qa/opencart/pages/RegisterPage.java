package com.qa.opencart.pages;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	// 1. decalre private driver
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2. page constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. private locators
	private By registerPage = By.linkText("Register");
	private By radioBtnYes = By.xpath("//div[@class='form-group']//following-sibling::div/label/input[@value=1]");
	private By radioBtnNo = By.xpath("//div[@class='form-group']//following-sibling::div/label/input[@value=0]");
	//WebElement ele = driver.findElement(By.linkText("Privacy Policy"));
	private By Fname = By.id("input-firstname");
	private By Lname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By confirm = By.id("input-confirm");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");
	private By successMsg = By.cssSelector("div#content h1");

	// 4. actions
//
//	public void enterRegister() {
////		driver.findElement(Fname).sendKeys("Nikki");
////		driver.findElement(Lname).sendKeys("J");
////		driver.findElement(email).sendKeys("nikki@gmail.com");
////		driver.findElement(telephone).sendKeys("866015153");
////		driver.findElement(pwd).sendKeys("nikhil@123");
////		driver.findElement(confirm).sendKeys("nikhil@123");
////		driver.findElement(radioBtn).click();
//		eleUtil.doSendKeys(Fname, "Nikki");
//		eleUtil.doSendKeys(Lname, "J");
//		eleUtil.doSendKeys(email, "nikki@gmail.com");
//		eleUtil.doSendKeys(telephone, "866015153");
//		eleUtil.doSendKeys(pwd, "nikhil@123");
//		eleUtil.doSendKeys(confirm, "nikhil@123");
//		eleUtil.doClick(radioBtn);
//
//		clickOnAgree();
//	}
//
//	public void navigateToRegister() {
////		driver.findElement(registerPage).click();
//		eleUtil.doClick(registerPage);
//	}

	public void clickOnAgree() {
		WebElement ele = driver.findElement(By.linkText("Privacy Policy"));
		driver.findElement(with(By.tagName("input")).toRightOf(ele)).click();
	}
	
	public boolean accountRegistration(String firstName, String lastName, String email, 
			String telephone, String password, String subscribe) {
		eleUtil.doSendKeys(Fname, firstName);
		eleUtil.doSendKeys(Lname, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doSendKeys(confirm, password);
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(radioBtnYes);
		} else {
			eleUtil.doClick(radioBtnNo);
		}
		clickOnAgree();
		eleUtil.doClick(continueBtn);
		String msg = eleUtil.waitForElementToBeVisible(successMsg, 5, 1000).getText();
		
		if(msg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			return true;
		}
		return false;
	}
	
	

}
