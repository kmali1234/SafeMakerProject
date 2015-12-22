package com.sysnet.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sysnet.helper.SeleniumHelper;

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
	private String emLength="";
	private By confirmEmailValidationMsg;
	private By passwordValidationMsg;
	private By confirmPasswordValidationMsg;
	private String expEmailValidMessg;
	private String expPasswordValidMessg;
	private By emailvalidmessage;
	private String emailBlankValidationMsg;
	private String emailinvalidvalidmessage;
	private WebDriver WebDriver;
	private String expUserValidMsg;
	private By actUserValidMsgLocator;
	private By actEmailValidMsgLocator;
	private String expEmailValidMsg;
	private By actPwdValidMsgLocator;
	private String expPwdValidMsg;
	private By actConfPwdValidMsgLocator;
	private String expConfPwdValidMsg;
	private String actEmailValidMsg;
	private String actUserValidMsg;
	private String actPwdValidMsg;
	private String actConfPwdValidMsg;
	private By actconfirmEmailValidMsgLocator;
	private By expconfirmEmailValidMsgLocator;
	private String actConfirmEmailValidMsg;
	private String expconfirmEmailValidMsg;
	private String expinvalidUserValidMsg;
	private String expEmailInvalidMsg;
	private String expInvalidPwdValidMsg;
	private String expInvalidConfPwdValidMsg;
	private String expInvalidconfirmEmailValidMsg;
	private String emailinvalidemailmessage;
	private String emailinvalidMessage;

	
	

	public PersonaliseValidation(WebDriver driver, Properties props) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		this.props = props;
		this.usernameFieldLocator=By.cssSelector(props.getProperty("personalise.textfield.username.css"));
		//this.conformUsernameLocator=By.id(props.getProperty("personalise.textfield.confirmUsername.css"));
		
		
        //css selector
        
        this.emailLocator=By.cssSelector(props.getProperty("personalise.textfield.email.css"));
		this.conformEmailLocators= By.cssSelector(props.getProperty("personalise.textfield.confirmEmail.css"));
		this.passwordLocator=By.cssSelector(props.getProperty("personalise.textfield.password.css"));
		this.conformPasswordLocator=By.cssSelector(props.getProperty("personalise.textfield.confirmPassword.css"));
		this.submitButtonLocator= By.cssSelector(props.getProperty("personalise.button.submit.css"));
		this.sucessValidation= By.cssSelector(props.getProperty("personalise.successValidation.username.css"));
		this.minLengthUsername=By.cssSelector(props.getProperty("personalise.minlength.usernam.css"));
		this.emailValidationMsg=By.cssSelector(props.getProperty("personalise.validationmsg.email.css"));
		this.confirmEmailValidationMsg=By.cssSelector(props.getProperty("personalise.ValidationMsg.confirmEmail.css"));
		this.passwordValidationMsg=By.cssSelector(props.getProperty("personalise.ValidationMsg.password.css"));
		this.confirmPasswordValidationMsg=By.cssSelector(props.getProperty("personalise.ValidationMsg.confirmPassword.css"));
        
        //actual css messages validation
       	this.actUserValidMsgLocator=By.cssSelector(props.getProperty("personalise.validationmsg.usernam.css"));
        this.actEmailValidMsgLocator=By.cssSelector(props.getProperty("personalise.validationmsg.email.css"));
        this.actconfirmEmailValidMsgLocator=By.cssSelector(props.getProperty("personalise.ValidationMsg.confirmEmail.css"));
        this.actPwdValidMsgLocator=By.cssSelector(props.getProperty("personalise.ValidationMsg.password.css"));
        this.actConfPwdValidMsgLocator=By.cssSelector(props.getProperty("personalise.ValidationMsg.password.css"));
        
               
        //blank msgs
        
		this.expUserValidMsg=props.getProperty("Personalise.validationmsg.blankUsername.text").toString();
        //this.expconfirmEmailValidMsgLocator=By.cssSelector(props.getProperty("Personalise.validationmsg.confirmEmail.text"));
        this.expPwdValidMsg=props.getProperty("Personalise.validationmsg.blankconfirmPassword.text").toString();
        this.expConfPwdValidMsg=props.getProperty("Personalise.validationmsg.blankconfirmEmail.text").toString();
        this.expconfirmEmailValidMsg=props.getProperty("Personalise.validationmsg.blankconfirmPassword.text").toString();
        this.emailBlankValidationMsg=props.getProperty("Personalise.validationmsg.blankemail.text").toString();
                   
        //invalid msgs
       // this.emailinvalidMessage=props.getProperty("Personalise.validationmsg.invalidemail.text").toString();
        this.expinvalidUserValidMsg=props.getProperty("Personalise.validationmsg.invalidUsernameMsg.text").toString();
        this.expEmailInvalidMsg=props.getProperty("Personalise.validationmsg.invalidemail.text").toString();
        //this.expconfirmEmailValidMsgLocator=By.cssSelector(props.getProperty("Personalise.validationmsg.confirmEmail.text"));
        this.expInvalidPwdValidMsg=props.getProperty("Personalise.validationmsg.expInvalidConfPwdValidMsg.text").toString();
        this.expInvalidConfPwdValidMsg=props.getProperty("Personalise.validationmsg.invalidconfirmEmail.text").toString();
        this.expInvalidconfirmEmailValidMsg=props.getProperty("Personalise.validationmsg.invalidconfirmEmail.text").toString();
        
	}


	public PersonaliseValidation usernamevalidation(){
	
		
		do{
			driver.findElement(usernameFieldLocator).sendKeys(Integer.toString(unLength));
		//	driver.findElement(submitButtonLocator).click();
			++unLength;
		}while(driver.findElement(minLengthUsername).getText().equals("Minimum characters requirement is not reached!"));
			
		System.out.println("Minmum length of username under personal page is "+unLength);
	
		
		return this;
		
	}
	
	public void blankValidation(){
		driver.findElement(submitButtonLocator).click();
		
		actUserValidMsg = driver.findElement(actUserValidMsgLocator).getText().toString();
		SeleniumHelper sh = new SeleniumHelper(driver, props);
		sh.textCompare(actUserValidMsg, expUserValidMsg);
		
		
		actEmailValidMsg = driver.findElement(actEmailValidMsgLocator).getText().toString();
		sh.textCompare(actEmailValidMsg, expEmailValidMsg);
		
		
		actConfirmEmailValidMsg = driver.findElement(actconfirmEmailValidMsgLocator).getText().toString();
		sh.textCompare(actConfirmEmailValidMsg, expconfirmEmailValidMsg);
		
    	
		actPwdValidMsg = driver.findElement(actPwdValidMsgLocator).getText().toString();
		sh.textCompare(actPwdValidMsg, expPwdValidMsg);
		
		
		actConfPwdValidMsg = driver.findElement(actConfPwdValidMsgLocator).getText().toString();
		sh.textCompare(actConfPwdValidMsg, expConfPwdValidMsg);
		
		
		
	}
	
	
	
	public void invalidValidation(){
		
		driver.navigate().refresh();
		driver.findElement(usernameFieldLocator).sendKeys("#!#!#$@#!$@!$@#$%#%^#$^^&%&$%*");
		driver.findElement(emailLocator).sendKeys("sysnet.global.com");
		driver.findElement(conformEmailLocators).sendKeys("sysne.global.com");
		driver.findElement(passwordLocator).sendKeys("@23");
		driver.findElement(conformPasswordLocator).sendKeys("@213");
		
	
		driver.findElement(submitButtonLocator).click();
		actUserValidMsg = driver.findElement(actUserValidMsgLocator).getText().toString();
		
		SeleniumHelper sh = new SeleniumHelper(driver, props);
		sh.textCompare(actUserValidMsg, expinvalidUserValidMsg);
		
	 
		
	
		actEmailValidMsg = driver.findElement(actEmailValidMsgLocator).getText().toString();
		sh.textCompare(actEmailValidMsg, expEmailInvalidMsg);
		
		
		actConfirmEmailValidMsg = driver.findElement(actconfirmEmailValidMsgLocator).getText().toString();
		sh.textCompare(actConfirmEmailValidMsg, expInvalidconfirmEmailValidMsg);
		
		
		actPwdValidMsg = driver.findElement(actPwdValidMsgLocator).getText().toString();
		sh.textCompare(actPwdValidMsg, expInvalidPwdValidMsg);
		
		
		actConfPwdValidMsg = driver.findElement(actConfPwdValidMsgLocator).getText().toString();
		sh.textCompare(actConfPwdValidMsg, expInvalidConfPwdValidMsg);
		
		
		
	}
	
    public PersonaliseValidation invalidConfirmEmailValidation()
    {
    	driver.findElement(submitButtonLocator).click();
    	actEmailValidMsg = driver.findElement(actEmailValidMsgLocator).getText();
		SeleniumHelper sh = new SeleniumHelper(driver, props);
		sh.textCompare(actEmailValidMsg, expInvalidconfirmEmailValidMsg);
		
		return this;

	}
    

    public PersonaliseValidation invalidEmailValidation()
    {
    	driver.findElement(submitButtonLocator).click();
    	actEmailValidMsg = driver.findElement(actEmailValidMsgLocator).getText();
		SeleniumHelper sh = new SeleniumHelper(driver, props);
		sh.textCompare(actEmailValidMsg, expInvalidconfirmEmailValidMsg);
		
		return this;

	}
    
    
    public PersonaliseValidation invalidPwdValidation()
    {
    	driver.findElement(submitButtonLocator).click();
		String actPwdValidMsg = driver.findElement(actPwdValidMsgLocator).getText();
		SeleniumHelper sh = new SeleniumHelper(driver, props);
		sh.textCompare(actPwdValidMsg, expInvalidPwdValidMsg);
		return this;

	}
    public PersonaliseValidation invalidrepeatPwdValidation()
    {
    	driver.findElement(submitButtonLocator).click();
		actConfPwdValidMsg = driver.findElement(actConfPwdValidMsgLocator).getText();
		SeleniumHelper sh = new SeleniumHelper(driver, props);
		sh.textCompare(actConfPwdValidMsg, expInvalidConfPwdValidMsg);
		return this;

	}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

