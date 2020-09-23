package org.iit.mmp.adminmodule.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MessagesAdminModulePage {

	HashMap<String, String> hMap;
	private By patientName = By.xpath("//table[@class='table']//tr[2]/td[1]");
	private By subjectMessage = By.xpath("//table[@class='table']//tr[2]/td[2]");
	private By descriptionMessage = By.xpath("//table[@class='table']//tr[3]/td[2]");
	private WebDriver driver;
	private String name;
	private String subject;
	private String message;

	public MessagesAdminModulePage(WebDriver driver) {
		this.driver = driver;
		hMap = new HashMap<String, String>();
	}

	public HashMap<String, String> retrieveRecentMessageDetails() {
		hMap = new HashMap<String, String>();
		name = driver.findElement(patientName).getText();
		subject = driver.findElement(subjectMessage).getText();
		message = driver.findElement(descriptionMessage).getText();

		hMap.put("Name", name);
		hMap.put("Subject", subject);
		hMap.put("Message", message);
		return hMap;
	}
}
