package Generic_Libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

/***
 * 
 * @author Naveen
 *
 */

public class ReadData {
	/**
	 * This method helps us to read data from property file
	 * 
	 * @param the associated key name in property file
	 * @return
	 */

	// reading from property file
	public static String fromPropertyFile(String key) {
		FileInputStream fis = null;
		Properties properties = null;
		try {
			fis = new FileInputStream(new File("./src/test/resources/testData/configration.properties"));
			properties = new Properties();
			properties.load(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);

	}

	// extracting only single cell value from excel
	public static String fromExcel(String sheetName, int rowNo, int cellNo) {
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = new FileInputStream(new File("./src/test/resources/testData/testData.xlsx"));
			workbook = WorkbookFactory.create(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();

	}

	// reading date from excel
	public static LocalDateTime fromExcelWithDateTime(String sheetName, int rowNo, int cellNo) {
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			fis = new FileInputStream(new File("./src/test/resources/testData/testData.xlsx"));
			workbook = WorkbookFactory.create(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook.getSheet(sheetName).getRow(rowNo).getCell(cellNo).getLocalDateTimeCellValue();

	}

	// getting only day from computer based on input
	public static int dayFromSystem(int num) {
		LocalDateTime systemdate = LocalDateTime.now().plusDays(num);

		int day = systemdate.getDayOfMonth();
		return day;
	}

	// getting only year from computer based on input
	public static int yearFromSystem(int num) {
		LocalDateTime systemdate = LocalDateTime.now().plusYears(num);

		int year = systemdate.getYear();
		return year;
	}

	// getting only month from computer based on input
	public static String monthFromSystem(int num) {
		LocalDateTime systemdate = LocalDateTime.now().plusMonths(num);
		String monthName = systemdate.getMonth().name();
		String month = "" + monthName.charAt(0) + monthName.substring(1, 3).toLowerCase();
		return month;
	}

	// reading multiple data from the excel(rows and columns)
	@DataProvider
	public static String[][] multipleDataFromExcel(String sheetName, String TestcaseID) {
		String[][] data = null;
		try {
			// Open the Excel file
			FileInputStream fis = new FileInputStream("./src/test/resources/testData/testData.xlsx");
			Workbook workbook = WorkbookFactory.create(fis);
			String TestcaseName = TestcaseID;

			// Get the sheet
			Sheet sheet = workbook.getSheet(sheetName);

			// Define a list to store the rows with the same testcaseID
			List<Row> rowsWithSameTestcaseID = new ArrayList<Row>();
			int rowCount = 0;
			int columnCount = 0;
			// Iterate through the rows
			for (Row row : sheet) {
				// Get the cell with the testcaseID
				Cell testcaseIDCell = row.getCell(0);

				// Check if the testcaseID is the same as the one we're looking for
				if (testcaseIDCell != null && testcaseIDCell.getStringCellValue().equals(TestcaseName)) {
					// Add the row to the list
					rowsWithSameTestcaseID.add(row);
				}
			}
			// Get the row count
			rowCount = rowsWithSameTestcaseID.size();
			System.out.println("Number of rows in sheet : " + rowCount);
			// Get the column count
			for (Row row : rowsWithSameTestcaseID) {
				columnCount = row.getLastCellNum();
			}
			System.out.println("Number of columns : " + columnCount);
			// Store the values in a array
			data = new String[rowsWithSameTestcaseID.size()][];
			int i = 0;
			// Iterate through the rows and store the cell values in the array
			for (Row row : rowsWithSameTestcaseID) {
				data[i] = new String[row.getLastCellNum()];
				int j = 0;
				for (Cell cell : row) {
					data[i][j] = cell.getStringCellValue();
					j++;
				}
				i++;
			}
			
			// Print the array to check the data
			for (int k = 0; k < data.length; k++) {
				for (int l = 0; l < data[k].length; l++) {
					System.out.print(data[k][l] + " ");
				}
				System.out.println();
			}
			// Close the file input stream
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

	// reading a single row data
	public static String[][] DataFromExcel(String sheetName, int rowNo) {
		FileInputStream fis = null;
		Workbook workBook = null;
		try {
			fis = new FileInputStream("./src/test/resources/testData/testData.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workBook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheetInfo = workBook.getSheet(sheetName);
		int columnCount = sheetInfo.getRow(rowNo).getPhysicalNumberOfCells() - 1;
		String data[][] = new String[1][columnCount];

		for (int j = 0; j < columnCount; j++) {
			data[0][j] = sheetInfo.getRow(rowNo).getCell(j + 1).toString();
		}

		return data;

	}

	// reading month from excel
	public static String readMonthFromExcel(String dat) {

		String month = null;
		String date[] = dat.split("/");

		String m = date[1];
		int months = Integer.parseInt(m);

		switch (months) {
		case 1:
			month = "Jan";
			break;
		case 2:
			month = "Feb";
			break;
		case 3:
			month = "Mar";
			break;
		case 4:
			month = "Apr";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "Jun";
			break;
		case 7:
			month = "Jul";
			break;
		case 8:
			month = "Aug";
			break;
		case 9:
			month = "Sep";
			break;
		case 10:
			month = "Oct";
			break;
		case 11:
			month = "Nov";
			break;
		case 12:
			month = "Dec";
			break;
		}
		return month;
	}

	// reading day from excel
	public static int readDayFromExcel(String dat) {
		String date[] = dat.split("/");
		String d = date[0];
		int day = Integer.parseInt(d);
		return day;
	}

	// reading year from excel
	public static int readyearFromExcel(String dat) {
		String date[] = dat.split("/");
		String y = date[2];
		int year = Integer.parseInt(y);
		return year;
	}

	public static String[][] readFromexcelBasedOnId(String Sheetname, String testCaseId) {
		try {
			// Open the Excel file
			FileInputStream fis = new FileInputStream("data.xlsx");
			Workbook workbook = WorkbookFactory.create(fis);

			// Get the first sheet
			Sheet sheet = workbook.getSheet(Sheetname);

			// Define a list to store the rows with the same testcaseID
			List<Row> rowsWithSameTestcaseID = new ArrayList<Row>();

			// Iterate through the rows
			for (Row row : sheet) {
				// Get the cell with the testcaseID
				Cell testcaseIDCell = row.getCell(0);

				// Check if the testcaseID is the same as the one we're looking for
				if (testcaseIDCell != null && testcaseIDCell.getStringCellValue().equals(testCaseId)) {
					// Add the row to the list
					rowsWithSameTestcaseID.add(row);
				}
			}

			// Print the rows with the same testcaseID
			for (Row row : rowsWithSameTestcaseID) {
				for (Cell cell : row) {
					System.out.print(cell.getStringCellValue() + " ");
				}
				System.out.println();
			}

			// Close the file input stream
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
