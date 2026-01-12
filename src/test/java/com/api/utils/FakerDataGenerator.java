package com.api.utils;

import com.api.request.model.BookingDates;
import com.api.request.model.CreateBooking;
import com.github.javafaker.Faker;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class FakerDataGenerator {

    // Because all these utils are following Utility Design Pattern,

    private static Faker faker = new Faker(new Locale("en-IND"));
    private final static Random RANDOM = new Random();
    private final static String COUNTRY = "India";



    private FakerDataGenerator() {
        // private constructor to prevent instantiation
    }


    public static CreateBooking generateFakeCreateBookingData() {

            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            int totalprice = RANDOM.nextInt(5000, 50000);
            boolean depositpaid = faker.bool().bool();
            BookingDates bookingDates = generateFakeCustomerBookingData();
            String additionalneeds = faker.food().dish();

        return new CreateBooking(fname, lname, totalprice, depositpaid,
                    bookingDates, additionalneeds);
    }


    public static Iterator<CreateBooking> generateFakeCreateBookingData(int count) {

        List<CreateBooking> createBookingPayloadList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            int totalprice = RANDOM.nextInt(5000, 50000);
            boolean depositpaid = faker.bool().bool();
            BookingDates bookingDates = generateFakeCustomerBookingData();
            String additionalneeds = faker.food().dish();

            CreateBooking createBookingPayload = new CreateBooking(
                    fname, lname, totalprice, depositpaid,
                    bookingDates, additionalneeds
            );
            createBookingPayloadList.add(createBookingPayload);
        }
        return createBookingPayloadList.iterator();
    }




    private static BookingDates generateFakeCustomerBookingData() {
        //Booking dates info can be added similarly
        String checkin = DateTimeUtil.getTimeWithDaysAgo(20);
        String checkout = DateTimeUtil.getTimeWithDaysAgo(0);
        return new BookingDates(checkin, checkout);
    }
}
