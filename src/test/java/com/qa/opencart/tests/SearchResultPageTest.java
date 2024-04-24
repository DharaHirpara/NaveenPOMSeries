package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchResultPageTest extends BaseTest {

    //pre-condition is doLogin ,do search then so i will land on this page
    @BeforeClass
    public void searchResultpageSetUp(){//loginPage and accPage ref get from parent basetest
        accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
     //   searchPage= accPage.doSearch("MacBook");//if i want click on others product also so dont write here do search
        //instead of dosearch macbook here make seperate test for each product and later i will parameterized
        // all search product and will use @DataProvider so i can not parameterized at class level so make
        // doSearch product method at test level not at class level
    }
    @DataProvider
    public Object[][] getProductCountData(){
        return new Object[][]{//here 3 row,2 column
            {"MacBook",3},
            {"iMac",1},
            {"Samsung",2},
        };
    }
    @Test(dataProvider ="getProductCountData")
    public void SearchProductCountTest(String searchKey,int productCount) {//holding parameter is 2
      //so now this method will get data from DataProvider 3 times so this method will run 3 times
        searchPage=accPage.doSearch(searchKey);
        int actSize=searchPage.getSearchProductCount();
        Assert.assertEquals(actSize,productCount);
    }

@Test
    public void selectProductTest(){
    searchPage=accPage.doSearch("MacBook");
    productPage= searchPage.selectProduct("MacBook");

}





}
