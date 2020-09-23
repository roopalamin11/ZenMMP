package org.iit.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.MessagesPatientModulePage;
import org.iit.util.AppLibrary;
import org.iit.util.BaseClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class MessagesPatientModuleTests extends BaseClass {
	private String filePath = System.getProperty("user.dir") + "\\Data\\LoginTestData.xls";
	private String sheetName = "MMPLogin";
	private String name;
	private String subject = "Prescription Refill";
	private String message = "Can you please refill my prescriptions";
	private HelperClass helperObject;
	private String urlPatient;
	private String urlAdmin;
	private String expectedAlrtMsg = "Messages Successfully sent.";
	private String actualAlrtMsg;
	private String adminName;
	private String adminPassword;

	// Used data provider, LoginTestData.xls to get login info for Patient Module
	@Test(dataProvider = "testdata")
	public void SendMessagesTests(String uName, String password) throws InterruptedException, IOException {

		// #TC1 - Open the Chrome Browser
		instantiateDriver();

		// TC2 - Enter URL
		helperObject = new HelperClass(driver);
		urlPatient = prop.getProperty("urlPatient");
		helperObject.launchApplicationURL(urlPatient);

		// TC3 - Enter login details
		// *uName and password comes from data provider (LoginTestData.xls)
		helperObject.login(uName, password);

		// *Retrieves First Name which is passed later to Admin Module to compare
		// details
		MessagesPatientModulePage SMPage = new MessagesPatientModulePage(driver);
		name = SMPage.retrieveFirstName();

		// TC4 - Navigate to Messages
		helperObject.switchToSideBar();
		helperObject.navigateToModule("Messages");

		// TC5 - Enter Message Details in Patient Module
		MessagesPatientModulePage msgPage = new MessagesPatientModulePage(driver);
		msgPage.sendMessage(subject, message);

		// TC5 - Validate Message had been sent successfully from Patient module by
		// veryfing Alert
		actualAlrtMsg = msgPage.validateMessageSend();
		Assert.assertEquals(actualAlrtMsg, expectedAlrtMsg);

		// TC6 - Logout of Patient module
		helperObject.navigateToModule("Logout");

		// TC7 - Login Admin module
		urlAdmin = prop.getProperty("urlAdmin");
		adminName = prop.getProperty("adminName");
		adminPassword = prop.getProperty("adminPassword");
		Assert.assertTrue(
				msgPage.validateMessageFromAdminModule(adminName, adminPassword, urlAdmin, name, subject, message));
	}

	@DataProvider(name = "testdata")
	public String[][] loginData() throws BiffException, IOException {
		String[][] loginData = AppLibrary.readXls(filePath, sheetName);
		return loginData;
	}

}
