package com.sysnet.pageobjects;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sysnet.helper.SeleniumHelper;

public class OnlineSaqAttest {
	private final Logger log = Logger.getLogger(UploadCompliance.class.getClass());
	private WebDriver driver;
	private Properties locators;
	private By onlineSaq;
	private By saqAnswer;
	private By saqNextButton;
	private By confirmButton;
	private By manageButton;
	private By yesButton;
	private By nextButton;
	private Properties props;
	private By finishButton ;
	
	public OnlineSaqAttest(WebDriver driver, Properties locators) {
	//	PropertyConfigurator.configure("log4j.properties");
		this.driver = driver;
		this.locators = locators;
		this.onlineSaq = By.cssSelector(locators.getProperty("button.onlinesaqyes.css"));
		this.saqAnswer = By.cssSelector(locators.getProperty("button.saqanswer.css"));
		this.saqNextButton = By.cssSelector(locators.getProperty("button.onlinesaqnext.css"));
		this.confirmButton = By.cssSelector(locators.getProperty("button.confirm.css"));
		this.manageButton = By.cssSelector(locators.getProperty("dashboard.button.manage.css"));
		this.yesButton = By.cssSelector(locators.getProperty("button.yes.css"));
		this.nextButton = By.cssSelector(locators.getProperty("dashboard.button.next.css"));
		this.finishButton = By.cssSelector(locators.getProperty("button.finish.css"));
		
	}

	public OnlineSaqAttest manageButton(){
		driver.findElement(manageButton).click();
		log.info("manageButton clicked");
		return this;
	}
	
	public OnlineSaqAttest answerButton(){
		driver.findElement(By.linkText("Answer now")).click();
		return this;
	}

	public OnlineSaqAttest yesButton() throws Exception{
		
		
		SeleniumHelper selh = new SeleniumHelper(driver, props);
		
		if(selh.isElementPresent(nextButton))
			{
			if(selh.isElementPresent(yesButton))
			{
				driver.findElement(yesButton).click();		
				log.info("yesButton clicked");
				Thread.sleep(1000);
				if(selh.isElementPresent(finishButton))
				{
					driver.findElement(finishButton).click();
					Thread.sleep(1000);
				}
			}
			driver.findElement(nextButton).click();
			Thread.sleep(1000);
			
			
		}
		
				driver.findElement(By.className("sgs-btn--confirm")).click();
		return this;
		
	}
	
	public OnlineSaqAttest onlineSaq(){
		driver.findElement(onlineSaq).sendKeys("onlineSaq");
		log.info("onlineSaq");
		return this;
	} 

	public OnlineSaqAttest saqAnswer(){
		driver.findElement(saqAnswer).sendKeys("saqAnswer");
		log.info("onlineSaqAttest");
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
	
	
}
