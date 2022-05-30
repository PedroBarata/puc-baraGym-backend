package br.com.barata.baragym.controller;

import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import br.com.barata.infrastructure.Constants;
import br.com.barata.infrastructure.stereotype.IntegrationTest;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
class UsuarioControllerIT {

 @Autowired
 WebApplicationContext wac;

 @Autowired
 JwtTokenUtil jwtTokenUtil;

 @ParameterizedTest
 @ValueSource(strings = {"1", "2"})
 void obtemUsuarioPorMatricula_comoAdmin_deveRetornarUsuarioEStatus200(String matricula) {
  Usuario usuario = Usuario
		  .builder()
		  .email("admin@teste.com")
		  .senha("123456")
		  .build();

  String jwt = jwtTokenUtil.geraToken(usuario);

  ExtractableResponse<MockMvcResponse> response = given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_AUTH,
				  Constants.HEADER_BEARER + jwt,

				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .when()
		  .get("/usuarios/" + matricula)
		  .then()
		  .status(HttpStatus.OK)
		  .extract();

  Usuario accountResponse = response.as(Usuario.class);

  assertThat(accountResponse).hasNoNullFieldsOrPropertiesExcept("senha");
  assertThat(accountResponse.getMatricula()).isEqualTo(matricula);
 }

 @Test
 void obtemUsuarioPorMatricula_comoUsuarioDaConta_deveRetornarUsuarioEStatus200() {
  Usuario usuario = Usuario
		  .builder()
		  .email("usuario2@teste.com")
		  .senha("123456")
		  .build();


  String jwt = jwtTokenUtil.geraToken(usuario);

  ExtractableResponse<MockMvcResponse> response = given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_AUTH,
				  Constants.HEADER_BEARER + jwt,

				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .when()
		  .get("/usuarios/2")
		  .then()
		  .status(HttpStatus.OK)
		  .extract();

  Usuario accountResponse = response.as(Usuario.class);

  assertThat(accountResponse.getEmail()).isNotBlank();

 }

 @Test
 void obtemUsuarioPorMatricula_comoOutroUsuarioDaConta_deveRetornarStatus403() {
  Usuario usuario = Usuario
		  .builder()
		  .email("usuario2@teste.com")
		  .senha("123456")
		  .build();


  String jwt = jwtTokenUtil.geraToken(usuario);

  given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_AUTH,
				  Constants.HEADER_BEARER + jwt,

				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .when()
		  .get("/usuarios/3")
		  .then()
		  .status(HttpStatus.FORBIDDEN);
 }

 @Test
 void obtemUsuarioPorMatricula_semTokenDeAcesso_deveRetornarStatus403() {
  given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .when()
		  .get("/usuarios/3")
		  .then()
		  .status(HttpStatus.FORBIDDEN)
		  .extract();
 }
}