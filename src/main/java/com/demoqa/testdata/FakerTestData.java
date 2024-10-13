package com.demoqa.testdata;

import com.github.javafaker.Faker;

public class FakerTestData {
    private static Faker faker = new Faker();

    public static String getFirstName() {
        return faker.name().firstName();
    }
    public static String getLastName() {
        return faker.name().lastName();
    }
    public static String getEmail() {
        return faker.internet().emailAddress();
    }
    public static String getAddress () {
        return faker.address().fullAddress();
    }
    public static String getPhoneNumber(){
        return String.valueOf(faker.number().numberBetween(1000000000, 1111111111));
    }
}
