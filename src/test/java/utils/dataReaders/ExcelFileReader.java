package utils.dataReaders;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelFileReader {


    public static String readDataFromExcel(String excelFilePath,String excelSheet, int excelRow, int excelColumn){
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook= WorkbookFactory.create(fileInputStream);

            int sheetsNumber=workbook.getNumberOfSheets();
            Sheet sheet;

            for(int i =0;i<sheetsNumber;i++){
                if(workbook.getSheetName(i).equalsIgnoreCase(excelSheet)){
                    sheet   = workbook.getSheetAt(i);
                    Row row = sheet.getRow(excelRow);
                    Cell cell = row.getCell(excelColumn);
                    return cell.getStringCellValue();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        }

        return null;
    }


}
