package com.pragmatic.examples.selenium.supportclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class expectedCondtionsExample {

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
    public void explicitWaitWithCustomExpectedConditions() throws InterruptedException {
        final WebElement btnResend = webDriver.findElement(By.id("resend-select"));
        btnResend.click();

        //Wait for visibility of the element
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

        wait.until(
                historyMessageCountIncreaseInNumber()
        );

        WebElement txtFirstMessage = webDriver.findElement(By.cssSelector("#single-list li.message"));
        Assertions.assertEquals("Received message: selected 1", txtFirstMessage.getText());

        //Ensure that implicit wait is set off
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private ExpectedCondition<Boolean> historyMessageCountIncreaseInNumber() {
        return new ExpectedCondition<Boolean>() {

            private int initialCount;

            @Override
            public Boolean apply(@Nullable WebDriver webDriver) {
                int currentCount = webDriver.findElements(By.cssSelector("li.message")).size();
                System.out.println("initialCount = %s, currentCount = %s ".formatted(initialCount, currentCount));
                return currentCount> initialCount;
            }
        };
    }


}
