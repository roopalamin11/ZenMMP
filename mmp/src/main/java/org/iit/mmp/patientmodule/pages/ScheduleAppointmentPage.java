package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.util.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {
	WebDriver driver;
	By createNewAppointment = By.xpath("//input[@value='Create new appointment']");
	String schAppointmentXpath = "//h4[contains(text(),'%doctorname%')]/ancestor::ul/following-sibling::button";
	String moduleName = "//span[contains(text(),'%moduleName%')]";
	By datepickerId = By.id("datepicker");
	By timeId = By.id("time");
	By continueButton = By.id("ChangeHeatName");
	By symptomsId = By.id("sym");
	String dateval = "09/11/2020";
	String timeval = "11Am";
	String symptoms = "sore throat";

	// HomePage identifiers
	By dateHomePage = By.xpath("//table[@class='table']/tbody/tr[1]/td[1]");
	By timeHomePage = By.xpath("//table[@class='table']/tbody/tr[1]/td[2]");
	By symptomsHomePage = By.xpath("//table[@class='table']/tbody/tr[1]/td[3]");
	By doctorHomePage = By.xpath("//table[@class='table']/tbody/tr[1]/td[4]");

	// Schedule Appt Page identifiers
	By dateSchApptPage = By.xpath("(//h3[@class='panel-title'])[2]");
	By timeSchApptPage = By.xpath("//a[contains(text(),'Time')]");
	By symptomsSchApptPage = By.xpath("//a[contains(text(),'Symptoms')]");
	By doctorSchApptPage = By.xpath("//a[contains(text(),'Provider')]");

	HashMap<String, String> makeApptMap = new HashMap<String, String>();

	public ScheduleAppointmentPage(WebDriver driver) {
		this.driver = driver;
	}

	public HashMap<String, String> schAppointment(String doctorName) {

		driver.findElement(createNewAppointment).click();

		schAppointmentXpath = schAppointmentXpath.replace("%doctorname%", doctorName);
		driver.findElement(By.xpath(schAppointmentXpath)).click();

		// Change focus to the new Frame for making New Appointment
		WebElement apptFrame = driver.findElement(By.id("myframe"));
		driver.switchTo().frame(apptFrame);

		driver.findElement(datepickerId).sendKeys(dateval);

		Select time = new Select(driver.findElement(timeId));
		time.selectByVisibleText(timeval);

		// Continue button is not intractable. Got error -
		// org.openqa.selenium.ElementNotInteractableException
		// Bcos the Time dropdown is hiding the Continue button
		// Hence added wait in below code

		WebElement continueWE = AppLibrary.waitforElementClickable(driver, 10, continueButton);
		continueWE.click();

		driver.findElement(symptomsId).sendKeys(symptoms);

		// Now that we have all 4 data values - store them in hashMap - makeApptMap
		makeApptMap.put("doctor", doctorName);
		makeApptMap.put("date", dateval);
		makeApptMap.put("time", timeval);
		makeApptMap.put("symptoms", symptoms);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		return makeApptMap;

	}

	// Verify data on HomePage
	// This method checks if the data displayed on first row of Homepage matches the
	// data entered

	public boolean compareValuesOnHomePage(HashMap<String, String> makeApptMap) {
		boolean match = false;
		if (makeApptMap.get("date").equals(driver.findElement(dateHomePage).getText())
				&& makeApptMap.get("time").equals(driver.findElement(timeHomePage).getText())
				&& makeApptMap.get("symptoms").equals(driver.findElement(symptomsHomePage).getText())
				&& makeApptMap.get("doctor").equals(driver.findElement(doctorHomePage).getText())) {
			match = true;
		}
		return match;
	}

	// Click on Schedule appointment
	// This method checks if the data displayed Schedule Appointment page matches
	// the data entered

	public boolean compareValuesOnScheduleAppt(HashMap<String, String> makeApptMap) {
		boolean match = false;
		if (makeApptMap.get("date").equals(driver.findElement(dateSchApptPage).getText())
				&& makeApptMap.get("time").equals(driver.findElement(timeSchApptPage).getText().split(":")[1].trim())
				&& makeApptMap.get("doctor")
						.equals(driver.findElement(doctorSchApptPage).getText().split(":")[1].trim())
				&& makeApptMap.get("symptoms")
						.equals(driver.findElement(symptomsSchApptPage).getText().split(":")[1].trim())) {
			match = true;
		}
		return match;
	}

}
