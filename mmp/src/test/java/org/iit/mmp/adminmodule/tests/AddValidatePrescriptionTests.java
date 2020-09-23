package org.iit.mmp.adminmodule.tests;

import java.util.HashMap;

import org.iit.mmp.adminmodule.pages.CreateVisitPage;
import org.iit.mmp.helper.HelperClass;
import org.iit.util.BaseClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddValidatePrescriptionTests extends BaseClass {
	HashMap<String, String> createVisitHMap = new HashMap<String, String>();
	String ssn = "666001236";
	String doctorName = "Becky";

	@Test
	public void validateCreateVisit() throws InterruptedException {
		// #TC1 - Open the Chrome Browser
		HelperClass helperObject = new HelperClass(driver);

		// TC2 - Enter URL and login details for Admin Module
		helperObject.launchApplicationURL("http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");
		helperObject.adminLogin("Thomas_444", "Edison_444");

		SoftAssert sa = new SoftAssert();

		// TC3 - Navigate to 'Patients' page
		helperObject.navigateToModule("Patients");

		// TC4 - Search for patient by SSN
		CreateVisitPage createVisitPage = new CreateVisitPage(driver);
		Boolean patientFound = createVisitPage.adminSearchPatientBySSN(ssn);
		sa.assertTrue(patientFound);

		// TC5 - Create Visit for the patient
		createVisitPage.addPrescription();
	}

}