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
public class LandingPage   {

    private final WebDriver webDriver;


    @FindBy(id="welcome")
    private WebElement lnkWelcome;

    @FindBy (xpath = "//a[text()='Logout']")
    private WebElement lnkLogout;


    public LandingPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void logout(){
        lnkWelcome.click();
        lnkLogout.click();
    }


    public String getWelcomeMessage(){
        return  lnkWelcome.getText();
    }



}
