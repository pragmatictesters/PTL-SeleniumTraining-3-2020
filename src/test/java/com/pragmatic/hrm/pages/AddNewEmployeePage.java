package com.pragmatic.hrm.pages;

import com.pragmatic.hrm.HRMTestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddNewEmployeePage  extends HRMTestBase {


    @FindBy (id="firstName")
    WebElement txtFirstname;

    @FindBy(id = "lastName")
    WebElement txtLastname;


    @FindBy(css = "span[for='firstName'].validation-error")
    WebElement errFirstname;

    @FindBy(css = "span[for='lastName'].validation-error")
    WebElement errLastname;

    @FindBy(xpath = "//*[@id='photofile']")
    WebElement btnUpload;

    @FindBy(xpath = "//input[@id='chkLogin']")
    WebElement chkCreateLogin;


    @FindBy(xpath = "//input[@id='user_name']")
    WebElement txtUsername;

    @FindBy(xpath = "//input[@id='user_password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@id='re_password']")
    WebElement txtConfirmPassword;


    @FindBy(xpath = "//select[@id='status']")
    WebElement lstStatus;

    @FindBy(id = "btnSave")
    WebElement btnSave;


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

    public void clickSaveWithSuccess() {
        btnSave.click();
        //TODO need to return appropriate page
    }

    public String getFirstNameError() {
        return errFirstname.getText();
    }


    public String getLastNameError() {
        return errLastname.getText();
    }


    public AddNewEmployeePage checkCreateLoginDetails() {
        check(chkCreateLogin);
        return this;
    }

    public AddNewEmployeePage uncheckCreateLoginDetails() {
        uncheck(chkCreateLogin);
        return this;
    }


    public AddNewEmployeePage selectStatus(String status) {
        new Select(lstStatus).selectByVisibleText(status);
        return this;

    }


}
