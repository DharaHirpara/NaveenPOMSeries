package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.opencart.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.customexception.FrameworkException;
import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {
	public static String highlight;// init prop and driver

	WebDriver driver;
    Properties prop;//this class is responsible for reading data from properties
OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// ThreadLocal has 2 method set and get-->set for:set driver--->get for:get
	// local copy of driver
	/**
	 * This method is used to launch browser or initialize driver on the basis of
	 * given browser name
	 * 
	 * 
	 * @return this return driver
	 */
	

	public WebDriver init_driver(Properties prop) {//init_driver method says give me browser so i can launch,but browser name present in properties class for give
		//ref of it and read
		String browserName=prop.getProperty("browser");//which properties you want to read supply key here:in config.prop file key and value(v update from .xml) pair is present,
		//so this method will return chrome so store inside variable

	//	System.out.println("browser name is: " + browserName);//browser get initialize then i have to tell them plese run on headless or incognito mode
		//so for that OptionsManager class is responsible to read prop from config.prop so create obj og that class to call method

		Log.info("Browser name is: " + browserName);

		highlight=prop.getProperty("highligjht");

		optionsManager = new OptionsManager(prop);//optionsManager please read prop from config.prop,

		switch (browserName.trim().toLowerCase()) {
			//for reading config prop in class always use trim() bcoz we can mistake to make space  in key and value format
		case "chrome":
			//driver=new ChromeDriver(optionsManager.getChromeOptions());//i have to supply here co but how?call method of OptionsManager class for headless
			//and incognito mode
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));//give me webDriver value=new ChromeDriver(optionsManager.getChromeOptions()
			break;

		case "firefox":
			//driver=new FirefoxDriver(optionsManager.getFireFoxOptions());//old way
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			break;

		case "edge":
			//driver=new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		case "safari":
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			//System.out.println("plz pass right browser"+browserName);
			Log.error("plz pass right browser"+browserName);
			throw new BrowserException("No BROWSER FOUND"+browserName);
			
		}
		getDriver().manage().deleteAllCookies();// give local copy of webDriver so use getDriver instead of driver
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));//which properties you want to read supply key here:in config.prop file key and value pair is present,
		//so this method will return url so it wull be like driver.get(url)
		return getDriver();
	}
public static WebDriver getDriver(){
		return tlDriver.get();//give me local copy of driver and i will return it
}
	public Properties init_prop() {// this method for read the config.properties file
		FileInputStream ip = null;//initially its null
		prop = new Properties();// create obj of Properties class for load value of config.properties
		// maven commandline argument
		// mvn clean install -Denv="qa"-->env is variable name,maven will pass Denv-->to
		// script-->at runtime it will read getProperty method

		String envName = System.getProperty("env");// if envName=null,actually i will pass envName from command promt
		System.out.println("Running tests on environment:" + envName);
		// what if i dont pass any value here -Denv=""-->null,so put condition pass it
		// by default in qa envi.
		if (envName == null) {//if someone not give the env
			System.out.println("no envi is given....hence running it on QA envi");
			try {
				ip = new FileInputStream("./src/test/resourses/config/qa.config.properties");// ny default run on qa
				// envi or i can also
				// terminate
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {// write here multi envi logic
			try {
				switch (envName.toLowerCase()) {// bcoz user can pass qa,Qa,QA so below in case i have to maintain
					// lowercase
					case "qa":
						ip = new FileInputStream("./src/test/resourses/config/qa.config.properties");// initialize qa envi
						break;
					case "dev":
						ip = new FileInputStream("./src/test/resourses/config/dev.config.properties");
						break;
					case "stage":
						ip = new FileInputStream("./src/test/resourses/config/stage.config.properties");
						break;
					case "uat":
						ip = new FileInputStream("./src/test/resourses/config/uat.config.properties");
						break;
					case "prod":
						ip = new FileInputStream("./src/test/resourses/config/prod.config.properties");
						break;

					default:
						System.out.println("please pass the right envi.value.." + envName);
						throw new FrameworkException("No environment found..");//FrameworkException custom exception with mesg

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FrameworkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;// prop is Properties class reference so write Properties instead of void
	}

	/**
	 * take screenshot,this method provide by selenium,but for take screenshot there
	 * is specific interface TakesScreenshot is available
	 *
	 * @return String
	 */
	public String getScreenshot() {

//step 1:take screenshot
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// getScreenshotAs supply path
		// of screen shot,OutputType is
		// a class,
		// file:type of screenshot,getScreenshotAs will return tempory file class
		// obj,this temp file path store inside srcFile
		// but what if i want to store this file in my project not in temporary file then
		// create path and pass this path to destination
//create string path:
		String path = "./screenshot/" + System.currentTimeMillis() + ".png";
		// ./-->you take the screen shot at root of project
		// screenshot-->and create screenshot folder
		// System.currentTimeMillis()--->take the screen shot with latest time,delete
		// old one,dont override previous screenshot
		// .png--->here i can also use.jpg
//create destination:
		File destination = new File(path);
//move screenshot from srcFile to destination
		try {
			FileHandler.copy(srcFile, destination);// supply srcFile and destination,FileHandler is class,copy is method
			// method
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
