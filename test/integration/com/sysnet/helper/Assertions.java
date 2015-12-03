package com.sysnet.helper;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Assertions {
	
	private String Actualtext;
	private WebDriver driver;

	public Assertions(WebDriver driver, Properties props)
	{
		
		this.driver = driver;
	}
   
	public void assertTitle(String expText) {
		  Actualtext = driver.getTitle();
		  Assert.assertEquals(Actualtext, expText);
		  System.out.print("\n assertion_method_1() -> Part executed");
		 } 

}
