package com.pragmatic.examples.testng;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * Created by Pragmatic Test Labs
 *
 * @Author Janesh Kodikara
 */
public class TestData {


    @DataProvider(name = "user_credentials")
    public Object[][] userCredentials(){
        return new Object[][]{
                {"","","Username cannot be empty"},
                {"","Ptl@#321","Username cannot be empty"},
                {"Admin","", "Password cannot be empty"}
        };


    }





    @DataProvider(name = "user_credentials-2")
    public Object[][] userCredentials(Method m){
        System.out.println(m.getName());
        if (m.getName().equalsIgnoreCase("testMethod6")) {
            return new Object[][]{
                    {"","","Username cannot be empty"},
                    {"","Ptl@#321","Username cannot be empty"},
                    {"Admin","", "Password cannot be empty"}
            };
        } else {
            return new Object[][]{
                    {"TEST!","TEST2","Username cannot be empty"},
                    {"","Ptl@#321","Username cannot be empty"},
                    {"Admin","", "Password cannot be empty"}
            };
        }




    }




}
