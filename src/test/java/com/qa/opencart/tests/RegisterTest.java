package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	
	@BeforeClass
	public void setupRegistrationPage() {
		registerPage = loginPage.goToRegisterPage();
	}
	
	
//	@Test
//	public void Register() {
//		registerPage.navigateToRegister();
//		registerPage.enterRegister();
//	}
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "MyFrameWork"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	
	@Test(dataProvider="getRegisterData",enabled=false)
	public void userRegistrationTest(String firstName, String lastName,
			String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.accountRegistration(firstName, lastName, getRandomEmail(), 
			 telephone, password, subscribe));
	}

}
