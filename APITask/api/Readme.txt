# Simple Books API Test Project

This project contains automated API tests for the Simple Books API (https://simple-books-api.glitch.me) using the REST Assured framework in Java.

## Project Setup and Dependencies

This project uses Maven to manage dependencies and build the project. The following key libraries are used:

* **REST Assured:**  Provides a fluent API for making HTTP requests and validating responses.
* **JUnit 5:**  The testing framework used to structure and run the tests.
* **Hamcrest:**  Used for creating matcher objects that allow flexible and readable assertions in tests.


**Required Tools:**

* **Java Development Kit (JDK) 11 or later:** Ensure you have a compatible JDK installed and configured.
* **Maven:**  Required for building the project and managing dependencies. (Apache Maven 3.8.1 or later recommended)
* **IDE (Optional but Recommended):**  An IDE like IntelliJ IDEA or Eclipse will greatly simplify development and running the tests.



## Project Structure
simple-books-api-tests/ ├── pom.xml (Maven project configuration) ├── src/ │ └── test/ │ └── java/ │ └── com/ │ └── example/ │ └── SimpleBooksApiTest.java (Test class) └── README.md (This file)



## Running the Tests

1. **Clone the repository:** `git clone <repository_url>` (if you have the project in a Git repository).

2. **Navigate to the project directory:** `cd simple-books-api-tests`

3. **Build the project:** `mvn clean install` (this will download the necessary dependencies and compile the code).

4. **Run the tests:** `mvn test`


The test results will be displayed in the console.  Maven will generate reports (including detailed failure reports, if any) in the `target/surefire-reports` directory.


## Test Cases

The `SimpleBooksApiTest.java` file contains the following test cases covering various API operations:

* **`testGetBookById()`:** Tests retrieving a book by its ID using a GET request.
* **`testCreateNewBook()`:** Tests creating a new book using a POST request.
* **`testUpdateBook()`:** Tests updating an existing book's author using a PATCH request.  This test first creates a book to avoid dependencies on existing data, making the test more robust.
* **`testDeleteBook()`:** Tests deleting a book using a DELETE request. This test also creates a book before deleting it to ensure data independence and includes a verification step to confirm the book's deletion.

## Code Highlights

* **Clear Test Structure:** Each test case focuses on a single API operation.
* **Logging:**  `log().all()` is used for detailed request and response logging to assist in debugging.
* **Assertions:** Hamcrest matchers provide clear and expressive assertions.
* **Data Independence:** The update and delete tests create their own test data, improving reliability.
* **Verification:** The delete test includes verification to ensure the resource is deleted.



## Troubleshooting

* If you encounter build errors, make sure you have the correct JDK version and that Maven is properly configured.
* Check the console output and the `target/surefire-reports` directory for detailed error messages if tests fail.
* Ensure that the Simple Books API is accessible at the base URL specified in the tests.