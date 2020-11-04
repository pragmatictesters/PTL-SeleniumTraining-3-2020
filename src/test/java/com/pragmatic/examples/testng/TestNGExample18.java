package com.pragmatic.examples.testng;

import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs
 *
 * @Author Janesh Kodikara
 */

@Test(groups = {"regression"})
public class TestNGExample18  extends TestBase {



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

   @Test(groups = {"smoke"})
    public void testMethod5(){

        System.out.println("TestNGExample5.testMethod1");
    }




}
