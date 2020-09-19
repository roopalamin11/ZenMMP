package org.iit.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppLibrary {

//e.g Enum can be defined here (for datePicker)
//Here We are not using driver.FindElement so we can use it for all projects

//** All methods in AppLibrary you have to make it as static

//** Anything here will be used in all projects
//e.g WaitForElementClickable, WaitForElementExistence, SwtichToAFrame, Random Number Generation
	// Methods that we put in conditional statements that are better to keep in App
	// Library so that we can call diferent parameter values.

	// Logic should come in AppLibrary not in helper class

	public static WebElement waitforElementClickable(WebDriver driver, long timeInSecs, By element) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		WebElement we = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
		return we;
	}

}
