package com.api.tests.datadriven;

import com.api.request.model.UserCredentials;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginAPIJsonDataDrivenTest {
    // Rest Assured test code for login API would go here

    @Test(description = "Verify Login API is working for multiple users - Data Driven with JSON",
            groups = {"api","regression","datadriven","json"},
            dataProviderClass = com.dataproviders.DataProviderUtils.class,
            dataProvider = "loginAPIJsonDataProvider")
    public void testLoginAPIwithJson(UserCredentials userCredentials) {


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
