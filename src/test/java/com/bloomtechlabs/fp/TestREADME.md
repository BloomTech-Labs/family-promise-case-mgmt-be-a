This test package comprises 3 different testing strategies, each within its own sub-package.

## Unit Tests:
This strategy uses Mockito to mock the dependencies of the various controller classes (e.g., HouseholdController class).
It tests the functionality of different methods (e.g. getHouseHoldByFirstName method) in isolation, without interacting with the service or repository layers of the application.
This test method sets up mock behavior for the service dependencies (e.g. HouseholdService) and verifies that the controller interacts with the service as expected. 
Unit tests focus on testing individual components of the system, ensuring that they work correctly in isolation and produce the expected output for a given input.
These tests are faster and more focused compared to integration and end-to-end tests, making it easier to pinpoint issues within a specific component.

## Integration Tests:
This test method is an integration test that uses Spring's TestRestTemplate to perform the API call. 
It tests the integration between the controller, the service, and the repository layers of the application. 
This test method uses the Repository dependencies (e.g., HouseholdRepository) to set up the test data and then validates the response using the TestRestTemplate.
Spring Test tests the application in a more controlled environment by allowing us to mock and interact with the application context, providing a higher degree of control over the test execution.

## E2E Tests:
This test method is an end-to-end test that uses REST Assured to perform the API call. 
It tests the entire application from the client's perspective, simulating the actual user experience. 
This test method does not interact with the application context or any internal components directly. 
Instead, it sends HTTP requests to the application and validates the responses. 
End-to-end tests focus on ensuring that the whole system works as expected from a user's perspective.