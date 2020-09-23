package org.iit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AppLibrary {

//e.g Enum can be defined here (for datePicker)
//Here We are not using driver.FindElement so we can use it for all projects

//** All methods in AppLibrary you have to make it as static

//** Anything here will be used in all projects
//e.g WaitForElementClickable, WaitForElementExistence, SwtichToAFrame, Random Number Generation
// Methods that we put in conditional statements that are better to keep in App
// Library so that we can call diferent parameter values.

	// Any Logic should come in AppLibrary not in helper class

	public static WebElement waitforElementClickable(WebDriver driver, long timeInSecs, By element) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSecs);
		WebElement we = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
		return we;
	}

	public static String[][] readXls(String filePath, String sheetName) throws BiffException, IOException {

		File srcFile = new File(filePath);
		Workbook wb = Workbook.getWorkbook(srcFile);
		// Sheet sheet = wb.getSheet("MMPLogin");
		Sheet sheet = wb.getSheet(sheetName);
		int row = sheet.getRows();
		int col = sheet.getColumns();

		String[][] str = new String[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				Cell cell = sheet.getCell(j, i);
				str[i][j] = cell.getContents().toString();
			}
		}

		return str;
	}

	public static String[][] readXlsx(String filePath) throws IOException {

		File srcFile = new File(filePath);
		FileInputStream fis = new FileInputStream(srcFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int row = sheet.getLastRowNum() + 1;
		int col = 2;
		String[][] str = new String[row][col];
		for (int i = 0; i < row; i++) {
			str[i][0] = sheet.getRow(i).getCell(0).toString();
			str[i][1] = sheet.getRow(i).getCell(1).toString();
		}
		wb.close();
		return str;
	}
}
