package br.com.serverest.test;

import br.com.serverest.dto.LoginDTO;
import br.com.serverest.dto.UsuarioDTO;
import br.com.serverest.service.UsuarioService;
import br.com.serverest.test.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("Acessos e-commerce")
@Feature("Fluxos de usuário e login")
public class LoginTest extends BaseTest {

    private UsuarioService usuarioService = new UsuarioService();

    @Test
    @Story("Login de usuario")
    @Severity(SeverityLevel.CRITICAL)
    @Description("CT-001: Realizar login com sucesso.")
    public void deveRealizarLoginComSucessoComCredenciaisValidas(){
        UsuarioDTO usuario = usuarioService.criarUsuario();
        LoginDTO informacoesLogin = new LoginDTO(usuario.getEmail(), usuario.getPassword());

        given()
            .spec(publicSpec())
            .body(informacoesLogin)
            .when()
            .post("/login")
            .then()
            .log().ifValidationFails()
            .spec(responseSpecCode200());
    }
}
