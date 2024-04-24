package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {//Homepage
//you are landed on 2rd page but check this actually you are landed on correct page or not?so check page title,url,header etc
	//maintain variable
	private WebDriver driver;
	private ElementUtil eleUtil;

	//By locators

	private By accountHeadersList = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon=By.cssSelector("div#search button");

	//constr.
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(this.driver);

	}

	//methods and return something from every method bcoz on the basis of  return(actual result) vs expected test class will perform assertion
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleContains(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	System.out.println("Account Page title is:" + title);
	return title;
	}

public String getAccPageUrl() {
		String url= eleUtil.waitForURLContains(Constants.ACCOUNT_PAGE_URL_FRACTION,15);
	System.out.println("Account Page URL is:" + url);
		return url;
	}
	public List<String> getAccountsPageHeadersList() {//will return list of string
	List<WebElement> headersList = eleUtil.waitForElementsVisible(accountHeadersList,Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT);

	List<String> accHeadersList = new ArrayList<String>();//Create blank list and add accSecList
	for (WebElement e : headersList) {
		String header = e.getText();
		accHeadersList.add(header);
	}
	return accHeadersList;
	}

public boolean isLogoutLinkExist() {
	return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).isDisplayed();//check isDisplayed or not
}
//
//	public LoginPage clickOnLogout() {
//		if (isLogoutLinkExist()) {
//			eleUtil.doClick(logoutLink);
//			//after click on logout user will landed on login page
//			//so its this method responsibility to create obj of next landing page class obj
//			return new LoginPage(driver);
//		}
//		return null;
//	}
public SearchResultPage doSearch(String searchKey){
eleUtil.doSendKeys(search,searchKey);
eleUtil.doClick(searchIcon);
return new SearchResultPage(driver);
}
//	public boolean issearchExist() {
//		return eleUtil.doIsDisplayed(search);//check displyed or not
//	}
//	
//	
//	
//	j
//	
//	
//	
}
