package com.sysnet.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SeleniumHelper {
	
	private WebDriver driver;
	private Workbook wrkBook;
	
	

	public SeleniumHelper(WebDriver driver, Properties props){
		this.driver = driver;
	}
	
	public boolean isElementDisplayed(By by){
			//driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
			if (isElementPresent(by)) {
			    if (driver.findElement(by).isDisplayed()) {
			        return true;
			    } else
			    
			        return false;
			}
			return false;
		}
	public boolean isElementPresent(By by) {
		/*This boolean method checks if the element passed to it is present on a page or not
		*The method creates a list of WebElements that match the "By" variable passed to it as an argument
		*If the size of this list is greater than 0, then the Element is present on the page.
		*Because Selenium WebDriver can take a while to search for an element, the call to create the list
		*times out after 2 seconds*/
		try {
			//driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			return driver.findElements(by).size() > 0;
			
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public Sheet readExcelFile(String filePath, String sheetName) throws Exception 
	{
		
		File f = new File(filePath);
		FileInputStream in = new FileInputStream(f);
		wrkBook = (Workbook)WorkbookFactory.create(in);
		Sheet sheet= wrkBook.getSheet(sheetName);
		
		return sheet;
		
	
	}
	public boolean textCompare(String actValidMsg, String expValidMsg){
		
	
		if(actValidMsg.equals(expValidMsg))
		{
			System.out.println("Actual matches with expected, expected emial validation message is "+ expValidMsg);
		}else{
			System.out.println("Actuall Message: "+actValidMsg+" does not match with expected message: "+expValidMsg);
		} 
		return true;
	}

	public SeleniumHelper clickDialogIfDisplayed() {
		// TODO Auto-generated method stub
		if(isElementDisplayed(By.cssSelector("div.modal-dialog div.sgs-modal__footer button"))){
			driver.findElement(By.cssSelector("div.modal-dialog div.sgs-modal__footer button")).click();
	}
	return this;
		
	}

	

	public void writeExcelFile(String merchantfilepath, String sheetName) throws Exception {
		// TODO Auto-generated method stub
		FileOutputStream out = new FileOutputStream(merchantfilepath);
		wrkBook.write(out);
		out.close();
		
	}

	
	
	
}


