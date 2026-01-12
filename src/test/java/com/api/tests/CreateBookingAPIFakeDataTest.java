package com.api.tests;

import com.api.request.model.BookingDates;
import com.api.request.model.CreateBooking;
import com.api.utils.FakerDataGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.constant.Role.ADMIN;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingAPIFakeDataTest {

    // Rest Assured test code for login API would go here
    private CreateBooking createBooking;

    @BeforeMethod(description = "Setup User Payload for Create Booking API")
    public void setup(){

        createBooking = FakerDataGenerator.generateFakeCreateBookingData();
        System.out.println(createBooking.getFirstname());
    }


    @Test(description = "Verify Create Booking API is working", groups = {"api","regression","smoke"})
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
                .body("booking.firstname", equalTo(createBooking.getFirstname()))
                .body("booking.lastname", equalTo(createBooking.getLastname()))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/CreateBookingResponseSchema.json"));
    }
}
