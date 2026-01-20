package com.api.tests;

import com.api.request.model.UserCredentials;
import static com.api.utils.EnvUtil.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Listeners(com.listeners.APITestListener.class)
public class LoginAPITest {

    // Integrate Allure Report generation step by step in framework
    // 1. Add Allure dependencies in pom.xml
    // 2. Add Allure annotations in test classes and methods
    // 3. Configure TestNG listeners for Allure in testng.xml
    // 4. Generate Allure report after test execution using Maven commands
    // - using command like : mvn clean test
    // - after running the tests, generate the report using:
    // - allure serve target/site/allure-maven-plugin
    // 5. View Allure report using Allure command line tool

    // Rest Assured test code for login API would go here
    private UserCredentials userCredentials;

    @BeforeMethod(description = "Setup User Credentials Payload for Login API Request")
    public void setup(){
        userCredentials = new UserCredentials(
                getEnvValue("ADMIN_API_USERNAME"),
                getEnvValue("ADMIN_API_PASSWORD")
        );
    }


    @Test(
            description = "Verify Login API is working for admin",
            groups = {"api","regression","smoke"},
            retryAnalyzer = com.listeners.APIRetryAnalyzer.class
    )
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
                //.body("token", equalTo("Bearer"))
                .and()
                .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
    }
}
