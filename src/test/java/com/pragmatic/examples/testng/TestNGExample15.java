package com.pragmatic.examples.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs
 *
 * @Author Janesh Kodikara
 */
public class TestNGExample15 {


    @BeforeMethod
    public void beforeMethod(){
        System.out.println("TestNGExample15.beforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("TestNGExample15.afterMethod");
    }

    @Test
    public void testMethod1(){

        System.out.println("TestNGExample1.testMethod1");
    }

   @Test
    public void testMethod2(){

        System.out.println("TestNGExample2.testMethod1");
    }

   @Test
    public void testMethod3(){

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
