package com.pragmatic.hrm.pages;

import com.pragmatic.examples.selenium.supportclasses.ui.Checkbox;
import com.pragmatic.hrm.HRMTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddNewEmployeePage extends HRMTestBase {

    private final WebDriver webDriver;


    @FindBy(id = "firstName")
    private WebElement txtFirstname;

    @FindBy(id = "lastName")
    private WebElement txtLastname;

    @FindBy(id = "employeeId")
    private WebElement txtEmpID;


    @FindBy(css = "span[for='firstName'].validation-error")
    private WebElement errFirstname;

    @FindBy(css = "span[for='lastName'].validation-error")
    private WebElement errLastname;

    @FindBy(xpath = "//*[@id='photofile']")
    private WebElement btnUpload;

    @FindBy(xpath = "//input[@id='chkLogin']")
    private WebElement chkCreateLogin;


    @FindBy(xpath = "//input[@id='user_name']")
    private WebElement txtUsername;


    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//input[@id='re_password']")
    private WebElement txtConfirmPassword;


    @FindBy(xpath = "//select[@id='status']")
    private WebElement lstStatus;

    @FindBy(id = "btnSave")
    private WebElement btnSave;


    public AddNewEmployeePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public AddNewEmployeePage typeFirstname(String firstName) {
        txtFirstname.sendKeys(firstName);
        return this;
    }


    public AddNewEmployeePage typeLastname(String lastName) {
        txtLastname.sendKeys(lastName);
        return this;
    }


    public AddNewEmployeePage uplaodProfile(String fileName) {
        btnUpload.sendKeys(fileName);
        return this;
    }

    public AddNewEmployeePage clickSaveWithError() {
        btnSave.click();
        return this;
    }

    public void clickSave() {
        btnSave.click();
    }

    public String getFirstNameError() {
        return errFirstname.getText();
    }


    public String getLastNameError() {
        return errLastname.getText();
    }


    public AddNewEmployeePage checkCreateLoginDetails() {
        Checkbox chkLogin = new Checkbox(chkCreateLogin);
        chkLogin.check();
        return this;
    }

    public AddNewEmployeePage uncheckCreateLoginDetails() {
        Checkbox chkLogin = new Checkbox(chkCreateLogin);
        chkLogin.uncheck();
        return this;
    }


    public AddNewEmployeePage selectStatus(String status) {
        new Select(lstStatus).selectByVisibleText(status);
        return this;
    }


    public AddNewEmployeePage typeEmployeeID(String idNumber) {
        txtEmpID.sendKeys(idNumber);
        return this;
    }
}
