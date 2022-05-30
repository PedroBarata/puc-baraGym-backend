package br.com.barata.baragym.controller.turma;

import br.com.barata.baragym.controller.turma.request.TurmaRequest;
import br.com.barata.baragym.model.Turma;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import br.com.barata.infrastructure.Constants;
import br.com.barata.infrastructure.stereotype.IntegrationTest;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.ExtractableResponse;
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
		"/db/testdata/turmas/deleta_todas_turmas.sql",
		"/db/testdata/turmas/insere_turmas.sql",

		"/db/testdata/usuarios/deleta_todos_usuarios.sql",
		"/db/testdata/usuarios/insere_usuarios.sql"
})
class TurmaCriarControllerIT {

 @Autowired
 WebApplicationContext wac;

 @Autowired
 JwtTokenUtil jwtTokenUtil;

 @Test
 void criaTurma_comoAdmin_deveRetornar200() {
  //cenario
  Integer capacidade = 100;
  String nome = "Sala Teste";

  TurmaRequest turma = TurmaRequest
		  .builder()
		  .nome(nome)
		  .capacidade(capacidade)
		  .build();

  //token
  Usuario usuario = Usuario
		  .builder()
		  .email("admin@teste.com")
		  .senha("123456")
		  .build();

  String jwt = jwtTokenUtil.geraToken(usuario);

  //acao
  ExtractableResponse<MockMvcResponse> response = given()
		  .webAppContextSetup(wac)
		  .headers(
				  Constants.HEADER_AUTH,
				  Constants.HEADER_BEARER + jwt,

				  Constants.HEADER_CONTENT_TYPE,
				  ContentType.JSON,

				  Constants.HEADER_ACCEPT,
				  ContentType.JSON)
		  .body(turma)
		  .when()
		  .post("/turmas")
		  .then()
		  .status(HttpStatus.OK)
		  .extract();

  //validacao
  Turma turmaResponse = response.as(Turma.class);

  assertThat(turmaResponse.getId()).isNotNull();
  assertThat(turmaResponse.getNome()).isEqualTo(nome);
  assertThat(turmaResponse.getCapacidade()).isEqualTo(capacidade);
 }

 @Test
 void criaTurma_comoUsuario_deveRetornar403() {
  //cenario
  Integer capacidade = 100;
  String nome = "Sala Teste";

  TurmaRequest turma = TurmaRequest
		  .builder()
		  .nome(nome)
		  .capacidade(capacidade)
		  .build();

  //token
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
		  .body(turma)
		  .when()
		  .post("/turmas")
		  .then()
		  .status(HttpStatus.FORBIDDEN);
 }
}