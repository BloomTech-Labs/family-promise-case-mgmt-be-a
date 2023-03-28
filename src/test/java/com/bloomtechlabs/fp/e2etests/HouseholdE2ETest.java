package com.bloomtechlabs.fp.e2etests;

import com.bloomtechlabs.fp.entities.Household;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * This class contains end-to-end tests for the Household resource using REST Assured.
 */
public class HouseholdE2ETest {

    /**
     * Configure a custom ObjectMapper for REST Assured to handle Java 8 date/time types
     * and ignore unknown properties when deserializing JSON responses.
     *
     * The ObjectMapper is configured to:
     * 1. Register the JavaTimeModule to handle Java 8 date/time types.
     * 2. Configure the DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES to 'false' to ignore
     *    unknown properties during deserialization.
     *
     * The configured ObjectMapper is then set as the default ObjectMapper for REST Assured.
     */

    static {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(
                        new ObjectMapperConfig()
                                .jackson2ObjectMapperFactory(
                                        (cls, charset) -> objectMapper
                                )
                );
    }

/**
 * This test case tests the retrieval of Households by first name.
 *
 * Test steps:
 * 1. Set the base URI for REST Assured.
 * 2. Define the 'firstName' parameter for the test.
 * 3. Send a GET request to "/households/firstname/{firstName}" with the 'firstName' parameter.
 * 4. Check if the response status code is 200 (OK).
 * 5. Extract the JSON response and deserialize it into a List<Household> object.
 * 6. Assert that the deserialized List<Household> object contains the expected data.
 */
    @Test
    public void getHouseHoldByFirstNameTest() {
        // Set the base URI for REST Assured
        RestAssured.baseURI = "http://localhost:8080";

        // Given
        // Use pre-seeded data ("
        String firstName = "Reynolds";

        // When
        List<Household> households = Arrays.asList(
                given()
                        .contentType(ContentType.JSON)
                        .pathParam("firstName", firstName)
                        .when()
                        .get("/households/firstname/{firstName}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Household[].class));

        //Then
        // Assert that the deserialized List<Household> object contains the expected data
        for (Household household : households) {
            assertThat(household.getName(), startsWithIgnoringCase("Reynolds"));
        }
    }
}
