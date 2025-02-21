package com.task.api;

import com.task.utils.RestAssuredUtils;
import com.task.config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CreateUserTest {

    private String baseUrl;
    
    @BeforeClass
    public void setUp() {
        baseUrl = ConfigReader.getBaseUrl();
        RestAssuredUtils.setup(baseUrl);
    }

    @Test
    public void createUser() {
        String body = "{ \"name\": \"John\", \"job\": \"Developer\", \"age\": 30 }";
        Response response = RestAssuredUtils.sendPostRequest("/api/users", body);
        
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.jsonPath().getString("name").contains("John"));
        Assert.assertTrue(response.jsonPath().getString("job").contains("Developer"));
    }
}

