package com.qa.opencart.pages;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.TimeUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.opencart.utils.ElementUtil;

public class LoginPage  {

	//page class/page library/page object
	private WebDriver driver;// every page class has their own webDriver reference,here driver=new chromedriver bcoz of this key word in BaseTest
	private ElementUtil eleUtil;
	
	// 1.maintain private By locator and use this in public page action or methods,why this is private bcoz according to POM noone can access this outside of this class
	//use within class
	private By emailId = By.cssSelector("input#input-email");
	private By password = By.cssSelector("input#input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	//private By accLogoutMessage = By.cssSelector("div#content h1");

	
	// 2.create public page class constr.:i will call all method of this class in
	// LoginPageTest so i have to create obj of this class but when
	// i create obj and pass driver there so this const.will be call ane this
	// constr.pass driver at class level by This keyword and class level driver pass
	// to all this method
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);//i want to use element util class method so create obj and maintain above ref variable
		//elemeniUtil method i s also waiting for driver so in argument provide driver
	}
	// 3.public page actions/methods:this is encapsulation bcoz locator is private
	// and we are using here in public method,and return something from every method bcoz on the basis of  return(actual result) vs expected test class will perform assertion
	@Step("getting login page title")//this comes from allure reports,this is not mandatory but just for decor your test cases
	public String getLoginPageTitle() {//non web element,login page title create here and return this title and on the basis of this do assert

		String title=eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_SHORT_TIME);

		//System.out.println("Login Page title is:" + title);
		Log.info("Login Page title is:" + title);

		return title;//i will assert this title in test class
	}
	@Step("checking login page url")
	public String getLoginPageURL() {//non web element

		String url=eleUtil.waitForURLContains(Constants.LOGIN_PAGE_URL_FRACTION,TimeUtil.DEFAULT_SHORT_TIME);

		//System.out.println("Login Page URL is:" + url);
		Log.info("Login Page URL is:" + url);

		return url;
	}
	@Step(" checking is ForgotPwd Link Exist or not")
	public boolean isForgotPwdLinkExist() {//web element,isDisplayed will return boolean,so instead of void  write boolean
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
//	public boolean isRegisterLinkExist() {
//		return eleUtil
//				   .waitForElementVisible(registerLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT)
//				        .isDisplayed();//check isDisplayed or not
//	}
@Step("login with username {0} and password {1}")//here 0 represent 0 parameter username and 1 for 1st parameter password
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementVisible(emailId,TimeUtil.DEFAULT_MEDIUM_TIME).sendKeys(username);
		eleUtil.doSendKeys(password,pwd);
		eleUtil.doClick(loginBtn);// i will land on Account page so its this method responsibility to
		//return landing class obj and pass driver bcoz landing page class constr is waiting for driver
		return new AccountsPage(driver);//this driver i have taken from class level of this class bcoz constr.of this call has pass it there

}
//	public String getLogoutMessage() {//don't create separate page class for account logout
//		String logoutMesg=eleUtil.waitForElementVisible(accLogoutMessage, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
//		System.out.println("logout successful mesg:"+logoutMesg);
//		return logoutMesg;
//	}
@Step("navigating to registration page")
public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink,TimeUtil.DEFAULT_LONG_TIME).click();
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
}
