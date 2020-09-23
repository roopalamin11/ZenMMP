package org.iit.mmp.helper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HelperClass {
//** Methods specifically for this project
//** e.g navigate to a module - can be used by anyone in the project
	WebDriver driver;

	public HelperClass(WebDriver driver) {
		this.driver = driver;
	}

	public void launchApplicationURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void login(String uname, String pwd) {
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.name("submit")).click();
	}

	public void adminLogin(String uName, String password) {
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("admin")).click();
	}

	public void navigateToModule(String module) {
		// Navigate to the required module
		// Using Actions class bcos of error in Admin Module Send Messages tests --
		// org.openqa.selenium.ElementClickInterceptedException
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'" + module + "')]"))).click().perform();
	}

	public void switchToSideBar() {
		driver.findElement(By.xpath("//div[@class='left-sidebar']")).click();
	}

}
