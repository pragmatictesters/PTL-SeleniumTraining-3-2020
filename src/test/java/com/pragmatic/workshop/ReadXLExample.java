package com.pragmatic.workshop;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ReadXLExample {



    @Test (dataProvider = "user_credentials")
    public void testReadXLDDT(JSONObject userCredentials){
        System.out.println("userCredentials = " + userCredentials);
        
    }
    

    @Test
    public void testReadXL(){

        readXLFile();

    }

    
    
    
    @DataProvider(name = "user_credentials")
    public Object[][] userCredentials(){
        return readXLFile();
        
    }

    private JSONObject[][] readXLFile(){
        String fileName = "src/test/resources/user_credentials.xlsx";
        int rowsInSheet =0 ;
        JSONObject[][] data = null;

        try {
            FileInputStream excelFile = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            rowsInSheet = sheet.getPhysicalNumberOfRows();
            data = new JSONObject[rowsInSheet-1][1];

            Iterator<Row> iterator = sheet.iterator();

            Row firstRow = iterator.next();
            Iterator<Cell> cellIterator = firstRow.iterator();
            String[] columns = new String[firstRow.getPhysicalNumberOfCells()];
            int columnID = 0;
            while (cellIterator.hasNext()){
                String value = cellIterator.next().getStringCellValue();
                columns[columnID++] = value;


            }


            while (iterator.hasNext()){
                JSONObject jsonObject = new JSONObject();

                Row currentRow = iterator.next();
                //Iterator<Cell> cellIterator = currentRow.iterator();
                cellIterator = currentRow.iterator();
                columnID =0;

                while (cellIterator.hasNext()){
                    String value = cellIterator.next().getStringCellValue();
                    jsonObject.put(columns[columnID++], value);

                }
                data[currentRow.getRowNum()-1][0]= jsonObject;

            }
            return data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return data;

    }

}
