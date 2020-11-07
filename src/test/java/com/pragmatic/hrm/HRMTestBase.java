package com.pragmatic.hrm;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */

@Listeners({ExtentITestListenerClassAdapter.class})
public class HRMTestBase {

    public WebDriver webDriver;
    public static String BASE_URL;
    public static String BROWSER_TYPE;

    static final Logger logger = LogManager.getLogger(HRMTestBase.class.getName());


    @BeforeSuite(alwaysRun = true, groups = {"regression", "smoke"})
    public void beforeSuite() {
        logger.info("Test is started");
        initProperties();

    }


    @BeforeMethod(groups = {"regression", "smoke"})
    public void beforeMethod() {
        webDriver = getBrowserInstance();
        webDriver.get(BASE_URL);
    }


    @AfterMethod(groups = {"regression", "smoke"})
    public void afterMethod() {

        if (webDriver != null) {
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
            logger.fatal("Property Initialization Failed", e);
            System.exit(-1);
        }

    }


    public WebDriver getBrowserInstance() {


        switch (BROWSER_TYPE.toLowerCase()) {
            case "chrome", "google-chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                options.setExperimentalOption("useAutomationExtension", false);
                webDriver = new ChromeDriver(options);
            }
            case "chrome-headless", "headless" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                options.setExperimentalOption("useAutomationExtension", false);
                webDriver = new ChromeDriver(options);
            }
            case "firefox", "mozilla" -> {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            }
            case "firefox-headless" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.setHeadless(true);
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver(options);
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                webDriver = new OperaDriver();
            }
            case "ie" -> {
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
            }
            case "safari" -> {
                webDriver = new SafariDriver();
            }
            default -> {
                //TODO Add a custom error message here
                logger.fatal("Valid browser type is not set.");
                System.exit(-1);
            }

        }


        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Browser " + BROWSER_TYPE + " is launched");
        return webDriver;
    }


    public LandingPage login(String userName, String password) {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
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

    public void check(WebElement checkbox) {

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }


    public void uncheck(WebElement checkbox) {
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void clearAndType(WebElement txtElement, String textToType) {
        txtElement.clear();
        txtElement.sendKeys(textToType);
    }


    public static JSONObject[][] readCSV(String fileName) {

        JSONObject[][] data = null;


        try {


            //Get the header information dynamically
            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader();
            FileReader fileReader = null;
            fileReader = new FileReader(fileName);
            CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);
            List csvRecords = csvFileParser.getRecords();


            int currentRow = 0;
            int rowsInCSV = csvRecords.size();
            data = new JSONObject[rowsInCSV][1];

            Reader in = new FileReader(fileName);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {

                JSONObject jsonObject = new JSONObject();
                for (String header : csvFileParser.getHeaderNames()) {
                    jsonObject.put(header, record.get(header));
                }
                data[currentRow++][0] = jsonObject;
            }
            return data;

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return data;

    }


    public static JSONObject[][] readXLFile(String fileName1) {
        String fileName = fileName1;
        int rowsInSheet = 0;
        JSONObject[][] data = null;

        try {
            FileInputStream excelFile = new FileInputStream(fileName);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            rowsInSheet = sheet.getPhysicalNumberOfRows();
            data = new JSONObject[rowsInSheet - 1][1];

            Iterator<Row> iterator = sheet.iterator();

            Row firstRow = iterator.next();
            Iterator<Cell> cellIterator = firstRow.iterator();
            String[] columns = new String[firstRow.getPhysicalNumberOfCells()];
            int columnID = 0;
            while (cellIterator.hasNext()) {
                String value = cellIterator.next().getStringCellValue();
                columns[columnID++] = value;
            }


            while (iterator.hasNext()) {
                JSONObject jsonObject = new JSONObject();

                Row currentRow = iterator.next();
                //Iterator<Cell> cellIterator = currentRow.iterator();
                cellIterator = currentRow.iterator();
                columnID = 0;

                while (cellIterator.hasNext()) {
                    String value = cellIterator.next().getStringCellValue();
                    jsonObject.put(columns[columnID++], value);

                }
                data[currentRow.getRowNum() - 1][0] = jsonObject;

            }
            return data;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }


    public static JSONObject[][] readXML(String fileName, String tagName) {
        JSONObject[][] data = null;

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document document = db.parse(fileName);


            NodeList nodeList = document.getElementsByTagName(tagName);
            data = new JSONObject[nodeList.getLength()][1];
            Node node = nodeList.item(0);
            Element element;

            for (int i = 0; i < nodeList.getLength(); i++) {

                JSONObject jsonObject = new JSONObject();
                node = nodeList.item(i);
                element = (Element) node;

                NodeList nodeList1 = element.getChildNodes();
                for (int k = 0; k < element.getChildNodes().getLength(); k++) {

                    if (nodeList1.item(k).getNodeName() != "#text") {
                        String nodeName = nodeList1.item(k).getNodeName();
                        jsonObject.put(nodeName, element.getElementsByTagName(nodeName).item(0).getTextContent());

                    }
                }
                data[i][0] = jsonObject;
            }
            return data;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public void selecItemtByVisibleText(String visibleText, WebElement element) {

        new Select(element).selectByVisibleText(visibleText);
    }


    public void takeScreenshot(String filename) {

        try {
            File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public void waitForElementPresence(WebElement webElement) {

        By by = getByFromElement(webElement);
        waitForElementPresence(by);
    }

    private By getByFromElement(WebElement element) {

        By by = null;
        String[] pathVariables = (element.toString()
                .split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "cssSelector":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
        return by;
    }
}
