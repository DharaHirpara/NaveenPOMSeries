package com.qa.opencart.tests;

import java.util.Random;

import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.StringUtil;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;


public class RegisterTest extends BaseTest {

    @BeforeClass//pre-condition is that on login page there is register link click on it
    public void regSetup() {
        registrationPage = loginPage.navigateToRegisterPage();
    }


    /*public String getRandomEmailId() {//i have created this method separately in StringUtil class
        Random random = new Random();
        String email = "automation"+random.nextInt(1000)+"@gmail.com";
        return email;
    }*/
    @DataProvider//rerurn type of data provider is 2 dimention Object[][]
    public Object[][] getRegisterTestDataFromExcel() {//call static method with class name of excelUtil which is responsible for read data from excel
        ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
        return new Object[0][];
    }

    //or
    @DataProvider(name="csvregisterData")//rerurn type of data provider is 2 dimention Object[][]
    public Object[][] getRegisterTestDataFromCSV() {//call static method with class name of excelUtil which is responsible for read data from excel
        return CSVUtil.csvData(Constants.REGISTER_SHEET_NAME);

    }

    //or
    @DataProvider//i can give here data provider name and then apply to @Test
    public Object[][] getRegisterTestData() {
        return new Object[][]{
                {"Dhara", "Patel", "1230452", "Radha@123.", "yes"},//i dont need to write email id ,it will generate automatic
                {"radha", "Patil", "12304562", "Radha@23.", "no"},
                {"shilpa", "Pate", "1230562", "Radha@12.", "yes"},

        };
    }
@Step("checking user resiter")
    @Test(dataProvider = "getRegisterTestData")//you can give here data provider method name or data provider name
    public void userRegisterTest(String firstname, String lastname, String telephone, String password, String subscribe) {

        Assert.assertTrue(registrationPage.userRegister(firstname, lastname, StringUtil.getRandomEmailId(), telephone, password, subscribe));

    }


//	@BeforeClass
//	public void regSetUp() {
//		regPage = loginPage.navigateToRegisterPage();// navigateToRegisterPage will return re.page class obj so store it
//														// in reg.page class reference
//		// Where i have created this ref?Base test
//	}
//
//	//utility special for email id bcoz once i have use one email id for register then next time it will shows during register that you account has already register
//	//so every time use diff.email id
//	public String getRandomEmailId() {
//		Random random=new Random();//random is class from java will create random number
//	String email="automation"+random.nextInt(1000)+"@gmail.com";//just change number of email id,max number 1000
//		return email;
//
//		}
//
//	@DataProvider
//	public Object[][] getRegisterTestData() {//Maintain excel sheet and provide data here from excel,use microsoft excel,this excel sheet should be
//		//part of my project so paste it  in testdata class
//    Object regData[][]=ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);//pass here sheetName but i don't want to supply hard data so create constants
//    return regData;
//	}
//
//	@Test(dataProvider="getRegisterTestData")//its called mapping
//	public void userRegisterTest(String firstName, String lastName, String telephone, String password,String subscribe) {//passe here 6 parameter bcoz we have 6 row
//		// I Want to register for multiple user for that i need to create
//									// @DataProvider but now instead of @DataProvider i want to provide data in
//									// Excel sheet and create static utility method in  excelUtil class and this method will convert excel sheet to 2D array and @DataProvider
//		//will get this 2d array by calling util method by class name bcoz its static
//		// i need to call page class method
//		Assert.assertTrue(regPage.userRegister( firstName,  lastName,  getRandomEmailId() ,  telephone,  password, subscribe));
//
//	}

}
