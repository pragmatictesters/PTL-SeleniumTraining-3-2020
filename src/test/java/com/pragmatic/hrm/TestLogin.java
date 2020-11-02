package com.pragmatic.hrm;

import com.pragmatic.util.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs
 *
 * @Auther Janesh Kodikara
 */
public class TestLogin {

    public static final String BASE_URL = "http://hrm.pragmatictestlabs.com";
    WebDriver webDriver;



    @BeforeClass
    public void beforeClass(){
        //Setup the browser
        WebDriverManager.chromedriver().setup();

    }


    @BeforeMethod
    public void beforeMethod(){
        //Launch the browser
        webDriver = new ChromeDriver();

        //Navigate to the login page
        webDriver.navigate().to(BASE_URL);

    }


    @AfterMethod()
    public void  afterMethod(){
        //webDriver.quit();
        //webDriver.quit();
    }


    @Test
    public void testLoginWithKeyboardActions(){
        WebElement txtUsername = webDriver.findElement(By.id("txtUsername"));

        Actions actions = new Actions(webDriver);
        actions.sendKeys(txtUsername, "Admin")
                .pause(500)
                .sendKeys(Keys.TAB)
                .pause(500)
                .sendKeys("Ptl@#321")
                .pause(500)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

    }



    @Test
    public void testLoginWithValidUserCredentials(){


        //Type username , password and click login
        webDriver.findElement(By.name("txtUsername")).sendKeys("Admin");
        webDriver.findElement(By.name("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.name("txtUsername")).submit();


    }

    @Test
    public void testLoginWithBlankUsername(){
        webDriver.findElement(By.name("txtUsername")).sendKeys("");
        webDriver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("");

        webDriver.findElement(By.name("txtPassword")).sendKeys("Ptl@#321");
        webDriver.findElement(By.id("btnLogin")).submit();
        String errorMessage = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, "Username cannot be empty");

    }
    @Test
    public void testLoginWithBlankUsernameAndBlankPassword(){
        webDriver.findElement(By.name("txtUsername")).sendKeys("");
        webDriver.findElement(By.name("txtPassword")).sendKeys("");
        webDriver.findElement(By.id("btnLogin")).submit();
        String errorMessage = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, "Username cannot be empty");

    }
    @Test
    public void testLoginWithInvalidPassword(){
        webDriver.findElement(By.name("txtUsername")).sendKeys("Admin");
        webDriver.findElement(By.name("txtPassword")).sendKeys("test");
        webDriver.findElement(By.id("btnLogin")).submit();
        String errorMessage = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, "Invalid credentials");

    }
    @Test
    public void testVerifyPasswordCaseSensitivity(){
        webDriver.findElement(By.name("txtUsername")).sendKeys("Admin");
        webDriver.findElement(By.name("txtPassword")).sendKeys("ptl@#321");
        webDriver.findElement(By.id("btnLogin")).submit();
        String errorMessage = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, "Invalid credentials");

    }




    @Test (dataProvider = "user_credentials", dataProviderClass = TestData.class)
    public void testInvalidUserLoginsDDT(String username, String password, String expected_outcome){
        webDriver.findElement(By.name("txtUsername")).sendKeys(username);
        webDriver.findElement(By.name("txtPassword")).sendKeys(password);
        webDriver.findElement(By.id("btnLogin")).submit();
        String errorMessage = webDriver.findElement(By.id("spanMessage")).getText();
        Assert.assertEquals(errorMessage, expected_outcome);

    }







}
