package com.sanity;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.generic.code.BaseLogins;
import com.generic.code.HomeSelection;

public class SanityTestWithTestNG {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() throws Throwable {
		driver = BaseLogins.getLogin();
	}
	@Test
	public void getHome() throws Throwable {
		HomeSelection.selectProperty(driver);
	}	
	
	@AfterTest
	public void exit() {
		driver.quit();
	}

}
