package com.api.utils;


import com.api.constant.Role;
import com.api.request.model.UserCredentials;
import io.restassured.http.ContentType;

import static com.api.constant.Role.*;
import static com.api.utils.EnvUtil.getEnvValue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthTokenProvider {

    private AuthTokenProvider() {
        // private constructor to prevent instantiation
    }

    // Get token based on user role
    // Role based authentication
    public static String getToken(Role role) {
        // Make the request for the auth api and we want to extract
        // the token from the response

        UserCredentials userCredentials = null;
        if(role == ADMIN){
            userCredentials = new UserCredentials(
                    getEnvValue("ADMIN_API_USERNAME"),
                    getEnvValue("ADMIN_API_PASSWORD"));
        } else if(role == USER){
            userCredentials = new UserCredentials(
                    getEnvValue("USER_API_USERNAME"),
                    getEnvValue("USER_API_PASSWORD"));
        } else if(role == GUEST){
            userCredentials = new UserCredentials(
                    getEnvValue("GUEST_API_USERNAME"),
                    getEnvValue("GUEST_API_PASSWORD"));
        }
        return given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .contentType(ContentType.JSON)
                .body(userCredentials)
                .when()
                .post("auth")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .body().jsonPath().getString("token");

    }
}
