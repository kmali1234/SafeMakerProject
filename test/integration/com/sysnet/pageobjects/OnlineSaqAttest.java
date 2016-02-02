package com.sysnet.pageobjects;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sysnet.helper.SeleniumHelper;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class OnlineSaqAttest {
	private final Logger log = Logger.getLogger(OnlineSaqAttest.class.getClass());
	private WebDriver driver;
	private Properties locators;
	private By onlineSaq;
	private By saqAnswer;
	private By saqNextButton;
	private By confirmButton;
	private By reProfileButton;
	private By manageButton;
	private By yesButton;
	private By nextButton;
	private Properties props;
	private By finishButton ;
	private By completesecuritybutton;
	private By attestButton;
	private By onlineSaqyes;
	private By turnOffHelpTextLocator;
	private By finalNextButton;
	private By questionnaireTextLocator;
	private String questionnaireText;

	
	public OnlineSaqAttest(WebDriver driver, Properties locators) {
		//PropertyConfigurator.configure("log4j.properties");
		this.driver = driver;
		this.locators = locators;
		
	    this.manageButton = By.cssSelector(locators.getProperty("saq.dashboard.button.manage.css"));
		this.onlineSaqyes = By.cssSelector(locators.getProperty("saq.button.yes.css"));
		this.questionnaireTextLocator=By.cssSelector(locators.getProperty("saq.questionnaire.text.css"));
		this.saqAnswer = By.cssSelector(locators.getProperty("saq.completesecurity.button.saqanswer.css"));
		this.saqNextButton = By.cssSelector(locators.getProperty("saq.button.onlinesaqnext.css"));
		this.confirmButton = By.cssSelector(locators.getProperty("saq.button.confirm.css"));
		this.reProfileButton = By.cssSelector(locators.getProperty("saq.button.reProfile.css"));
	    this.turnOffHelpTextLocator = By.cssSelector(locators.getProperty("saq.filterbutton.turnoff.css"));
		this.yesButton = By.cssSelector(locators.getProperty("saq.yes.button.css"));
	//	this.nextButton = By.cssSelector(locators.getProperty("next.button.css"));
		//this.finishButton = By.cssSelector(locators.getProperty("button.finish.css"));
		this.finishButton = By.cssSelector(locators.getProperty("saq.next.button.css"));
		this.attestButton = By.cssSelector(locators.getProperty("saq.attest.button.css"));
		this.finalNextButton = By.cssSelector(locators.getProperty("saq.finalNext.button.css"));
	}

	public OnlineSaqAttest manageButton() throws Exception{
		driver.findElement(manageButton).click();
		//driver.findElement(By.linkText("wdgt__btn--second")).click();
		Thread.sleep(3000);
		log.info("manageButton clicked");
		return this;
	}
	
	public OnlineSaqAttest saqAnswer(){
		//driver.findElement(By.linkText("sgs-action-view__action-link")).click();
		driver.findElement(saqAnswer).click();
		return this;
	}

	
	
//	public OnlineSaqAttest onlineSaq(){
//		driver.findElement(onlineSaq).sendKeys("onlineSaq");
//		log.info("onlineSaq");
//		return this;
//	} 
	public OnlineSaqAttest yesButton() throws Exception{
		
		
		SeleniumHelper selh = new SeleniumHelper(driver, props);
		WebElement temp = driver.findElement(turnOffHelpTextLocator);
        JavascriptLibrary jsLib = new JavascriptLibrary();
		 jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", temp,"click", "0,0");
		 Thread.sleep(1000);
		while(selh.isElementPresent(saqNextButton) || selh.isElementPresent(finalNextButton))
			{
			while(selh.isElementPresent(yesButton))
			{
				questionnaireText=driver.findElement(questionnaireTextLocator).getText();
				driver.findElement(yesButton).click();		
				log.info("yesButton clicked");
				Thread.sleep(1000);
				
				log.info(" Question: \""+questionnaireText+"\" is answered as Yes");
				JavascriptExecutor js = (JavascriptExecutor) driver;
			        js.executeScript("javascript:window.scrollBy(250,350)");
			        while(selh.isElementPresent(finishButton))
					{
			        	
						driver.findElement(finishButton).click();	
						log.info("finishButton clicked");	
						Thread.sleep(1000);
					}
			     
			}
			if(selh.isElementPresent(saqNextButton)){
			driver.findElement(saqNextButton).click();
			} else if(selh.isElementPresent(finalNextButton)){
				driver.findElement(finalNextButton).click();
			}
			Thread.sleep(1000);
			
			
		}
		
				driver.findElement(By.className("sgs-btn--confirm")).click();
		return this;
		
	}
	
		

			
			
	public OnlineSaqAttest saqNextButton(){
		driver.findElement(saqNextButton).sendKeys("saqNextButton");
		log.info("saqNextButton");
		return this;
	}

	public OnlineSaqAttest confirmButton(){
		driver.findElement(confirmButton).click();
		log.info("confirmButton");
		return this;
	}
	
	public OnlineSaqAttest saqCompliant() throws Exception{
		try{
		Thread.sleep(500);
		
		manageButton();
		log.info("clicked on manage SAQ sucessufully");
		Thread.sleep(1000);
		saqAnswer();
		log.info("clicked on answer button sucessufully");
		Thread.sleep(1000);
		yesButton();
		log.info(" clicked on yes button sucessufully");
		Thread.sleep(1000);
//		onlineSaq();
//		log.info("clicked on onlinesaq sucessufully");
//		saqNextButton();
//		log.info("clicked on saqnextbutton sucessufully");
//		Thread.sleep(1000);
//		log.info("clicked on confirmbutton sucessufully");
//		confirmButton();
		Thread.sleep(1000);
		}catch(Exception e)
		{
			log.error("Unable to complete SAQ and Attestation", e);
		}
		return this;
	}
	
	
}