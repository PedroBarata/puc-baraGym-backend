package br.com.barata.baragym.controller;

import br.com.barata.baragym.model.Usuario;
import br.com.barata.baragym.security.jwt.JwtTokenUtil;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UsuarioControllerIT {

 @Autowired
 WebApplicationContext wac;

 @Autowired
 JwtTokenUtil jwtTokenUtil;

 @Test
 @Sql({"/delete_usuarios.sql", "/insert_usuarios.sql"})
 void testFindAllReturnsName() {
  Usuario usuario = Usuario
		  .builder()
		  .nome("admin123")
		  .matricula("40308")
		  .email("admin@helpdesk.com")
		  .senha("123456")
		  .build();

  String jwt = jwtTokenUtil.geraToken(usuario);

  ExtractableResponse<MockMvcResponse> response = given()
		  .webAppContextSetup(wac)
		  .headers(
				  "Authorization",
				  "Bearer " + jwt,
				  "Content-Type",
				  ContentType.JSON,
				  "Accept",
				  ContentType.JSON)
		  .when()
		  .get("/usuarios/40308")
		  .then()
		  .status(HttpStatus.OK)
		  .extract();

  Usuario accountResponse = response.as(Usuario.class);

  assertThat(accountResponse.getEmail()).isNotBlank();

 }
}