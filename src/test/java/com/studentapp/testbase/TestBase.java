package com.studentapp.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    @BeforeClass
    public static void setUpBeforeClass(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
        RestAssured.basePath = "student";
    }
}
