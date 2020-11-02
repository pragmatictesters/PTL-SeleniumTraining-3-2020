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
public class LandingPage {


    @FindBy(id="welcome")
    WebElement lnkWelcome;

    @FindBy (xpath = "//a[text()='Logout']")
    WebElement lnkLogout;

    public LoginPage logout(WebDriver webDriver){
        lnkWelcome.click();
        lnkLogout.click();
        return PageFactory.initElements(webDriver, LoginPage.class);
    }


    public String getWelcomeMessage(){
        return  lnkWelcome.getText();
    }



}
