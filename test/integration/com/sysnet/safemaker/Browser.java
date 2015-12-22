package com.sysnet.safemaker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {

	private WebDriver driver;
	private DesiredCapabilities dCaps;

	public WebDriver getdriver(String browser) {
		System.out.println(browser + " is uesd to run script");
		switch (browser) {

		case "Firefox": {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			System.out.println("Firefox browser is selected");
			break;
		}
		case "Chrome": {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			System.out.println("Chrome browser is selected");

			break;
		}
		case "IE10": {

			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;
		}
		case "phanthomjs": {
			dCaps = new DesiredCapabilities();
			dCaps.setJavascriptEnabled(true);
			dCaps.setCapability("takesScreenshot", false);

			driver = new PhantomJSDriver(dCaps);
			break;

		}
		}

		return driver;
	}
}
