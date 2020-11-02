package com.pragmatic.testng;

import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;


/**
 * Created by Pragmatic Test Labs
 *
 * @Auther Janesh Kodikara
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
