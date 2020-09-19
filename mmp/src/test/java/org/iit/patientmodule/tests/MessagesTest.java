package org.iit.patientmodule.tests;

import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.patientmodule.pages.MessagesPage;
import org.iit.util.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MessagesTest extends BaseClass {
	String expectedMsg = "Messages Successfully sent.";

	@Test
	public void validateScheduleAppointment() throws InterruptedException {

		// SoftAssert sa = new SoftAssert();

		// #TC1 - Open the Chrome Browser
		HelperClass helperObject = new HelperClass(driver);

		// TC2 - Enter URL and login details
		helperObject.login("ria1", "Ria12345",
				"http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");

		// TC3 - Navigate to Messages
		helperObject.navigateToAModule("Messages");

		// TC4 - Enter Message Details
		MessagesPage msgPage = new MessagesPage(driver);
		msgPage.enterMessageDetails();

		// TC5 - Validate Alert popup message - Message Successfully Sent
		String actualMsg = msgPage.validateMessageSend();
		Assert.assertEquals(actualMsg, expectedMsg);
		// sa.assertAll();

	}
}
