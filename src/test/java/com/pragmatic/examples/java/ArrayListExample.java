package com.pragmatic.examples.java;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ArrayListExample {


    public static void main(String[] args) {
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add("Brown");

        for (String color : colors) {
            System.out.println(color);
        }


        for (int i = 0; i < colors.size(); i++) {
            System.out.println("S : " + colors.get(i));
        }

        List<String> sortedColors = colors.stream().sorted().collect(Collectors.toList());

        for (String color : sortedColors) {
            System.out.println(color);
        }

    }

}
