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
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.testng.Assert;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ColorTest {

    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver(){
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get("https://eviltester.github.io/supportclasses/");

    }


    @AfterAll
    public static void closeDriver(){
        webDriver.close();
    }

    @Test
    public void anInitialTest(){
        final WebElement title = webDriver.findElement(By.id("instruction-title"));

        final Color blackValue = Colors.BLACK.getColorValue();

        Assertions.assertEquals("#000000", blackValue.asHex());
        Assertions.assertEquals("rgba(0, 0, 0, 1)", blackValue.asRgba());
        Assertions.assertEquals("rgb(0, 0, 0)", blackValue.asRgb());

        Assertions.assertEquals(title.getCssValue("background-color"), blackValue.asRgb());


        final Color redValue = Colors.RED.getColorValue();
        Assertions.assertEquals(title.getCssValue("color"), redValue.asRgb());



    }

}
