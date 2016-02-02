package com.sysnet.pageobjects;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.sysnet.helper.SeleniumHelper;
import com.sysnet.pageobjects.LoginPage;

public class UploadScanScenario {

private static final String scan = null;
private static final String Convert = null;
private static final String wait = null;
private WebDriver WebDriver;
private Properties Properties;
private String url;
private String password;
private String username;
private By loginbuttonlocator;
private By passwordlocator;
private By usernamelocator;
private List<org.openqa.selenium.WebElement> noOfColumns;
private org.openqa.selenium.WebElement datePicker;
private org.openqa.selenium.WebDriver driver;
private java.util.Properties locators;
private By startbusinessprofile;
private By uploadresults;
private By executivereport;
private By technicalreport;
private By asv;
private By calendar;
private By upload;

private By startuploadresults;
private By startexecutivereport;
private By starttechnicalreport;
private By startasv;
private By startcalendar;
private By SubmitUploadlocator;
private By managebutton;
private By actScanValidationMessg;
private String expScanValidationMessg;
private String actScanValidMsg;
private By uploadresbutton;
private By uploadres;
private By managescan;
private By uploadresscan;
private By executivereportscan;
private By technicalreportscan;
private By asvscan;
private By calendarScan;
private By uploadbutton;
private SeleniumHelper sha;
private Object multiSelectDropDown;
private WebElement dateWidget;
private List<WebElement> rows;
private List<WebElement> columns;
private Object date;
private By uploadManageScan;

static final Logger log = Logger.getLogger(UploadScanScenario.class);
public UploadScanScenario(WebDriver driver, Properties locators) throws Exception 
{
	this.driver=driver;
	this.locators=locators;
  	this.uploadManageScan=By.cssSelector(locators.getProperty("scan.button.Manage.css"));
    this.uploadresscan=By.cssSelector(locators.getProperty("scan.uploadresults.css"));
    this.executivereportscan=By.cssSelector(locators.getProperty("scan.executivereport.css"));
    this.technicalreportscan=By.cssSelector(locators.getProperty("scan.technicalreport.css"));
    this.asvscan=By.cssSelector(locators.getProperty("scan.asv.css"));
	this.calendarScan=By.cssSelector(locators.getProperty("scan.calendar.xpath"));
	this.uploadbutton=By.cssSelector(locators.getProperty("scan.upload.button.css"));
	this.actScanValidationMessg = By.xpath(locators.getProperty("dashboard.text.scanActValidMessg.xpath"));
	this.expScanValidationMessg = locators.getProperty("dashboard.text.scanExpValidMessg.text");
	
}



	public UploadScanScenario manageASVScan() {
		driver.findElement(uploadManageScan).click();
		// driver.findElement(managescan).sendKeys("managescan");
		log.info("Clicked on upload manage scan sucessfully");
	return this;
	}

	public UploadScanScenario startmanage() {
		sha = new SeleniumHelper(driver, locators);
		actScanValidMsg = driver.findElement(actScanValidationMessg).getText();
		sha.textCompare(actScanValidMsg, expScanValidationMessg);
		log.info("Clicked on Start Manage sucessfully");
		return this;
	}

	public UploadScanScenario chooseUploadScan() {
		driver.findElement(uploadresscan).click();
		// driver.findElement(managescan).sendKeys("managescan");
		log.info("Clicked Upload Results sucessfully");
		return this;
	}

	public UploadScanScenario startexecutivereport() throws Exception {
		
		driver.findElement(executivereportscan).click();
		Thread.sleep(1000);
		
		Runtime.getRuntime().exec("C:\\AutomationDoc\\FileUploadExecutive.exe");
		//driver.get("C:\\Users\\kmali\\Desktop\\fileUploadLatest.html");
		//driver.get("C:\\AutomationDoc\\FileUpload.exe");
		Thread.sleep(1000);
		//driver.findElement(By.xpath(".//*[@id='1']")).click();
		
		log.info("Clicked on Executive Report button sucessfully");
		
		return this;

	}

	public UploadScanScenario starttechnicalreport() throws Exception {
		
		driver.findElement(technicalreportscan).click();
		Thread.sleep(1000);
		//driver.get("C:\\Users\\kmali\\Desktop\\fileUploadLatest.html");
		Runtime.getRuntime().exec("C:\\AutomationDoc\\FileUploadExecutive.exe");
		//Runtime.getRuntime().exec("C:\\AutomationDoc\\FileUpload.exe");
		Thread.sleep(1000);
		//driver.findElement(By.xpath(".//*[@id='1']")).click();
		
		log.info("Clicked on Start Technical Report sucessfully");
		return this;

	}

	public UploadScanScenario selectasvscan() throws Exception {
		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('asv').removeAttribute('readonly',0);");
		new Select(
				driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ui-view/div/div[2]/form/div[3]/div/sgs-select/div/div/select")))
				.selectByVisibleText("Alert Logic, Inc.");
		log.info("Selected Asv Scan option from drop down menu");
		return this;

	}

	public UploadScanScenario SubmitUpload() {
		driver.findElement(uploadbutton).click();
		log.info("Clicked on submit button sucessfully");
		return this;
	}

	public UploadScanScenario calendarScan() throws Exception {
		// click on textfield of date
		driver.findElement(By.id("resultDate")).click();
		// click on back arrow
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ui-view/div/div[2]/form/div[4]/div/sgs-date-picker/div/input")).click();
		driver.findElement(By.className("sgs-datepicker__btn--back")).click();
		// select particular date
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("/html/body/div[1]/div/div/div/div[1]/ui-view/div/div[2]/form/div[4]/div/sgs-date-picker/div/div/ul/li/div/table/tbody/tr[3]/td[4]/button"))
				.click();
		Thread.sleep(2000);
		log.info("Selected a particular date sucessfully");
		return this;
	}

	public void clickOnUpload() throws Exception {
		driver.findElement(By.className("sgs-btn--confirm")).click();
		log.info("Clicked on upload button sucessfully");
	}

	 public UploadScanScenario doScanUpload() throws Exception{
			try{
				manageASVScan();
				Thread.sleep(1000);
				log.info("Upload Manage Scan clicked successsfully");
				chooseUploadScan();
			Thread.sleep(1000);
			log.info("Upload result scan button clicked sucessfully");
			startexecutivereport();
			Thread.sleep(1000);
			log.info("Clicked on Start Executive Report sucessfully");
			starttechnicalreport();
			Thread.sleep(1000);
			log.info("Clicked on start technical report sucessfully");
			selectasvscan();
			Thread.sleep(1000);
			log.info("selected asv scan option sucessfully");
			Thread.sleep(2000);
			calendarScan();
			Thread.sleep(3000);
			log.info("Clicked on calendar date sucessfully");
			clickOnUpload();
			Thread.sleep(2000);
			} catch(Exception e) {
				log.error("unable to upload scan documents", e);
			}
			return this;
			
	 }
	

}
