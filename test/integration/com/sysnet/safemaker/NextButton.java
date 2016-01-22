package com.sysnet.safemaker;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sysnet.helper.SeleniumHelper;

public class NextButton {

	private static int nextPageSleepLocator;
	private static By nextButtonLoctor;

	public static void click(WebDriver driver, Properties clientProps) throws Exception {
		// TODO Auto-generated method stub
		nextPageSleepLocator=Integer.parseInt(clientProps.getProperty("nextstep.sleep.delay.millyscecond"));
		nextButtonLoctor=By.cssSelector(clientProps.getProperty("profile.button.next.css"));
		SeleniumHelper sHelp=new SeleniumHelper(driver, clientProps);
		if(sHelp.isElementDisplayed(nextButtonLoctor)){
		 driver.findElement(By.cssSelector(clientProps.getProperty("profile.button.next.css"))).click();
		Thread.sleep(nextPageSleepLocator);
		}
		
	}

}
