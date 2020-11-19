package com.pragmatic.hrm.pages;

import com.pragmatic.hrm.HRMTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class EmployeeProfilePage {


    private final WebDriver webDriver;
    @FindBy(xpath = "//*[@id='personal_txtEmpFirstName']")
    private WebElement txtFirstName;

    @FindBy(xpath = "//*[@id='personal_txtEmpLastName']")
    private WebElement txtLastName;


    public EmployeeProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getFirstname() {
        String firstname = txtFirstName.getText();
        return firstname;
    }

    public String getLastname() {
        String lastName = txtLastName.getText();
        return lastName;
    }
}
