package com.pragmatic.hrm.support.ui;

import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class Checkbox  implements ICheckbox, WrapsElement {


    private final WebElement element;

    public Checkbox(WebElement element){
        String tagName = element.getTagName();
        String type = element.getAttribute("type");


        if (tagName !=null && type != null && "input".equalsIgnoreCase(tagName) && "checkbox".equalsIgnoreCase(type)){
            if (!element.isEnabled()){
                throw new ElementNotSelectableException("%s element is not Selectable ".formatted( element.getTagName()));
            } else if (!element.isDisplayed()){
                throw new ElementNotVisibleException("%s element is not visible ".formatted(element.getTagName()));
            }

            this.element = element;
        } else  {
            throw  new UnexpectedTagNameException("input type=checkbox", "tag name %s type %s".formatted(tagName, type));
        }
    }

    @Override
    public WebElement check() {
        if (!element.isSelected()){
            element.click();
        }
        return element;
    }

    @Override
    public WebElement uncheck() {
        if (element.isSelected()){
            element.click();
        }
        return element;
    }

    @Override
    public WebElement toggle() {
        element.click();
        return element;
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }
}
