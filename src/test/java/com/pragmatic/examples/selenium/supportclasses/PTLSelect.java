package com.pragmatic.examples.selenium.supportclasses;

import io.cucumber.java.eo.Se;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class PTLSelect extends Select {

    private Select select;


    public PTLSelect(WebElement element) {
        super(element);
        select = new Select(element);

    }


    public String getLastElementText(){
        String textLastElement = "";
        WebElement lastElement = null;
        int elementsCount = select.getOptions().size();
        List<WebElement> selectedElements = select.getAllSelectedOptions();

        for (WebElement webElement : selectedElements){
            lastElement = webElement;
        }
        textLastElement = lastElement.getText();

        return textLastElement;

    }




}
