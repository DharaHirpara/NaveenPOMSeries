//package com.qa.opencart.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import com.qa.opencart.utils.Constants;
//import com.qa.opencart.utils.ElementUtil;
//
//public class CommonElementsPage {
//
//	//maintain variable
//	private WebDriver driver;
//	private ElementUtil eleUtil;
//
//	//By locators
//	private By search = By.name("search");
//	private By searchIcon = By.cssSelector("div#search button");
//	
//	//constr.
//		public CommonElementsPage(WebDriver driver) {
//			this.driver = driver;
//			eleUtil=new ElementUtil(this.driver);
//		}
//	
//		//methods and return something from every method bcoz on the basis of  return(actual result) vs expected test class will perform assertion
//		public SearchResultsPage doSearch(String productName) {
//		WebElement searchEle=	eleUtil.waitForElementVisible(search,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT );
//		searchEle.clear();
//		searchEle.sendKeys(productName);
//			eleUtil.doClick(search);//after click on search i landed on results page so this method has to return next landing page class obj
//			return new SearchResultsPage(this.driver);
//		}
//}
