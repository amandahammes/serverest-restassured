package br.com.serverest.dataFactory;

import br.com.serverest.dto.UsuarioDTO;
import com.github.javafaker.Faker;

public class DataFactory {

    public Faker faker = new Faker();

    public UsuarioDTO criarUsuarioAdminFaker(boolean usuarioEhAdmin){
        return UsuarioDTO.builder()
                .nome(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(8,20, true, true))
                .administrador(String.valueOf(usuarioEhAdmin))
                .build();
    }
}
