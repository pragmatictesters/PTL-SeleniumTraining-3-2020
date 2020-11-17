package com.pragmatic.examples.selenium.supportclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ByAllTest {

    private static final String BASE_URL = "https://eviltester.github.io/supportclasses/";
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
    public void testByAll() {
        final List<WebElement> buttons = webDriver.findElements(
                new ByAll(By.id("resend-select"),
                        By.name("resend-select")));

        Assertions.assertEquals(2, buttons.size());
        Assertions.assertTrue(buttons.contains(webDriver.findElement(By.id("resend-select"))));
        Assertions.assertTrue(buttons.contains(webDriver.findElement(By.name("resend-select"))));





    }

}
