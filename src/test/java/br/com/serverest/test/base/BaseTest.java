package br.com.serverest.test.base;

import br.com.serverest.util.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public static void setup(){
        RestAssured.baseURI = ConfigLoader.getProperty("base_url");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected RequestSpecification requestSpec(String token) {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    protected RequestSpecification publicSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    protected ResponseSpecification responseSpecCode200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    protected ResponseSpecification responseSpecCode201() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

}