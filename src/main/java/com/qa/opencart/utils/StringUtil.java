package com.qa.opencart.utils;

public class StringUtil {
    public static String getRandomEmailId() {//its static so if i want to use in other class i dont need to create obj
        String emailId = "decbatch" + System.currentTimeMillis() + "@opencart.com";
        return emailId;
    }
}
