package excel;
//importing required classes and interfaces  from respective packages.
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelInput{
	//declaring and defining getExcelInput() method of ArrayList<String> return type
	public static ArrayList <String> getExcelInput() throws IOException {
		//Creating an ArrayList
		ArrayList<String> list=new ArrayList<String>();
		//creating an XSSFWorkbook object and defining the excel file path.
		 XSSFWorkbook workbook=new XSSFWorkbook("./src/main/excel/Readandwrite.xlsx");
		 XSSFSheet sheet=workbook.getSheetAt(0);
		 int  noOfRows=sheet.getPhysicalNumberOfRows();
		 int  noOfCells =sheet.getRow(0).getLastCellNum();
		 //iterating through the rows and cells of the excel file to read the inputs 
		 for(int i=1;i<noOfRows;i++) {
			 XSSFRow row=sheet.getRow(i);
			 if(row!=null) {
				 for(int j=0;j<noOfCells;j++) {
					 XSSFCell cell=row.getCell(j);
					 if(cell!=null) {
						 list.add(cell.getStringCellValue().replaceAll("\"", ""));
					 }
				 }
			 }
		 }
		 //closing the resource 
		 workbook.close();
		 //returning the list of read inputs in the excel file.
		 return list;
		
	}

	
	

	
}