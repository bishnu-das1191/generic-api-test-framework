package com.api.tests;

import com.api.request.model.UserCredentials;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginAPITest_OLD {
    // Rest Assured test code for login API would go here

    @Test
    public void testLoginAPI() {

        UserCredentials userCredentials = new UserCredentials("admin", "password123");

        //setup
        given()
                .baseUri(getProperty("BASE_URI")) // ConfigManagerOLD.getProperty("BASE_URI")
                .and()
                .contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and()
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()
                // action
                .when()
                .post("auth")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                //validation
                .and()
                //.body("message", equalTo("Success"))
                .body("token", notNullValue())
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));




    }

}
