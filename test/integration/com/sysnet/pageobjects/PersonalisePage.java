package com.sysnet.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PersonalisePage {

	private WebDriver driver;
	private By usernameFieldLocator;
	private By passwordLocator;
	private By conformPasswordLocator;
	private By emailLocator;
	private By conformEmailLocators;
	private By submitButtonLocator;
	private By conformUsernameLocator;

			public PersonalisePage(WebDriver driver, Properties props) {
				
				this.driver = driver;
				
				
				
				this.usernameFieldLocator=By.cssSelector(props.getProperty("personalise.button.accept.css"));
				this.usernameFieldLocator=By.id(props.getProperty("personalise.textfield.username.id"));
				this.conformUsernameLocator=By.id(props.getProperty("personalise.textfield.confirmUsername.id"));
				this.passwordLocator=By.id(props.getProperty("personalise.textfield.password.id"));
				this.conformPasswordLocator=By.id(props.getProperty("personalise.textfield.confirmPassword.id"));
				this.emailLocator=By.id(props.getProperty("personalise.textfield.email.id"));
				this.conformEmailLocators= By.id(props.getProperty("personalise.textfield.confirmEmail.id"));
				this.submitButtonLocator= By.cssSelector(props.getProperty("personalise.button.submit.css"));
				
			}

	public void personaliseMerchant(String username) {
		enterUserName(username);
		EnterPasswordEmail();
		submitForm();

	}

	private PersonalisePage enterUserName(String username) {

		driver.findElement(usernameFieldLocator).clear();
		driver.findElement(usernameFieldLocator).sendKeys(username);
		return this;

	}

	public PersonalisePage EnterPasswordEmail() {
		driver.findElement(emailLocator).clear();
		driver.findElement(emailLocator).sendKeys("sysnet@sysnet.ie");
		driver.findElement(conformEmailLocators).clear();
		driver.findElement(passwordLocator).clear();
		driver.findElement(conformEmailLocators).sendKeys("sysnet@sysnet.ie");
		driver.findElement(passwordLocator).sendKeys("Sysnet12");
		driver.findElement(conformPasswordLocator).sendKeys("Sysnet12");
		return this;
	}

	public PersonalisePage submitForm() {
		driver.findElement(submitButtonLocator).click();

		return this;
	}

}
