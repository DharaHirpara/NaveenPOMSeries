package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.Constants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

//this all check in report:behaviour
@Epic("EPIC-100:Design login page for open cart application")
@Story("US-101:design login page features")
@Feature("Feature 201: Adding login feature")
public class LoginPageTests extends BaseTest{
	@Description("verify login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginPageTitleTest() {
	String actTitle=loginPage.getLoginPageTitle();
	Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	@Description("verify login Page Url Test")
@Severity(SeverityLevel.NORMAL)
    @Test(priority=2)
	public void loginPageUrlTest() {
	String actUrl=loginPage.getLoginPageURL();
	Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND);
	}
	@Description("verify forgotpwd Link Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

@Description("verify user is able to login applic with correct credentials Test")
@Severity(SeverityLevel.BLOCKER)//this annotation comes from allure
	@Test(priority=4)
	public void loginTest()  {
accPage =loginPage.doLogin("dec2024@opencart.com", "radha123");
String actAcctPageTitle=accPage.getAccPageTitle();
Assert.assertEquals(actAcctPageTitle,Constants.ACCOUNTS_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
}
}








