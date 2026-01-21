package com.api.utils;

import com.api.constant.Role;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static com.api.utils.ConfigManager.getProperty;

public class SpecUtil {

    // GET Request Specification or // Delete Request Specification
    @Step("Creating Request Specification without Payload")
    public static RequestSpecification requestSpec(){

        RequestSpecification request = new RequestSpecBuilder()
                .setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .addFilter(new AllureRestAssured())
                .build();
        return request;

    }


    //POST - PUT - PATH {BODY} Request Specification
    @Step("Creating Request Specification with Payload")
    public static RequestSpecification requestSpec(Object payload){

        RequestSpecification request = new RequestSpecBuilder()
                .setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBody(payload)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .addFilter(new AllureRestAssured())
                .build();
        return request;

    }


    // common Assertions for API Response Specification
    @Step("Creating Common Response Specification for Status Code 200")
    public static ResponseSpecification responseSpec_OK(){
        ResponseSpecification responseSpecification =  new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(Matchers.lessThan(5000L)) // ideally should be less than 2000 ms
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
        return responseSpecification;

    }

    // Response Specification with JSON response type
    @Step("Creating Response Specification with JSON Response Type for Status Code: {statusCode}")
    public static ResponseSpecification responseSpec_JSON(int statusCode){
        ResponseSpecification responseSpecification =  new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(statusCode)
                .expectResponseTime(Matchers.lessThan(2000L))
                //.log(LogDetail.ALL)
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
        return responseSpecification;

    }


    // Response Specification for TEXT response type
    @Step("Creating Response Specification with TEXT Response Type for Status Code: {statusCode}")
    public static ResponseSpecification responseSpec_TEXT(int statusCode){
        ResponseSpecification responseSpecification =  new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectResponseTime(Matchers.lessThan(4000L))
                //.log(LogDetail.ALL)
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
        return responseSpecification;

    }

    // RequestSpecification with Authorization Header
    /*
    public static RequestSpecification requestSpecWithAuth(Role role){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", AuthTokenProvider.getToken(role))
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
        return requestSpecification;
    }


     */


    // RequestSpecification with Authorization Header and Body
    @Step("Creating Request Specification with Auth for Role: {role} and Payload")
    public static RequestSpecification requestSpecWithAuth(Role role, Object payload){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(getProperty("BASE_URI"))
                .setContentType(ContentType.JSON)
                //.setAccept(ContentType.JSON)
                .setAccept(ContentType.ANY)
                .addHeader("Authorization", AuthTokenProvider.getToken(role))
                .setBody(payload)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .addFilter(new AllureRestAssured())
                .build();
        return requestSpecification;
    }
}
