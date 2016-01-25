package com.sysnet.safemaker;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.LogoutPage;


//import com.sun.java.util.jar.pack.Package.File;

	public class DoLogin {
		
		private Properties clientProps;
		private String loginpageUrlSufix;
		private WebDriver driver;
		private String username;
		private String propertyfilepath;
		private String password;
		private String url;
		private Object baseUrl;
		private Sheet merchantSheet;
		private int count;
		private Row midRow;

		public final Logger log = Logger.getLogger(DoLogin.class.getClass());
		@Before
		public void SetUp() throws Exception
		{
			

			PropertyConfigurator.configure("log4j.properties");
			propertyfilepath="test/integration/aibms.properties";
			clientProps = new Properties();
			FileInputStream locatorStream = new FileInputStream(propertyfilepath);
			clientProps.load(locatorStream);
			baseUrl=clientProps.getProperty("baseUrl");
			loginpageUrlSufix=clientProps.getProperty("login.url.suffix");
			url = baseUrl+loginpageUrlSufix;
			
			
			SeleniumHelper sh= new SeleniumHelper(driver, clientProps);
			merchantSheet = sh.readExcelFile("test/integration/TestAccs.xlsx", "Status");
			count = merchantSheet.getLastRowNum();
			System.out.println(count);
			
			
		}
		
		
		@Test
		public void MerchantLogin() throws Exception
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			
			for(int i=1; i<=count; i++){
				midRow = merchantSheet.getRow(i);
				username = midRow.getCell(6).toString();
				password = "Sysnet12";
				
				
			LoginPage lp = new LoginPage(driver, clientProps);
			lp.LoginUser(username, password);
			log.info(username + "successfully logged in");
			
			// take the screenshot at the end of every test
	        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        // now save the screenshto to a file some place
	        FileUtils.copyFile(scrFile, new java.io.File("SafeMakerProject/Screenshots"));
	        LogoutPage lop = new LogoutPage(driver, clientProps);
	        lop.userLogout();
	        log.info(username + "successfully logged out");    
		}
	        
	}
		
		
		
		@After
		public void Quit()
		{
			 driver.close();
			 
		}
		
		
}


