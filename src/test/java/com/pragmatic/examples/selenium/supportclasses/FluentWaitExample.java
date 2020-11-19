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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class FluentWaitExample {

    private static final String BASE_URL = "https://eviltester.github.io/supportclasses/#_5000";
    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.get(BASE_URL);

    }


    @AfterAll
    public static void closeDriver() {
        webDriver.close();
    }

    @Test
    public void expectedConditionIsFluent() {

        WebElement btnResendSingleOption = webDriver.findElement(By.id("resend-select"));
        btnResendSingleOption.click();

        WebElement message = new WebDriverWait(webDriver, Duration.ofSeconds(10)).
                withMessage("Could not find a message").
                pollingEvery(Duration.ofMillis(100)).
                until(ExpectedConditions.
                        visibilityOfElementLocated(By.cssSelector("#single-list li.message")));


        Assertions.assertTrue(message.getText().startsWith("Received message:"));

    }

    @Test
    public void usingFluentWait() {

        WebElement btnResendSingleOption = webDriver.findElement(By.id("resend-select"));
        btnResendSingleOption.click();


        WebElement singleListParent = webDriver.findElement(By.id("single-list"));

        FluentWait wait = new FluentWait<WebElement>(singleListParent).
                withTimeout(Duration.ofSeconds(10)).
                withMessage("Could not find any new messages ").
                pollingEvery(Duration.ofMillis(100));

        wait.until(
                new HistoryMessageIncreaseInNumber(0)
        );


        Assertions.assertTrue(webDriver.findElement(By.cssSelector("li.message"))
                .getText()
                .startsWith("Received message:"));

    }


    private class HistoryMessageIncreaseInNumber implements Function<WebElement, Boolean> {
        private final int initialCount;
        private  int delay = 0;

        public HistoryMessageIncreaseInNumber(int initialCount) {
            this.initialCount = initialCount;

        }

        @Override
        public Boolean apply(WebElement webElement) {
            int currentCount = webElement.findElements(By.cssSelector("li.message")).size();
            delay +=100;
            System.out.printf("Delay in millis %s %n", delay);

            return currentCount > initialCount;
        }
    }
}
