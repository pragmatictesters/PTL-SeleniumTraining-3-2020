package com.pragmatic.examples.java;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class GetSystemProperties {


    public static void main(String[] args) {
        
        String project_dir  =  System.getProperty("user.dir");
        System.out.println("project_dir = " + project_dir);
        
        
        
    }
    

}
