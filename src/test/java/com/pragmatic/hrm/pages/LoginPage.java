package com.pragmatic.hrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoginPage {


    //Elements
    @FindBy( xpath = "//input[@id='txtUsername']")
    WebElement txtUsername;

    @FindBy(id="txtPassword")
    WebElement txtPassword;

    @FindBy(id = "btnLogin")
    WebElement btnLogin;

    @FindBy(id= "spanMessage")
    WebElement msgError;



    //Actions
    public LoginPage typeUsername(String username){
        txtUsername.sendKeys(username);
        return this;
    }


    public LoginPage cleanAndTypeUsername(String username ){
        txtUsername.clear();
        typeUsername(username);
        return this;
    }

        public LoginPage typePassword(String password){
        txtPassword.sendKeys(password);
        return this;
    }


    public LoginPage cleanAndTypePassword(String password ){
        txtPassword.clear();
        typeUsername(password);
        return this;
    }


    public LoginPage clickLoginButton(){
        btnLogin.click();
        return this;

    }

    public String getErrorMessage(){
        return msgError.getText();
    }




}
