package br.com.serverest.test;

import br.com.serverest.dto.LoginDTO;
import br.com.serverest.dto.UsuarioDTO;
import br.com.serverest.service.UsuarioService;
import br.com.serverest.test.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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

    @Test
    @Story("Login com e-mail inválido.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("CT-002: Realizar login com e-mail inválido.")
    public void deveFalharAoRealizarLoginComEmailInvalido(){
        LoginDTO informacoesLogin = new LoginDTO("email@email.com", "password");
        given()
                .spec(publicSpec())
                .body(informacoesLogin)
                .when()
                .post("/login")
                .then()
                .log().ifValidationFails()
                .spec(responseSpecCode401())
                .body("message", equalTo("Email e/ou senha inválidos"));
    }

}
