package com.pragmatic.examples.java;

import java.util.Arrays;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ArraysExample {


    public static void main(String[] args) {

        //Primitive data
        int[] numbers1 = {4,9, 7, 1, 19, 500, 54};
        int[] numbers2 = {4,21, 7, 221, 19, 212, 54};

        System.out.println("Compare two arrays " + Arrays.compare(numbers1, numbers2));

        Arrays.sort(numbers1, 2,2);

        for (int i = 0; i < numbers1.length; i++) {
            System.out.println(" Number  = " + numbers1[i]);
        }

        Arrays.parallelSort(numbers2);
        
        for (int number : numbers2) {
            System.out.println("number = " + number);
        }


    }

}
