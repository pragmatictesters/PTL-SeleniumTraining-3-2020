package com.pragmatic.examples.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SwitchToWindowsTest {

    private static final String BASE_URL = "http://formy-project.herokuapp.com/switch-window";
    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);

    }


    @AfterAll
    public static void closeDriver() {
        //webDriver.close();
        webDriver.quit();
    }

    @Test
    public void switchWindowTest() {
        String originalWindowHandle = webDriver.getWindowHandle();

        Assertions.assertEquals("Switch Window",getHeader(webDriver.findElement(By.tagName("h1"))) );

        WebElement btnOpenNewTab = webDriver.findElement(By.id("new-tab-button"));
        btnOpenNewTab.click();

        //Switching to the latest window
        for (String newWindowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(newWindowHandle);
            getHeader(webDriver.findElement(By.tagName("h1")));
        }

        Assertions.assertEquals("Welcome to Formy",getHeader(webDriver.findElement(By.tagName("h1"))) );

        List<WebElement> links = webDriver.findElements(By.cssSelector("li>a"));
        displayLinks(links);

        //Switch to the original window
        webDriver.switchTo().window(originalWindowHandle);

        Assertions.assertEquals("Switch Window",getHeader(webDriver.findElement(By.tagName("h1"))) );


    }

    private void displayLinks(List<WebElement> links) {
        for (WebElement link: links){
            System.out.println(link.getText());
        }

    }


    private String getHeader(WebElement header) {
        System.out.println(header.getText());
        return header.getText();
    }



}
