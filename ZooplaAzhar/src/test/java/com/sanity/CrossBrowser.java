package com.sanity;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.generic.code.BaseLoginCrossBrowser;
import com.generic.code.HomeSelection;

public class CrossBrowser {
	
	WebDriver driver;
	
	@Test
	@Parameters("BrowserName")
	public void getHome(String BrowserValue) throws Throwable {
		driver = BaseLoginCrossBrowser.getLogin(BrowserValue);
		HomeSelection.selectProperty(driver);
	}	
	
	@AfterTest
	public void exit() {
		driver.quit();
	}



}
