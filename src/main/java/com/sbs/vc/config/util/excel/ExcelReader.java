package com.sbs.vc.config.util.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	public void readExcelFile(String xlsFileLocation) throws IOException, InvalidFormatException {
		

		        // Creating a Workbook from an Excel file (.xls or .xlsx)
		        Workbook workbook = WorkbookFactory.create(new File(xlsFileLocation));

		        // Retrieving the number of sheets in the Workbook
		        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		        /*
		           =============================================================
		           Iterating over all the sheets in the workbook (Multiple ways)
		           =============================================================
		        */

		      
		        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
		        workbook.forEach(sheet -> {
		            System.out.println("=> " + sheet.getSheetName());
		        });

		        /*
		           ==================================================================
		           Iterating over all the rows and columns in a Sheet (Multiple ways)
		           ==================================================================
		        */

		        // Getting the Sheet at index zero
		        Sheet sheet = workbook.getSheetAt(0);

		       
		        // 3. Or you can use Java 8 forEach loop with lambda
		        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
		        sheet.forEach(row -> {
		        	row.forEach(cell -> {
		                //String cellValue = dataFormatter.formatCellValue(cell);
		                //System.out.print(cellValue + "\t");
		            	printCellValue(cell);
		            });
		            System.out.println();
		        });
		        
		     
		       // Closing the workbook
		        workbook.close();

	}
	
	public List<List<Object>> readFile(String xlsFileLocation,int sheetNumber) throws IOException, InvalidFormatException {
		List<List<Object>> data=new ArrayList<>();
		// Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(xlsFileLocation));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        Sheet sheet = workbook.getSheetAt(sheetNumber);

        int rowcount = sheet.getLastRowNum()- sheet.getFirstRowNum();
        System.out.println("Total row number: "+rowcount);
        for(int i=1; i<rowcount+1; i++){
            //Create a loop to get the cell values of a row for one iteration
            Row row = sheet.getRow(i);
            List<Object> arrName = new ArrayList<>();
            for(int j=0; j<row.getLastCellNum(); j++){
                Cell cell = row.getCell(j);
                
                switch (cell.getCellTypeEnum()) {
    	        case BOOLEAN:
    	        	arrName.add(cell.getBooleanCellValue());
    	            break;
    	        case STRING:
    	        	arrName.add(cell.getRichStringCellValue().getString());
    	            break;
    	        case NUMERIC:
    	            if (DateUtil.isCellDateFormatted(cell)) {
    	            	arrName.add(cell.getDateCellValue());
    	            } else {
    	            	arrName.add(cell.getNumericCellValue());
    	            }
    	            break;
    	        case FORMULA:
    	        	arrName.add(cell.getCellFormula());
    	            break;
    	        case BLANK:
    	        	arrName.add("");
    	            break;
    	        default:
    	        	arrName.add("");
                }
             }
            data.add(arrName);
        }
        // Closing the workbook
        workbook.close();
        return data;

}
	
	private  void printCellValue(Cell cell) {
	    switch (cell.getCellTypeEnum()) {
	        case BOOLEAN:
	            System.out.print(cell.getBooleanCellValue());
	            break;
	        case STRING:
	            System.out.print(cell.getRichStringCellValue().getString());
	            break;
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                System.out.print(cell.getDateCellValue());
	            } else {
	                System.out.print(cell.getNumericCellValue());
	            }
	            break;
	        case FORMULA:
	            System.out.print(cell.getCellFormula());
	            break;
	        case BLANK:
	            System.out.print("");
	            break;
	        default:
	            System.out.print("");
	    }

	    System.out.print("\t");
	}
	
	public static void main(String arg[]) throws InvalidFormatException, IOException {
		ExcelReader excelReader=new ExcelReader();
		System.out.println(excelReader.readFile("C:\\Users\\shrikant.kushwaha\\Desktop\\R\\loadhebrewstockmasterdata.xlsx",1));
		System.out.println(excelReader.readFile("C:\\Users\\shrikant.kushwaha\\Desktop\\R\\loadhebrewstockmasterdata.xlsx",2));
		System.out.println(excelReader.readFile("C:\\Users\\shrikant.kushwaha\\Desktop\\R\\loadhebrewstockmasterdata.xlsx",0));
	}

}
