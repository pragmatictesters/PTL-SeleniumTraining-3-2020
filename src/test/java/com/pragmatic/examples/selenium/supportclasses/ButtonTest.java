package com.pragmatic.examples.selenium.supportclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ButtonTest {

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

      WebElement btnResend = webDriver.findElement(By.id("resend-select"));
      PTLButton ptlButton = new PTLButton(btnResend);






    }

    private class PTLButton implements WrapsElement {
        WebElement ptlButton;

        public PTLButton(WebElement btnResend) {
            this.ptlButton = btnResend;
        }

        @Override
        public WebElement getWrappedElement() {
            return  ptlButton;
        }

        public void click(){
            ptlButton.click();
        }

    }
}
