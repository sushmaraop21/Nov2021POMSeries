package com.qa.opencart.utils;	

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static String TEST_DATA_SHEET=".\\src\\test\\Resources\\TestData\\DemocartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		
		Object data[][] = null; // the local var in java shud always be initialized with default value
		
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET);
		book=	WorkbookFactory.create(ip); // this workbook is a class coming from apache and create is a method
		sheet= book.getSheet(sheetName);
		
		//this is actually data=new Object[4][6]- v r using sheet method
		data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				
			}
		}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
}
