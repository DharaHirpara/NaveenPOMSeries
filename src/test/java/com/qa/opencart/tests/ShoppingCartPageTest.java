package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShoppingCartPageTest extends BaseTest {

    @BeforeClass
    public void shoppingCartPageSetUp(){
        accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

    }
    @Test
    public void shoppingCartPageTest(){
        searchPage=accPage.doSearch("MacBook");//precondition:doSearch
        productPage=searchPage.selectProduct("MacBook");
        shoppingCart=productPage.clickOnShoppingCart();
        String ActTitle=shoppingCart.getShoppingCartPageTitle();
        Assert.assertEquals(ActTitle,"Shopping Cart");
    }
@Test
    public void getShoppingCartPageURLTest(){
    searchPage=accPage.doSearch("MacBook");// precondition:doSearch
    productPage=searchPage.selectProduct("MacBook");
    shoppingCart=productPage.clickOnShoppingCart();
   String actUrl= shoppingCart.getShoppingcartPageURL();
   Assert.assertTrue(true,"route=checkout/cart");
}
@Test
    public void getShoppingcartPageUnitPriceTest(){
    searchPage=accPage.doSearch("MacBook");//precondition:doSearch
    productPage=searchPage.selectProduct("MacBook");
    shoppingCart=productPage.clickOnShoppingCart();
   String actText= shoppingCart.getShoppingcartPageUnitPrice();
   Assert.assertEquals(actText,"Unit Price");
}










}
