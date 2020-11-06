package com.pragmatic.examples.java.inheritence;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LaunchBrowser {


    public static void main(String[] args) {
        Browser browser = new Chrome();
        browser.navigate("http://hrm.pragmatictesters.com");

    }

}
