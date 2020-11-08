package com.pragmatic.hrm.tests;

import com.pragmatic.hrm.BaseDataProvider;
import com.pragmatic.hrm.HRMTestBase;
import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import org.json.simple.JSONObject;
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
        LandingPage landingPage = loginPage.typeUsername("Admin").typePassword("Ptl@#321").clickLoginWithSuccess();
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

    @Test(groups = {"regression"}, dataProvider = "user_credentials", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLogin(String username, String passowrd, String expected_resullt) {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(username).typePassword(passowrd).clickLoginButtonWithError();
        Assert.assertEquals(loginPage.getErrorMessage(), expected_resullt);
    }

    @Test(groups = {"regression"}, dataProvider = "user_credentials_csv", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLoginDPCSV(JSONObject credentials) {

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(credentials.get("username").toString())
                .typePassword(credentials.get("password").toString())
                .clickLoginButtonWithError();
        Assert.assertEquals(loginPage.getErrorMessage(), credentials.get("expected_outcome").toString());
    }


    @Test(groups = {"regression"}, dataProvider = "user_credentials_xl", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLoginDPXL(JSONObject credentials) {

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(credentials.get("username").toString())
                .typePassword(credentials.get("password").toString())
                .clickLoginButtonWithError();
        Assert.assertEquals(loginPage.getErrorMessage(), credentials.get("expected_outcome").toString());
    }

    @Test(groups = {"regression"}, dataProvider = "user_credentials_xml", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLoginDPXML(JSONObject credentials) {

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(credentials.get("username").toString())
                .typePassword(credentials.get("password").toString())
                .clickLoginButtonWithError();
        Assert.assertEquals(loginPage.getErrorMessage(), credentials.get("expected_outcome").toString());
    }


}
