package com.pragmatic.hrm.tests;

import com.pragmatic.hrm.BaseDataProvider;
import com.pragmatic.hrm.BrowserManager;
import com.pragmatic.hrm.Constants;
import com.pragmatic.hrm.HRMTestBase;
import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */


public class LoginTest extends HRMTestBase {


    private WebDriver webDriver;


    @BeforeMethod(groups = {"regression", "smoke"})
    public void beforeMethod() {
        webDriver = new BrowserManager().getBrowserInstance(Constants.BROWSER_TYPE);
        webDriver.get(Constants.BASE_URL);
    }


    @AfterMethod(groups = {"regression", "smoke"})
    public void afterMethod() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test(groups = {"smoke", "regression"})
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(Constants.ADMIN_USERNAME).typePassword(Constants.ADMIN_PASSWORD).clickLogin();
        LandingPage landingPage = PageFactory.initElements(webDriver, LandingPage.class);
        Assert.assertEquals(landingPage.getWelcomeMessage(), "Welcome %s".formatted(Constants.ADMIN_USERNAME));
    }

    @Test(groups = {"regression"})
    public void testLoginWithBlankUsername() {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername("").typePassword("Ptl").clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Username cannot be empty");
    }


    @Test(groups = {"regression"})
    public void testLoginWithBlankUsernameAndBlankPassword() {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername("").typePassword("").clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Username cannot be empty");
    }


    @Test(groups = {"regression"})
    public void testLoginWithBlankPassword() {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(Constants.ADMIN_USERNAME).typePassword("").clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), "Password cannot be empty");
    }

    @Test(groups = {"regression"}, dataProvider = "user_credentials", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLogin(String username, String passowrd, String expected_resullt) {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(username).typePassword(passowrd).clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), expected_resullt);
    }

    @Test(groups = {"regression"}, dataProvider = "user_credentials_csv", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLoginDPCSV(JSONObject credentials) {

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(credentials.get("username").toString())
                .typePassword(credentials.get("password").toString())
                .clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), credentials.get("expected_outcome").toString());
    }


    @Test(groups = {"regression"}, dataProvider = "user_credentials_xl", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLoginDPXL(JSONObject credentials) {

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(credentials.get("username").toString())
                .typePassword(credentials.get("password").toString())
                .clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), credentials.get("expected_outcome").toString());
    }

    @Test(groups = {"regression"}, dataProvider = "user_credentials_xml", dataProviderClass = BaseDataProvider.class)
    public void testInvalidUserLoginDPXML(JSONObject credentials) {

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.typeUsername(credentials.get("username").toString())
                .typePassword(credentials.get("password").toString())
                .clickLogin();
        Assert.assertEquals(loginPage.getErrorMessage(), credentials.get("expected_outcome").toString());
    }


}
