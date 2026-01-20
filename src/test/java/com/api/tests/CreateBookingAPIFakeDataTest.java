package com.api.tests;

import com.api.request.model.BookingDates;
import com.api.request.model.CreateBooking;
import com.api.utils.FakerDataGenerator;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constant.Role.ADMIN;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Listeners(com.listeners.APITestListener.class)
@Epic("User Management Epic")
@Feature("Create Booking API Feature")
public class CreateBookingAPIFakeDataTest {

    // Rest Assured test code for login API would go here
    private CreateBooking createBooking;

    @BeforeMethod(description = "Setup User Payload for Create Booking API")
    public void setup(){

        createBooking = FakerDataGenerator.generateFakeCreateBookingData();
        System.out.println(createBooking.getFirstname());
    }


    @Story("Create Booking Story with Faker Data")
    @Description("Test to verify that Create Booking API is working with Faker Data and response schema is valid.")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Verify Create Booking API is working with Faker Data",
            groups = {"api","regression","smoke"},
            retryAnalyzer = com.listeners.APIRetryAnalyzer.class)
    public void createBookingAPIWithFakeDataTest() {

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
