package com.pragmatic.examples.java;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TwoDimensionalArraysExample {


    public static void main(String[] args) {

        String[] [] login_data = {
                {"","", "Username cannot be blank"},
                {"","Ptl@#321", "Username cannot be blank"},
                {"Admin","", "Password cannot be blank"},
                {"Admin","ptl@#321", "Invalid credentials"}
        };


        for (String[] userinput : login_data){
            StringBuffer sb = new StringBuffer();
            sb.append("user name \"")
                    .append(userinput[0])
                    .append("\" password \"")
                    .append(userinput[1])
                    .append("\" and expected outcome is \"")
                    .append(userinput[2])
                    .append("\"");

            System.out.println(sb.toString());

        }


        Object[][] objects = login_data;
        for (String[] userinput : (String[][]) objects){
            StringBuffer sb = new StringBuffer();
            sb.append("user name \"")
                    .append(userinput[0])
                    .append("\" password \"")
                    .append(userinput[1])
                    .append("\" and expected outcome is \"")
                    .append(userinput[2])
                    .append("\"");

            System.out.println(sb.toString());

        }


    }

}
