package com.pragmatic.examples.selenium.supportclasses;

import org.openqa.selenium.support.ui.Quotes;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class QuoteEscapingTest {


    public static void main(String[] args) {
        String quotedText = Quotes.escape("\"quot'end\"");
        System.out.println("quotedText = " + quotedText);

    }

}
