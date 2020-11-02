package com.pragmatic.testng;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs
 *
 * @Auther Janesh Kodikara
 */

@Ignore
@Test
public class TestNGExample19 {




    public void testMethod1(){
        System.out.println("TestNGExample1.testMethod1");
    }

    public void testMethod2(){
        System.out.println("TestNGExample2.testMethod1");
    }


    public void testMethod3(){
        System.out.println("TestNGExample3.testMethod1");
    }


    public void testMethod4(){
        System.out.println("TestNGExample4.testMethod1");
    }

   @Test
    public void testMethod5(){
        System.out.println("TestNGExample5.testMethod1");
    }




}
