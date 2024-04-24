package com.qa.opencart.tests;


import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest  extends BaseTest {

	// write test case for accountPage class so first login then it will works
	// so precondition is login

	@BeforeClass
	public void accSetUp() {// precondition:login for land on this page(AccountsPage)
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		//i have taken this loginPage and prop ref from BaseTest(parents)in inheritance so i can call doLogin method
		//and take un and pass from config.prop,doLogin will return me AccountsPage class obj ref var so store in it
	}

	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageUrlTest() {
		String actAccURL=accPage.getAccPageUrl();
		Assert.assertTrue(actAccURL.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));
	}
	@Test
	public void iskkkLogoutlinkExistTest(){
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Test
	public void accPageSectionListTest() {
		List<String> actualAccSecList = accPage.getAccountsPageHeadersList();//will return list of string
		System.out.println("Actual Acc Page  Header Sections:" + actualAccSecList);
		/*Collections.sort(actualAccSecList);// if sec. list change there order then we can not get right result so this
											// will solve issue
		Collections.sort(Constants.ACCOUNTS_PAGE_SECTIONS_HEADER_LIST);
		Assert.assertEquals(actualAccSecList, Constants.ACCOUNTS_PAGE_SECTIONS_HEADER_LIST);*/

		// or
//		Collections.sort(actualAccSecList);
//		Collections.sort(Constants.ACCOUNTS_PAGE_SECTIONS_HEADER_LIST);
//		Assert.assertEquals(accPage.getAccountSectionsList(),Constants.ACCOUNTS_PAGE_SECTIONS_HEADER_LIST);
	}
@Test
	public void searchTest(){
	accPage.doSearch("MacBook");//this is test data, not constants
}
//	@Test
//	public void isUserLoggedOutTest() {// how can i validate that user logged out
//		// ans:check that user landed on login page, check Header title should be
//		// Account logout or check url
//		accPage.clickOnLogout();// call clickonLogout method,
//		Assert.assertEquals(loginPage.getLogoutMessage(), Constants.USER_LOGOUT_MESSAGE);
//	}
//
//	@DataProvider
//	public Object[][] getProductName() {
//		return new Object[][] { 
//			    { "MacBook" }, // here maintained 1 column 3 rows
//				{ "iMac" },
//				{ "Samsung" },
//				{ "Apple" } };
//	}
//
//	@Test(dataProvider = "productName")
//	public void searchTest(String productName) {
//		// precondition is doLogin
//		// i need to catch search box ,enter product name,click so for this call do
//		// Search of of commonElementsPage
//		commPage = new CommonElementsPage(driver);
//		searchResultsPage = commPage.doSearch(productName);// but product name could be anything so create dataprovider
//		String textResulPageHeader = searchResultsPage.getResultsPageHeader();
//		Assert.assertTrue(textResulPageHeader.contains(productName));// verify
//	}
//	@DataProvider
//	public Object[][] getProductData() {
////		return new Object[][] { 
////			    { "MacBook","MacBook Pro" }, // here maintained 2 column 3 rows
////				{ "iMac","MacBook Air"},
////				{ "Samsung","Samsung SyncMaster 941BW" },
////				};
//		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);//sheetName
//	}
//	@Test(dataProvider = "getProductData")
//	public void productInfoTest(String productName, String mainproductName) {
//		// precondition is doLogin
//		// i need to catch search box ,enter product name,click so for this call do
//		// Search of of commonElementsPage
//		commPage = new CommonElementsPage(driver);
//		searchResultsPage = commPage.doSearch(productName);// but product name could be anything so create dataprovider
//		
//		productInfoPage = searchResultsPage.selectProductName(mainproductName);// call selectproductName method from
//																				// SearchResultsPage by using
//																				// ref.variable searchResultsPage
//		String mainProductName = productInfoPage.getMainProductName();
//		System.out.println(mainproductName);
//		Assert.assertTrue(mainProductName.contains(mainproductName));// verify
//
	}

