package org.iit.mmp.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperClass {
//** Methods specifically for this project
//** e.g navigate to a module - can be used by anyone in the project
	WebDriver driver;

	public HelperClass(WebDriver driver) {
		this.driver = driver;
	}

	public void launchBrowser(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void login(String uname, String pwd, String url) {
		launchBrowser(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.name("submit")).click();
	}

	public void adminLogin(String uName, String password, String url) {
		launchBrowser(url);
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("admin")).click();

	}

	public void navigateToAModule(String module) {
		// Navigate to the required module
		driver.findElement(By.xpath("//span[contains(text(),'" + module + "')]")).click();
	}

	public boolean adminSearchPatientBySSN(String ssn) throws InterruptedException {
		// This method assumes that since the SSN is provided there will be only one
		// matching row. Hence the xpaths are derived accordingly

		driver.findElement(By.id("search")).sendKeys(ssn);
		driver.findElement(By.xpath("//input[@class='tfbutton']")).click();

		Boolean found = false;

		Thread.sleep(3000);

		WebElement nameWE = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]/a"));

		WebElement ssnWE = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));

		// If row found with Name displayed and SSN matches then click name hyperlink
		if ((nameWE.isDisplayed()) && (ssnWE.getText().equals(ssn))) {
			nameWE.click();
			found = true;
		} else
			found = false;
		return found;

		/*
		 * WebElement ssnWE = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
		 * List<WebElement> patientNameList = driver
		 * .findElements(By.xpath("//div[@id='show']/descendant::table/tbody/tr/td/a"));
		 * 
		 * for (int i = 1; i <= patientNameList.size(); i++) {
		 * System.out.println("inside for " + i); String ssnDisplayed = driver
		 * .findElement(By.xpath("//div[@id='show']/descendant::table/tbody/tr[" + i +
		 * "]/td[2]")).getText(); if (ssnDisplayed.equals(ssn)) {
		 * System.out.println("Inside if condition"); patientNameList.get(i -
		 * 1).click(); found = true; break; } } return found;
		 */

	}

}
