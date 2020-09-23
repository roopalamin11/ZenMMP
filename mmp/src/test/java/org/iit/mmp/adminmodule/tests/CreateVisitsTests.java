package org.iit.mmp.adminmodule.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.CreateVisitPage;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.util.AppLibrary;
import org.iit.util.BaseClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.read.biff.BiffException;

public class CreateVisitsTests extends BaseClass {
	private String filePath = System.getProperty("user.dir") + "\\Data\\TestData.xls";
	private String sheetName = "Create Visit";
	private String urlAdmin;
	private String adminName;
	private String adminPassword;
	private HashMap<String, String> createVisitHMap = new HashMap<String, String>();
	// private String ssn = "666001238";
	// private String doctorName = "Becky";
	private String urlPatient;
	private String patientName;
	private String patientPassword;
	private Boolean patientFound;
	SoftAssert sa = new SoftAssert();

	// Used data provider, TestData.xls to get ssn and doctor name
	@Test(dataProvider = "testdata")
	public void validateCreateVisit(String ssn, String doctorName) throws InterruptedException, IOException {
		// #TC1 - Open the Chrome Browser
		instantiateDriver();

		// TC2 - Enter URL
		HelperClass helperObject = new HelperClass(driver);
		urlAdmin = prop.getProperty("urlAdmin");
		helperObject.launchApplicationURL(urlAdmin);

		// TC3 - Enter Login details for Admin Module
		adminName = prop.getProperty("adminName");
		adminPassword = prop.getProperty("adminPassword");
		helperObject.adminLogin(adminName, adminPassword);

		// TC4 - Navigate to 'Patients' page
		helperObject.navigateToModule("Patients");

		// TC5 - Search for patient by SSN
		CreateVisitPage createVisitPage = new CreateVisitPage(driver);
		patientFound = createVisitPage.adminSearchPatientBySSN(ssn);
		Assert.assertTrue(patientFound);

		// TC6 - Create Visit for the patient
		createVisitHMap = createVisitPage.createVisit(doctorName);

		// TC7 - Enter URL for Patient module
		urlPatient = prop.getProperty("urlPatient");
		helperObject.launchApplicationURL(urlPatient);

		// TC8 - Enter login details for Patient Module
		patientName = prop.getProperty("patientName");
		patientPassword = prop.getProperty("patientPassword");
		helperObject.login(patientName, patientPassword);

		// TC9 - Navigate to Home page and check if Appointment
		// displayed here for the visit created in Admin module.
		helperObject.navigateToModule("HOME");

		ScheduleAppointmentPage schApptPage = new ScheduleAppointmentPage(driver);

		sa.assertTrue(schApptPage.compareValuesOnHomePage(createVisitHMap),
				"Validation on Patient Module - Home Page Failed");

		// TC10 - Navigate to Schedule Appointment page and check if Appointment
		// displayed here for the visit created in Admin module.
		helperObject.navigateToModule("Schedule Appointment");

		sa.assertTrue(schApptPage.compareValuesOnScheduleAppt(createVisitHMap),
				"Validation on Patient Module - Schedule Appointment page Failed");

		sa.assertAll();
	}

	@DataProvider(name = "testdata")
	public String[][] visitData() throws BiffException, IOException {

		String[][] visitData = AppLibrary.readXls(filePath, sheetName);
		return visitData;

	}
}
