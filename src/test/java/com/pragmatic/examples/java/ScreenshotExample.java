package com.pragmatic.examples.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ScreenshotExample {


    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://hrm.pragmatictestlabs.com");

        //Create the screenshot
        File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        try {
            //Saving the screenshot
            FileUtils.copyFile(srcFile,  new File("screens/homepage.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        webDriver.quit();


    }

}
