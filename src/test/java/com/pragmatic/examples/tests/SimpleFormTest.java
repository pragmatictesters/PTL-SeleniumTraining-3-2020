package com.pragmatic.examples.tests;

import com.pragmatic.examples.pages.SimpleForm;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SimpleFormTest {

    WebDriver webDriver;
    private SimpleForm simpleForm;


    @BeforeSuite
    public void beforeSuite(){
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public void beforeMethod(){
        webDriver= new ChromeDriver();
        webDriver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        simpleForm = PageFactory.initElements(webDriver, SimpleForm.class);

    }


    @Test
    public void testValidMessage(){
        String messageToType = "Hello Janesh";
        String message = simpleForm.typeMessage(messageToType)
                .clickShowMessageButton()
                .getMessage();

        Assert.assertEquals(message, messageToType);


    }


    @Test
    public void testTotal(){
        String strAnswer = simpleForm.typeNumberOne("1").typeNumberTwo("2").clickGetTotalButton().getTotalValue();
        Assert.assertEquals(strAnswer, "3");



    }

}
