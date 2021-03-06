package com.generic.code;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import com.config.BaseConfig;
import com.page.object.model.LoginsPage;
import com.util.Wait;
import com.util.Highlighter;
import com.util.ScreenShot;

public class TempClassDNU {
	protected static WebDriver driver;
	public static void getLogin() throws Throwable {
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		driver = new ChromeDriver();
		LoginsPage login = new LoginsPage(driver);
		
		driver.manage().window().maximize();
		driver.get(BaseConfig.getconfig("URL"));
				
		login.getAcceptcookies().click();

		Highlighter.getcolor(driver, login.getSignin());				
		ScreenShot.getScreenShot(driver, "SignInPage");
		login.getSignin().click();
		
		new Wait().getExplicitWait(driver, login.getEmail());
		
		login.getEmail().sendKeys(BaseConfig.getconfig("userName"));
		Highlighter.getcolor(driver, login.getEmail(),"green");
				
		login.getPasswrd().sendKeys(BaseConfig.getconfig("passWord"));
		Highlighter.getcolor(driver, login.getPasswrd(),"yellow");		
				
		Highlighter.getcolor(driver, login.getLogin());		
		ScreenShot.getScreenShot(driver, "Login Page");
		login.getLogin().click();
		System.out.println("Title of the Page is: "+driver.getTitle());
		ScreenShot.getScreenShot(driver, "Home Page");
		
	}	
	
}
