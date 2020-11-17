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
import org.openqa.selenium.support.ByIdOrName;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ByIDorNameTest {

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
            final WebElement instructionsPara = webDriver.findElement(new ByIdOrName("instruction-text"));
            final List<WebElement> instructionsParaAgain = webDriver.findElements(new ByIdOrName("instructions"));

            Assertions.assertEquals(instructionsPara.getText(), instructionsParaAgain.get(0).getText());


    }

}
