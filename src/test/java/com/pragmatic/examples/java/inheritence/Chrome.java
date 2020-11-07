package com.pragmatic.examples.java.inheritence;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class Chrome extends Browser {

    @Override
    public Chrome navigate(String url) {
        System.out.printf("Navigating to %s with chrome ", url);
        return this;
    }
}
