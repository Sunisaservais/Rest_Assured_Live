package com.cydeo.liveLabs;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework1 {

    //Document: https://documenter.getpostman.com/view/13495747/2sAYdkFoc6

    /**
     * Task 1 :
     * - Given accept type is Json
     * - And base URI: http://cydeo.onthewifi.com:1000/ords/hr
     * - When users sends request to endpoints:/countries/US
     * - Then status code is 200
     * - And Content - Type is application/json
     * - And response contains United States of America
     */

    @DisplayName("Request United States of America")
    @Test
    public void test1() {
        baseURI = "http://cydeo.onthewifi.com:1000/ords/hr";
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .when()
                .get(baseURI + "/countries/US");

        //response.prettyPrint();

        //Verify status code is 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        //Verify Content - Type is application/json
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        //Verify response contains United States of America
        //assertEquals("United States of America", response.path("country_name"));
        assertTrue(response.getBody().asString().contains("United States of America"));
    }

    /**
     * Task 2 : NEGATIVE TESTS
     * - Given accept type is Json
     * - And base URI: http://cydeo.onthewifi.com:1000/ords/hr
     * - When users sends request to endpoints: /employees/1
     * - Then status code is 404
     */

    @DisplayName("Negative Test")
    @Test
    public void test2() {
        baseURI = "http://cydeo.onthewifi.com:1000/ords/hr";
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .when()
                .get(baseURI + "/employees/1");

        //response.prettyPrint();

        //Verify status code is 404
        assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
    }

    /**
     * Task 3 :
     * - Given Accept type  is Json
     * - And base URI: http://cydeo.onthewifi.com:1000/ords/hr
     * - When users sends request to endpoints:/regions/10
     * - Then status code is 200
     * - And Content - Type is application/json
     * - And response contains Europe
     * - And header should contains Date
     * - And "Transfer-Encoding" should be "chunked"
     */

    @DisplayName("Request Europe")
    @Test
    public void test3() {
        baseURI = "http://cydeo.onthewifi.com:1000/ords/hr";
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .when()
                .get(baseURI+"/regions/10");

        //response.prettyPrint();

        //Verify status code is 200
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        //Verify Content - Type is application/json
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        //Verify response contains Europe
        assertTrue(response.asString().contains("Europe"));

        //Verify header should contains Date
        assertTrue(response.getHeaders().hasHeaderWithName("Date"));
        assertTrue(response.getHeaders().hasHeaderWithName("date"));

        //Verify "Transfer-Encoding" should be "chunked"
        assertEquals("chunked", response.getHeader("Transfer-Encoding"));
    }
}
