package com.api.tests.datadriven;

import com.api.request.model.CreateBooking;

import org.testng.annotations.Test;

import static com.api.constant.Role.ADMIN;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingAPIFakeDataDrivenTest {

    // to execute only faker data driven test case from maven command line filtering with group
    //mvn test -Denv=qa -DsuiteXmlFile=testng-datadriven.xml -Dgroups=faker

    @Test(description = "Verify Create Booking API is working and response schema is valid",
            groups = {"api","regression","datadriven","faker"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "CreateBookingAPIFakerDataProvider")
    public void createJobAPIWithFakeDataTest(CreateBooking createBooking) {

        // Implementation for creating a job via API

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
                .body(matchesJsonSchemaInClasspath(
                        "response-schema/CreateBookingResponseSchema.json"));


    }
}
