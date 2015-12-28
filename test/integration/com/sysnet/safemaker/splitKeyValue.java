package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.sysnet.helper.SeleniumHelper;

public class splitKeyValue {
	
	private static String profileKey;
	private static String keyValue;
	private WebDriver driver;
	private String propertyfilepath;
	private Properties clientProps;
	private Sheet saqASheet;
	private int lastRow;
	private Object currentScenario;
	private Row sRow;
	private Cell cell;



	@Before
	public void setup() throws Exception{
		propertyfilepath="test/integration/aibms.properties";
		clientProps = new Properties();
		FileInputStream locatorStream = new FileInputStream(propertyfilepath);
		clientProps.load(locatorStream);
		SeleniumHelper sh=new SeleniumHelper(driver, clientProps);
		saqASheet=sh.readExcelFile("test/integration/aibms/Profile.xlsx", "SAQ type A");
		
	}
	
	@Test
	public void getKeyValuePair() throws Exception{
		lastRow=saqASheet.getLastRowNum();
		for(int scenario=1;scenario<=lastRow; ){
			sRow=saqASheet.getRow(scenario);
			currentScenario=sRow.getCell(0).toString();
			if(currentScenario!=null){
				
				System.out.println(currentScenario);
				
				for (int screenNumber = 1; screenNumber < sRow.getLastCellNum(); screenNumber++) {
					cell=sRow.getCell(screenNumber);
					if(cell!=null){
						
								Profile.splitLine(sRow.getCell(screenNumber).getStringCellValue(), driver, clientProps);
						System.out.println(sRow.getCell(screenNumber).getStringCellValue());
						Thread.sleep(300);
						System.out.println("\n");
						
						
					}else{
						
					}
					
					
				}
				
			}
			
			scenario++;
		}
	}

	
	

}
