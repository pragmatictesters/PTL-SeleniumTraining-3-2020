package com.pragmatic.examples.java;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SwitchExample {


    public static void main(String[] args) {


        String BROWSER_TYPE = "chrome";

        //Example 1
        switch (BROWSER_TYPE.toLowerCase()) {

            case "chrome" :
                System.out.println("launch chrome browser");
                break;
            case "chrome-headless" :
                System.out.println("launch chrome-headless browser");
                break;
            case "firefox" :
                System.out.println("launch firefox browser");
                break;
            case "firefox-headless" :
                System.out.println("launch firefox-headless browser");
                break;
            default:
                System.out.println("browser type is not set");





        }


        //Example 2
        switch (BROWSER_TYPE.toLowerCase()) {
            case "chrome", "google-chrome" -> {
                System.out.println("Google chrome is launched BROWSER_TYPE = " + BROWSER_TYPE);
            }
            case "firefox", "mozilla" -> {
                System.out.println("Firefox is launched ");
            }default -> {
                System.out.println("Browser type is not set ");
            }


        }


    }

}
