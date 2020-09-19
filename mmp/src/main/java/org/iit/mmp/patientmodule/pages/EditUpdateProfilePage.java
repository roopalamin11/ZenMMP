package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditUpdateProfilePage {

	WebDriver driver;
	By editBtn = By.id("Ebtn");
	By saveBtn = By.id("Sbtn");
	By ageID = By.id("age");
	By weightID = By.id("weight");

	Random rand = new Random();

	HashMap<String, String> edithMap = new HashMap<String, String>();
	HashMap<String, String> updatedhMap = new HashMap<String, String>();

	public EditUpdateProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickEdit() {
		driver.findElement(editBtn).click();
	}

	public void editWeight() {
		String randomWeight = 10 + rand.nextInt(80) + "";
		WebElement we = driver.findElement(weightID);
		we.clear();
		we.sendKeys(randomWeight);
		edithMap.put("weight", randomWeight);
	}

	public void editAge() {
		String randomAge = 10 + rand.nextInt(80) + "";
		WebElement we = driver.findElement(ageID);
		we.clear();
		we.sendKeys(randomAge);
		edithMap.put("age", randomAge);
	}

	public void clickSave() {
		driver.findElement(saveBtn).click();
	}

	public String acceptAlert() {
		Alert alrt = driver.switchTo().alert();
		String alrtMsg = alrt.getText();
		alrt.accept(); /*******************/
		return alrtMsg;
	}

	public HashMap<String, String> editAllFields() {
		editWeight();
		editAge();
		return edithMap;
	}

	public HashMap<String, String> getAllFields() {
		updatedhMap.put("weight", driver.findElement(weightID).getAttribute("value"));
		updatedhMap.put("age", driver.findElement(ageID).getAttribute("value"));
		return updatedhMap;
	}

}
