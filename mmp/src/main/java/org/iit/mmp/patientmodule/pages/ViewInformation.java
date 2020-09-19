package org.iit.mmp.patientmodule.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.iit.util.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewInformation extends BaseClass {

	// @Test

	public ViewInformation(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyInfo() throws IOException {

		String actualText = driver.findElement(By.xpath("//div[contains(@class,'panel-title')]")).getText();

		// remove &nbsp - A non-breaking space
		actualText.replace("&nbsp;", "");

		// remove \n - newline character.
		actualText = actualText.replace("\n", "");

		// replace 2 or more white spaces with 1 whitespace.
		while (actualText.contains("  ")) { // 2 spaces
			actualText = actualText.replace("  ", " "); // (2 spaces, 1 space)
		}

		// trim() method removes whitespace from both ends of a string
		actualText = actualText.trim();

		// actualText = actualText.replace('\u00A0', ' ').trim();
		System.out.println("actualText ---------------------------------------------");
		System.out.println(actualText);

		File file = new File("C:\\Users\\RJA\\Documents\\ViewInformation.txt");
		System.out.println("expectedtext---------------------------------------------");

		// Making sure character encoding of file matches
		// Single quote was not encoding properly and java was reading it as â€'
		/// BufferedReader can read any text with a standard encoding. A problem arises
		// when the encoding used by the file doesn't match the default encoding you use
		// to read the file. This is not something which can be determined
		// automatically, you need to know what the correct encoding is to read it
		// reliably.
		// https://stackoverflow.com/questions/19992018/reading-single-quotes-from-text-file-in-java-using-bufferedreader

		// Changing encoding of file to default used by java
		String charset = "UTF-8"; // or what corresponds
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

		String expectedText;
		String expectedTextFinal = "";
		while ((expectedText = br.readLine()) != null) {
			// need to keep adding lines read by br.readline() to get final string
			expectedTextFinal = expectedTextFinal + expectedText;
		}

		// remove &nbsp - A non-breaking space
		actualText.replace("&nbsp;", "");

		// remove \n - newline character.
		expectedTextFinal = expectedTextFinal.replace("\n", "");

		// replace 2 or more white spaces with 1 whitespace.
		while (expectedTextFinal.contains("  ")) { // 2 spaces
			expectedTextFinal = expectedTextFinal.replace("  ", " "); // (2 spaces, 1 space)
		}

		// trim() method removes whitespace from both ends of a string
		expectedTextFinal = expectedTextFinal.trim();

		System.out.println("expectedTextFinal");
		System.out.println(expectedTextFinal);

		System.out.println("Result---------------------------------------------------------------------");

		if (actualText.equals(expectedTextFinal)) {
			System.out.println("Information displayed matches with text file");
		} else {
			System.out.println("Information displayed does not match with text file\"");
		}
	}
}