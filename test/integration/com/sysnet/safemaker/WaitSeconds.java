package com.sysnet.safemaker;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class WaitSeconds {
	

	public static void TimeDelay(WebDriver driver, Properties props){
		
		int sec =Integer.parseInt(props.getProperty("delay.waitsecond.timeunits.seconds"));
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

}
