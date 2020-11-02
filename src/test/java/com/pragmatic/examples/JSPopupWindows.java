package com.pragmatic.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class JSPopupWindows {


    public static final String BASE_URL ="http://demosite.pragmatictestlabs.com/Alerts.html";
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
        webDriver.quit();
    }


    @Test
    public void testAlerts(){
        webDriver.findElement(By.xpath("//button[text()='Simple Alert']")).click();

        String message = webDriver.switchTo().alert().getText();
        Assert.assertEquals("This Is Simple Alert", message);

        //Close the alert
        webDriver.switchTo().alert().accept();
        String confirmation = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals("Alert is gone.", confirmation);
   }


    @Test
    public void testConfirmationOK(){
        webDriver.findElement(By.xpath("//button[text()=' Confirm Alert']")).click();
        String message = webDriver.switchTo().alert().getText();
        Assert.assertEquals("Press a button!", message);
        webDriver.switchTo().alert().accept();
        String confirmation = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals("Confirmed.", confirmation);
   }

   @Test
    public void testConfirmationCANCEL(){
        webDriver.findElement(By.xpath("//button[text()=' Confirm Alert']")).click();
        String message = webDriver.switchTo().alert().getText();
        Assert.assertEquals("Press a button!", message);
        webDriver.switchTo().alert().dismiss();
        String confirmation = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals("Rejected!", confirmation);
   }



    @Test
    public void testPrompt(){

        String name = "Pragmatic";
        webDriver.findElement(By.xpath("//button[text()='Prompt Alert']")).click();
        String message = webDriver.switchTo().alert().getText();
        Assert.assertEquals("Please enter your name", message);
        webDriver.switchTo().alert().sendKeys(name);
        webDriver.switchTo().alert().accept();
        String confirmation = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals(name, confirmation);
    }


    @Test
    public void testTimingAlert(){
        webDriver.findElement(By.xpath("//button[text()='Timing Alert']")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.alertIsPresent());


        String message = webDriver.switchTo().alert().getText();
        Assert.assertEquals("This is Timing Alert", message);
        webDriver.switchTo().alert().accept();
        String confirmation = webDriver.findElement(By.id("confirm-demo")).getText();
        Assert.assertEquals("0", confirmation);

    }












}
