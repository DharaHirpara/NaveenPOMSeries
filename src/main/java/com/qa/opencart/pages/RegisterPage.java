package com.qa.opencart.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage  {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
@Step("User registration")
	public boolean userRegister(String firstName, String lastName,
			String email, String telephone, String password,
			String subscribe) {

		//eleUtil.waitForElementVisible(firstName,10).sendKeys(firstName);//error bcoz
		//method says give me (By locator,sendkeys)but in by locator i have given name:firstName and
		// in userRegister()also given firstName so i can i differentiate to both?so change name of one of them or use this keyword
		//for class level by locator
		eleUtil.waitForElementVisible(this.firstName,10).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);//don't need to wait for everyone logically
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);//radio button
		} else {
			eleUtil.doClick(subscribeNo);
		}

		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);//i will land on next page and i can see successul msg so don't create seperate class for over small thing

		String regiSuccessMesg = eleUtil.waitForElementVisible(successMessg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT)
											.getText();
		System.out.println(regiSuccessMesg);

		if (regiSuccessMesg.contains(Constants.REGISTER_SUCCESS_MESSG)) {//or i can do this with TestNg in test class
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}

	}

//	 maintain variable
//	private WebDriver driver;// every page class has their own webDriver reference,here driver=new
//								// chromedriver bcoz of this key word in BaseTest
//	private ElementUtil eleUtil;
//
//	// 1.maintain private By locator :i have done registration from login page
//	private By firstName = By.id("input-firstname");// here firstName means by locator(this.firstName),in method
//													// firstName means value so write this keyword for differentiate
//	private By lastName = By.id("input-lastname");
//	private By emailId = By.id("input-email");
//	private By telephone = By.id("input-telephone");
//	private By password = By.id("input-password");
//	private By confirmPassword = By.id("input-confirm");
//
//	private By isSubscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
//	private By isSubscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
//
//	private By policyCheckBox = By.name("agree");
//	private By continueBtn = By.xpath("//input[@type='submit']");
//
//	By successMessg = By.cssSelector("div#content h1");// validation
//
//	// now i want to register another user so for that i need to logout so again
//	// i land on login page and find register link from there--->inspect logout
//	private By logoutLink = By.linkText("Logout");
//	private By registerLink = By.linkText("Register");
//
//	// 2.create public page class constr.:
//	public RegisterPage(WebDriver driver) {
//		this.driver = driver;
//		eleUtil = new ElementUtil(this.driver);
//	}
//
//	// 3.public page actions/methods:and return something from every method bcoz on
//	// the basis of return(actual result) vs expected test class will perform
//	// assertion
//	public boolean userRegister(String firstName, String lastName, String emailId, String telephone, String password,String subscribe) {// here firstName means value i need to pass here by locator and value but name of both are same
//		// so how can i differentiate firstName?with this key word
//
//		eleUtil.doSendKeys(this.firstName, firstName);
//		eleUtil.doSendKeys(this.lastName, lastName);
//		eleUtil.doSendKeys(this.emailId, emailId);
//		eleUtil.doSendKeys(this.telephone, telephone);
//		eleUtil.doSendKeys(this.password, password);
//		eleUtil.doSendKeys(this.confirmPassword,password);
//
//		// for subscribe yes or no so write if condition:
//		if (subscribe.equalsIgnoreCase("yes")) {
//			eleUtil.doClick(isSubscribeYes);
//		} else {
//			eleUtil.doClick(isSubscribeNo);
//		}
//
//		// click on privacy check box and continue
//		eleUtil.doClick(policyCheckBox);
//		eleUtil.doClick(continueBtn);
//
//		// will get success msg
//		String successMsg = eleUtil.waitForElementVisible(successMessg, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).getText();
//		System.out.println(successMsg);
//
//		// return result
//		if (successMsg.contains(Constants.REGISTER_SUCCESS_MESSG)) {
//			eleUtil.doClick(logoutLink);
//			eleUtil.doClick(registerLink);//after clicking next user is ready for registration
//			return true;
//		} else {
//			return false;
//		}
//		//for 2nd user i need to click on logout and then click on register and again back to reg.page like this 3rd,4th user....
//	}


}
