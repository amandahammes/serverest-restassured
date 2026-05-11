package br.com.serverest.test;

import br.com.serverest.util.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public static void setup(){
        RestAssured.baseURI = ConfigLoader.getProperty("base_url");

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}