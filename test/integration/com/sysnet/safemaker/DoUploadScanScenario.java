package com.sysnet.safemaker;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.LogoutPage;
import com.sysnet.pageobjects.PersonalisePage;
import com.sysnet.pageobjects.UploadScanScenario;

//import com.sun.java.util.jar.pack.Package.File;

public class DoUploadScanScenario {
	
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
	private Sheet merchantFile;
	private int count;
	private Row midRow;
	private String date;
	public final Logger log = Logger.getLogger(DoLogin.class.getClass());
	private WebElement datePicker;
	private Object usernamelocator;
	private Object passwordlocator;
	private Object loginbuttonlocator;
	private By actScanValidationMessg;
	private String expScanValidationMessg;
	private Properties props;
	private String businessprofile;
	private String calendar;
	private Properties locators;
	private String actValidMsg;
	private String expUsername;
	private String expPassword;
	private String executivereport;
	private String technicalreport;
	private String uploadbutton;
	private String asv;
	public final Logger logg = Logger.getLogger(DoLogin.class.getClass());
	private SeleniumHelper sh;

	@Before
	public void SetUp() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		propertyfilepath = "test/integration/aibms.properties";
		clientProps = new Properties();
		FileInputStream locatorStream = new FileInputStream(propertyfilepath);
		clientProps.load(locatorStream);
		baseUrl = clientProps.getProperty("baseUrl");
		loginpageUrlSufix = clientProps.getProperty("login.url.suffix");
		url = baseUrl + loginpageUrlSufix;
		sh =new SeleniumHelper(driver, clientProps);
		merchantFile = sh.readExcelFile("test/integration/TestAccs.xlsx", "Status");
		count = merchantFile.getLastRowNum();
		System.out.println(count);

	}

	@Test
	public void getScanComplaint() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(500);
		for (int i = 1; i <= count; i++) {
			midRow = merchantFile.getRow(i);
			username = midRow.getCell(6).toString();
			//password = "Sysnet12";
			password = "g1tmast3r";			
			Thread.sleep(500);
			LoginPage lp = new LoginPage(driver, clientProps);
			lp.LoginUser(username, password);
			log.info(username + " logged in sucessufully");
			Thread.sleep(1000);
			UploadScanScenario upl = new UploadScanScenario(driver, clientProps);
			upl.startmanage();
			log.info("start manage button clicked successsfully");
			upl.uploadManageScan();
			Thread.sleep(1000);
			log.info("Upload Manage Scan clicked successsfully");
			upl.uploadresscan();
			Thread.sleep(1000);
			log.info("Upload result scan button clicked sucessfully");
			upl.startexecutivereport();
			Thread.sleep(1000);
			log.info("Clicked on Start Executive Report sucessfully");
			upl.starttechnicalreport();
			Thread.sleep(1000);
			log.info("Clicked on start technical report sucessfully");
			upl.selectasvscan();
			Thread.sleep(1000);
			log.info("selected asv scan option sucessfully");
			Thread.sleep(2000);
			upl.calendarScan();
			Thread.sleep(2000);
			log.info("Clicked on calendar date sucessfully");
			upl.clickOnUpload();
			
		}

	}

	private void passwordlocator() {

		PersonalisePage Per = new PersonalisePage(driver, props);
		Per.personaliseMerchant(expPassword);
		Per.submitForm();
	}

	private void usernamelocator() {

		PersonalisePage Per = new PersonalisePage(driver, props);
		Per.personaliseMerchant(expUsername);
		Per.submitForm();
	}

}
