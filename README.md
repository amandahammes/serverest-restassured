# Automação de Testes - Serverest API

Primeira etapa da automação de testes com REST Assured da Aplicação [Serverest API](https://serverest.dev/?lang=pt-BR#/). <br>
O objetivo final é garantir que as operações CRUD de `login`, `usuarios`, `carrinhos` e `produtos` funcionem corretamente.

## Escopo e Cenários

### Endpoints Mapeados

    - **LOGIN**: Autenticar o usuário para montar um carrinho e, se for administrador, gerenciar os produtos.
    - **USUARIOS**: Gerenciar os usuários, consultar dados para login e cadastrar administrador.
    - **PRODUTOS**: Consultar produtos cadastrados ou, como administrador, gerenciar os produtos da loja.
    - **CARRINHO**: Consultar os carrinhos cadastrados, criar um para o usuário e, no final, desistir da compra pois estava só dando uma olhadinha.


### Cenários de testes desenvolvidos:

Classificação (Class.): N = Negativo e P = Positivo.

| ID     | Func.        | Class. | Cenário                                  | Método | Endpoint        | Code | Resposta Esperada                                                                 | 
|:-------|:-------------|:-------|:-----------------------------------------|:-------|:----------------|:-----|:----------------------------------------------------------------------------------|
| CT-001 | **LOGIN**    | P      | Realizar login com sucesso.              | POST   | /login          | 200  | "Login realizado com sucesso"                                                     |
| CT-002 | **LOGIN**    | N      | E-mail inválido.                         | POST   | /login          | 401  | "Email e/ou senha inválidos"                                                      |
| CT-003 | **LOGIN**    | N      | Senha inválida.                          | POST   | /login          | 401  | "Email e/ou senha inválidos"                                                      |
| CT-004 | **USUARIOS** | P      | Criar usuário válido.                    | POST   | /usuarios       | 201  | "Cadastro realizado com sucesso" + ID.                                            |
| CT-005 | **USUARIOS** | N      | Tentar criar com e-mail existente.       | POST   | /usuarios       | 400  | "Este email já está sendo usado"                                                  |
| CT-006 | **USUARIOS** | P      | Editar usuário.                          | PUT    | /usuarios/{_id} | 200  | "Registro alterado com sucesso"                                                   |
| CT-007 | **USUARIOS** | N      | Editar usuário com e-mail já cadastrado. | PUT    | /usuarios/{_id} | 400  | "Este email já está sendo usado"                                                  |
| CT-008 | **USUARIOS** | P      | Excluir usuário.                         | DELETE | /usuarios/{_id} | 200  | "Registro excluído com sucesso / Nenhum registro excluído"                        |
| CT-009 | **USUARIOS** | N      | Excluir usuário com carrinho cadastrado. | DELETE | /usuarios/{_id} | 400  | "Não é permitido excluir usuário com carrinho cadastrado"                         |
| CT-010 | **USUARIOS** | P      | Buscar usuário por ID.                   | GET    | /usuarios/{_id} | 200  | JSON com informações do usuário.                                                  |
| CT-011 | **USUARIOS** | N      | Buscar usuário por ID inexistente.       | GET    | /usuarios/{_id} | 400  | "Usuário não encontrado"                                                          |
| CT-012 | **USUARIOS** | P      | Listar usuários cadastrados.             | GET    | /usuarios       | 200  | JSON com lista de usuários e respectivas informações.                             |
| CT-013 | **PRODUTOS** | P      | Cadastrar produto.                       | POST   | /produtos       | 201  | "Cadastro realizado com sucesso" + ID                                             |
| CT-014 | **PRODUTOS** | N      | Cadastrar produto com nome existente.    | POST   | /produtos       | 400  | "Já existe produto com esse nome"                                                 |
| CT-015 | **PRODUTOS** | N      | Cadastrar produto com token inválido.    | POST   | /produtos       | 401  | "Token de acesso ausente, inválido, expirado ou usuário do token não existe mais" |
| CT-016 | **PRODUTOS** | N      | Cadastrar produto com usuário não admin. | POST   | /produtos       | 403  | "Rota exclusiva para administradores"                                             |
| CT-017 | **PRODUTOS** | P      | Alterar produto.                         | PUT    | /produtos/{_id} | 200  | "Registro alterado com sucesso"  + ID                                             |
| CT-018 | **PRODUTOS** | N      | Alterar produto com nome já existente.   | PUT    | /produtos/{_id} | 400  | "Já existe produto com esse nome"                                                 |
| CT-019 | **PRODUTOS** | N      | Alterar produto com token inválido.      | PUT    | /produtos/{_id} | 401  | "Token de acesso ausente, inválido, expirado ou usuário do token não existe mais" |
| CT-020 | **PRODUTOS** | N      | Alterar produto com usuário não admin.   | PUT    | /produtos/{_id} | 403  | "Rota exclusiva para administradores"                                             |
| CT-021 | **PRODUTOS** | P      | Excluir produto.                         | DELETE | /produtos/{_id} | 200  | "Registro excluído com sucesso / Nenhum registro excluído"                        |
| CT-022 | **PRODUTOS** | N      | Excluir usuário com carrinho cadastrado. | DELETE | /produtos/{_id} | 400  | "Não é permitido excluir usuário com carrinho cadastrado"                         |
| CT-023 | **PRODUTOS** | N      | Deletar produto com token inválido.      | DELETE | /produtos/{_id} | 401  | "Token de acesso ausente, inválido, expirado ou usuário do token não existe mais" |
| CT-024 | **PRODUTOS** | N      | Deletar produto com usuário não admin.   | DELETE | /produtos/{_id} | 403  | "Rota exclusiva para administradores"                                             |
| CT-025 | **PRODUTOS** | P      | Buscar produto por ID.                   | GET    | /produtos/{_id} | 200  | JSON com informações do produto.                                                  |
| CT-026 | **PRODUTOS** | N      | Buscar produto por ID inexistente.       | GET    | /produtos/{_id} | 400  | "Produto não encontrado"                                                          |
| CT-027 | **PRODUTOS** | P      | Listar produtos cadastrados.             | GET    | /usuarios       | 200  | JSON com lista de produtos e respectivas informações.                             |




## Ferramentas e Tecnologias Utilizadas

Este projeto utiliza a stack de automação Java com as seguintes tecnologias:

    - Java 21: Linguagem base do projeto.
    - JUnit 5: Framework de execução de testes.
    - Rest Assured: Biblioteca para automação de testes de APIs REST.
    - Maven: Gerenciador de dependências e automação de build.
    - DataFaker: Geração de massa de dados aleatória.
    - Hamcrest: Biblioteca de matchers que permite criar regras de verificação (assertions) mais legíveis.
    - Jackson Databind: Responsável pela serialização e desserialização de objetos Java para JSON e vice-versa. 
    - Jackson Datatype: Módulo complementar que permite ao Jackson serializar e desserializar tipos modernos do Java, como datas (LocalDate) e horários.
    - Allure: cria arquivos .txt e .json com os dados sobre os testes rodados.

Comando para ver Relatório em gráficos:
& "C:\allure-2.38.1\allure-2.38.1\bin\allure.bat" open allure-report


## Ambiente

URL Swagger: http://localhost:8080/swagger-ui/index.html <br>
Só estará disponível ao rodar a API como explicado no item: Rodar API.

## Para rodar o Projeto na sua Máquina

### Pré requisitos

[Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) <br>
[Maven](https://maven.apache.org/download.cgi) <br>
[E-commerce API](https://github.com/renansalves/E-Commerce-API) <br>


## Rodar API

Para rodar a API, o docker deve estar instalado/aberto e, na pasta principal do projeto, é necessário rodar o comando: `docker compose up -d --build app postgres`. Para mais informações, acessar repositório da [E-commerce API](https://github.com/renansalves/E-Commerce-API)


## Rodar testes na sua máquina

1. **Clonar o projeto:**

Para realizar o clone do projeto, clique em clone, escolha a forma que deseja fazer a clonagem (se por SSH ou HTTPS), no seu ambiente local de trabalho, abra o terminal, cole o seguinte código e execute o comando: git clone [cole-o-link-copiado-aqui]

2. **Como executar projeto**

Após o clone do projeto, acesse o diretório recém clonado e execute o seguinte comando no terminal: `mvn clean test`


### Resultados

Como a API ainda está em processo de desenvolvimento e ajustes estão sendo realizados, há a possibilidade de alguns testes quebrarem.

### Issues

[CT-101](https://github.com/renansalves/E-Commerce-API/issues/4) <br>
[CT-102](https://github.com/renansalves/E-Commerce-API/issues/5) <br>
[CT-105](https://github.com/renansalves/E-Commerce-API/issues/6) <br>
[CT-106](https://github.com/renansalves/E-Commerce-API/issues/7) <br>
[CT-107](https://github.com/renansalves/E-Commerce-API/issues/9) <br>
[CT-109](https://github.com/renansalves/E-Commerce-API/issues/8) <br>

* Projeto desenvolvido por [Amanda Kopper Hammes](https://github.com/amandahammes) entre janeiro e março de 2026.