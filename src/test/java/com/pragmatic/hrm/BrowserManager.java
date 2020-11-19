package com.pragmatic.hrm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class BrowserManager {


    private  WebDriver webDriver;
    static final Logger logger = LogManager.getLogger(BrowserManager.class);


    public void initWebDrivers(String browserType){

        switch (browserType.toLowerCase()) {
            case "chrome", "google-chrome", "chrome-headless", "headless" -> {
                WebDriverManager.chromedriver().setup();
                break;
            }
            case "firefox", "mozilla", "firefox-headless" -> {
                WebDriverManager.firefoxdriver().setup();
                break;
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                break;
            }
            case "ie" -> {
                WebDriverManager.iedriver().setup();
                break;
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                break;
            }
            case "safari" -> {
                webDriver = new SafariDriver();
                break;
            }
            default -> {
                logger.fatal("Supported browser type is not set");
                throw new BrowserManagerException("Supported browser is not configured");
            }

        }

    }


    public WebDriver getBrowserInstance(String browserType) {



        try {
            switch (browserType.toLowerCase()) {
                case "chrome", "google-chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    options.setExperimentalOption("useAutomationExtension", false);
                    webDriver= new ChromeDriver(options);
                }
                case "chrome-headless", "headless" -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                    options.setExperimentalOption("useAutomationExtension", false);
                    webDriver =new ChromeDriver(options);
                }
                case "firefox", "mozilla" -> {
                     webDriver = new FirefoxDriver();
                }
                case "firefox-headless" -> {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(true);
                    webDriver = new FirefoxDriver(options);
                }
                case "opera" -> {
                    webDriver = new OperaDriver();
                }
                case "ie" -> {
                    webDriver = new InternetExplorerDriver();
                }
                case "edge" -> {
                    webDriver = new EdgeDriver();
                }
                case "safari" -> {
                    webDriver = new SafariDriver();
                }
                default -> {
                    logger.fatal("Supported browser type is not set");
                    throw new BrowserManagerException("Supported browser is not configured");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return webDriver;
    }
}