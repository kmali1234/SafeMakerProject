
package com.sysnet.safemaker;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.LogoutPage;
import com.sysnet.pageobjects.PersonaliseValidation;

public class DoPersonaliseValidation {

	private String propertyfilepath;
	private Properties clientProps;
	private String baseUrl;
	private String loginpageUrlSufix;
	private String url;
	private Sheet merchantFile;
	private int count;
	private WebDriver driver;
	private String username;
	private String password;
	private Row midRow;

	@Before
	public void SetUp() throws Exception {

		propertyfilepath = "test/integration/aibms.properties";
		clientProps = new Properties();
		FileInputStream locatorStream = new FileInputStream(propertyfilepath);
		clientProps.load(locatorStream);
		baseUrl = clientProps.getProperty("baseUrl");
		loginpageUrlSufix = clientProps.getProperty("login.url.suffix");
		url = baseUrl + loginpageUrlSufix;

	

		SeleniumHelper sh= new SeleniumHelper(driver, clientProps);


		merchantFile = sh.readExcelFile("test/integration/TestAccs.xlsx", "Status");
		count = merchantFile.getLastRowNum();

	}

	@Test
	public void getPersonaliseValidation() throws Exception {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);

		for (int i = 1; i <= count; i++) {
			midRow = merchantFile.getRow(i);
			username = midRow.getCell(0).toString();
			password = "Sysnet12";

			LoginPage lp = new LoginPage(driver, clientProps);
			lp.LoginUser(username, password);
			PersonaliseValidation pv = new PersonaliseValidation(driver,
					clientProps);
			Thread.sleep(5000);
			pv.blankValidation();
			Thread.sleep(5000);
			pv.invalidValidation();
			LogoutPage lop = new LogoutPage(driver, clientProps);
			lop.userLogout();			
		}

	}

	@After
	public void Quit() {
		driver.close();

	}

}
