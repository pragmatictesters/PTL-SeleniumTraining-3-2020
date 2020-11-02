package com.pragmatic.workshop;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ReadCSV {


    @Test (dataProvider = "user_credentials")
    public void testCVS(JSONObject userCredentials) {
        printUserCredentials(userCredentials);
    }

    private void printUserCredentials(JSONObject userCredentials) {
        System.out.print("Username " +  userCredentials.get("username"));
        System.out.print(" Password  " +  userCredentials.get("password"));
        System.out.println(" Expected " +  userCredentials.get("expected_outcome"));
    }

    @Test
    public void testCSVMethod(){
        readCSV();
    }

    
    @DataProvider(name = "user_credentials")
    public Object[][] userCredetials(){
        return readCSV();
    }
    

    private JSONObject[][] readCSV() {

        //String fileName = "./src/test/resources/user_credentials.csv";
        String fileName = "/Users/hansi/Downloads/user_credentials - user_credentials.csv";
        JSONObject[][] data = null;

        Reader in = null;
        try {
            in = new FileReader(fileName);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);


            int currentRow =0;
            int rowsInCSV=0;

            for (CSVRecord record : records){
                rowsInCSV++;
            }


            data = new JSONObject[rowsInCSV][1];
            in = null;
            in = new FileReader(fileName);
            Iterable<CSVRecord> records2 = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records2) {
                JSONObject jsonObject = new JSONObject();
                String username = record.get("username");
                jsonObject.put("username", username);
                String password = record.get("password");
                jsonObject.put("password", password);
                String expected_outcome = record.get("expected_outcome");
                jsonObject.put("expected_outcome", expected_outcome);
                data[currentRow++][0]= jsonObject;
            }
            return data;

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return data;

}

}
