package com.pragmatic.hrm.pages;

import com.pragmatic.hrm.HRMTestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddNewEmployeePage  extends HRMTestBase {


    @FindBy (id="firstName")
    WebElement txtFirstname;

    @FindBy(id="lastName")
    WebElement txtLastname;

    @FindBy(id="btnSave")
    WebElement btnSave;


    @FindBy(css = "span[for='firstName'].validation-error")
    WebElement errFirstname;

    @FindBy(css = "span[for='lastName'].validation-error")
    WebElement errLastname;


    public AddNewEmployeePage  typeFirstname(String firstName){
        txtFirstname.sendKeys(firstName);
        return this;
    }


    public AddNewEmployeePage typeLastname(String lastName){
        txtLastname.sendKeys(lastName);
        return this;
    }

    public AddNewEmployeePage clickSaveWithError(){
        btnSave.click();
        return this;
    }

    public void clickSaveWithSuccess() {
        btnSave.click();
        //TODO need to return appropriate page
    }

    public String getFirstNameError(){
        return errFirstname.getText();
    }


    public String getLastNameError(){
        return errLastname.getText();
    }


}
