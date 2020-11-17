package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ScrollTest {

    private static final String BASE_URL = "http://formy-project.herokuapp.com/scroll";
    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);

    }


    @AfterAll
    public static void closeDriver() {
        webDriver.close();
    }

    @Test
    public void scrollTest() {
        WebElement txtFullName = webDriver.findElement(By.id("name"));

        //Selenium scrolls implicitly to the given element and type the value
        txtFullName.sendKeys("Janesh Kodikara");


    }

    @Test
    public void scrollWithActionsClassTest() {
        WebElement txtFullName = webDriver.findElement(By.id("name"));

        //Selenium scrolls implicitly to the given element and type the value
        Actions actions = new Actions(webDriver);
        actions.moveToElement(txtFullName)
                .build()
                .perform();

        txtFullName.sendKeys("Janesh Kodikara");


    }

    @Test
    public void scrollWithJSExecutorTest() {
        WebElement txtFullName = webDriver.findElement(By.id("name"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", txtFullName);
        txtFullName.sendKeys("Janesh Kodikara");

    }


    @Test
    public void scrollWithJSExecutorScrollByTest() {
        WebElement txtFullName = webDriver.findElement(By.id("name"));
        scrollBy(webDriver, 0, 200);
        txtFullName.sendKeys("Janesh Kodikara");
    }


    private void scrollBy(WebDriver webDriver, int x, int y) {
        //window.scrollBy(x, y);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("window.scrollBy(%s, %s)".formatted(x, y));

    }


}
