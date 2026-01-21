package com.api.utils;


import com.api.constant.Role;
import com.api.request.model.UserCredentials;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.api.constant.Role.*;
import static com.api.utils.EnvUtil.getEnvValue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthTokenProvider {

    private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);

    // ConcurrentHashMap to store tokens for different roles
    // we used ConcurrentHashMap to make it thread safe for parallel execution
    // this will improve performance by reducing redundant token generation requests
    private static Map<Role, String> tokenCache = new ConcurrentHashMap<>();

    private AuthTokenProvider() {
        // private constructor to prevent instantiation
    }

    // Get token based on user role
    // Role based authentication
    @Step("Getting Auth Token for Role: {role}")
    public static String getToken(Role role) {
        // Make the request for the auth api and we want to extract
        // the token from the response

        LOGGER.info("Checking if the token for role {} is present in the cache.", role);
        if(tokenCache.containsKey(role)) {
            LOGGER.info("Token found in cache for role {}. Returning cached token.", role);
            return tokenCache.get(role);
        }

        LOGGER.info("Token not found in cache. Generating... new token for role {}.", role);
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

        String token = generateToken(userCredentials);
        LOGGER.info("Generated new token for role {}. Caching the token.", role);
        tokenCache.put(role, token);
        return token;
    }

    private static String generateToken(UserCredentials userCredentials) {
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
