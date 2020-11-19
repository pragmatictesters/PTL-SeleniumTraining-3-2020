package com.pragmatic.examples.selenium.supportclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SynchronizationExample {

    private static final String BASE_URL = "https://eviltester.github.io/supportclasses/#2000";
    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(BASE_URL);

    }


    @AfterAll
    public static void closeDriver() {
        webDriver.close();
    }

    @Test
    public void whyWaitsAreRequired() {
        final WebElement btnResend = webDriver.findElement(By.id("resend-select"));
        btnResend.click();

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            webDriver.findElement(By.cssSelector("#single-list li.message"));
        });

    }


    @Test
    public void waitWithThreadSleep() throws InterruptedException {
        final WebElement btnResend = webDriver.findElement(By.id("resend-select"));
        btnResend.click();
        Thread.sleep(3_000);

        WebElement txtFirstMessage = webDriver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertEquals("Received message: selected 1", txtFirstMessage.getText());
    }


    @Test
    public void waitWithImplicitWait() throws InterruptedException {
        final WebElement btnResend = webDriver.findElement(By.id("resend-select"));
        btnResend.click();

        webDriver.manage().timeouts().implicitlyWait(5_000, TimeUnit.MILLISECONDS);

        WebElement txtFirstMessage = webDriver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertEquals("Received message: selected 1", txtFirstMessage.getText());

        //Ensure that implicit wait is set off
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }


    @Test
    public void waitWithExplicitWait() throws InterruptedException {
        final WebElement btnResend = webDriver.findElement(By.id("resend-select"));
        btnResend.click();

        //Wait for visibility of the element
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).
                until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#single-list li.message"))
                );

        WebElement txtFirstMessage = webDriver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertEquals("Received message: selected 1", txtFirstMessage.getText());

        //Ensure that implicit wait is set off
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }


    @Test
    public void waitWithExplicitWaitShareWait() throws InterruptedException {
        final WebElement btnResend = webDriver.findElement(By.id("resend-select"));
        btnResend.click();

        //Wait for visibility of the element
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#single-list li.message"))
        );

        WebElement txtFirstMessage = webDriver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertEquals("Received message: selected 1", txtFirstMessage.getText());

        //Ensure that implicit wait is set off
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }


}
