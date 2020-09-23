package org.iit.mmp.adminmodule.pages;

import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.util.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateVisitPage {
	WebDriver driver;

	// Identifiers for Create Visit
	private HashMap<String, String> createVisitHMap = new HashMap<String, String>();
	private By clickBtn = By.xpath("//p[1]//a[1]//input[1]");
	private String createVisitXpath = "//h4[contains(text(),'%doctorname%')]/ancestor::ul/following-sibling::button";
	private By datepickerId = By.id("datepicker");
	private By timeId = By.id("time");
	private By continueButton = By.id("ChangeHeatName");
	private By symptomsId = By.name("sym");
	private String dateval = "09/11/2020";
	private String timeval = "11Am";
	private String symptoms = "cough and cold";
	private HelperClass helperObject;

	// Identifiers for Add Prescription
	By addPrescriptionBtn = By.xpath("//p[2]//a[1]//input[1]");

	public CreateVisitPage(WebDriver driver) {
		this.driver = driver;
		helperObject = new HelperClass(driver);
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

	/*
	 * public boolean validateAppointmentDetailsinHomePage(HashMap<String, String>
	 * hMap) { boolean result = false; helperObject.navigateToModule("HOME"); if
	 * (hMap.get("dateOfAppointment").equals(driver.findElement(dateOfAppointmentHP)
	 * .getText()) && hMap.get("time").equals(driver.findElement(timeHP).getText())
	 * && hMap.get("symptoms").equals(driver.findElement(symptomsHP).getText()) &&
	 * hMap.get("doctorName").contains(driver.findElement(doctorNameHP).getText()))
	 * { result = true; } return result; }
	 */

	public void addPrescription() {
		System.out.println("In Add Prescription");
		driver.findElement(addPrescriptionBtn).click();

	}
}
