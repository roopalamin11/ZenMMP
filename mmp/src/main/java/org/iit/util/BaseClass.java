package org.iit.util;

import java.io.IOException;
import java.util.Properties;

import org.iit.config.ProjectConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	// ** Here We are not using driver.FindElement so we can use it for all
	// projects
	// ** Can be used for all projects

	// ** Base class we inherit so it should have relationship

	// default
	protected WebDriver driver;
	protected Properties prop;
	String browser;

	public void instantiateDriver() throws IOException {
		ProjectConfiguration pConfig = new ProjectConfiguration();
		prop = pConfig.loadProperties();
		browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
	}

	/*
	 * @BeforeTest public void instantiateDriver() {
	 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
	 * 
	 * }
	 */

	// @AfterClass
	public void tearDriver() {
		driver.close();
	}
}
