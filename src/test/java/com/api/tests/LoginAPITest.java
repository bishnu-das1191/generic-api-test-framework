package com.api.tests;

import com.api.request.model.UserCredentials;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginAPITest {

    // Rest Assured test code for login API would go here
    private UserCredentials userCredentials;

    @BeforeMethod(description = "Setup User Credentials Payload for Login API Request")
    public void setup(){
        userCredentials = new UserCredentials("admin", "password123");
    }


    @Test(description = "Verify Login API is working for admin", groups = {"api","regression","smoke"})
    public void testLoginAPI() {


        //setup
        given()
                .spec(requestSpec(userCredentials))
                // action
                .when()
                .post("auth")
                .then()
                .spec(responseSpec_OK())
                //validation
                .and()
                .body("token", notNullValue())
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
    }
}
