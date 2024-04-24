package com.qa.opencart.pages;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
private By unitPrice= By.xpath("//td[normalize-space()='Unit Price']");
private By checkOut=By.xpath("//a[@class='btn btn-primary']");
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getShoppingCartPageTitle() {
        String title = eleUtil.waitForTitleContains(Constants.SHOPING_PAGE_TITLE, 15);
        return title;
    }

    public String getShoppingcartPageURL() {
        String url = eleUtil.waitForURLContains(Constants.SHOPPING_PAGE_URL_FRACTION, 15);
        System.out.println("Shopping cartPageURL is:"+url);
        return url;
    }

        public String getShoppingcartPageUnitPrice () {
            String text=eleUtil.doGetElementText(unitPrice);
            return text;



        }
    }
