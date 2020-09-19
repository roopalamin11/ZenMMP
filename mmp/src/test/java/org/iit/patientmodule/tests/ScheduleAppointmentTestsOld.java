package org.iit.patientmodule.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iit.util.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ScheduleAppointmentTestsOld extends BaseClass {
	static HashMap<String, String> makeApptMap = new HashMap<String, String>();
	static HashMap<String, String> homepageMap = new HashMap<String, String>();
	static HashMap<String, String> scheduleApptMap = new HashMap<String, String>();

	// ***** TC#1 Login to the application
	@Test
	public void validateLogin() {
		// Assert.assertTrue(true);
		System.out.println("JS");
		System.setProperty("webdriver.chrome.driver", "C:\\Roopal\\SeleniumWork\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
		// driver.get("http://96.84.175.78/MMP-Release1-Integrated-Build.2.4.000");
		driver.manage().window().maximize();

		// Login to the NAMTG website
		driver.findElement(By.xpath("//a[@class='button button-alt'][contains(text(),'Login')]")).click();
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
	}

	// ***** TC#2 Click on Schedule appointment
	// *****TC#3 Click on the "Create New Appointment" button
	// ***** TC#4 Click "Book Appointment" under the Provider Name with whom the
	// appointment is to be scheduled.
	// ***** TC#5 Enter Date
	// ***** TC#6 Enter Time
	// ***** TC#7 Click the Continue button
	// ***** TC#8 Enter your symptoms and click Submit button

	@Test(dependsOnMethods = { "validateLogin" })
	public void makeAppt() {
		System.out.println("JS");

		// Click Schedule Appointment on left side options
		driver.findElement(By.xpath("//span[contains(.,'Schedule Appointment')]")).click();

		// *****TC#3 Click on the "Create New Appointment" button
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// Select a provider
		// hardcoded - need to change provider code to click button underneath the reqd
		// provider
		driver.findElement(
				By.xpath("//button[contains(@onclick,\"showUrlInDialog('bookdate.php?id=7&pid='); return false;\")]"))
				.click();

		// ***** TC#4 Click "Book Appointment" under the Provider Name with whom the
		// appointment is to be scheduled.

		// String providerName =
		// driver.findElement(By.xpath("//h4[contains(text(),'Dr.Annabeth')]"))
		// .getAttribute("value");

		String doctorName = ("Annabeth");
		// System.out.println("providerName " + doctorName);

		// Change focus to the new Frame for making New Appointment
		WebElement apptFrame = driver.findElement(By.id("myframe"));
		driver.switchTo().frame(apptFrame);

		// ***** TC#5 Enter Date
		WebElement we = driver.findElement(By.id("datepicker"));
		we.sendKeys("09/01/2020");

		String apptDate = driver.findElement(By.id("datepicker")).getAttribute("value");
		// System.out.println("apptDate " + apptDate);
		// Used tab to tab out of Calendar date field.
		we.sendKeys(Keys.TAB);

		// ***** TC#6 Enter Time
		// Can write single statement to select time value from ListBox
		WebElement we1 = driver.findElement(By.xpath("//option[@value='11Am']"));
		String apptTime = driver.findElement(By.xpath("//option[@value='11Am']")).getAttribute("value");
		// System.out.println("apptTime " + apptTime);
		we1.click();

		// Continue button is not interactable. Got error -
		// org.openqa.selenium.ElementNotInteractableException
		// Bcos the Time dropdown is hiding the Continue button
		// Hence write the below code

		// ***** TC#7 Click the Continue button
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement we2 = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ChangeHeatName']")));
		we2.click();

		// ***** TC#8 Enter your symptoms and click Submit button
		driver.findElement(By.id("sym")).sendKeys("sore throat");

		String symptoms = driver.findElement(By.id("sym")).getAttribute("value");
		// System.out.println("symptoms " + symptoms);

		// Now that we have all 4 data values - store them in hashMap - makeApptMap
		makeApptMap.put("Doctor", doctorName);
		makeApptMap.put("Date", apptDate);
		makeApptMap.put("Time", apptTime);
		makeApptMap.put("Appointment", symptoms);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}

	// ***** TC#9 Verify data on HomePage
	// This method checks if the data displayed on first row of Homepage matches the
	// data entered
	@Test(dependsOnMethods = { "makeAppt" })
	public void compareValuesOnHomePage() {

		System.out.println("JS");

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

	}

	// ***** TC#10 Click on Schedule appointment

	// This method checks if the data displayed Schedule Appointment page matches
	// the data entered
	@Test(dependsOnMethods = { "compareValuesOnHomePage" })
	public void compareValuesOnScheduleAppt() {
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
