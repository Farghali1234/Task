package com.task.utils;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class RestAssuredUtils {

    public static void setup(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        RestAssured.config = RestAssuredConfig.config().sslConfig(new SSLConfig().allowAllHostnames());
    }

    public static Response sendPostRequest(String endpoint, String body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response sendGetRequest(String endpoint) {
        return given()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response sendPutRequest(String endpoint, String body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}

