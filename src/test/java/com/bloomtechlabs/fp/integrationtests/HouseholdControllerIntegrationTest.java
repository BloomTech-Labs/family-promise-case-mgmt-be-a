package com.bloomtechlabs.fp.integrationtests;

import com.bloomtechlabs.fp.entities.Household;
import com.bloomtechlabs.fp.repositories.HouseholdRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.DeserializationFeature;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * This class contains integration tests for the HouseholdController.
 * The tests validate the functionality of the controller's endpoints using the TestRestTemplate.
 * The test setup includes creating a real Spring Boot application context and a real database.
 * The tests run with the "SpringExtension" test runner.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HouseholdControllerIntegrationTest {

    // TestRestTemplate is a convenient utility for making HTTP requests to the running application
    @Autowired
    private TestRestTemplate restTemplate;

    // HouseholdRepository is a repository for accessing the Household entity in the database
    @Autowired
    private HouseholdRepository householdRepository;

    private Household household1;
    private Household household2;

    /**
     * This test checks if the getHouseHoldByFirstName endpoint returns the expected households.
     * The test data is stored in the database, and the endpoint is called using TestRestTemplate.
     * The returned JSON response is then deserialized to a list of households, and assertions are
     * used to validate the response.
     */
    @Test
    public void getHouseHoldByFirstNameTest() throws JsonProcessingException {
        // Given
        String firstName = "John";

        // Populate the repository with test data
        household1 = Household.builder().withName("John Mc'Ollen").build();
        household2 = Household.builder().withName("John Allison").build();
        List<Household> expectedHouseholds = Arrays.asList(household1, household2);
        householdRepository.saveAll(expectedHouseholds);

        // When
        ResponseEntity<String> response = restTemplate.getForEntity("/households/firstname/{firstName}", String.class, firstName);

        // Then
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the JavaTimeModule
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignore unknown properties since we're only concerned with name

        List<Household> households = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Household.class));
        assertThat(households, hasSize(2));
        for (Household household : households) {
            assertThat(household.getName(), startsWithIgnoringCase("John"));
        }
    }

    /**
     * This method is executed after each test to clean up the test data from the database.
     * It deletes the households that were created during the test.
     */
    @AfterEach
    public void cleanup() {
        householdRepository.deleteById(household1.getId());
        householdRepository.deleteById(household2.getId());
    }
}
