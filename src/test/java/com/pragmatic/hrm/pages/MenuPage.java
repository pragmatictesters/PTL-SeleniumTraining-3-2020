package com.pragmatic.hrm.pages;

import com.pragmatic.hrm.HRMTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class MenuPage {

    private final WebDriver webDriver;


    @FindBy(xpath = "//a[@id='menu_pim_viewPimModule']")
    private WebElement mnuPIM;

    @FindBy(xpath = "//*[@id='menu_pim_addEmployee']")
    private WebElement mnuAddEmployee;

    public MenuPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }


    public void navigateToAddEmployeePage() {
        mnuPIM.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(mnuAddEmployee));
        mnuAddEmployee.click();
    }


}
