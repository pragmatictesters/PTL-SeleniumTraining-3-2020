package com.pragmatic.examples.testng;

import org.testng.annotations.*;


/**
 * Created by Pragmatic Test Labs
 *
 * @Author Janesh Kodikara
 */
public class TestBase {


    @BeforeSuite(groups = {"smoke"})
    public void beforeSuite() {
        System.out.println("TestBase.beforeSuite");
    }


    @BeforeGroups(groups = {"smoke"})
    public void beforeGroups() {
        System.out.println("TestBase.beforeGroups");
    }

    @BeforeSuite(groups = {"regression"})
    public void beforeSuiteCommon() {
        System.out.println("TestBase.beforeSuiteCommon");
    }

    @BeforeTest(groups = {"smoke"})
    public void beforeTest() {
        System.out.println("TestBase.beforeTest");
    }


    @BeforeClass
    public void beforeClass() {
        System.out.println("TestBase.beforeClass");
    }


    @AfterMethod
    public void afterMethod() {
        System.out.println("TestBase.afterMethod");
    }





}
