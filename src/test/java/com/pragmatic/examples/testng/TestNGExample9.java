package com.pragmatic.examples.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs
 *
 * @Author Janesh Kodikara
 */
public class TestNGExample9 {


    @Test(dataProvider = "user_credentials", dataProviderClass = TestData.class)
    public void testMethod1(String userName, String password, String expectedOutcome) {
        System.out.println("userName = " + userName + ", password = " + password + ", expectedOutcome = " + expectedOutcome);

    }

    @Test(dataProvider = "user_credentials-2", dataProviderClass = TestData.class)
    public void testMethod6(String userName, String password, String expectedOutcome) {
        System.out.println("userName = " + userName + ", password = " + password + ", expectedOutcome = " + expectedOutcome);

    }

    @Test(dataProvider = "user_credentials-2", dataProviderClass = TestData.class)
    public void testMethod7(String userName, String password, String expectedOutcome) {
        System.out.println("userName = " + userName + ", password = " + password + ", expectedOutcome = " + expectedOutcome);

    }



    @Test(invocationCount = 5, invocationTimeOut = 1000, skipFailedInvocations = false)
    public void testMethod2() throws InterruptedException {

        Thread.sleep(500);
        System.out.println("TestNGExample2.testMethod1");
    }

    @Test (timeOut = 2000)
    public void testMethod3() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("TestNGExample3.testMethod1");
    }

    @Test
    public void testMethod4() {
        Assert.fail("Forced failure by PTL");
        System.out.println("TestNGExample4.testMethod1");
    }

    @Test
    public void testMethod5() {
        Assert.assertFalse(true, "Forced failure");
        System.out.println("TestNGExample5.testMethod1");
    }


    //TODO 1 : How to throw an error from Assert.fail
    //TODO 2 : Reading data from CSV file
    //TODO 3 : Reading data from XML file
    //TODO 4 : Reading data from DB
    //TODO 5 : PDFngreport integration
    //TODO 6 : Learn about Gauge


}
