package com.qa.opencart.pages;


import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
//verify that same product name display over there or not
    // Verify how many images(thumbnail) are there:1 Ul-->4 Li-->inside every Li 1
    // image so create cssSelector
    // verify description paragraph by creating cssSelector

    //  maintain variable
    private WebDriver driver;
    private ElementUtil eleUtil;

    // By locators
    private By mainProductName = By.cssSelector("div#content h1");
    private By productImages = By.xpath("//ul[@class='thumbnails']//img");
    private By productDescription = By.cssSelector("div#tab-description");
    private By productMetaData = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[1]/li");//4 data
    private By productPriceData = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[2]/li");
    private By productQuantity = By.xpath("//input[@id='input-quantity']");
    private By addToCart=By.xpath("//button[@id='button-cart']");
    private By successMesg=By.xpath("//div[contains(text(),'Success: You have added ')]");
    private By shoppingCart=By.xpath("//span[normalize-space()='Shopping Cart']");
    // constr.
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    // methods and return something from every method bcoz on the basis of  return(actual result) vs expected test class will perform assertion
    public String getMainProductName() {
        String name = eleUtil.doGetElementText(mainProductName);
        System.out.println("MainProductName is:" + name);
        return name;
    }

    public int getProductImagesCount() {
        int imageSize = eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_WAIT_TIME_OUT).size();
        System.out.println("ProductImagesCount is:" + imageSize);
        return imageSize;
    }

    public String getProductDescription() {

        return eleUtil.doGetElementText(productDescription);
    }
    //top casting:Map parent interface,HashMap
    Map<String, String> productInfoMap = new HashMap<String, String>();//HashMap does not maintain index
    //Map<String, String> productInfoMap = new LinkedHashMap<String, String>();//LinkedHashMap will maintain index
    //Map<String, String> productInfoMap = new TreeMap<String, String>();//TreeMap will maintain sorted order on the basis of key first capital letter value will come first then small,then numeric
//if key=null then null will go at first position in HashMap
    private void getProductMetaData() {
        //metadata-->means key-value data -->inspect-->ul and inside ul-->4
        // li-->xpath-->maintaiín by locator and create HashMap
        // 1.Brand:Apple-->what is right collection to store this data,array ,arraylist or HashMap?A:HashMap,why?bcoz key-value
        // brand index 0 and apple index 1
        // 2.Product Code:Product 17-->0,1
        // 3.Reward Points:700-->0,1
        // 4.Availability:In stock-->0,1
        List<WebElement> metaList = eleUtil.getElements(productMetaData);
        for (WebElement e : metaList) {
            String metaDataText = e.getText();
            String metaKey = metaDataText.split(":")[0].trim();// split always return string[],so spit and give o key and remove space
            // split or segregate key and value by : and key on 0 and value on 1 position and trim if space after before key and value
            String metaVal = metaDataText.split(":")[1].trim();//Apple

            productInfoMap.put(metaKey, metaVal);// when for loop is done my HashMap will fill with MetaData
        }
    }
    private void getPriceMetaData(){
//price data--> means price data-->maintain By locator
        // 1.$1,202.00-->index 0
        // 2.Ex Tax:$1,000.00-->index 1
        List<WebElement> priceList = eleUtil.getElements(productPriceData);
        String price = priceList.get(0).getText().trim();//$1,202.00-->index 0
        String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();//Ex Tax:$1,000.00-->index 1
        productInfoMap.put("price", price);// when i dont have key then i can make custom key
        productInfoMap.put("exTaxPrice", exTaxPrice);//created own key
    }
    public Map<String, String> getMainProductNameMetaData(){
        productInfoMap.put("name", getMainProductName());
        getProductMetaData();
        getPriceMetaData();
        return productInfoMap;
    }

    public String addProduct(String quantity){
        eleUtil.doSendKeys(productQuantity,quantity);
        eleUtil.doClick(addToCart);
        return  eleUtil.waitForElementVisible(successMesg,10).getText();

    }
    public ShoppingCartPage clickOnShoppingCart(){
        eleUtil.waitForElementVisible(shoppingCart,10).click();

        return new ShoppingCartPage(driver);
    }
}


