package com.pragmatic.examples.java;

import com.github.javafaker.Faker;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class JavaFakerExample {

    public static void main(String[] args) {


        Faker faker = new Faker();

        String name = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String streetAddress = faker.address().streetAddress();

        System.out.println("name = " + name);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("streetAddress = " + streetAddress);
    }

}
