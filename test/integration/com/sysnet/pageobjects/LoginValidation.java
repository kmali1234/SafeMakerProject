package com.sysnet.pageobjects;

import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.Properties;

import javax.validation.constraints.AssertTrue;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.annotation.Order;
import org.springframework.test.AssertThrows;

import com.sysnet.helper.SeleniumHelper;
import com.thoughtworks.selenium.Selenium;

public class LoginValidation {
			
		private WebDriver driver;
		private By usernamelocator;
		private By passwordlocator;
		private By loginbuttonlocator;
		private String url;
		
		private final Logger log =Logger.getLogger(LoginPage.class.getClass());
		private Properties locators;
		private Object alertMessage;
		private By userValidationMessage;
		private By passwordValidationMessage;
		private PrintStream verificationErrors;
		private By mismatchValidationMessage;
		private Object SeleniumQueryBy;
		private Object selector;
		private Object Cleanup;
		private WebElement username;
		private String String;
		
		
		public LoginValidation(WebDriver driver, Properties locators) {
			this.driver = driver;
			this.locators= locators;
			this.url =locators.getProperty("baseUrl")+locators.getProperty("login.url.suffix");
			this.usernamelocator = By.xpath(locators.getProperty("login.textfield.username.xpath"));
			this.passwordlocator = By.xpath(locators.getProperty("login.textfield.password.xpath"));
			this.loginbuttonlocator = By.cssSelector(locators.getProperty("login.button.submit.css"));
			this.userValidationMessage=By.cssSelector(locators.getProperty("login.validationmessage.username.css"));
			this.passwordValidationMessage=By.cssSelector(locators.getProperty("login.validationmessage.password.css"));
			this.mismatchValidationMessage=By.cssSelector(locators.getProperty("login.validationmessage.mismatch.css"));

		}
		
	public void blankCredentials() throws Exception{
			
			driver.findElement(usernamelocator).sendKeys("");
		    driver.findElement(passwordlocator).sendKeys("");
		    driver.findElement(loginbuttonlocator).click();
		    Thread.sleep(2000);
		    //user validation
		    String userValidationmsg = "This field is mandatory and can not be empty!";
		    String usernameassert = driver.findElement(userValidationMessage).getText();
		    
		    if(userValidationmsg.equals(usernameassert)){
		    	System.out.println("Warning message for username field is as expected");
		    }
		    else
		    	System.out.println("Warning message for username field is not as expected");
  	    
		    String passwordmsg = "This field is mandatory and can not be empty!";
		    String passwordassert = driver.findElement(passwordValidationMessage).getText();
		    
		    if(passwordmsg.equals(passwordassert)){
		    	System.out.println("Warning message for password field is as expected");
		    }
		    else
		    	System.out.println("Warning message for password field is not as expected");
  	    
		}
		

			public void blankUserInvalidPassword() throws Exception{	
			driver.navigate().refresh();
			driver.findElement(usernamelocator).sendKeys(" ");
		    driver.findElement(passwordlocator).sendKeys("%^&*(!@#");
		    driver.findElement(loginbuttonlocator).click();
		    Thread.sleep(600);
		    //invalid validation
		    String userValidationmessg = "This field is mandatory and can not be empty!";
		    String usernameassertmessg = driver.findElement(userValidationMessage).getText();
		    
		    if(userValidationmessg.equals(usernameassertmessg)){
		    	System.out.println("Warning message for username field is as expected");
		    }
		    else
		    	System.out.println("Warning message for username field is not as expected");
  	    
		    SeleniumHelper sh = new SeleniumHelper(driver, locators);
		    
		   
 	    
		}

				public void invalidUserValidPassword() throws Exception{
				driver.navigate().refresh();
				driver.findElement(usernamelocator).sendKeys("%^&*(!@#$@#");
			    driver.findElement(passwordlocator).sendKeys("Sysnet12");
			    driver.findElement(loginbuttonlocator).click();
			    Thread.sleep(3000);
			    //invalid validation
		 		String userValidationmessag = "The username you have entered is incorrect. Please try again or click the \"Forgot Username\"link below to have this reissued to you";
			    String usernameassertmessag = driver.findElement(mismatchValidationMessage).getText();
			    
			    if(usernameassertmessag.equals(usernameassertmessag)){
			    	System.out.println("Warning message for invalid User with Valid Password is as expected");
			    }
			    else
			    	System.out.println("Warning message for invalid User with Valid Password is not as expected");
			 //   SeleniumHelper sh = new SeleniumHelper(driver, locators);  	    
			 
			    
	  	    
			}
			
		public void invalidUserInvalidPassword() throws MalformedURLException, IOException, Exception {
				driver.navigate().refresh();
				driver.findElement(usernamelocator).sendKeys("^12321212^343434");
			    driver.findElement(passwordlocator).sendKeys("Sysnet12Ab");
				driver.findElement(loginbuttonlocator).click();
				Thread.sleep(200);
			    String userValidationmessag = "The username you have entered is incorrect. Please try again or click the \"Forgot Username\"link below to have this reissued to you";
			    //invalid validation
			    String userValidationmessage = driver.findElement(By.cssSelector("/html/body/div[1]/div/sgs-alert/div/div[1]/div/strong/span")).getText();
			  
			    String usernameassertmessag = driver.findElement(mismatchValidationMessage).getText();
			    
			    if(userValidationmessage.equals(usernameassertmessag)){
			    	System.out.println("Warning message for invalid User with Valid Password is as expected");
			    }
			    else
			    	System.out.println("Warning message for invalid User with Valid Password is not as expected");
			 
	  	    
			} 
			


		public void validUserInvalidPassword() throws Exception{
			driver.navigate().refresh();
			driver.findElement(usernamelocator).sendKeys("20151209011");
		    driver.findElement(passwordlocator).sendKeys("$%#&*##%$");
		    driver.findElement(loginbuttonlocator).click();
		    Thread.sleep(600);
			    //invalid validation
			    String userValidationmes = "The username you have entered is incorrect. Please try again or click the \"Forgot Username\" link below to have this reissued to you";
			    		
			    String mismatch = driver.findElement(mismatchValidationMessage).getText();
			    
			    if(mismatch.equals(userValidationmes)){
			    	System.out.println("Warning message for username field is as expected");
			    }
			    else
			    	System.out.println("Warning message for username field is not as expected");
		  	    
			} 
			
			
			}
	
		
		
		
		
		

