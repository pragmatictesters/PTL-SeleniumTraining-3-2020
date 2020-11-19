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
public class LoginPage {

    private final WebDriver webDriver;

    //Elements
    @FindBy(xpath = "//input[@id='txtUsername']")
    private WebElement txtUsername;

    @FindBy(id = "txtPassword")
    private WebElement txtPassword;

    @FindBy(id = "btnLogin")
    private WebElement btnLogin;

    @FindBy(id = "spanMessage")
    private WebElement msgError;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }


    public LoginPage typeUsername(String username) {
        txtUsername.sendKeys(username);
        return this;
    }


    public LoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }


    public LoginPage clickLogin() {
        btnLogin.click();
        return this;
    }

    public void login(String username, String password) {
        typeUsername(username)
                .typePassword(password)
                .clickLogin();
    }


    public String getErrorMessage() {
        return msgError.getText();
    }


}
