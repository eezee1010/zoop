package com.generic.code;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.config.BaseConfig;
import com.page.object.model.LoginsPage;
import com.util.Highlighter;
import com.util.ScreenShot;
import com.util.Wait;

import org.openqa.selenium.WebElement;

public class BaseLogins {

	//protected static WebDriver driver;

	public static WebDriver getLogin() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		WebDriver driver = new ChromeDriver();
		LoginsPage logins = new LoginsPage(driver);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(BaseConfig.getconfig("URL"));

		System.out.println(driver.getTitle());

		System.out.println(driver.getCurrentUrl());

		logins.getAcceptcookies().click();

		new Highlighter().getcolor(driver, logins.getSignin(), "green", "red");
		ScreenShot.getScreenShot(driver, "SignInPage");
		logins.getSignin().click();

		new Wait().getExplicitWait(driver, logins.getEmail());
		new Highlighter().getcolor(driver, logins.getEmail(), "green", "red");
		logins.getEmail().sendKeys(BaseConfig.getconfig("email"));
		
		new Highlighter().getcolor(driver, logins.getPasswrd(),  "green", "red");
		logins.getPasswrd().sendKeys(BaseConfig.getconfig("pass"));
		

		new Highlighter().getcolor(driver, logins.getLogin(), "green", "red");
		ScreenShot.getScreenShot(driver, "Login page");
		logins.getLogin().click();

		System.out.println("Title of the Page is: " + driver.getTitle());
		ScreenShot.getScreenShot(driver, "Home Page");
		return driver;
		
	}
	
}
