package com.generic.code;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.config.BaseConfig;
import com.page.object.model.LoginsPage;
import com.util.Highlighter;
import com.util.ScreenShot;
import com.util.Wait;

public class BaseLoginCrossBrowser {
	
	static WebDriver driver;

	public static WebDriver getLogin(String browser) throws Throwable {
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}			

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(BaseConfig.getconfig("URL"));

		System.out.println(driver.getTitle());

		System.out.println(driver.getCurrentUrl());
		
		LoginsPage logins = new LoginsPage(driver);

		logins.getAcceptcookies().click();

		new Highlighter().getcolor(driver, logins.getSignin(), "green", "red");
		ScreenShot.getScreenShot(driver, "SignInPage");
		logins.getSignin().click();

		new Wait().getExplicitWait(driver, logins.getEmail());
		Highlighter.getcolor(driver, logins.getEmail(), "green", "red");
		logins.getEmail().sendKeys(BaseConfig.getconfig("email"));
		

		logins.getPasswrd().sendKeys(BaseConfig.getconfig("pass"));
		Highlighter.getcolor(driver, logins.getPasswrd(),  "green", "red");

		Highlighter.getcolor(driver, logins.getLogin(), "green", "red");
		ScreenShot.getScreenShot(driver, "Login page");
		logins.getLogin().click();

		System.out.println("Title of the Page is: " + driver.getTitle());
		ScreenShot.getScreenShot(driver, "Home Page");
		return driver;

	}
}
