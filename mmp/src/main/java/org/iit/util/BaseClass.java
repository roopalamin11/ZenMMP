package org.iit.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	// ** Here We are not using driver.FindElement so we can use it for all
	// projects
	// ** Can be used for all projects

	// ** Base class we inherit so it should have relationship

	// default
	protected WebDriver driver;

	@BeforeTest
	public void instantiateDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	// @AfterClass
	public void tearDriver() {
		driver.close();
	}
}
