package com.pragmatic.examples.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SimpleForm {


    @FindBy(xpath = "//*[@id=\"user-message\"]")
    private WebElement inpMessage;

    @FindBy(xpath = "//*[@id=\"get-input\"]/button")
    private WebElement btnShowMessage;

    @FindBy(xpath = "//*[@id=\"display\"]")
    private WebElement spnMessage;


    @FindBy(xpath = "//*[@id=\"sum1\"]")
    private WebElement txtNumberOne;

    @FindBy(xpath = "//*[@id=\"sum2\"]")
    private WebElement txtNumberTwo;


    @FindBy(xpath = "//*[@id=\"displayvalue\"]")
    private WebElement spnAnswer;

    @FindBy(xpath = "//*[@id=\"gettotal\"]/button")
    private WebElement btnGetTotal;


    public SimpleForm typeMessage(String message) {
        inpMessage.sendKeys(message);
        return this;
    }


    public SimpleForm clickShowMessageButton() {
        btnShowMessage.click();
        return this;
    }


    public String getMessage() {
        return spnMessage.getText();
    }


    public SimpleForm typeNumberOne(String numberOne){
        txtNumberOne.sendKeys(numberOne);
        return this;
    }

    public SimpleForm typeNumberTwo(String numberTwo){
        txtNumberTwo.sendKeys(numberTwo);
        return this;
    }

    public SimpleForm clickGetTotalButton(){
        btnGetTotal.click();
        return this;
    }

    public String getTotalValue(){
        return spnAnswer.getText();
    }





}
