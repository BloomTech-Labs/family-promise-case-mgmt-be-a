package com.bloomtechlabs.fp.unittests;

import com.bloomtechlabs.fp.controllers.HouseholdController;
import com.bloomtechlabs.fp.entities.Household;
import com.bloomtechlabs.fp.services.HouseholdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * HouseholdControllerTest is a test class for the HouseholdController.
 * It serves as a template to test other functions in the HouseholdController.
 *
 * This class uses JUnit 5 (Jupiter) for testing and Mockito for mocking dependencies.
 */
@ExtendWith(MockitoExtension.class)
public class HouseholdControllerUnitTest {

    // @InjectMocks is used to create an instance of the class being tested
    // and injects the mocked dependencies (annotated with @Mock) into it.
    @InjectMocks
    private HouseholdController householdController;

    // @Mock is used to create a mock instance of the dependency.
    // In this case, it creates a mock instance of the HouseholdService.
    @Mock
    private HouseholdService householdService;

    /**
     * getHouseHoldByFirstNameTest tests the functionality of the getHouseHoldByFirstName method
     * in the HouseholdController class. It verifies that the method returns the expected
     * households with the given first name and the response has the correct HTTP status.
     */
    @Test
    public void getHouseHoldByFirstNameTest() {
        // Given: Define the initial test data and set up the mock behavior.
        String firstName = "John";
        Household household1 = Household.builder().withName("John Mc'Ollen").build();
        Household household2 = Household.builder().withName("John Allison").build();
        List<Household> expectedHouseholds = Arrays.asList(household1, household2);

        Mockito.when(householdService.getHouseholdsByFirstName(firstName)).thenReturn(expectedHouseholds);

        // When: Call the method being tested.
        ResponseEntity<List<Household>> response = householdController.getHouseHoldByFirstName(firstName);

        // Then: Assert the expected results and behavior.
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(expectedHouseholds));

        // Verify that the getHouseholdsByFirstName method of the HouseholdService was called once.
        Mockito.verify(householdService, Mockito.times(1)).getHouseholdsByFirstName(firstName);
    }
}
