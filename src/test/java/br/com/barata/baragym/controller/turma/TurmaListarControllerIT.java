package br.com.barata.baragym.controller.turma;

import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import br.com.barata.infrastructure.Constants;
import br.com.barata.infrastructure.stereotype.IntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@IntegrationTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql({
		"/db/testdata/turmas/deleta_todas_turmas.sql",
		"/db/testdata/turmas/insere_turmas.sql",

		"/db/testdata/usuarios/deleta_todos_usuarios.sql",
		"/db/testdata/usuarios/insere_usuarios.sql"
})
class TurmaListarControllerIT {

 @Autowired
 WebApplicationContext wac;

 @Autowired
 JwtTokenUtil jwtTokenUtil;

 @Test
 void listarTodasTurmas_comoAdmin_deveRetornarUsuariosEStatus200() {
  Usuario usuario = Usuario
		  .builder()
		  .email("admin@teste.com")
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
		  .get("/turmas")
		  .then()
		  .status(HttpStatus.OK);

 }

 @Test
 void listarTodasTurmas_comoAdminEParametros_deveRetornarUsuariosEStatus200() {
  Usuario usuario = Usuario
		  .builder()
		  .email("admin@teste.com")
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
		  .queryParam("page", 0)
		  .queryParam("size", 1)
		  .when()
		  .get("/turmas")
		  .then()
		  .status(HttpStatus.OK);

 }

 @Test
 void listarTodasTurmas_comoUsuario_deveRetornarStatus403() {
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
		  .get("/turmas")
		  .then()
		  .status(HttpStatus.FORBIDDEN);
 }

 @Test
 void listarTodosUsuarios_semTokenDeAcesso_deveRetornarStatus403() {
  given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .when()
		  .get("/usuarios")
		  .then()
		  .status(HttpStatus.FORBIDDEN)
		  .extract();
 }

}