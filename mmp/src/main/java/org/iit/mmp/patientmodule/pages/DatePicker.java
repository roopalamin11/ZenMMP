/*
 * package org.iit.mmp.patientmodule.pages;
 * 
 * import java.util.List;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.WebElement;
 * 
 * public class DatePicker {
 * 
 * public void datePicker() { System.setProperty("webdriver.chrome.driver",
 * "C:\\Roopal\\SeleniumWork\\chromedriver.exe"); // WebDriver driver = new
 * ChromeDriver(); driver.get(
 * "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/providers.php"
 * ); //
 * driver.get("http://96.84.175.78/MMP-Release1-Integrated-Build.2.4.000");
 * driver.manage().window().maximize();
 * 
 * // Select a provider // hardcoded - need to change provider code to click
 * button underneath the reqd // provider driver.findElement(By.xpath(
 * "\"//h4[contains(text(),'Beth')]/ancestor::ul/following-sibling::button\"").
 * click();
 * 
 * // ***** TC#4 Click "Book Appointment" under the Provider Name with whom the
 * // appointment is to be scheduled.
 * 
 * // String providerName = //
 * driver.findElement(By.xpath("//h4[contains(text(),'Dr.Annabeth')]")) //
 * .getAttribute("value");
 * 
 * String doctorName = ("Annabeth"); // System.out.println("providerName " +
 * doctorName);
 * 
 * // Change focus to the new Frame for making New Appointment WebElement
 * apptFrame = driver.findElement(By.id("myframe"));
 * driver.switchTo().frame(apptFrame);
 * 
 * 
 * // ! - USED negation IN WHILE SO THAT TO GO INTO THE LOOP TILL MONTH MATCHES
 * // MARCH // While loop will keep on executing until it becomes false while
 * (!driver.findElement(By.
 * cssSelector("[class='datepicker-days'] [class='datepicker-switch']")).getText
 * () .contains("March 2022")) { // nOT SURE HOW CSS WAS FOUND IN LESSON JUST
 * COPIED IT HERE FROM VIDEO - // USES PARENT CHILD TO FIND NEXT > ARROW AND
 * CLICK // -->>[class='datepicker-days'] th[class='next']
 * driver.findElement(By.
 * cssSelector("[class='datepicker-days'] th[class='next']")).click(); }
 * 
 * ///// FIND DATE -- 23 // GRAB COMMOM ATTRIBUTE, PUT IN LIST AND ITERATE FOR
 * CALENDAR UI List<WebElement> dates =
 * driver.findElements(By.cssSelector("[class='day']"));
 * 
 * int count = driver.findElements(By.cssSelector("td[class='day']")).size();
 * 
 * for (int i = 0; i < count; i++) { String text =
 * driver.findElements(By.cssSelector("[class='day']")).get(i).getText(); if
 * (text.equalsIgnoreCase("23")) {
 * driver.findElements(By.cssSelector("[class='day']")).get(i).click(); break; }
 * }
 * 
 * } }
 */
