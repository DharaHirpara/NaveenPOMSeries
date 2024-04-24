package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static String TEST_DATA_SHEET_PATH = "./src/test/resourses/testdata/OpencartTestData1.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {//call this method from test class
System.out.println("reading test data from sheet"+sheetName);
		Object data[][] = null;//my target is return 2D array,so create 2d obj array which is pointing initially null

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);//make connection with this TEST_DATA_SHEET_PATH excel sheet,
			//give exception with try catch bcoz what if tomorrow file is not present 
			book = WorkbookFactory.create(ip);//WorkbookFactory class comes from apache poi,create method,it will create copy of excel file in java memory in form of workbook.
			//so store it in workBook and create class level ref of this workBook class
			sheet = book.getSheet(sheetName);//create global ref  of Workbook and make it static so i dont need to create obj,getSheet method give sheet name to them

			//Initialize static array with new keyword Object[represent rows=4 here][represent column/cellj=6 here]
			//no.of row and column  is different in every excel sheet to just catch last row num and column num, dont give hard core number
			//getRow means pass row no. so in excel sheet its 1st row but for Java it will be 0 index so goto to 0 index and give me column count
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			//create 2 for lop:outer and inner
			for (int i = 0; i < sheet.getLastRowNum(); i++) {//outer loop represent no.of rows(i),00,01,02..
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {//inner loop represent no.of columns(J)

					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();//start fill data in 2D array(convert excel sheet to 2D array)
					//i=0 pint to first row which contains first,last name...
					//but i want to capture data from 2nd row so write i+1(means 2nd row),j=0(means first column),toString will covert excel string to java string
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;//2D obj array

	}

//	private static String TEST_DATA_SHEET_PATH = "./src/test/resourses/testdata/OpencartTestData.xlsx";//. means from
//																										// current
//																										// project goto
//																										// src....
//	private static Workbook book;
//	private static Sheet sheet;
//
//	public static Object[][] getTestData(String sheetName) {// dheetName could be anything like register,login...,call this method in test class
//
//		Object data[][] = null;// 2D object array bcoz this is 2 dimension row and column
//
//		try {
//			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
//			book = WorkbookFactory.create(ip);// WorkbookFactory.create() class from apache and it will interact with
//												// workBook ip then it will reach inside excel sheet
////create method will return workbook class ref so create class level private static ref and store this in ref book
//			sheet=book.getSheet(sheetName);
//			//getSheet method will return sheet class ref so create class level private static ref and store this in ref sheet
//		
//			//how to initialize static array?with new keyword
//			data=new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//row column
//			//getLastRowNum() means-->give me no.og last row
//			//sheet.getRow(0) means--->for java its 0 column but for excel sheet its 1st column
//			//getLastCellNum() means--->so from o column give me last cell no.
//			for(int i=0; i<sheet.getLastRowNum(); i++) {
//			     for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
//			    	 
//			    	data[i][j] =sheet.getRow(i+1).getCell(j).toString();//toString(); means-->in excel sheet monika is string value of excel sheet and convert it to java string
//			    			//sheet.getRow(i+1) means-->i=0 means check sheet on 0 row there is yellow clr but we want to collect data from 1 so write i+1
//			    	 //i+j-->00,j++,i+j--->01,j++,i+j-->o2 inner loop will execute completely then once outerloop will execute then again complete loop so on..
//			    	 
//			     }	
//				}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (InvalidFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//       return data;
//	}

}
