package com.pragmatic.examples.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ExtentReportTestNGExample {
    ExtentReports extent;


    @BeforeClass
    public void beforeClass() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");

        extent.attachReporter(spark);
    }


    @BeforeMethod
    public void beforeMethod() {

    }


    @Test
    public void testMethod1() {


        extent.createTest("MyFirstTest")
                .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
        extent.flush();
    }

    @Test
    public void testMethod2() {
        extent.createTest("MySecondTest")
                .log(Status.FAIL, "This is a logging event for my second, and it failed!");
        extent.flush();
    }

    @Test
    public void testMethod3() {
        extent.createTest("MyThirdTest")
                .log(Status.SKIP, "This is a logging event for my third test, and it skipped!");
        extent.flush();
    }


    @Test
    public void testMethod4() {
        extent.createTest("MyFourthTest")
                .log(Status.INFO, "This is a logging event for my fourth, and it info!");
        extent.flush();
    }

    @Test
    public void testMethod5() {
        extent.createTest("MyFifthTest")
                .log(Status.WARNING, "This is a logging event for my fourth, and it is a warning!");
        extent.flush();
    }


}
