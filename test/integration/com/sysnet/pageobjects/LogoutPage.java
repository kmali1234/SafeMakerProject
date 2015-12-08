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


	
	public LogoutPage(WebDriver driver, Properties props) {
		
		this.driver=driver;
		this.logoutlocator = By.xpath(".//*[@id='nav-dropdown__user']/ul/li[2]/a");
	}

	public  WebDriver userLogout(){
		WebElement menu = driver.findElement(By.xpath(".//*[@id='nav-dropdown__user']/a"));
		Actions builder = new Actions(driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(logoutlocator)); 
		driver.findElement(logoutlocator).click();
		return driver;
	}

}
