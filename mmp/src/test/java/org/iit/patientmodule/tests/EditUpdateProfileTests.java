package org.iit.patientmodule.tests;

import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.EditUpdateProfilePage;
import org.iit.util.BaseClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditUpdateProfileTests extends BaseClass {
	@Test
	public void validateEditUpdateProfile() {

		SoftAssert sa = new SoftAssert();
		// #TC1 - Open the Chrome Browser
		HelperClass helperObject = new HelperClass(driver);

		// TC2 - Enter URL and login details
		helperObject.launchApplicationURL("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		helperObject.adminLogin("ria1", "Ria12345");

		// driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/index.php");

		// TC3 - Navigate to Schedule Appointment
		helperObject.navigateToModule("Profile");

		EditUpdateProfilePage profilePage = new EditUpdateProfilePage(driver);
		// TC #4 - Click Edit button
		profilePage.clickEdit();

		// TC #5 - Edit fields
		HashMap<String, String> edithMap = profilePage.editAllFields();

		// TC #6 - Click Save
		profilePage.clickSave();

		// TC # 7 - Check Alert message
		String acutalMsg = profilePage.acceptAlert();
		String expectedMsg = "Your Profile has been updated.";
		sa.assertEquals(acutalMsg, expectedMsg);

		// TC #8 - Check edit and update field values match
		HashMap<String, String> updatedhMap = profilePage.getAllFields();
		sa.assertEquals(edithMap, updatedhMap);
		sa.assertAll();

	}
}
