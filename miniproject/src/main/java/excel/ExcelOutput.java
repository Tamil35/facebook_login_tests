package excel;

//importing required classes and interfaces  from respective packages
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class ExcelOutput {

	/*declaring and defining the writeOutput() method to 
	  write the results in the excel file
	 */

	public static  void writeOutput(String result) throws IOException, InvalidFormatException{

		FileInputStream fis=new FileInputStream("./src/main/excel/Readandwrite.xlsx");
		//using WorkbookFactory class to write the result single excel file 

		Workbook workbook=WorkbookFactory.create(fis);
		XSSFSheet sheet=(XSSFSheet) workbook.getSheet("Sheet1");

		//selecting the row of the target cell
		XSSFRow row= ((XSSFSheet) sheet).getRow(1);

		//creating the cell to write the result
		XSSFCell cell= row.createCell(5);

		//setting the result value in the target cell 
		cell.setCellValue(result);

		//creating the object of the FileOutputStream class with the excel file path
		FileOutputStream fileOutput=new FileOutputStream("./src/main/excel/Readandwrite.xlsx");

		/*writing the results in the respective cells in the excel 
		 using FileOutputStream object */
		workbook.write(fileOutput);

		//closing the resource 
		fileOutput.close();
		workbook.close();

	}

}
