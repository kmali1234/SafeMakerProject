package com.sysnet.pageobjects;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sysnet.helper.SeleniumHelper;

		public class PersonalisePage {

			

			private WebDriver driver;
			private By usernameFieldLocator;
			private By conformUsernameLocator;
			private By passwordLocator;
			private By conformPasswordLocator;
			private By emailLocator;
			private By conformEmailLocators;
			private By submitButtonLocator;
			private By acceptFieldLocator;
			private Properties props;

			private boolean notPersonalised;
			private final Logger log =Logger.getLogger(PersonalisePage.class.getClass());
			public PersonalisePage(WebDriver driver, Properties props) {
				
				this.driver = driver;
				
				
				
				//this.acceptFieldLocator=By.cssSelector(props.getProperty("personalise.button.accept.css"));
				this.usernameFieldLocator=By.cssSelector(props.getProperty("personalise.textfield.username.css"));
				//this.conformUsernameLocator=By.cssSelector(props.getProperty("personalise.textfield.confirmUsername.css"));
				this.passwordLocator=By.cssSelector(props.getProperty("personalise.textfield.password.css"));
				this.conformPasswordLocator=By.cssSelector(props.getProperty("personalise.textfield.confirmPassword.css"));
				this.emailLocator=By.cssSelector(props.getProperty("personalise.textfield.email.css"));
				this.conformEmailLocators= By.cssSelector(props.getProperty("personalise.textfield.confirmEmail.css"));
				this.submitButtonLocator= By.cssSelector(props.getProperty("personalise.button.submit.css"));
				
			}

			public  void personaliseMerchant(String username) {
				try{
					enterUserName(username);
					EnterPasswordEmail();
					submitForm();
					log.info("Merchant is personalised and the username is: "+username);
				}catch(Exception e){
					log.error("Merchant "+username+" personalision failed", e);
				}
				
				
			}

			

			private PersonalisePage enterUserName(String username) {
			
				driver.findElement(usernameFieldLocator).clear();
				driver.findElement(usernameFieldLocator).sendKeys(username);
				
				return this;
				
			}
			
			public  PersonalisePage EnterPasswordEmail(){
				driver.findElement(passwordLocator).sendKeys("Sysnet12");
				driver.findElement(conformPasswordLocator).sendKeys("Sysnet12");
				driver.findElement(emailLocator).clear();
				driver.findElement(emailLocator).sendKeys("sysnet@sysnet.ie");
				driver.findElement(conformEmailLocators).clear();
				driver.findElement(conformEmailLocators).sendKeys("sysnet@sysnet.ie");
				return this;
			}
			
			
			public  PersonalisePage submitForm(){
				driver.findElement(submitButtonLocator).click();
				
				return this;
			}	
			
			private boolean isPerssonalised() {
				// TODO Auto-generated method stub
				SeleniumHelper sh= new SeleniumHelper(driver, props);
				if (sh.isElementDisplayed(usernameFieldLocator)) {
						return true;
					}else{
						System.out.println("Merchant is Personalised");
						return false;
					}
				
				
			}

}


