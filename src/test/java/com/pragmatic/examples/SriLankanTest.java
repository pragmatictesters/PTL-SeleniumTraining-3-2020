package com.pragmatic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SriLankanTest {


    public static final String BASE_URL ="https://www.srilankan.com/en_uk/lk";
    private WebDriver webDriver;


    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();

    }


    @BeforeMethod
    public void beforeMethod(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
       // options.addArguments("--headless");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        webDriver= new ChromeDriver(options);
        webDriver.get(BASE_URL);
    }


    @AfterMethod
    public void afterMethod(){
       // webDriver.quit();
    }


    @Test
    public void fillForm(){
        webDriver.findElement(By.id("radio2_tidtmp")).click();

        typeAndSelect("Colombo", webDriver.findElement(By.id("suggest1")));
        typeAndSelect("London", webDriver.findElement(By.id("suggest2")));


    }

    private void typeAndSelect(String text, WebElement webElement) {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(webElement, text)
                .pause(2000)
                .sendKeys(Keys.ARROW_DOWN)
                .pause(2000)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

    }


}
