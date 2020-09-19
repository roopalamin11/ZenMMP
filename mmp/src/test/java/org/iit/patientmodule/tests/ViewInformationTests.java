package org.iit.patientmodule.tests;

import java.io.IOException;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.ViewInformation;
import org.iit.util.BaseClass;
import org.testng.annotations.Test;

public class ViewInformationTests extends BaseClass {
	@Test
	public void validateScheduleAppointment() throws InterruptedException, IOException {

		// #TC1 - Open the Chrome Browser
		HelperClass helperObject = new HelperClass(driver);

		// TC2 - Enter URL and login details
		helperObject.login("ria1", "Ria12345",
				"http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");

		// TC3 - Navigate to Information
		helperObject.navigateToAModule("Information");

		// TC4 - Verify information on webpage matched with text file
		ViewInformation viewInfo = new ViewInformation(driver);
		viewInfo.verifyInfo();
	}

}
