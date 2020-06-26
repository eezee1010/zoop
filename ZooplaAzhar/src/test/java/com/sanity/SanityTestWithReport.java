package com.sanity;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.generic.code.BaseLogins;
import com.generic.code.HomeSelection;
import com.report.ExtentTestManager;

public class SanityTestWithReport {
	WebDriver driver;
	ExtentTest test;
	
	@BeforeTest
	public void setup() throws Throwable {
		test = ExtentTestManager.startTest("Regression_tc1"); 
		test.assignCategory("Regression Test");
		test.createNode("setup");
		
		driver = BaseLogins.getLogin();
	}
	@Test
	public void getHome() throws Throwable {
		HomeSelection.selectProperty(driver);
	}	
	
	@AfterTest
	public void exit() {
		driver.quit();
		ExtentTestManager.endTest();
	}


}
