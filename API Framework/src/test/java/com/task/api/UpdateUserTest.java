package com.task.api;

import com.task.utils.RestAssuredUtils;
import com.task.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UpdateUserTest {

    private static final Logger logger = LogManager.getLogger(UpdateUserTest.class);
    private String baseUrl;

    @BeforeClass
    public void setUp() {
        baseUrl = ConfigReader.getBaseUrl();
        RestAssuredUtils.setup(baseUrl);
    }

    @Test
    public void updateUser() {
    
        String body = "{ \"name\": \"John\", \"job\": \"Senior Developer\", \"age\": 32 }";
        Response response = RestAssuredUtils.sendPutRequest("/api/users/2", body);
        try {
                   // Validate status code
                   if (response.getStatusCode() != 200) {
                    logger.error("Error occurred: Expected status 200 but got " + response.getStatusCode());
                    Assert.fail("Error: User update failed with status code " + response.getStatusCode());
                }
    
                // Log success
                logger.info("User updated successfully: " + response.getBody().asString());
                Assert.assertEquals(response.getStatusCode(), 200);
                Assert.assertTrue(response.jsonPath().getString("job").contains("Senior Developer"));
        }
        
        catch (Exception e) {
            logger.error("Exception occurred during user update", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

    }
}
