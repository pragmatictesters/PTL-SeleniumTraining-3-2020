package com.pragmatic.examples.selenium.supportclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SelectExample {

    public static final String BASE_URL = "https://eviltester.github.io/supportclasses/";
    private static WebDriver webDriver;


    @BeforeAll
    public static void createDriver(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(BASE_URL);

    }


    @AfterAll
    public static void closeDriver(){
        webDriver.close();
    }

    @Test
    public void selectingElementWithFindElements(){
        final WebElement selectMenu = webDriver.findElement(By.id("select-menu"));
        final List<WebElement> options = selectMenu.findElements(By.tagName("option"));
        WebElement option = null;

        for (WebElement foundOption : options){
            if (foundOption.getText().contentEquals("Option 3")){
                option = foundOption;
                break;
            }
        }
        option.click();

        Assertions.assertEquals("3", option.getAttribute("value"));
    }


    @Test
    public void selectingOptionWithXPath(){
        final WebElement selectMenu = webDriver.findElement(By.id("select-menu"));
        final WebElement option = selectMenu.findElement(By.xpath("option[. ='Option 3']"));

        option.click();
        Assertions.assertEquals("3", option.getAttribute("value"));

    }


    @Test
    public void selectByVisibleText(){
        final WebElement selectMenu = webDriver.findElement(By.id("select-menu"));
        final Select select = new Select(selectMenu);
        select.selectByVisibleText("Option 3");
        Assertions.assertEquals("3", select.getFirstSelectedOption().getAttribute("value"));
    }



    @Test
    public void listMultiSelectItems(){
        final WebElement selectMulti = webDriver.findElement(By.id("select-multi"));

        final Select select = new Select(selectMulti);

        Assertions.assertTrue(select.getAllSelectedOptions().size()==0);
        Assertions.assertTrue(select.isMultiple());

        select.selectByVisibleText("First");
        Assertions.assertTrue(select.getAllSelectedOptions().size()==1);

        select.selectByVisibleText("Second");
        Assertions.assertTrue(select.getAllSelectedOptions().size()==2);

        select.deselectAll();
        Assertions.assertTrue(select.getAllSelectedOptions().size()==0);

        select.selectByVisibleText("First"); //First item
        select.selectByIndex(1); //Second item
        select.selectByValue("30"); //Third item

        List<WebElement> selectedElements = select.getAllSelectedOptions();

        Assertions.assertEquals(3, selectedElements.size());
        WebElement firstOption = selectedElements.get(0);
        Assertions.assertEquals("First", firstOption.getText());


        select.deselectAll();
        Assertions.assertThrows(NoSuchElementException.class, () ->{
            select.getFirstSelectedOption();
        });

    }


    @Test
    public void testPTLSelectAbstraction(){
        final WebElement selectMulti = webDriver.findElement(By.id("select-multi"));
        final PTLSelect select = new PTLSelect(selectMulti);
        select.selectByVisibleText("First"); //First item
        select.selectByIndex(1); //Second item
        select.selectByValue("30"); //Third item
        Assertions.assertEquals("Third", select.getLastElementText());
    }


}
