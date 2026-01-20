package com.api.tests;

import com.api.request.model.BookingDates;
import com.api.request.model.CreateBooking;
import com.api.request.model.UserCredentials;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constant.Role.ADMIN;
import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Listeners(com.listeners.APITestListener.class)
public class CreateBookingAPITest {

    // Rest Assured test code for login API would go here
    private CreateBooking createBooking;

    @BeforeMethod(description = "Setup User Payload for Create Booking API")
    public void setup(){

        BookingDates bookingDates = new BookingDates("2024-09-01", "2024-09-10");
        createBooking = new CreateBooking(
                "Jim",
                "Brown",
                111,
                true,
                bookingDates,
                "Breakfast"
        );
    }


    @Test(description = "Verify Create Booking API is working",
            groups = {"api","regression","smoke"},
            retryAnalyzer = com.listeners.APIRetryAnalyzer.class)
    public void createBookingAPITest() {

        //setup
        given()
                .spec(requestSpecWithAuth(ADMIN, createBooking))
                // action
                .when()
                .post("booking")
                .then()
                .spec(responseSpec_TEXT(200))
                //validation
                .and()
                //.body("firstname", equalTo("Jim"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/CreateBookingResponseSchema.json"));
    }
}
