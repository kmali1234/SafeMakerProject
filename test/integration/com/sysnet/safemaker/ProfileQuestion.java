package com.sysnet.safemaker;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sysnet.helper.SeleniumHelper;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class ProfileQuestion {

	private static String profileKey;
	private static String keyValue;
	private static String cssSelector;
	private static String select;
	private final static  Logger log = Logger.getLogger(ProfileQuestion.class.getName());

	public ProfileQuestion(String profileKey, String keyValue) {
		// TODO Auto-generated constructor stub
		
	}

	public static  void answer(String stringCellValue, WebDriver driver,Properties clientProps) {
		// TODO Auto-generated method stub
		String[] cellLine=getProfileKeyAndKeyValue(stringCellValue);
		for(String str : cellLine){
			if(!(str.equals(null))){
			splitProfileKeyAndKeyValue(str);
			createselectorAndDoEvent(profileKey, keyValue, clientProps, driver);
			System.out.println("proflileKey: "+profileKey);
			System.out.println("KeyValue: "+keyValue);
			log.info(profileKey+" question is answered as: "+keyValue);
			}
		}
		
	}

	private static void createselectorAndDoEvent(String profileKey, String keyValue,Properties clientProps, WebDriver driver) {
		// TODO Auto-generated method stub
		
		if( !(Character.isDigit(profileKey.charAt(0)))){
			if(keyValue.toUpperCase().equals("YES")){
				cssSelector="span input#"+ProfileQuestion.profileKey+"0";
				System.out.println(cssSelector);
				clickQuestionAndLogText(driver, cssSelector, clientProps);
				
			} else if(keyValue.toUpperCase().equals("NO")){
				cssSelector="span input#"+ProfileQuestion.profileKey+"1";
				System.out.println(cssSelector);
				clickQuestionAndLogText(driver, cssSelector, clientProps);
			} else if(keyValue.toUpperCase().equals("CHECK")){
				cssSelector="span.sgs-form__checkbox input#"+profileKey;
				System.out.println(cssSelector);
				clickQuestionAndLogText(driver, cssSelector, clientProps);
			} else if(keyValue.toUpperCase().startsWith("INFO")){
				
					answerPickList(driver, clientProps);
			} else if(keyValue.toUpperCase().startsWith("TEXT")){
				cssSelector="div textarea#"+profileKey;
				select=keyValue+" is "+ profileKey;
				System.out.println(cssSelector);
				enterText(driver, cssSelector, select, clientProps);
			} 
		}
	}

	private static void enterText(WebDriver driver, String cssSelector,String select, Properties clientProps) {
		// TODO Auto-generated method stub
driver.findElement(By.cssSelector(cssSelector)).sendKeys(keyValue);
		
		SeleniumHelper spc=new SeleniumHelper(driver, clientProps);
		spc.clickDialogIfDisplayed();
		log.info(profileKey+" answer is "+keyValue);
		
	}

	private static void clickQuestionAndLogText(WebDriver driver, String cssSelector, Properties clientProps) {
		// TODO Auto-generated method stub
		 WebElement temp = driver.findElement(By.cssSelector(cssSelector));
		 JavascriptLibrary jsLib = new JavascriptLibrary();
		 jsLib.callEmbeddedSelenium(driver,"triggerMouseEventAt", temp,"click", "0,0");
		SeleniumHelper spc=new SeleniumHelper(driver, clientProps);
		spc.clickDialogIfDisplayed();
	
			log.info(profileKey+" answer is "+keyValue);
	}

	private static void answerPickList(WebDriver driver, Properties clientProps) {
		// TODO Auto-generated method stub
		driver.findElement(By.cssSelector(clientProps.getProperty("picklist.checkbox.firstoption.css"))).click();
	}

	

	private static ProfileQuestion splitProfileKeyAndKeyValue(String str) {
		// TODO Auto-generated method stub
		String[] splitString = str.split(":");
		profileKey=splitString[0];
		keyValue=splitString[1];
		return new ProfileQuestion(profileKey, keyValue);
		
	}

	private static String[] getProfileKeyAndKeyValue(String stringCellValue) {
		// TODO Auto-generated method stub
		if(!(stringCellValue.equals(null))){
			String []filedElements= stringCellValue.split("\n");
			return filedElements;
			}else{
				return null;
			}
	}

}
