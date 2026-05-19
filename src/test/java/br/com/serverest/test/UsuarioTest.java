package br.com.serverest.test;

import br.com.serverest.dataFactory.DataFactory;
import br.com.serverest.dto.UsuarioDTO;
import br.com.serverest.service.UsuarioService;
import br.com.serverest.test.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class UsuarioTest extends BaseTest {
    private DataFactory dataFactory = new DataFactory();

    @Test
    @Story("Criar usuário válido")
    @Severity(SeverityLevel.CRITICAL)
    @Description("CT-004: Criar usuário válido.")
    public void deveCriarUsuarioValido(){
        UsuarioDTO usuarioLogin = dataFactory.criarUsuarioAdminFaker(true);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(usuarioLogin)
                .when()
                .post("/usuarios")
                .then()
                .log().ifValidationFails()
                .spec(responseSpecCode201());
    }
}
