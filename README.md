# family-promise-case-mgmt-be-a

### Basic Java API Scaffold

Welcome to your `Family Promise`. Use this to start your own Greenfield Project using java, spring, and common industry standards.

This repository assumes a handful of industry practices and standards. We strive to keep you on the bleeding edge of the industry and as a result, we have made some opinions for you so that you don't have to; you're welcome.

## Requirements

Labs teams must follow all [Labs Engineering Standards](https://bloomtechlabs.gitbook.io).


## Getting Started

### Environment Variables

- `awsaccesskey` - key to access aws instance local/deployed
- `awssecretkey` - authorization token for aws instance (eg. SUPERSECRET)
- `dynamodburl` - connection string for dynamo

See application-dev.properties for example values

# Pagination
To add pagination to your service and controller, follow these steps:

## Service Class
1. Add a method named getAllExamplesPaginated that takes in two arguments: offset and limit.
2. Implement the logic for fetching a paginated list of Example objects from your data source (e.g. database).

Example:
```
  /**
  * @param offset page index to return results from.
  * @param limit number of results to include per page.
  * @return returns a paginated list of Example objects.
  */
  public Page<Example> getAllExamplesPaginated(int offset, int limit) {
  return exampleRepository.findAll(PageRequest.of(offset, limit));
  }
```
## Controller Class
1. Add a method named getAllExamplesPaginated that takes in two @PathVariable arguments: offset and limit.
2. Call the getAllExamplesPaginated method from your service class, passing in the offset and limit arguments.
3. Return the paginated results as a JSON response to the client.

Example:
```
  /**
  * @param offset page index to return results from.
  * @param limit number of results to include per page.
  * @return returns a paginated list of Example objects.
  */
  @GetMapping("{offset}/{limit}")
  public ResponseEntity<Page<Example>> getAllExamplePaginated(@PathVariable int offset, @PathVariable int limit) {
  return ResponseEntity.ok(exampleService.getAllExamplesPaginated(offset, limit));
  }
```