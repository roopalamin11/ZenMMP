package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPageOld {

	By createNewAppointment = By.xpath("//input[@value='Create new appointment']");
	String bookAppointmentXpath = "//h4[contains(text(),'%doctorname%')]/ancestor::ul/following-sibling::button";
	String moduleName = "//span[contains(text(),'%moduleName%')]";
	By datepickerId = By.id("datepicker");
	By timeId = By.id("time");
	By continueButton = By.id("ChangeHeatName");
	By symptomsId = By.id("sym");

	HashMap<String, String> makeApptMap = new HashMap<String, String>();
	HashMap<String, String> homepageMap = new HashMap<String, String>();
	HashMap<String, String> scheduleApptMap = new HashMap<String, String>();

	public void navigateToAModule(WebDriver driver, String module) {
		System.out.println(" In navigateToAModule");

		// Navigate to the required module
		driver.findElement(By.xpath("//span[contains(text(),'" + module + "')]")).click();
		System.out.println("module  " + module);
		System.out.println("out navigateToAModule");
	}

	public void scheduleAppointment(WebDriver driver, String doctorName) {
		System.out.println(" In scheduleAppointment");
		driver.findElement(createNewAppointment).click();

		bookAppointmentXpath = bookAppointmentXpath.replace("%doctorname%", doctorName);
		System.out.println("bookAppointmentXpath " + bookAppointmentXpath);
		driver.findElement(By.xpath(bookAppointmentXpath)).click();

		// Change focus to the new Frame for making New Appointment
		WebElement apptFrame = driver.findElement(By.id("myframe"));
		driver.switchTo().frame(apptFrame);

		driver.findElement(datepickerId).sendKeys("09/05/2020");

		Select time = new Select(driver.findElement(timeId));
		time.selectByVisibleText("11Am");

		driver.findElement(continueButton).click();

		// ***** TC#8 Enter your symptoms and click Submit button
		driver.findElement(symptomsId).sendKeys("sore throat");

		// String symptoms = driver.findElement(By.id("sym")).getAttribute("value");
		// System.out.println("symptoms " + symptoms);

		// Now that we have all 4 data values - store them in hashMap - makeApptMap
		makeApptMap.put("Doctor", doctorName);
		makeApptMap.put("Date", "09/05/2020");
		makeApptMap.put("Time", "11Am");
		makeApptMap.put("Appointment", "sore throat");

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		System.out.println(" out scheduleAppointment");
	}

	// ***** TC#9 Verify data on HomePage
	// This method checks if the data displayed on first row of Homepage matches the
	// data entered
	public void compareValuesOnHomePage(WebDriver driver) {
		System.out.println("in compareValuesOnHomePage");
		List<WebElement> trList = driver.findElements(By.xpath("//table/tbody/tr"));
		System.out.println("trlist.size " + trList.size());
		WebElement we = trList.get(0);
		String trData = we.getText();
		// System.out.println(trData);

		List<WebElement> header = driver.findElements(By.xpath("//table/thead"));
		List<WebElement> thList = header.get(0).findElements(By.tagName("th"));
		int headerCount = thList.size();
		// System.out.println("headerCount " + headerCount);

		List<WebElement> tdList = trList.get(0).findElements(By.tagName("td"));
		int noOfCols = tdList.size();
		// System.out.println("noOfCols " + noOfCols);

		for (int l = 0; l < headerCount; l++) {
			// System.out.println(thList.get(l).getText());
			// System.out.println(tdList.get(l).getText());
			homepageMap.put(thList.get(l).getText(), tdList.get(l).getText());
		}

		// Printing to check for blank key
		for (String k : homepageMap.keySet()) {
			System.out.println(homepageMap.get(k));
		}

		System.out.println("+++++++++++++++++++++++Printing makeApptMap");
		for (Map.Entry<String, String> entry : makeApptMap.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			System.out.println("makeappt HashMap - Key, " + key + " value " + value);
		}

		System.out.println("+++++++++++++++++++++++ homepageMap");
		for (Map.Entry<String, String> entry : homepageMap.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			System.out.println("*" + entry.getKey().toString() + "*");
			System.out.println("length " + key.length());
			String keyTrim = key.trim();
			if (keyTrim.length() < 1) {
				// System.out.println("Removing");
				homepageMap.remove(" ");
				break;
			} else {
				// System.out.println("Not removing");
			}
			System.out.println("homepageMap HashMap - Key " + key + " value " + value);
		}

		System.out.println("Again +++++++++++++++++++++++ homepageMap after removing blank row");
		for (Map.Entry<String, String> entry : homepageMap.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			// System.out.println("length " + key.length());
			System.out.println("homepageMap HashMap - Key " + key + " value " + value);
		}

		if (makeApptMap.equals(homepageMap)) {
			System.out.println("makeAppt & Homepage Hash Maps equal");
		} else {
			System.out.println("makeAppt & Homepage Hash Maps not equal");
		}
		System.out.println(" out compareValuesOnHomePage");
	}

	// ***** TC#10 Click on Schedule appointment

	// This method checks if the data displayed Schedule Appointment page matches
	// the data entered
	public void compareValuesOnScheduleAppt(WebDriver driver) {
		// Click on Schedule Appointment
		driver.findElement(By.xpath("//a[@href='sheduleappointments.php']")).click();

		WebElement weDate = driver.findElement(By.xpath("//h3[contains(.,'09/01/2020')]"));
		String date = weDate.getText();
		// System.out.println("Schedule" + date);

		WebElement weTime = driver.findElement(By.xpath("//a[contains(text(),'Time : 11Am')]"));
		String time = weTime.getText();
		time = time.replaceAll("Time : ", "");
		// System.out.println("Schedule " + time);

		WebElement weDoctor = driver.findElement(By.xpath("//a[contains(.,'Provider:Annabeth')]"));
		String doctor = weDoctor.getText();
		doctor = doctor.replaceAll("Provider:", "");
		// System.out.println("Schedule " + doctor);

		WebElement weSymptoms = driver.findElement(By.xpath("//a[contains(.,'sore throat')]"));
		String symptoms = weSymptoms.getText();
		symptoms = symptoms.replaceAll("Symptoms:", "");
		// System.out.println("Schedule " + symptoms);

		scheduleApptMap.put("Date", date);
		scheduleApptMap.put("Time", time);
		scheduleApptMap.put("Doctor", doctor);
		scheduleApptMap.put("Appointment", symptoms);

		System.out.println("+++++++++++++++++++++++ scheduleApptMap");
		for (Map.Entry<String, String> entry : scheduleApptMap.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			System.out.println("key scheduleApptMap, " + key + " value " + value);
		}

		System.out.println("+++++++++++++++++++++++ makeApptMap");
		for (Map.Entry<String, String> entry : makeApptMap.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			System.out.println("key makeApptMap, " + key + " value " + value);
		}

		if (makeApptMap.equals(scheduleApptMap)) {
			System.out.println("makeAppt & scheduleApptMap HashMaps are equal");
		} else {
			System.out.println("makeAppt & scheduleApptMap Hash Maps are not equal");
		}
	}

}
