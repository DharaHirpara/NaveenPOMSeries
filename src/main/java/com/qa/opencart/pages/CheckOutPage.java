package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public CheckOutPage(WebDriver driver) {
        this.driver=driver;
        eleUtil=new ElementUtil(driver);
    }
}
