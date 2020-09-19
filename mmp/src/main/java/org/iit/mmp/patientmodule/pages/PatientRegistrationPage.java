package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;
import java.util.Random;

import org.iit.util.RandomDateOfBirth;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//Register functionality cannot be validated bcos sysem does not allow to login with new user details
//Hence did not complete the logic in page

public class PatientRegistrationPage {
	WebDriver driver;
	By registerButtton = By.xpath("//input[@value='Register']");

	public PatientRegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnRegisterButton() {
		driver.findElement(registerButtton).click();
	}

	public void registernValidateData() {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		Random random = new Random();

		WebElement fName = driver.findElement(By.id("firstname"));
		fName.clear();
		// To generate random number between 65 and 90
		int alphaNum = 65 + random.nextInt(26);
		String fNameVal = (char) alphaNum + "FName" + (char) alphaNum;
		fName.sendKeys(fNameVal);
		dataMap.put("fName", fNameVal);

		WebElement lName = driver.findElement(By.id("lastname"));
		lName.clear();
		// To generate random number between 65 and 90
		alphaNum = 65 + random.nextInt(26);
		String lNameVal = (char) alphaNum + "LName" + (char) alphaNum;
		lName.sendKeys(lNameVal);
		dataMap.put("lName", lNameVal);

		WebElement date = driver.findElement(By.id("datepicker"));
		date.clear();
		// generates date in yyyy-mm-dd format
		RandomDateOfBirth dob = new RandomDateOfBirth();
		String randomDOB = dob.randomDateOfBirth();
		date.sendKeys(randomDOB);
		dataMap.put("DOB", randomDOB);

		driver.findElement(By.name("register")).click();

	}
}
