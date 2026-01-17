package com.api.utils;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DemoRunner {
    private static Faker faker = new Faker(new Locale("en-IND"));

    public static void main(String[] args) {

/*        System.out.println("Demo Runner Executed");
        // faker code to generate date in this format "2024-01-10"
        // faker.date().future(10, TimeUnit.DAYS);
        String checkin = faker.date().future(1,TimeUnit.DAYS).toInstant().toString().substring(0,10);
        System.out.println(checkin);
        //future date than checkin date
//        String checkout = Instant.now().minus(5, ChronoUnit.DAYS).toString();
//        System.out.println(checkout);

        String date = faker.date().future(20,TimeUnit.DAYS).toInstant().toString().substring(0,10);
        System.out.println(date);*/

       String checkin = Instant.now().minus(0, ChronoUnit.DAYS).toString().substring(0,10);
        //System.out.println(checkin);
/*
        String checkout = Instant.now().minus(0, ChronoUnit.DAYS).toString().substring(0,10);
        System.out.println(checkout);*/

//        boolean depositpaid = faker.bool().bool();
//        System.out.println(depositpaid);

        Faker faker = new Faker(new Locale("en-IND"));
        System.out.println(faker.date().future(10, TimeUnit.DAYS).toInstant().toString().substring(0,10));
    }
}
