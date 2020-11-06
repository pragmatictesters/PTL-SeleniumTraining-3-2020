package com.pragmatic.examples.java.inheritence;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class Browser {


    private String hidden_info = "This is not accessible by subclasses ";


    public Browser navigate(String url){
        System.out.println("Navigating to " + url);
        return this;
    }

}
