package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.sysnet.helper.SeleniumHelper;

public class SAQAProfile {

	private String conifgFilePath;
	private Properties conifgProps;
	private String client;
	private Properties clientProps;
	private String propertyfilepath;
	private String baseUrl;
	private String loginpageUrlSufix;
	private String url;
	private int count;
	private WebDriver driver;
	private Sheet profileSheet;
	private int pRowCount;
	private Sheet merchantSheet;


	@Before
	public void setup() throws Exception {
		conifgFilePath = "test/integration/config.Properties";
		conifgProps = new Properties();
		FileInputStream locatorStream = new FileInputStream(conifgFilePath);
		conifgProps.load(locatorStream);
		client = conifgProps.getProperty("client");
		propertyfilepath = "test/integration/" + client
				+ "/locators.properties";
		clientProps = new Properties();
		FileInputStream locatorStream1 = new FileInputStream(propertyfilepath);
		clientProps.load(locatorStream1);

		baseUrl = conifgProps.getProperty("server");
		loginpageUrlSufix = clientProps.getProperty("login.url.suffix");
		url = baseUrl + loginpageUrlSufix;
		System.out.println(url);
		driver.get(url);
		SeleniumHelper sh= new SeleniumHelper(driver, clientProps);
		merchantSheet = sh.readExcelFile("test/integration/TestAccs.xlsx", "Status");
		count = merchantSheet.getLastRowNum();

		profileSheet = sh.readExcelFile("test/integration/aibms/Profile.xlsx", "SAQ type A-EP");
		pRowCount = profileSheet.getLastRowNum();

		Browser browser = new Browser();
		driver = browser.getdriver(conifgProps.getProperty("browser")
				.toString());
	}

	@Test
	public void merchantJourney() throws Exception {
		System.out.println(pRowCount);
		int merchantCount=merchantSheet.getLastRowNum();
		if(pRowCount>merchantCount){
			String warning="This script needs sufficient number of Merchants";
			JOptionPane.showMessageDialog(null,warning);
			Thread.sleep(100);
			
			driver.quit();
		}

		
	}

	// @After
	// public void close() {
	// // driver.close();
	// }
}
