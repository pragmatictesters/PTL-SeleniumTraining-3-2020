package com.pragmatic.examples.selenium.supportclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ByChainedTest {

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
    public void testByChained(){
        final WebElement resendSingle = webDriver.findElement(By.id("resend-select"));
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();


        final WebElement resendSMulti = webDriver.findElement(By.id("resend-multi"));
        resendSMulti.click();
        resendSMulti.click();


        final List<WebElement> allMessages = webDriver.findElements(By.className("message"));
        Assertions.assertEquals(5, allMessages.size());


        final List<WebElement> singleMessages = webDriver.findElements(new ByChained(By.id("single-list"),
                                                By.className("message")));
        Assertions.assertEquals(3, singleMessages.size());

        final List<WebElement> multiMessages = webDriver.findElements(new ByChained(By.id("multi"),
                                                By.className("message")));
        Assertions.assertEquals(2, multiMessages.size());




    }


     @Test
    public void testByAttribute(){
        final WebElement resendSingle = webDriver.findElement(By.id("resend-select"));
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();


        final WebElement resendSMulti = webDriver.findElement(By.id("resend-multi"));
        resendSMulti.click();
        resendSMulti.click();


        //final List<WebElement> allMessages = webDriver.findElements(By.className("message"));
        final List<WebElement> allMessages = webDriver.findElements(new ByAttributeValue("class","message"));
        Assertions.assertEquals(5, allMessages.size());





    }


    private class ByAttributeValue extends By {
        private final String name;
        private final String value ;

        public ByAttributeValue(String attribute, String value) {
            this.name = attribute;
            this.value = value;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            return context.findElements(By.cssSelector(
                    String.format("[%s=%s]", name,value)
            ));
        }
    }
}
