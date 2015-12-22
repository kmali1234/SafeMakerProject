package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

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
	private Sheet merchantFile;
	private int count;
	private WebDriver driver;

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

		merchantFile = SeleniumHelper.readExcelFile(
				"test/integration/TestAccs.xlsx", "Status");
		count = merchantFile.getLastRowNum();

		Browser browser = new Browser();
		driver = browser.getdriver(conifgProps.getProperty("browser")
				.toString());
	}

	@Test
	public void merchantJourney() {

		driver.get(url);
	}

	// @After
	// public void close() {
	// // driver.close();
	// }
}
