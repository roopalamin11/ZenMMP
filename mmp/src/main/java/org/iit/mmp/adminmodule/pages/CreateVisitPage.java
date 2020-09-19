package org.iit.mmp.adminmodule.pages;

import java.util.HashMap;

import org.iit.util.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateVisitPage {
	WebDriver driver;

	// Identifiers for Create Visit
	HashMap<String, String> createVisitHMap = new HashMap<String, String>();
	By clickBtn = By.xpath("//p[1]//a[1]//input[1]");
	String createVisitXpath = "//h4[contains(text(),'%doctorname%')]/ancestor::ul/following-sibling::button";
	By datepickerId = By.id("datepicker");
	By timeId = By.id("time");
	By continueButton = By.id("ChangeHeatName");
	By symptomsId = By.name("sym");
	String dateval = "09/11/2020";
	String timeval = "11Am";
	String symptoms = "cough and cold";

	// Identifiers for Add Prescription
	By addPrescriptionBtn = By.xpath("//p[2]//a[1]//input[1]");

	public CreateVisitPage(WebDriver driver) {
		this.driver = driver;
	}

	public HashMap<String, String> createVisit(String doctorName) throws InterruptedException {
		Thread.sleep(3000);
		// Click on Create Visit button
		WebElement continueWE = AppLibrary.waitforElementClickable(driver, 10, clickBtn);
		continueWE.click();

		createVisitXpath = createVisitXpath.replace("%doctorname%", doctorName);
		driver.findElement(By.xpath(createVisitXpath)).click();

		WebElement iframe = driver.findElement(By.id("myframe"));
		driver.switchTo().frame(iframe);
		driver.findElement(datepickerId).sendKeys(dateval);

		Select time = new Select(driver.findElement(timeId));
		time.selectByVisibleText(timeval);

		// Continue button is not intractable. Got error -
		// org.openqa.selenium.ElementNotInteractableException
		// Bcos the Time dropdown is hiding the Continue button
		// Hence added wait in below code

		WebElement continueBtnWE = AppLibrary.waitforElementClickable(driver, 10, continueButton);
		continueBtnWE.click();

		Thread.sleep(3000);
		driver.findElement(symptomsId).clear();
		driver.findElement(symptomsId).sendKeys(symptoms);

		// Now that we have all 4 data values - store them in hashMap - makeApptMap
		createVisitHMap.put("doctor", doctorName);
		createVisitHMap.put("date", dateval);
		createVisitHMap.put("time", timeval);
		createVisitHMap.put("symptoms", symptoms);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		return createVisitHMap;
	}

	public void addPrescription() {
		System.out.println("In Add Prescription");
		driver.findElement(addPrescriptionBtn).click();

	}
}
