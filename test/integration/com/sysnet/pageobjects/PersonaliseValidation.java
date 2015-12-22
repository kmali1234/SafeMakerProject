package com.sysnet.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonaliseValidation {

	private By usernameFieldLocator;
	private By emailLocator;
	private By passwordLocator;
	private By conformPasswordLocator;
	private By conformEmailLocators;
	private By submitButtonLocator;
	private WebDriver driver;
	private int unLength;
	private By sucessValidation;
	private By minLengthUsername;
	private By emailValidationMsg;
	private Properties props;

	public PersonaliseValidation(WebDriver driver, Properties props) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.props = props;
		this.usernameFieldLocator = By.cssSelector(props
				.getProperty("personalise.textfield.username.css"));
		// this.conformUsernameLocator=By.id(props.getProperty("personalise.textfield.confirmUsername.css"));
		this.passwordLocator = By.cssSelector(props
				.getProperty("personalise.textfield.password.css"));
		this.conformPasswordLocator = By.cssSelector(props
				.getProperty("personalise.textfield.confirmPassword.css"));
		this.emailLocator = By.cssSelector(props
				.getProperty("personalise.textfield.email.css"));
		this.conformEmailLocators = By.cssSelector(props
				.getProperty("personalise.textfield.confirmEmail.css"));
		this.submitButtonLocator = By.cssSelector(props
				.getProperty("personalise.button.submit.css"));
		this.sucessValidation = By.cssSelector(props
				.getProperty("personalise.successValidation.username.css"));
		this.minLengthUsername = By.cssSelector(props
				.getProperty("personalise.minlength.usernam.css"));
		this.emailValidationMsg = By.cssSelector(props
				.getProperty("personalise.validationmsg.email.css"));
	}

	public PersonaliseValidation usernamevalidation() {

		do {
			driver.findElement(usernameFieldLocator).sendKeys(
					Integer.toString(unLength));
			driver.findElement(submitButtonLocator).click();
			++unLength;
		} while (driver.findElement(minLengthUsername).getText()
				.equals("Minimum characters requirement is not reached!"));

		System.out.println("Minmum length of username under personal page is "
				+ unLength);
		System.out.println();

		return this;

	}

	public PersonaliseValidation blankEmail() {
		driver.findElement(emailLocator).clear();
		driver.findElement(submitButtonLocator).click();
		String actEmailValidMsg = driver.findElement(emailValidationMsg)
				.getText();
		String expEmailValidMsg = props
				.getProperty("Personalise.validationmsg.email.text");
		if (actEmailValidMsg.equals(expEmailValidMsg)) {
			System.out
					.println("Actual matches with expected, expected emial validation message is "
							+ expEmailValidMsg);
		} else {
			System.out.println("Actuall Message: " + actEmailValidMsg
					+ " does not match with expected message: "
					+ expEmailValidMsg);
		}
		return this;

	}

}
