package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.OnlineSaqAttest;

public class DoSAQAttest {
	private Properties clientProps;
	private String loginpageUrlSufix;
	private WebDriver driver;
	private String propertyfilepath;
	private String url;
	private Object baseUrl;
	private Sheet merchantFile;
	private int count;
	private Row midRow;
	
	public final Logger log = Logger.getLogger(DoSAQAttest.class.getClass());
	private String username;
	private String password;
	private SeleniumHelper sh;

	
	@Before
	public void SetUp() throws Exception {
		
			//PropertyConfigurator.configure("log4j.properties");
			propertyfilepath = "test/integration/aibms.properties";
			clientProps = new Properties();
			FileInputStream locatorStream = new FileInputStream(propertyfilepath);
			clientProps.load(locatorStream);
			baseUrl = clientProps.getProperty("baseUrl");
			loginpageUrlSufix = clientProps.getProperty("login.url.suffix");
			url = baseUrl + loginpageUrlSufix;

	merchantFile = sh.readExcelFile("test/integration/TestAccs.xlsx", "Status");
			count = merchantFile.getLastRowNum();
			System.out.println(count);
		}

	@Test
	public void getSaqAttest() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(500);
		for (int i = 1; i <= count; i++) {
			midRow = merchantFile.getRow(i);
			username = midRow.getCell(6).toString();
			password = "Sysnet12";
			//password = "g1tmast3r";	
			LoginPage lpage = new LoginPage(driver, clientProps);
			lpage.LoginUser(username, password);
			Thread.sleep(500);
			OnlineSaqAttest olp = new OnlineSaqAttest(driver, clientProps);
			olp.saqCompliant();
			
			
		}

	}



}
