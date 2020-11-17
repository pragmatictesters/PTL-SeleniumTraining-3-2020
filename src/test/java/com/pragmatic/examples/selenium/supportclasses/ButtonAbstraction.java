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
public class ButtonAbstraction {

    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get("https://eviltester.github.io/supportclasses/");

    }


    @AfterAll
    public static void closeDriver() {
        webDriver.close();
    }

    @Test
    public void buttonAbstractionText() {
        WebElement buttonElement = webDriver.findElement(By.id("resend-select"));
        Button button = new Button(buttonElement);
        String btnText = button.getText();
        Assertions.assertEquals("Resend Single Option Message", btnText);
        button.click();
    }

    private class Button implements WrapsElement {
        private  WebElement buttonElement;

        public Button(WebElement buttonElement) {
            this.buttonElement = buttonElement;
        }

        @Override
        public WebElement getWrappedElement() {
            return buttonElement ;
        }


        public String getText(){
            return buttonElement.getText();
        }


        public WebElement click(){
            buttonElement.click();
            return buttonElement;

        }

    }

//    private class Button implements WrapsElement {
//        private WebElement buttonElement;
//
//        public Button(WebElement buttonElement) {
//            this.buttonElement = buttonElement;
//        }
//
//        @Override
//        public WebElement getWrappedElement() {
//            return buttonElement;
//        }
//
//        public String getText() {
//            return buttonElement.getText();
//        }
//
//
//        public void click() {
//            buttonElement.click();
//        }
//    }
}
