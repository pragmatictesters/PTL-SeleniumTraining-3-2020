package com.pragmatic.hrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddNewEmployeePage {


    @FindBy (id="firstName")
    WebElement txtFirstname;

    @FindBy(id="lastName")
    WebElement txtLastname;

    @FindBy(id="btnSave")
    WebElement btnSave;


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

}
