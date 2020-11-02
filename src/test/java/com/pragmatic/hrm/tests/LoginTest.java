package com.pragmatic.hrm.tests;

import com.pragmatic.hrm.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginTest {

    private WebDriver webDriver;
    public static final String BASE_URL = "http://hrm.pragmatictestlabs.com";

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

    }


    @BeforeMethod
    public void beforeMethod(){
        webDriver.get(BASE_URL);
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    @Test
    public void testLoginWithBlankUsername(){
        LoginPage loginPage= PageFactory.initElements(webDriver,LoginPage.class);
        loginPage.typeUsername("").typePassword("Ptl").clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage(), "Username cannot be empty");
    }

}
