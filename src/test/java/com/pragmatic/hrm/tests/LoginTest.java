package com.pragmatic.hrm.tests;

import com.pragmatic.hrm.HRMTestBase;
import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */


public class LoginTest extends HRMTestBase {




    @Test(groups = {"smoke", "regression"})
    public void testLoginWithValidCredentials(){
        LoginPage loginPage= PageFactory.initElements(webDriver,LoginPage.class);
        LandingPage landingPage = loginPage.typeUsername("Admin").typePassword("Ptl@#321").clickLoginWithSuccess(webDriver);
        Assert.assertEquals(landingPage.getWelcomeMessage(), "Welcome Admin");
    }

    @Test(groups = {"regression"})
    public void testLoginWithBlankUsername(){
        LoginPage loginPage= PageFactory.initElements(webDriver,LoginPage.class);
        loginPage.typeUsername("").typePassword("Ptl").clickLoginButtonWithError();
        Assert.assertEquals(loginPage.getErrorMessage(), "Username cannot be empty");
    }


    @Test(groups = {"regression"})
    public void testLoginWithBlankUsernameAndBlankPassword(){
        LoginPage loginPage= PageFactory.initElements(webDriver,LoginPage.class);
        loginPage.typeUsername("").typePassword("").clickLoginButtonWithError();
        Assert.assertEquals(loginPage.getErrorMessage(), "Username cannot be empty");
    }


    @Test(groups = {"regression"})
    public void testLoginWithBlankPassword() {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername("Admin").typePassword("").clickLoginButtonWithError();
        Assert.assertEquals(loginPage.getErrorMessage(), "Password cannot be empty");
    }







}
