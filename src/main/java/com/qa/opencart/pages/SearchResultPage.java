package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
//verify that same product name display over there or not
    // Verify how many images(thumbnail) are there:1 Ul-->4 Li-->inside every Li 1
    // image so create cssSelector
    // verify description paragraph by creating cssSelector

    // maintain variable
    private WebDriver driver;
    private ElementUtil eleUtil;

    // By locators

    private By searchProducts = By.xpath("//div[@id='content']//h4");


    // constr.
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }
public int getSearchProductCount(){

        return eleUtil.waitForElementsVisible(searchProducts,10).size();
    }

//on this page i have to select Macbook and click so i will land on next page productInfoPage so return here the obj of landing page class
    //so i need to create by locator of MacBook

    public ProductInfoPage selectProduct(String productName){
        System.out.println("searching for product:"+productName);
    eleUtil.waitForElementVisible(By.linkText(productName),20).click();
    return new ProductInfoPage(driver);
    }
}
