package com.generic.code;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.util.Highlighter;

public class AllBaseLoginDnd {

	public static void main(String[] args) throws Throwable {		
		File src;
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		System.setProperty("ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY", "true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		
		WebDriver driver = new ChromeDriver();	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		driver.manage().window().maximize();
		driver.get("https://www.zoopla.co.uk/");		
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		//Cookies Page
		WebElement acceptcookies = driver.findElement(By.xpath("//*[@class='ui-button-primary ui-cookie-accept-all-medium-large']"));
		acceptcookies.click();
		
		WebElement signin = driver.findElement(By.xpath("//*[@class='button button--tertiary-dark account-link__text']"));
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",signin);
		
		src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,new File("./ScreenShots/SignIn.jpg"));		
		signin.click();
		//login page
		WebDriverWait obj = new WebDriverWait(driver, 30);
		
		obj.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='signin_email']"), 0));		
		WebElement email = driver.findElement(By.xpath("//*[@id='signin_email']"));
		email.sendKeys("eezee1010@hotmail.com");
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",email);
		
		WebElement passwrd = driver.findElement(By.xpath("//*[@name='signin_password']"));
		passwrd.sendKeys("love7232");
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",passwrd);
		
		WebElement login = driver.findElement(By.xpath("//*[@id='signin_submit']"));
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",login);
		login.click();
		
		obj.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search-input-location']")));		
		WebElement locationName = driver.findElement(By.xpath("//input[@id='search-input-location']"));
		locationName.sendKeys("New York, Lincolnshire");
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",locationName);
				
		WebElement searchSubmit = driver.findElement(By.xpath("//button[@id='search-submit']"));		
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",searchSubmit);
		searchSubmit.click();			
		
		//List<Integer>intPrices = new ArrayList<>();
		obj.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='listing-results-price text-price']")));
		
		List<WebElement> homePricesXpath = driver.findElements(By.xpath("//a[@class='listing-results-price text-price']"));
		String [] prices;
		//System.out.println("Total prices: "+homePricesXpath.size());
		int intPrice;
		List<Integer> price = new ArrayList<>();
		
		for (int i = 0; i<homePricesXpath.size();i ++) {
			
		//	System.out.println(homePricesXpath.get(i).getText());
			prices = homePricesXpath.get(i).getText().split(" "); 
		//	System.out.println("Price Array: "+Arrays.toString(prices));
		//	System.out.println(prices[0].replace("£", "").replace(",",""));
			intPrice = Integer.parseInt(prices[0].replace("£", "").replace(",",""));  //Type casting from string to int
			price.add(intPrice);			
			
		//	homePrice.add(Integer.parseInt(homePrice[0].replace("£", "").replace(",","");		
	
		}
		System.out.println(price);
		Collections.sort(price);
		System.out.println("Sorted Asc Price: "+price);
		Collections.reverse(price);
		System.out.println("Sorted desc Price: "+price);		
		homePricesXpath.get(4).click();		
		
		obj.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='js-lazy-loaded']")));
		WebElement propertyLogo = driver.findElement(By.xpath("//img[@class='js-lazy-loaded']"));
		
		if (propertyLogo.isDisplayed()){
			System.out.println("Logo is available");
		}
		else {
			System.out.println("Logo is not available");
		}
		//agent info
		obj.until(ExpectedConditions.elementToBeClickable(By.xpath("(//h4[@class='ui-agent__name'])[1]")));		
		WebElement agentName = driver.findElement(By.xpath("(//h4[@class='ui-agent__name'])[1]"));		
		System.out.println("Agent Name is = "+agentName.getText());
		
		WebElement agentPhoneNum = driver.findElement(By.xpath("(//*[@class='ui-link'])[4]"));
		System.out.print("Agent Number is ="+agentPhoneNum.getText().replace("Call", ""));
				
		WebElement myZooplaBtn = driver.findElement(By.xpath("//*[@id='header-account-panel__signed-in-link']"));
		Actions action = new Actions(driver);
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",myZooplaBtn);
		action.moveToElement(myZooplaBtn).perform();
		
		WebElement signOutBtn = driver.findElement(By.xpath("//span[contains(text(),'Sign out')]"));
		js.executeScript("arguments[0].setAttribute('style','background:green; border:4px solid yellow;');",signOutBtn);
		signOutBtn.click();		
		
		driver.quit();
		
	}
	
}
