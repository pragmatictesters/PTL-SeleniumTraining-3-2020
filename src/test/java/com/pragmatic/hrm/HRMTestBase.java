package com.pragmatic.hrm;

import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HRMTestBase {

    public WebDriver webDriver;
    public static String BASE_URL;
    public static  String BROWSER_TYPE;

    static final Logger logger = LogManager.getLogger(HRMTestBase.class.getName());




    @BeforeSuite(alwaysRun = true, groups = {"regression", "smoke"})
    public void beforeSuite(){
        logger.info("Test is started");
        initProperties();

    }




    @BeforeMethod(groups = {"regression", "smoke"})
    public void beforeMethod(){
        webDriver = getBrowserInstance();
        webDriver.get(BASE_URL);
    }



    @AfterMethod(groups = {"regression", "smoke"})
    public void afterMethod(){

        if (webDriver!=null){
            webDriver.quit();
        }

    }

    private void initProperties() {
        try {
            Configuration config = new PropertiesConfiguration("hrm.properties");
            BASE_URL = config.getString("base.url");
            BROWSER_TYPE = config.getString("browser.type");
            logger.info("Properties are initialized ");

        } catch (ConfigurationException e) {
            logger.error("Property Initialization Failed", e);
            System.exit(-1);
        }

    }


    public WebDriver getBrowserInstance() {

      if (BROWSER_TYPE.equalsIgnoreCase("chrome")){
          WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("start-maximized");
          options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
          options.setExperimentalOption("useAutomationExtension", false);
          webDriver= new ChromeDriver(options);

      }else if(BROWSER_TYPE.equalsIgnoreCase("chrome-headless")){
          WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--headless");
          options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
          options.setExperimentalOption("useAutomationExtension", false);
          webDriver= new ChromeDriver(options);

      } else if (BROWSER_TYPE.equalsIgnoreCase("firefox")){
          //TODO Upgrade the IDEA project to JDK 13
          WebDriverManager.firefoxdriver().setup();
          webDriver = new FirefoxDriver();

      } else if (BROWSER_TYPE.equalsIgnoreCase("firefox-headless")){
          //TODO Upgrade the IDEA project to JDK 13
          FirefoxOptions options = new FirefoxOptions();
          options.setHeadless(true);
          WebDriverManager.firefoxdriver().setup();
          webDriver = new FirefoxDriver(options);

      } else if (BROWSER_TYPE.equalsIgnoreCase("ie")){
          WebDriverManager.iedriver().setup();
          webDriver = new InternetExplorerDriver();
      } else if (BROWSER_TYPE.equalsIgnoreCase("safari")){
          webDriver = new SafariDriver();

      }else if (BROWSER_TYPE.equalsIgnoreCase("opera")){
          WebDriverManager.operadriver().setup();
          webDriver = new OperaDriver();

      }else if (BROWSER_TYPE.equalsIgnoreCase("edge")){
          WebDriverManager.edgedriver().setup();
          webDriver = new EdgeDriver();

      } else {
          //TODO Add a custom error message here
          System.exit(-1);
      }

      webDriver.manage().window().maximize();
      webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      logger.info("Browser is launched");
      return webDriver;
    }



    public LandingPage login(String userName, String password){
        LoginPage loginPage= PageFactory.initElements(webDriver,LoginPage.class);
        LandingPage landingPage = loginPage.typeUsername(userName).typePassword(password).clickLoginWithSuccess(webDriver);
        return landingPage;

    }



    //TODO Add custom errors

    public void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void check(WebElement checkbox){

        if (!checkbox.isSelected()){
            checkbox.click();
        }
    }


    public void uncheck(WebElement checkbox){
        if(checkbox.isSelected()){
            checkbox.click();
        }
    }

    public void clearAndType(WebElement txtElement, String textToType){
        txtElement.clear();
        txtElement.sendKeys(textToType);
    }




}
