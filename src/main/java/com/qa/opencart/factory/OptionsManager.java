package com.qa.opencart.factory;

import java.util.Properties;

import com.qa.opencart.logger.Log;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {// this class is special for headless and incognito function
	// this both we have written in config.prop class
	// so here for read properties from config.prop class we need prop obj refe

	private Properties prop;// Properties is class from java
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

//create constr.	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	// create method
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();//intialize ChromeOptions
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {// "headless" this string value will give me "true" so i can not write "true"in if
			// condi.bcoz if only accept boolean value  so covert string to booblean
			//for reading config prop in class always use trim() bcoz we can mistake to make space  in key and value format

			//System.out.println("Running chrome in headless mode");
			Log.info("Running chrome in headless mode");

			// co.setHeadless(true); --->error
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {// headless this string value i can not write in if
																	// condi.bcoz if only accept boolean value
			Log.info("Running chrome in incognito mode");
			// so covert string to booblean
			co.addArguments("--incognito");
		}
		return co;
	}

	public FirefoxOptions getFireFoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {// headless this string value i can not write in if
																	// condi.bcoz if only accept boolean value  so covert string to booblean

			System.out.println("Running ff in headless mode");
			// co.setHeadless(true); --->error
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {// headless this string value i can not write in if
																	// condi.bcoz if only accept boolean value
			// so covert string to booblean

			fo.addArguments("--incognito");
		}
		return fo;
	}
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {// headless this string value i can not write in if
			// condi.bcoz if only accept boolean value  so covert string to booblean

			// co.setHeadless(true); --->error
			System.out.println("Running edge in headless mode");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {// headless this string value i can not write in if
			// condi.bcoz if only accept boolean value
			// so covert string to booblean
			eo.addArguments("--inprivate");
		}
		return eo;
	}

}
