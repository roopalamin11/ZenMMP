package org.iit.patientmodule.tests;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.PatientRegistrationPage;
import org.iit.util.BaseClass;
import org.testng.annotations.Test;

//Register functionality cannot be validated bcos sysem does not allow to login with new user details
//Hence did not complete the logic in page
public class PatientRegistrationTests extends BaseClass {

	@Test
	public void validateRegistration() {
		// TC1 - Open the Browser
		HelperClass helperObject = new HelperClass(driver);

		// TC2 - Enter URL
		helperObject.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObject.adminLogin("ria1", "Ria12345");

		PatientRegistrationPage patientRegister = new PatientRegistrationPage(driver);
		// TC3 - Click the Register button
		patientRegister.clickOnRegisterButton();

		// TC4 - Fill all the fields and click the Submit button
		patientRegister.registernValidateData();
	}
}
