package com.sysnet.safemaker;

import java.util.Properties;

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

	public WebDriver getdriver(String browser, Properties clientProps) throws Exception {
		System.out.println(browser + " is uesd to run script");
		switch (browser.toUpperCase()) {

		case "FIREFOX": {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			System.out.println("Firefox browser is selected");
			break;
		}
		case "CHROME": {
			System.setProperty("webdriver.chrome.driver", "C:\\seleniumdrivers\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--start-maximized");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("Chrome browser is selected");

			break;
		}
		case "IE10": {
			
			System.setProperty("webdriver.ie.driver", "C:\\seleniumdrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;
		}
		case "PHANTHOMJS": {
			dCaps = new DesiredCapabilities();
			dCaps.setJavascriptEnabled(true);
			dCaps.setCapability("takesScreenshot", false);

			driver = new PhantomJSDriver(dCaps);
			break;

		}
		}
		Thread.sleep(Integer.parseInt(clientProps.getProperty("delay.waitsecond.timeunits.seconds")));
		return driver;
	}
}
