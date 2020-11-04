package com.pragmatic.hrm;

import org.testng.annotations.DataProvider;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestData {

    @DataProvider(name = "user_credentials")
    public Object[][] getUserCredentials() {
        return new Object[][]{
                {"", "", "Username cannot be empty"},
                {"", "Ptl@#321", "Username cannot be empty"},
                {"Admin", "", "Password cannot be empty"},
                {"Admin", "test", "Invalid credentials"},
                {"Admin", "ptl@#321", "Invalid credentials"}


        };
    }

    @DataProvider(name = "user_credentials_csv")
    public Object[][] getUserCredentialsCSV() {
        return HRMTestBase.readCSV("src/test/resources/user_credentials.csv");

    }

    @DataProvider(name = "user_credentials_xl")
    public Object[][] getUserCredentialsXL() {
        return HRMTestBase.readXLFile("src/test/resources/user_credentials.xlsx");
    }

    @DataProvider(name = "user_credentials_xml")
    public Object[][] getUserCredentialsXML() {
        return HRMTestBase.readXML("src/test/resources/user_credentials.xml", "row");
    }


}
