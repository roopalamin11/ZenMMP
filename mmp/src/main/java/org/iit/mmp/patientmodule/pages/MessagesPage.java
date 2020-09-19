package org.iit.mmp.patientmodule.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesPage {

	WebDriver driver;
	By msgReason = By.id("subject");
	By detailSubject = By.id("message");
	By sendBtn = By.xpath("//tr[4]//td[1]//input[1]");

	String msgReasonTxt = "Message Reason";
	String detailSubjectTxt = "Message details..........";
	String actualMsg;

	public MessagesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterMessageDetails() {
		driver.findElement(msgReason).sendKeys(msgReasonTxt);
		driver.findElement(detailSubject).sendKeys(detailSubjectTxt);
		driver.findElement(sendBtn).click();
	}

	/*
	 * public boolean validateMessageSent() { if
	 * (driver.switchTo().alert().getText().equals(actualMsg)) return true; else
	 * return false; }
	 */

	public String validateMessageSend() {

		System.out.println("Validating.....");
		try {
			Alert alert = driver.switchTo().alert();
			actualMsg = alert.getText();
			alert.accept();
		} catch (Exception e) {
			System.out.println("Alert Not Present : " + e.getMessage());
		}
		return actualMsg;
	}
}
