package com.generic.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.config.BaseConfig;
import com.page.object.model.PropertyPage;
import com.util.Highlighter;
import com.util.ScreenShot;
import com.util.Wait;


public class HomeSelection {
	public static void selectProperty(WebDriver driver) throws Throwable {
		// getLogin();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyPage pfy = new PropertyPage(driver);

		pfy.getLocationName().sendKeys(BaseConfig.getconfig("locationName"));
		ScreenShot.getScreenShot(driver, "LocationName");
		new Highlighter().getcolor(driver, pfy.getLocationName(), "green", "red");
		
		new Highlighter().getcolor(driver, pfy.getSearchSubmit(), "green", "red");
		ScreenShot.getScreenShot(driver, "Search");
		pfy.getSearchSubmit().click();

		String[] prices;

		List<Integer> intPrices = new ArrayList<>();
		for (int i = 0; i < pfy.getHomePricesXpath().size(); i++) {
			// System.out.println(pfy.getHomePricesXpath().get(i).getText());
			prices = pfy.getHomePricesXpath().get(i).getText().split(" ");
			// System.out.println("Price= "+prices);
			intPrices.add(Integer.parseInt(prices[0].replace("£", "").replace(",", "").trim()));
		}
		System.out.println("House Prices: " + intPrices);
		Collections.reverse(intPrices);
		System.out.println("Home Prices Descending Sorted: " + intPrices);

		new Wait().getExplicitWait(driver, pfy.getHomePricesXpath().get(4));
		pfy.getHomePricesXpath().get(4).click();

		if (pfy.getPropertyLogo().isDisplayed()) {
			System.out.println("Logo is available");

		} else {
			System.out.println("Logo is not available");
		}
		System.out.println("Agent Name is: " + pfy.getAgentName().getText());

		System.out.println("Agent Phone Number: " + pfy.getAgentPhoneNum());

		ScreenShot.getScreenShot(driver, "Agent Details");

		Actions logout = new Actions(driver);
		logout.moveToElement(pfy.getMyZooplaBtn()).build().perform();
		new Highlighter().getcolor(driver, pfy.getMyZooplaBtn(), "green", "red");
		ScreenShot.getScreenShot(driver, "Log Out");

		new Highlighter().getcolor(driver, pfy.getSignOutBtn(), "green", "red");
		pfy.getSignOutBtn().click();
		
		driver.quit();
	}
}
