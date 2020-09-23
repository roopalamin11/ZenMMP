package org.iit.mmp.patientmodule.pages;

import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.MessagesAdminModulePage;
import org.iit.mmp.helper.HelperClass;
import org.iit.util.AppLibrary;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MessagesPatientModulePage {

	private WebDriver driver;

	private By msgReason = By.id("subject");
	private By detailSubject = By.id("message");
	private By sendBtn = By.xpath("//tr[4]//td[1]//input[1]");
	private By firstName = By.id("fname");
	private String actualMsg;

	private HelperClass helperObject;
	private By editBtn = By.id("Ebtn");
	boolean result;

	public MessagesPatientModulePage(WebDriver driver) {
		this.driver = driver;
		helperObject = new HelperClass(driver);
	}

	// Retrieves First Name of the Patient from Profile page
	public String retrieveFirstName() {
		helperObject.navigateToModule("Profile");
		driver.findElement(editBtn).click();
		// With all the regular elements, you would use getText() to get the "text" of
		// an element, but input elements are special - the "text" is kept under the
		// value attribute.
		String name = driver.findElement(firstName).getAttribute("value");
		return name;
	}

	// Enter message details and click Send
	public void sendMessage(String subject, String message) {
		driver.findElement(msgReason).sendKeys(subject);
		driver.findElement(detailSubject).sendKeys(message);
		WebElement WE = AppLibrary.waitforElementClickable(driver, 10, sendBtn);
		WE.click();
	}

	// Vallidate Alert message displayed and return message Displayed
	public String validateMessageSend() {
		try {
			Alert alert = driver.switchTo().alert();
			actualMsg = alert.getText();
			alert.accept();
		} catch (Exception e) {
			System.out.println("Alert Not Present : " + e.getMessage());
		}
		return actualMsg;
	}

	public boolean validateMessageFromAdminModule(String adminName, String adminPassword, String urlAdmin, String name,
			String subject, String message) {
		result = false;

		// Launch Admin URL
		helperObject.launchApplicationURL(urlAdmin);

		// Enter Admin Login details
		helperObject.adminLogin(adminName, adminPassword);

		// Navigate to Messages in Admin Module
		helperObject.navigateToModule("Messages");

		// Retrieve recent message on Admin Messages Page
		MessagesAdminModulePage AdminMsgPage = new MessagesAdminModulePage(driver);
		HashMap<String, String> AdminMsgHMap = new HashMap<String, String>();
		AdminMsgHMap = AdminMsgPage.retrieveRecentMessageDetails();

		// Compare retrieved recent message on Admin Message Page with info passed in
		// hashMap from Patient Message Page
		if (AdminMsgHMap.get("Subject").equals(subject) && AdminMsgHMap.get("Message").equals(message)
				&& AdminMsgHMap.get("Name").equals(name)) {
			result = true;
		}

		return result;
	}
}
