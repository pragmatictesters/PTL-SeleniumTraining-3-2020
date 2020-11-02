package com.pragmatic.hrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class MenuPage {


    @FindBy(xpath = "//a[@id='menu_pim_viewPimModule']")
    WebElement mnuPIM;

    @FindBy(xpath = "//*[@id='menu_pim_addEmployee']']")
    WebElement mnuAddEmployee;


    public AddNewEmployeePage navigateToAddEmployeePage(WebDriver webDriver) {
        mnuPIM.click();
        mnuAddEmployee.click();
        return PageFactory.initElements(webDriver, AddNewEmployeePage.class);
    }


}
