package com.cydeo.Weekend.week1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SimpleGETRequest {

    /**
     * Requirements:
     * - Given accept type is Json
     * - And base URI: http://cydeo.onthewifi.com:1000/ords/hr
     * - When user sends GET request to endpoint:/regions
     * - Store the response in Response Object that comes from GET Request
     * - Print out followings:
     * - Headers
     * - Content-Type
     * - Status Code
     * - Response
     * - Date
     * - Then verify followings:
     * - Response body has "Europe"
     * - Response headers has "Date"
     */

    @DisplayName("Get Request")
    @Test
    public void test1() {

        baseURI = "http://cydeo.onthewifi.com:1000/ords/hr";

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .baseUri(baseURI)
                .when()
                .get("/regions");

        System.out.println("-------------------HEADERS-------------------");
        System.out.println("response.headers() = " + response.headers());

        System.out.println("-------------------Content-Type-------------------");
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));

        System.out.println("-------------------StatusCode-------------------");
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        System.out.println("-------------------Response-------------------");
        System.out.println("response.getHeader(\"Response\") = " + response.getBody().asString());
        response.prettyPrint();

        System.out.println("-------------------Date-------------------");
        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));

        //Then verify followings:
        //Response body has "Europe"
        assertTrue(response.getBody().asString().contains("Europe"));

        //Response headers has "Date"
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));

    }
}
