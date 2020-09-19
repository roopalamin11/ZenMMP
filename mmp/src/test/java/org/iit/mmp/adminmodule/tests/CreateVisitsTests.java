package org.iit.mmp.adminmodule.tests;

import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.CreateVisitPage;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.util.BaseClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateVisitsTests extends BaseClass {
	HashMap<String, String> createVisitHMap = new HashMap<String, String>();
	String ssn = "666001236";
	String doctorName = "Becky";

	@Test
	public void validateCreateVisit() throws InterruptedException {
		// #TC1 - Open the Chrome Browser
		HelperClass helperObject = new HelperClass(driver);

		// TC2 - Enter URL and login details for Admin Module
		helperObject.adminLogin("Thomas_444", "Edison_444",
				"http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");

		SoftAssert sa = new SoftAssert();

		// TC3 - Navigate to 'Patients' page
		helperObject.navigateToAModule("Patients");

		// TC4 - Search for patient by SSN
		Boolean patientFound = helperObject.adminSearchPatientBySSN(ssn);
		sa.assertTrue(patientFound);

		// TC5 - Create Visit for the patient
		CreateVisitPage createVisitPage = new CreateVisitPage(driver);

		createVisitHMap = createVisitPage.createVisit(doctorName);

		// TC6 - Login to the Patient Module
		helperObject.login("ria1", "Ria12345",
				"http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");

		// TC7 - Navigate to Schedule Appointment page and check if Appointment
		// displayed here for the visit created in Admin module.
		helperObject.navigateToAModule("Schedule Appointment");

		ScheduleAppointmentPage schApptPage = new ScheduleAppointmentPage(driver);

		Boolean match = schApptPage.compareValuesOnScheduleAppt(createVisitHMap);

		sa.assertTrue(match);

		sa.assertAll();

	}
}
