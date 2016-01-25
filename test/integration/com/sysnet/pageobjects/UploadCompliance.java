package com.sysnet.pageobjects;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.sysnet.helper.SeleniumHelper;


public class UploadCompliance {

	private WebDriver driver;
	private Properties locators;
	private By pciDssVersion;
	private By uploadCompliancebutton;
	private By manageUploadAoc;
	private String actComplianceMessg;
	private String expScanValidationMessg;
	private By actualComplianceMessg;
	private By actualComplianceMessgLocator;
	private SeleniumHelper sha;
	private By uploadCalendar;
	private By agreeCheckbox;
	private By saqVersion;
	private final Logger log = Logger.getLogger(UploadCompliance.class.getClass());
	
	public UploadCompliance(WebDriver driver, Properties locators) {
		PropertyConfigurator.configure("log4j.properties");
		this.driver = driver;
		this.locators = locators;
		this.manageUploadAoc = By.cssSelector(locators.getProperty("button.Upload.Manage.css"));
		this.uploadCompliancebutton = By.cssSelector(locators.getProperty("button.uploadCompliance.css"));
		this.pciDssVersion = By.xpath(locators.getProperty("dropdown.pciDssVersion.xpath"));
		this.uploadCalendar = By.xpath(locators.getProperty("upload.calendar.xpath"));
		this.agreeCheckbox = By.xpath(locators.getProperty("agree.checkbox.xpath"));
		this.actualComplianceMessgLocator=By.cssSelector(locators.getProperty("dashboard.text.complianceActMessg.css"));
		this.expScanValidationMessg = locators.getProperty("dashboard.text.complianceExpMessg.text");
	}


	public UploadCompliance manageUploadAoc() {
		sha = new SeleniumHelper(driver, locators);
		actComplianceMessg = driver.findElement(actualComplianceMessgLocator).getText();
		sha.textCompare(actComplianceMessg, expScanValidationMessg);
		// driver.findElement(managescan).sendKeys("managescan");
		
		//driver.findElement(By.linkText("wdgt__btn--second")).click();
		driver.findElement(manageUploadAoc).click();
		log.info("Uploaded Documents sucessfully");
		return this;
	}

	public UploadCompliance uploadCompliancebutton() throws Exception {
		driver.findElement(uploadCompliancebutton).click();
		// driver.findElement(managescan).sendKeys("managescan");
		/*Thread.sleep(1000);
		Runtime.getRuntime().exec(
				"C:\\Users\\kmali\\Desktop\\FileUploadExecutive.exe");
		Thread.sleep(1000);*/
		log.info("Uploaded Compliance Documents sucessfully");
		return this;
	}
	
	
	public UploadCompliance uploadDocsbutton() throws Exception {
		driver.findElement(By.className("sgs-btn--upload")).click();
		Thread.sleep(1000);
		//driver.findElement(uploadCompliancebutton).click();
		// driver.findElement(managescan).sendKeys("managescan");
		Thread.sleep(1000);
		Runtime.getRuntime().exec(
				"C:\\Users\\kmali\\Desktop\\FileUploadExecutive.exe");
		Thread.sleep(1000);
		log.info("Clicked on Uploaded Documents sucessfully");
		return this;
	}
	
	public UploadCompliance pciDssVersion() {
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('saqVersion').removeAttribute('readonly',0);");
		new Select(
		driver.findElement(By.xpath(".//*[@id='saqVersion']"))).selectByIndex(3);				
		log.info("Selected Pci Dss Version sucessfully");
		return this;

	}
	
	public UploadCompliance uploadCalendar() throws Exception {
		// click on textfield of date
		driver.findElement(By.id("resultDate")).click();
		// click on back arrow
		Thread.sleep(2000);
		// driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ui-view/div/div[2]/form/div[4]/div/sgs-date-picker/div/input")).click();
		driver.findElement(By.className("sgs-datepicker__btn--back")).click();
		// select particular date
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div[1]/ui-view/div/div[2]/form/div[4]/div/sgs-date-picker/div/div/ul/li/div/table/tbody/tr[3]/td[4]/button"))
				.click();
		Thread.sleep(2000);
		log.info("Clicked on Calendar option sucessfully");
		return this;
	}
	
	public UploadCompliance agreeCheckbox() {
		driver.findElement(agreeCheckbox).click();
		// driver.findElement(managescan).sendKeys("managescan");
		log.info("Agree Checkbox clicked sucessfully");
		return this;
	}
	
	public UploadCompliance attestButton() {
		driver.findElement(By.className("sgs-btn--confirm")).click();
		log.info("Clicked on Attest Button sucessfully");
		return this;
	}

}
