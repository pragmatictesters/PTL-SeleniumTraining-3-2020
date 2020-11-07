package com.pragmatic.examples;

import org.testng.annotations.DataProvider;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestData {

    @DataProvider(name= "user_credentials")
    public  Object[][] user_credentials() {
        return new Object[][]{
                {"", "", "Username cannot be empty"},
                {"", "Ptl@#321", "Username cannot be empty"},
                {"Admin", "", "Password cannot be empty"},
                {"Admin", "test", "Invalid credentials"},
                {"Admin", "ptl@#321", "Invalid credentials"}


        };
    }


}
