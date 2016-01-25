package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
//	public final Logger log = Logger.getLogger(DoSAQAttest.class.getClass());
	private String username;
	private String password;
	private Properties locators;
	private SeleniumHelper sh;
	private String merchantFilePath;
	private Workbook wrkBook1;
	private Sheet merchantSheet;
	private FileInputStream in;
	
	@Before
	public <merchantFile> void SetUp() throws Exception {
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
	public void getSaqAttest() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
		for(int i=1; i<=count; i++){
			midRow = merchantSheet.getRow(i);
			username = midRow.getCell(6).toString();
			password = "Sysnet12";
			
			LoginPage lp = new LoginPage(driver, clientProps);
			lp.LoginUser(username, password);
			Thread.sleep(1000);
			OnlineSaqAttest olp = new OnlineSaqAttest(driver, clientProps);
			olp.manageButton();
		//	log.info("clicked on manage sucessufully");
			Thread.sleep(1000);
			olp.answerButton();
		//	log.info("clicked on answer button sucessufully");
			Thread.sleep(1000);
			olp.yesButton();
		//	log.info(" clicked on yes button sucessufully");
			Thread.sleep(1000);
			olp.onlineSaq();
		//	log.info("clicked on onlinesaq sucessufully");
			Thread.sleep(1000);
			olp.saqAnswer();
		//	log.info("clicked on saqAnswer sucessufully");
			olp.saqNextButton();
		//	log.info("clicked on saqnextbutton sucessufully");
			Thread.sleep(1000);
		//	log.info("clicked on confirmbutton sucessufully");
			olp.confirmButton();
			Thread.sleep(1000);
			
		}

	}



}