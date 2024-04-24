package com.qa.opencart.utils;


import java.util.Arrays;
import java.util.List;

public class Constants {
//create final variable here

	public static final String LOGIN_PAGE_TITLE = "Account Login";// this string value comes from LoginPageTest so call
																	// this variable there by class name
	// bcoz this variable is static so no need to create obj and call directly by class name Constants

	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";// partial url
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";



	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";

	public static final List<String> ACCOUNTS_PAGE_SECTIONS_HEADER_LIST = Arrays.asList("My Account", "My Orders",
			"my Affiliate Account", "Newsletter");

	public static final String USER_LOGOUT_MESSAGE = "Account Logout";

	public static final int DEFAULT_ELEMENT_WAIT_TIME_OUT = 25;//for element
	public static final int DEFAULT_TIME_OUT = 15;//for non web element


	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";

	//***************sheet names**************************

	public static final String REGISTER_SHEET_NAME = "register";

	public static final String PRODUCT_SHEET_NAME = "Product";


    public static final String SHOPING_PAGE_TITLE = "Shopping Cart";
	public static final String SHOPPING_PAGE_URL_FRACTION = "route=checkout/cart";
}
