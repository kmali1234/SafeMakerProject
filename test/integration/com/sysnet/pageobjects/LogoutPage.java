package com.sysnet.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

	private WebDriver driver;
	private By logoutlocator;


	
	public LogoutPage(WebDriver driver, Properties props) {
		
		this.driver=driver;
		this.logoutlocator = By.linkText(props.getProperty("logout.link.linktext"));
	}

	public  WebDriver userLogout(){
		driver.findElement(logoutlocator).click();
		return driver;
	}

}
