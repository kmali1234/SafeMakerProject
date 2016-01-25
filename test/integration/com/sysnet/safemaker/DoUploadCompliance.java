package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.PersonalisePage;
import com.sysnet.pageobjects.UploadCompliance;

public class DoUploadCompliance {

	private String propertyfilepath;
	private Properties clientProps;
	private String baseUrl;
	private String loginpageUrlSufix;
	private String url;
	private Sheet merchantFile;
	private int count;
	private FirefoxDriver driver;
	private Row midRow;
	private String username;
	private String password;
	private Object log;
	private String expPassword;
	private String expUsername;
	private Properties props;
	private final Logger logs = Logger.getLogger(DoLogin.class.getClass());
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
		sh= new SeleniumHelper(driver, clientProps);

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
			Thread.sleep(1000);
			UploadCompliance upcl = new UploadCompliance(driver, clientProps);
			Thread.sleep(1000);
			upcl.manageUploadAoc();
			logs.info(" Manage Uploaded file sucessfully");
			Thread.sleep(3000);
			upcl.uploadCompliancebutton();
			logs.info("Upload Compliance file sucessfully");
			Thread.sleep(1000);
			upcl.uploadDocsbutton();
			logs.info("Uploaded Documents sucessfully");
			upcl.pciDssVersion();
			logs.info("Selected option Documents sucessfully");
			Thread.sleep(1000);
			upcl.agreeCheckbox();
			logs.info("Clicked on agree Checkbox");
			Thread.sleep(1000);
			upcl.attestButton();
			logs.info("Clicked on Attest Button");
			Thread.sleep(1000);
			
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
