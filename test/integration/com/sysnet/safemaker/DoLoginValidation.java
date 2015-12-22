package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginValidation;

public class DoLoginValidation {
	
	private String propertyfilepath;
	private Properties clientProps;
	private String baseUrl;
	private String loginpageUrlSufix;
	private Sheet merchantFile;
	private int count;
	private String url;
	private WebDriver driver;

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
		System.out.println(count);
		
		
	}
	
	@Test
	public void MerchantLoginValidation() throws Exception
	{
	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
	//	Thread.sleep(5000);
		LoginValidation lv = new LoginValidation(driver, clientProps);
		lv.blankCredentials();
		Thread.sleep(500);
		lv.blankUserInvalidPassword();
		Thread.sleep(500);
		lv.invalidUserValidPassword();
		Thread.sleep(500);
		lv.validUserInvalidPassword();
	}
	

}
