package com.pragmatic.examples.java;

import io.github.bonigarcia.wdm.WebDriverManager;
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
public class ListOfElementsExample {


    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://hrm.pragmatictestlabs.com");

        List<WebElement> inputElements = webDriver.findElements(By.tagName("input"));
        for (WebElement element : inputElements) {

            System.out.println("Type is " + element.getAttribute("type"));
        }
        webDriver.quit();


    }

}
