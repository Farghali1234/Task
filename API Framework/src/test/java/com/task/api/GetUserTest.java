package com.task.api;

import com.task.utils.RestAssuredUtils;
import com.task.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetUserTest {

    private String baseUrl;
    private static final Logger logger = LogManager.getLogger(GetUserTest.class);

    @BeforeClass
    public void setUp() {
        baseUrl = ConfigReader.getBaseUrl();
        RestAssuredUtils.setup(baseUrl);
    }

    @Test
    public void getUser() {
        try {

        Response response = RestAssuredUtils.sendGetRequest("/api/users/2");
    // Validate status code
    if (response.getStatusCode() != 200) {
        logger.error("Error occurred: Expected status 200 but got " + response.getStatusCode());
        Assert.fail("Error: Unable to retrieve user with status code " + response.getStatusCode());
    }

    // Validate response body
    String responseBody = response.getBody().asString();
    if (responseBody.contains("error")) {
        logger.error("Error in response body: " + responseBody);
        Assert.fail("Error: Response body contains error");
    }

    // Log success
    logger.info("User retrieved successfully: " + responseBody);

    } 
    catch (Exception e) {
    logger.error("Exception occurred during user retrieval", e);
    Assert.fail("Test failed due to exception: " + e.getMessage());
    }
    }
}