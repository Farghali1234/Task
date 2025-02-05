package com.task;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SimpleBooksApiTest {

    @Test
    public void testGetBookById() {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";

        given()
                .log().all() // Log the request for debugging
        .when()
                .get("/books/1")
        .then()
                .log().all() // Log the response for debugging
                .statusCode(200)
                .body("id", equalTo(1));

                //assertTrue( equal true );
    }


    @Test
    public void testCreateNewBook() {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";

        String requestBody = "{\"name\": \"New Test Book\", \"author\": \"Test Author\"}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/books")
        .then()
                .log().all()
                .statusCode(201)  // Expect 201 Created
                .body("name", equalTo("New Test Book")); //assertTrue( equal true );

    }


    @Test
    public void testUpdateBook() {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";

        // First create a book to update (to avoid data dependency on existing books)
        String createBody = "{\"name\": \"Book To Update\", \"author\": \"Original Author\"}";
        Response createResponse = given().contentType(ContentType.JSON).body(createBody).post("/books");
        int bookId = createResponse.jsonPath().getInt("id");


        String updateBody = "{\"author\": \"Updated Author\"}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(updateBody)
        .when()
                .patch("/books/" + bookId)
        .then()
                .log().all()
                .statusCode(200)
                .body("author", equalTo("Updated Author"));
    }

    @Test
    public void testDeleteBook() {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";


        // Create a book to delete (avoids dependencies on pre-existing data and makes test more robust)
        String createBody = "{\"name\": \"Book To Delete\", \"author\": \"Delete Author\"}";
        Response createResponse = given().contentType(ContentType.JSON).body(createBody).post("/books");
        int bookId = createResponse.jsonPath().getInt("id");



        given()
                .log().all()
        .when()
                .delete("/books/" + bookId)
        .then()
                .log().all()
                .statusCode(204);  // 204 No Content is expected for successful delete



        // Verify the book is actually deleted
        given().log().all().get("/books/" + bookId).then().log().all().statusCode(404);

    }


}