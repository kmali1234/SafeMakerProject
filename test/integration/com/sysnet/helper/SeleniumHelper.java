package com.sysnet.helper;

import java.io.File;
import java.io.FileInputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumHelper {
	
	private WebDriver driver;

	public SeleniumHelper(WebDriver driver, Properties props){
		this.driver = driver;
	}
	
	public boolean isElementDisplayed(By by){
		driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
		if (isElementPresent(by)) {
		    if (driver.findElement(by).isDisplayed()) {
		        return true;
		    } else
		    
		        return false;
		}
		return false;
		}
	private boolean isElementPresent(By by) {
		/*This boolean method checks if the element passed to it is present on a page or not
		*The method creates a list of WebElements that match the "By" variable passed to it as an argument
		*If the size of this list is greater than 0, then the Element is present on the page.
		*Because Selenium WebDriver can take a while to search for an element, the call to create the list
		*times out after 2 seconds*/
		try {
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			return driver.findElements(by).size() > 0;
			
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public static Sheet readExcelFile(String filePath, String sheetName) throws Exception 
	{
		
		File f = new File(filePath);
		FileInputStream in = new FileInputStream(f);
		Workbook wrkBook = (Workbook)WorkbookFactory.create(in);
		Sheet merchantsheet= wrkBook.getSheet(sheetName);
		
		return merchantsheet;
		
	
	}

}


