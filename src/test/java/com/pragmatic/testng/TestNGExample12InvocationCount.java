package com.pragmatic.testng;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs
 *
 * @Auther Janesh Kodikara
 */

public class TestNGExample12InvocationCount {


    @BeforeMethod(firstTimeOnly = true)
    public void beforeMethod() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test (invocationCount = 3)
    public void testMethod1(){

        System.out.println("TestNGExample1.testMethod1");
    }

   @Test (invocationCount = 3, invocationTimeOut = 1000)
    public void testMethod2(){

        System.out.println("TestNGExample2.testMethod1");
    }

   @Test(invocationCount = 10)
    public void testMethod3(ITestContext testContext){

        int invocationCount = testContext.getAllTestMethods()[0].getCurrentInvocationCount();
       System.out.println("invocationCount = " + invocationCount);
        System.out.println("TestNGExample3.testMethod1");
    }

   @Test
    public void testMethod4(){

        System.out.println("TestNGExample4.testMethod1");
    }

   @Test
    public void testMethod5(){

        System.out.println("TestNGExample5.testMethod1");
    }




}
