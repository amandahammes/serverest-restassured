package br.com.serverest.service;

import br.com.serverest.dto.LoginDTO;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class LoginService {

    @Step("Realizando login")
    public String realizarLogin(String email, String senha){
        LoginDTO userLogin = new LoginDTO(email, senha);

        String token = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userLogin)
                .when()
                .post("/login")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .path("token");

        assert token != null && !token.isEmpty() : "O token de login não foi gerado!";

        return token;
    }
}
