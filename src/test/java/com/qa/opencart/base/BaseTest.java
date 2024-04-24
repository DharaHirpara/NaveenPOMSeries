package com.qa.opencart.base;

import java.util.Properties;

import com.qa.opencart.pages.*;
import com.qa.opencart.utils.StringUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;//protected because other package class can use this
	//In config.properties file i have written key value pair and if value is 1 even if all value consider as a string always
	protected LoginPage loginPage;
	protected RegisterPage registrationPage;
    protected AccountsPage accPage;
    protected SearchResultPage searchPage;
	protected ProductInfoPage productPage;
	protected SoftAssert softAssert;
	protected ShoppingCartPage shoppingCart;
	protected StringUtil stringUtil;
//	protecte CheckOutPage checkOut;

	@Step("SetUp:launching {0} browser and init the properties")//{0} means (String browserName) has only one para with 0 index
	@Parameters({"browser"})
    @BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.init_prop(); // its return prop so store it in prop and so above create prop class ref
		if (browserName!=null)
		{
			prop.setProperty("browser",browserName);//key from config.prop and ,value from .xml,this value of .xml
			//will update at run time in config.prop,and this prop.setProperty value will give here driver = df.init_driver(prop);
		}
		driver = df.init_driver(prop);//call by ref prop, its return threadlocal driver so store it in driver and so above create Webdriver class ref
		loginPage = new LoginPage(driver);
		softAssert= new SoftAssert();
	}
@Step("tearDown:closing browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
