package org.iit.patientmodule.tests;

import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ScheduleAppointmentPage;
import org.iit.util.BaseClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ScheduleAppointmentTests extends BaseClass {
	private HelperClass helperObject;

	String doctorName = "Sophia Rich";

	@Test
	public void validateScheduleAppointment() throws InterruptedException {

		SoftAssert sa = new SoftAssert();

		// #TC1 - Open the Chrome Browser
		helperObject = new HelperClass(driver);

		// TC2 - Enter URL and login details
		helperObject.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObject.adminLogin("ria1", "Ria12345");

		// TC3 - Navigate to Schedule Appointment
		helperObject.navigateToModule("Schedule Appointment");

		ScheduleAppointmentPage schApptPage = new ScheduleAppointmentPage(driver);

		// TC4 - Schedule an appointment with the doctor
		HashMap<String, String> makeApptMap = schApptPage.schAppointment(doctorName);
		helperObject.navigateToModule("HOME");

		// TC5 - Validate the Schedule Appointment details on the Homepage
		boolean match = schApptPage.compareValuesOnHomePage(makeApptMap);
		sa.assertTrue(match);

		// TC6 - Validate the Schedule Appointmeny details on the Schedule Appointment
		// page
		helperObject.navigateToModule("Schedule Appointment");
		match = schApptPage.compareValuesOnScheduleAppt(makeApptMap);
		sa.assertTrue(match);

		sa.assertAll();
	}
}
