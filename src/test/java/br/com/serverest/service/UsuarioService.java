package br.com.serverest.service;

import br.com.serverest.dataFactory.DataFactory;
import br.com.serverest.dto.UsuarioDTO;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class UsuarioService {

    private DataFactory dataFactory = new DataFactory();

    @Step("Criar usuário")
    public UsuarioDTO criarUsuario(){
        UsuarioDTO usuarioLogin = dataFactory.criarUsuarioAdminFaker(true);

        String idUsuario = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(usuarioLogin)
                .when()
                .post("/usuarios")
                .then()
                .log().ifValidationFails()
                .statusCode(201)
                .extract()
                .path("_id");

        usuarioLogin.set_id(idUsuario);

        return usuarioLogin;
    }
}