package com.pragmatic.hrm;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.github.javafaker.Faker;
import com.pragmatic.hrm.pages.LandingPage;
import com.pragmatic.hrm.pages.LoginPage;
import com.pragmatic.hrm.support.ui.Checkbox;
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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */

@Listeners({ExtentITestListenerClassAdapter.class})
public class HRMTestBase {

    public WebDriver webDriver;
    public Faker faker;
    public ExtentReports extent = new ExtentReports();


    static final Logger logger = LogManager.getLogger(HRMTestBase.class.getName());
    private WebDriverWait wait;


    @BeforeSuite(alwaysRun = true, groups = {"regression", "smoke"})
    public void beforeSuite() {
        logger.info("Test is started");
        ConfigManager.initProperties();
        new BrowserManager().initWebDrivers(Constants.BROWSER_TYPE);
        faker = new Faker();
    }


    @AfterSuite(alwaysRun = true, groups = {"regression", "smoke"})
    public void afterSuite() {

    }





    public void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
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


    public String getValue(WebElement webElement) {
        return webElement.getAttribute("value");
    }
}
