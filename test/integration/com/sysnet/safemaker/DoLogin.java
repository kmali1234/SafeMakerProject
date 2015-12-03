package com.sysnet.safemaker;


	import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.pageobjects.LoginPage;


//import com.sun.java.util.jar.pack.Package.File;

	public class DoLogin {
		
		private Properties clientProps;
		private String loginpageUrlSufix;
		private WebDriver driver;
		private String username;
		private String propertyfilepath;
		private Properties userprops;
		private String password;
		private String url;
		private String propertyfilepath1;
		private Object baseUrl;


		@Before
		public void SetUp() throws Exception
		{
			
			
			propertyfilepath="test/integration/sysnetuslocators.properties";
			clientProps = new Properties();
			FileInputStream locatorStream = new FileInputStream(propertyfilepath);
			clientProps.load(locatorStream);
			baseUrl=clientProps.getProperty("baseUrl");
			loginpageUrlSufix=clientProps.getProperty("login.url.suffix");
			url = baseUrl+loginpageUrlSufix;
			
			
			propertyfilepath1= "test/integration/objects.properties";
			userprops = new Properties();
			FileInputStream UserLocators = new FileInputStream(propertyfilepath1);
			userprops.load(UserLocators);
			username = userprops.getProperty("username");
			password = userprops.getProperty("password"); 
			
			
		}
		
		
		@Test
		public void MerchantLogin() throws Exception
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(url);
			LoginPage lp = new LoginPage(driver, clientProps);
			lp.LoginUser(username, password);
			// take the screenshot at the end of every test
	        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        // now save the screenshto to a file some place
	        FileUtils.copyFile(scrFile, new java.io.File("ComplianceProject/Screenshots"));
	        
		}
		
		
		
		@After
		public void Quit()
		{
			 driver.close();
			 
		}
		
		
}


