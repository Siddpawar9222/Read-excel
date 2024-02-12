package com.example.readexcel.services;


import java.io.IOException;
import java.util.*;

import com.example.readexcel.model.MySheet;
import com.example.readexcel.repository.MySheetRepository;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReadFileService {
//      public void readFile(MultipartFile file){
//          try {
//              HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream()); //only for xls file
//              HSSFSheet sheet = workbook.getSheetAt(0);
//
//              Iterator<Row> rowIterator = sheet.iterator();
//              while (rowIterator.hasNext()) {
//                  Row row = rowIterator.next();
//                  Iterator<Cell> cellIterator = row.cellIterator();
//                  while (cellIterator.hasNext()) {
//                      Cell cell = cellIterator.next();
//                      switch (cell.getCellType()) {
//                          case NUMERIC:
//                              System.out.print(cell.getNumericCellValue() + "\t");
//                              break;
//                          case STRING:
//                              System.out.print(cell.getStringCellValue() + "\t");
//                              break;
//                      }
//                  }
//                  System.out.println();
//              }
//          } catch (IOException e) {
//              e.printStackTrace();
//          }
//      }

    @Autowired
    private MySheetRepository mySheetRepository ;

    public void readFileAndStoreInDatabase(MultipartFile file) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream()); // Open the workbook

            HSSFSheet sheet = workbook.getSheetAt(0); // Get the first sheet

            // Iterate through each row in the sheet and store data in the database
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                MySheet entity = new MySheet();
                Map<String, String> rowData = new HashMap<>();
                for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        rowData.put("Column_" + columnIndex, cell.toString()); // Store cell value in the map
                    } else {
                        rowData.put("Column_" + columnIndex, null); // Store null for empty cells
                    }
                }
                entity.setData(rowData);
                mySheetRepository.save(entity);
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
