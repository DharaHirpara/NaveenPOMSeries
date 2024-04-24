package com.qa.opencart.tests;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.utils.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

//@Listeners(ExtentReportListener.class)-->for when i run individual run test class take screenshots
@Epic("EPIC-100:Design login page for open cart application")//this annotation comes from allure report
@Story("US-101:design login page features")//this annotation comes from allure
@Feature("Feature 201: Adding login feature")
public class LoginPageTest extends BaseTest{// has precondition like @BT and @AT
//its not mandatory to every page class has to separate test class,for 5 page class can have only 1 test class,100 page class=2-3 test class possible

	@Description("verify login Page Title Test")//this annotation comes from allure report
	@Severity(SeverityLevel.NORMAL)//this annotation comes from allure report
	@Test(priority=1)
	public void loginPageTitleTest() {//calling method
	String actTitle=loginPage.getLoginPageTitle();//child class.LoginPageTest inherit variable from another package parent class:BaseTest
	Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);//use with class name bcoz its static
	}
//	public void loginPageHeaderTest() {
//		loginPage.getLoginPageTitle();//inheritance,loginPageref created in basetest parent
//		String actualTitle = "Open Cart";
//		Assert.assertEquals(actualTitle, "Open Cart");
//	}
@Description("verify login Page Url Test")//this annotation comes from allure
@Severity(SeverityLevel.NORMAL)//this annotation comes from allure
    @Test(priority=2)
	public void loginPageUrlTest() {
	String actUrl=loginPage.getLoginPageURL();//child class.LoginPageTest inherit variable from another package parent class:BaseTest
	Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND);//login&language constant value will never change so move this string value in constant class
	}
	@Description("verify forgotpwd Link Exist Test")//this annotation comes from allure
	@Severity(SeverityLevel.CRITICAL)//this annotation comes from allure
	@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());//child class.LoginPageTest inherit variable from another package parent class:BaseTest
	}
//	
//	public void registerLinkExistTest() {
//		Assert.assertTrue(loginPage.isRegisterLinkExist());//child class.LoginPageTest inherit variable from another package parent class:BaseTest
//	
//	}
//
@Description("verify user is able to login applic with correct credentials Test")//this annotation comes from allure
@Severity(SeverityLevel.BLOCKER)//this annotation comes from allure
	@Test(priority=4)
	public void loginTest()  {
accPage =loginPage.doLogin("dec2024@opencart.com", "radha123");//doLogin method return accountspage class ref var so assert it here
String actAcctPageTitle=accPage.getAccPageTitle();
Assert.assertEquals(actAcctPageTitle,Constants.ACCOUNTS_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
}
}








