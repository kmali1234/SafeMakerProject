package com.sysnet.safemaker;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sysnet.pageobjects.LoginPage;
import com.sysnet.pageobjects.LogoutPage;
import com.sysnet.pageobjects.PersonalisePage;

public class DoPersonalization {
	//we are about to identify an element with attribute ng-model
	
	private Properties clientProps;
	private String loginpageUrlSufix;
	private WebDriver driver;
	private String propertyfilepath;
	private String username;
	private String password;
	private String url;
	private Object baseUrl;
	
	private Sheet merchantFile;
	private int count;
	private Row midRow;
	
    //driver.findElement(By.xpath("//input[@ng-model='yourName']")).sendKeys("xyz");
   // WebElement username = driver.findElement(By.xpath("//h1[@class='ng-binding']"));
  //  System.out.println(fld_Result.getText());
  //  if (username). 
    //		getText() == "Hello xyz!")) //returns 0, if equals, so negation
     //  {
      //     System.out.println("Passed");
     //  }
    @Test
	public void getPersonalisation() throws Exception
	{
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		for(int i=1; i<=count; i++){
			midRow = merchantFile.getRow(i);
			username = midRow.getCell(0).toString();
			password = "Sysnet12";
			
			
		
		Thread.sleep(500);	
		LoginPage lp = new LoginPage(driver, clientProps);
		lp.LoginUser(username, password);
		PersonalisePage pp = new PersonalisePage(driver, clientProps);
		pp.personaliseMerchant(username);
		
		// take the screenshot at the end of every test
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // now save the screenshto to a file some place
        FileUtils.copyFile(scrFile, new java.io.File("ComplianceProject/Screenshots"));
//        Assertions pers = new Assertions(driver, clientProps);
//        pers.assertTitle("Personalise");
        Thread.sleep(500);
        LogoutPage logout = new LogoutPage(driver, clientProps);
        logout.userLogout();
         driver.switchTo().alert().accept();
        
        
		}
        
}}
