package com.pragmatic.examples.selenium.supportclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class InitialTest {

    private static final String BASE_URL = "https://eviltester.github.io/supportclasses/";
    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver(){
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(BASE_URL);

    }


    @AfterAll
    public static void closeDriver(){
        webDriver.close();
    }

    @Test
    public void anInitialTest(){
        Assertions.assertEquals("Support Classes Example", webDriver.getTitle());
    }

}
