package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.UploadScanScenario;

//import com.sun.java.util.jar.pack.Package.File;

public class DoUploadScanScenario {
	
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
	
	
	
	public final Logger log = Logger.getLogger(DoUploadScanScenario.class.getClass());
	private SeleniumHelper sh;

	@Before
	public void SetUp() throws Exception {
		
		propertyfilepath = "test/integration/aibms.properties";
		clientProps = new Properties();
		FileInputStream locatorStream = new FileInputStream(propertyfilepath);
		clientProps.load(locatorStream);
		baseUrl = clientProps.getProperty("baseUrl");
		loginpageUrlSufix = clientProps.getProperty("login.url.suffix");
		url = baseUrl + loginpageUrlSufix;
		sh =new SeleniumHelper(driver, clientProps);
		merchantFile = sh.readExcelFile("test/integration/TestAccs.xlsx", "SAQAEP");
		count = merchantFile.getLastRowNum();
		System.out.println(count);

	}

	@Test
	public void getScanComplaint() throws Exception  {
	
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(500);
		for (int i = 1; i <= count; i++) {
			midRow = merchantFile.getRow(i);
			username = midRow.getCell(6).toString();
			password = "Sysnet12";
					
			Thread.sleep(500);
			LoginPage lp = new LoginPage(driver, clientProps);
			lp.LoginUser(username, password);
			log.info(username + " logged in sucessufully");
			Thread.sleep(1000);
			UploadScanScenario upl = new UploadScanScenario(driver, clientProps);
			upl.doScanUpload();
		}
		
	}

	

}
