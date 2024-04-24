package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
//import com.qa.opencart.pages.CommonElementsPage;

public class ProductInfoTest extends BaseTest{
	// write test case for accountPage class so first login then it will works
	// so precondition is login

	@BeforeClass
	public void productInfoSetup() {// precondition:login,dosearch,selectProduct
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}
	@DataProvider
	public Object[][] mainProductNameData(){
		return new Object[][]{
				{"MacBook","MacBook Pro"},
				{"imac","iMac"},
				{"Samsung","Samsung Galaxy Tab 10.1"},
				{"Samsung","Samsung SyncMaster 941BW"}
		};
	}


@Test(dataProvider = "mainProductNameData",priority=1)//AAA pattern in all test cases
	public void mainProductNameTest(String searchKey,String productName){
	searchPage=accPage.doSearch(searchKey);// precondition:dosearch,arrange
	productPage=searchPage.selectProduct(productName);// precondition:selectProduct//arrange
	String actName=productPage.getMainProductName();//act
	Assert.assertEquals(actName,productName);//assertion
}
	@DataProvider
	public Object[][] productImagesCountData(){
		return new Object[][]{
				{"MacBook","MacBook Pro",4},
				{"imac","iMac",3},
				{"Samsung","Samsung Galaxy Tab 10.1",7},
				{"Samsung","Samsung SyncMaster 941BW",1}
		};
	}

@Test(dataProvider = "productImagesCountData",priority=2)
	public void ProductImagesCountTest(String searchKey,String productName,int productImageCount){
	searchPage=accPage.doSearch(searchKey);//// precondition:dosearch
	productPage=searchPage.selectProduct(productName);// precondition:selectProduct
	int actImageCount=productPage.getProductImagesCount();
	Assert.assertEquals(actImageCount,productImageCount);
}
	@DataProvider
	public Object[][] ProductDescriptionData(){
		return new Object[][]{
				{"MacBook","MacBook Pro","MacBook Air is ultrathin"},
				{"imac","iMac","More powerful Intel Core 2 Duo "},
				{"Samsung","Samsung Galaxy Tab 10.1","WXGA capacitive touch screen with 1280 x 800 pixels "},
				{"Samsung","Samsung SyncMaster 941BW","Imagine the advantages of going big"}
		};
	}
@Test(dataProvider = "ProductDescriptionData")
	public void ProductDescriptionTest(String searchKey,String productName,String description){//AAA pattern
	searchPage=accPage.doSearch(searchKey);// precondition:dosearch
	productPage=searchPage.selectProduct(productName);// precondition:selectProduct
	String actDescription=productPage.getProductDescription();
	Assert.assertTrue(true,description);
}
	@DataProvider
	public Object[][] MainProductNameMetaData(){
		return new Object[][]{
				{"MacBook","MacBook Pro"},
				{"imac","iMac"},
				{"Samsung","Samsung Galaxy Tab 10.1"},
				{"Samsung","Samsung SyncMaster 941BW"}
		};
	}
@Test(dataProvider = "MainProductNameMetaData")
	public void MainProductNameMetaDataTest(String searchKey,String productName){
	searchPage=accPage.doSearch(searchKey);// precondition:dosearch
	productPage=searchPage.selectProduct(productName);// precondition:selectProduct
	Map<String, String> actMetaData=productPage.getMainProductNameMetaData();
	//this is hard assertion-->first assertion will fail then it will not execute left ones
	softAssert.assertEquals(actMetaData.get("Brand"),"Apple");//get(object key),expected:value
	softAssert.assertEquals(actMetaData.get("Product Code"),"Product 18");//get(object key),expected:value
	softAssert.assertEquals(actMetaData.get("exTaxPrice"),"$2,000.00");//get(object key),expected:value
	softAssert.assertEquals(actMetaData.get("Availability"),"In Stock");//get(object key),expected:value
	softAssert.assertAll();//assertAll will tell me which is fail and pass,so mandatory to write
}
	@DataProvider
	public Object[][] addProductData(){
		return new Object[][]{
				{"MacBook","MacBook Pro","1","Success: You have added "},
				{"imac","iMac","1","Success: You have added "},
				{"Samsung","Samsung Galaxy Tab 10.1","1","Success: You have added "},
				{"Samsung","Samsung SyncMaster 941BW","1","Success: You have added "}
		};
	}

	@Test(dataProvider = "addProductData")
	public void addProductTest(String searchKey,String productName,String  quantity,String mesg){
	searchPage=accPage.doSearch(searchKey);//// precondition:dosearch
	productPage=searchPage.selectProduct(productName);
	String ActText=productPage.addProduct(quantity);
	Assert.assertTrue(true,mesg);
}
	@DataProvider
	public Object[][] clickOnShoppingCartData(){
		return new Object[][]{
				{"MacBook","MacBook Pro"},
				{"imac","iMac"},
				{"Samsung","Samsung Galaxy Tab 10.1"},
				{"Samsung","Samsung SyncMaster 941BW"}
		};
	}
	@Test(dataProvider = "clickOnShoppingCartData")
	public void clickOnShoppingCartTest(String searchKey,String productName){
	searchPage=accPage.doSearch(searchKey);//// precondition:dosearch
	productPage=searchPage.selectProduct(productName);
	productPage.clickOnShoppingCart();
}

	/*@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "MacBook", "MacBook Pro", "4" }, // here maintained 2 column 3 rows
				{ "iMac", "MacBook Air", "4" }, { "Samsung", "Samsung SyncMaster 941BW", "1" }, };
	}

	@Test(dataProvider = "getProductData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		commPage = new CommonElementsPage(driver);
		searchResultsPage = commPage.doSearch(searchKey);// but product name could be anything so create dataprovider

		productInfoPage = searchResultsPage.selectProductName(productName);// call selectproductName method from
																			// SearchResultsPage by using
																			// ref.variable searchResultsPage
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
	}*/

	/*@Test
	public void productDescriptionTest() {
		searchResultsPage = commPage.doSearch("MacBook");// but product name could be anything so create dataprovider

		productInfoPage = searchResultsPage.selectProductName("MacBook Air");// call selectproductName method from
																				// SearchResultsPage by using
																				// ref.variable searchResultsPage
	String 	productDesc=productInfoPage.getProductDescription();//verify this below
		System.out.println("product description:"+productDesc);

		//for verify product description i can not use constants bcoz desc.might be change
		//but if you want to create constants then create separate constants:DescriptionConstants
		//so at better way i can just check that description should not null,empty and it should contain product name atleast
//Assert.assertTrue( productDesc!=null);//i am expecting here assertTrue,if this fail then it will not check next all assertion its called hard assertion,
		//Assert it self hard assertion class of TestNg
		//so write concept of soft assertion in that 1st assertion got fail even if it will execute next assertion
		//SoftAssert is class of TestNg so create ref.variable in BaseTest class
//Assert.assertFalse( productDesc.isEmpty());//i am expecting here assertFalse
//Assert.assertTrue( productDesc.contains("MacBook Air"));

		softAssert.assertTrue( productDesc!=null);
		softAssert.assertFalse( productDesc.isEmpty());
		softAssert.assertTrue( productDesc.contains("MacBook Air"));
		softAssert.assertAll();//this assertion is mandatory bcoz it will give report how many assertion got  failed and pass
	}*/
		/*@Test
		public void productDataTest() {
			//precondition doSearch
			searchResultsPage = commPage.doSearch("MacBook");// but product name could be anything so create dataprovider

			productInfoPage = searchResultsPage.selectProductName("MacBook Air");
			Map<String, String> actProductInfoMap=	productInfoPage.getProductInfo();

			actProductInfoMap.forEach((k,v)->System.out.println(k+":"+v));//result will not maintain order bcoz it will calculate HashCode and we dont need to bother about order just fatch value
		//HashMap
			//Brand:Apple
			//Availability:In stock
			//price:$1,202.00
			//Ex Tax:$1,000.00
			//name:MacBook Air
			//Product Code:Product 17
			//Reward Points:700

			softAssert.assertEquals(actProductInfoMap.get("Brand"),"Apple");//pass key will give value,so is actual data and value is expected data and check assertion is fail or pass
			softAssert.assertEquals(actProductInfoMap.get("Availability"),"In stock");//pass key will give value
			softAssert.assertEquals(actProductInfoMap.get("price"),"$1,202.00");//pass key will give value
			softAssert.assertEquals(actProductInfoMap.get("Ex Tax"),"$1,000.00");//pass key will give value
			softAssert.assertEquals(actProductInfoMap.get("name"),"MacBook Air");//pass key will give value
			softAssert.assertEquals(actProductInfoMap.get("Product Code"),"Product 17");//pass key will give value
			softAssert.assertEquals(actProductInfoMap.get("Reward Points"),"700");//pass key will give value
			softAssert.assertAll();



		}
*/
		//LikedHashMap:
//		name:MacBook Air
//		Brand:Apple
//		Product Code:Product 17
//		Reward Points:700
//		Availability:In Stock
//		price:$1,202.00
//		extaxprice:Ex Tax: $1,000.00

		//TreeMap:
//		Availability:In Stock
//		Brand:Apple
//		Product Code:Product 17
//		Reward Points:700
//		extaxprice:Ex Tax: $1,000.00
//		name:MacBook Air
//		price:$1,202.00





	}

