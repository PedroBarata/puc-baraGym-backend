package br.com.barata.baragym.controller.usuario;

import br.com.barata.baragym.controller.usuario.request.UsuarioRequest;
import br.com.barata.baragym.controller.usuario.response.UsuarioResponse;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import br.com.barata.infrastructure.Constants;
import br.com.barata.infrastructure.stereotype.IntegrationTest;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql({
		"/db/testdata/usuarios/deleta_todos_usuarios.sql",
		"/db/testdata/usuarios/insere_usuarios.sql"
})
class UsuarioCriarControllerIT {

 @Autowired
 WebApplicationContext wac;

 @Autowired
 JwtTokenUtil jwtTokenUtil;

 @Test
 @Disabled("Melhorar o tratamento de excecoes")
 void criaUsuario_comEmailJaCadastrado_deveRetornarStatus400() {
  //cenario
  String email = "usuario2@teste.com";
  String nome = "Usuario Teste";

  UsuarioRequest usuario = UsuarioRequest
		  .builder()
		  .email(email)
		  .senha("123456")
		  .nome(nome)
		  .build();

  //acao
  given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .body(usuario)
		  .when()
		  .post("/usuarios")
		  .then()
		  .status(HttpStatus.INTERNAL_SERVER_ERROR)
		  .extract();
 }

 @Test
 void criaUsuario_semEmailCadastrado_deveRetornarUsuarioEStatus200() {
  //cenario
  String email = "usuario-novo@teste.com";
  String nome = "Usuario Teste";

  UsuarioRequest usuario = UsuarioRequest
		  .builder()
		  .email(email)
		  .senha("123456")
		  .nome(nome)
		  .build();

  //acao
  ExtractableResponse<MockMvcResponse> response = given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .body(usuario)
		  .when()
		  .post("/usuarios")
		  .then()
		  .status(HttpStatus.OK)
		  .extract();

  //validacao
  UsuarioResponse usuarioResponse = response.as(UsuarioResponse.class);

  assertThat(usuarioResponse.getEmail()).isEqualTo(email);
  assertThat(usuarioResponse.getNome()).isEqualTo(nome);
  assertThat(usuarioResponse.getMatricula()).isNotNull();
 }

}