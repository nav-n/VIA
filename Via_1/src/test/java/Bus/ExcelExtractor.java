package Bus;

import org.apache.poi.ss.usermodel.*;

import Generic_Libraries.BaseClass;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelExtractor extends BaseClass {

    public static void main(String[] args) {
    	String[][] data = null;
		String TestcaseID = "Bus_Oneway_Place";
		String sheetName = "naveen";
        try {
        	int columnCount =0;
        	int rowCount = 0;
            // Open the Excel file
            FileInputStream fis = new FileInputStream("./src/test/resources/testData/testData.xlsx");
            Workbook workbook = WorkbookFactory.create(fis);

            // Get the first sheet
            Sheet sheet = workbook.getSheet(sheetName);

            // Define a list to store the rows with the same testcaseID
            List<Row> rowsWithSameTestcaseID = new ArrayList<Row>();

            // Get the number of rows in the sheet
            rowCount = sheet.getLastRowNum()+1;
            System.out.println("Number of rows in sheet : " + rowCount);
            for (Row row : rowsWithSameTestcaseID) {
				columnCount = row.getLastCellNum();
			}
            // Iterate through the rows
            for (Row row : sheet) {
                // Get the cell with the testcaseID
                Cell testcaseIDCell = row.getCell(0);

                // Check if the testcaseID is the same as the one we're looking for
                if (testcaseIDCell != null && testcaseIDCell.getStringCellValue().equals(TestcaseID)) {
                    // Add the row to the list
                    rowsWithSameTestcaseID.add(row);
                }
            }
            // Create a 2D string array to store the cell values
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
    }
}