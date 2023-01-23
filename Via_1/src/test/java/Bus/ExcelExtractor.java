package Bus;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import Generic_Libraries.BaseClass;

public class ExcelExtractor extends BaseClass{
	public static void main(String[] args) {
        try {
            // Open the Excel file
            FileInputStream fis = new FileInputStream("./src/test/resources/testData/testData.xlsx");
            Workbook workbook = WorkbookFactory.create(fis);
            String TestcaseName = "Bus_Oneway_Place";

            // Get the sheet
            Sheet sheet = workbook.getSheet("Naveen");

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
            //Get the row count
            rowCount = rowsWithSameTestcaseID.size();
            System.out.println("Number of rows in sheet : " + rowCount);
            //Get the column count
            for (Row row : rowsWithSameTestcaseID) {
            	columnCount = row.getLastCellNum();
            }
            System.out.println("Number of columns : " + columnCount);
            //Store the values in a array
            String[][] data = new String[rowsWithSameTestcaseID.size()][];
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