package com.sysnet.pageobjects;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage {

	private WebDriver driver;
	private By logoutlocator;
	private By logoutmenulocator;


	
	public LogoutPage(WebDriver driver, Properties props) {
		
		this.driver=driver;
		this.logoutlocator = By.xpath(props.getProperty("logout.link.xpath"));
		this.logoutmenulocator = By.xpath(props.getProperty("logout.menu.xpath"));
	}

	public  WebDriver userLogout(){
		WebElement menu = driver.findElement(logoutmenulocator);
		Actions builder = new Actions(driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(logoutlocator)); 
		driver.findElement(logoutlocator).click();
		return driver;
	}

}
