package com.pragmatic.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

import java.util.Collections;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HRMTestBase {

    public WebDriver webDriver;
    public static final String BASE_URL = "http://hrm.pragmatictestlabs.com";
    public static final String BROWSER_TYPE = "chrome";



    @BeforeSuite(alwaysRun = true, groups = {"regression", "smoke"})
    public void beforeSuite(){

        WebDriverManager.chromedriver().setup();

    }


    public WebDriver getBrowserInstance() {

      if (BROWSER_TYPE.equalsIgnoreCase("chrome")){
          ChromeOptions options = new ChromeOptions();
          options.addArguments("start-maximized");
          options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
          options.setExperimentalOption("useAutomationExtension", false);
          webDriver= new ChromeDriver(options);

      }else if(BROWSER_TYPE.equalsIgnoreCase("chrome-headless")){
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--headless");
          options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
          options.setExperimentalOption("useAutomationExtension", false);
          webDriver= new ChromeDriver(options);

      } else {
          //TODO Add a custom error message here
      }

      return webDriver;
    }




}
