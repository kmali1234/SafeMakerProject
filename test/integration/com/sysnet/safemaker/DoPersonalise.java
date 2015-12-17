package com.sysnet.safemaker;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import sun.util.logging.resources.logging;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.LogoutPage;
import com.sysnet.pageobjects.PersonalisePage;



	//import com.sun.java.util.jar.pack.Package.File;

		public class DoPersonalise {
			
			private Properties clientProps;
			private String loginpageUrlSufix;
			private WebDriver driver;
			private String username;
			private String propertyfilepath;
			private String password;
			private String url;
			private Object baseUrl;
			
			private Sheet merchantFile;
			private int count;
			private Row midRow;
			private String expUsername;
		
			private final Logger log = Logger.getLogger(DoLogin.class.getClass());
			
			@Before
			public void SetUp() throws Exception
			{
				
				
				propertyfilepath="test/integration/aibms.properties";
				clientProps = new Properties();
				FileInputStream locatorStream = new FileInputStream(propertyfilepath);
				clientProps.load(locatorStream);
				baseUrl=clientProps.getProperty("baseUrl");
				loginpageUrlSufix=clientProps.getProperty("login.url.suffix");
				url = baseUrl+loginpageUrlSufix;
				
		
				merchantFile = SeleniumHelper.readExcelFile("test/integration/TestAccs.xlsx", "Status");
				count = merchantFile.getLastRowNum();
		        
				
				
			}
			
			
			@Test
			public void getPersonalise() throws Exception
			{
				
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.get(url);
				for(int i=1; i<=count; i++){
					midRow = merchantFile.getRow(i);
					username = midRow.getCell(0).toString();
					password = "Sysnet12";
					expUsername = "test"+username;
					
				
				Thread.sleep(500);	
				LoginPage lp = new LoginPage(driver, clientProps);
				lp.LoginUser(username, password);
				log.info(username+" logged in sucessufully");
				PersonalisePage pp = new PersonalisePage(driver, clientProps);
				pp.personaliseMerchant(expUsername);
				log.info(expUsername+" personalised sucessfully");
				
				// take the screenshot at the end of every test
		        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        // now save the screenshto to a file some place
		        FileUtils.copyFile(scrFile, new java.io.File("ComplianceProject/Screenshots"));
//		        Assertions pers = new Assertions(driver, clientProps);
//		        pers.assertTitle("Personalise");
		        Thread.sleep(3000);
		        LogoutPage lop = new LogoutPage(driver, clientProps);
		        lop.userLogout();
		        // driver.switchTo().alert().accept();
		        
		        
				}
		        
		        
		        
			}

			
			@After
			public void Quit()
			{
				 driver.close();
				 
			}
			
			


	}

