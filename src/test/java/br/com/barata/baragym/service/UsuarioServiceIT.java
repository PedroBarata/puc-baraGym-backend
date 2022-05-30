package br.com.barata.baragym.service;

import br.com.barata.baragym.controller.request.UsuarioRequest;
import br.com.barata.baragym.exception.EmailJaCadastradoException;
import br.com.barata.baragym.exception.UsuarioNaoEncontradoException;
import br.com.barata.baragym.model.Usuario;
import br.com.barata.infrastructure.stereotype.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({
		"/db/testdata/usuarios/deleta_todos_usuarios.sql",
		"/db/testdata/usuarios/insere_usuarios.sql"
})
class UsuarioServiceIT {

 @Autowired
 private UsuarioService service;

 @Test
 void buscarPorEmail_quandoExistir_deveRetornarUsuario() {
  //cenario
  String email = "usuario2@teste.com";

  //acao
  Usuario usuario = service.buscarPorEmail(email);

  //validacao
  assertThat(usuario).hasNoNullFieldsOrPropertiesExcept("senha");
  assertThat(usuario.getEmail()).isEqualTo(email);
 }

 @Test
 void buscarPorEmail_quandoNaoExistir_deveLancarExcecao() {
  //cenario
  String email = "usuario-inexistente@teste.com";

  //acao e validacao
  assertThrows(UsuarioNaoEncontradoException.class, () -> service.buscarPorEmail(email));
 }

 @Test
 void buscarPorMatricula_quandoExistir_deveRetornarUsuario() {
  //cenario
  String matricula = "2";

  //acao
  Usuario usuario = service.buscarPorMatricula(matricula);

  //validacao
  assertThat(usuario).hasNoNullFieldsOrPropertiesExcept("senha");
  assertThat(usuario.getMatricula()).isEqualTo(matricula);
 }

 @Test
 void buscarPorMatricula_quandoNaoExistir_deveLancarExcecao() {
  //cenario
  String matricula = "121212121";

  //acao e validacao
  assertThrows(UsuarioNaoEncontradoException.class, () -> service.buscarPorMatricula(matricula));
 }

 @Test
 void listarTodosUsuarios_deveRetornarListaPreenchida() {
  //acao
  Page<Usuario> usuarioPage = service.listarTodosUsuarios(PageRequest.of(0, 10));

  //validacao - paginacao
  assertThat(usuarioPage.getTotalPages()).isEqualTo(1);
  assertThat(usuarioPage.getTotalElements()).isEqualTo(3);

  //validacao - conteudo do elemento - admin
  assertThat(usuarioPage.getContent().get(0).getMatricula()).isEqualTo("1");

 }

 @Test
 void criarUsuario_quandoNaoExistir_deveRetornarUsuarioCriado() {
  //cenario
  String email = "teste1@teste.com";
  String nome = "Usuario teste";
  UsuarioRequest request = UsuarioRequest
		  .builder()
		  .nome(nome)
		  .email(email)
		  .senha("12345")
		  .build();

  //acao
  Usuario usuario = service.criarUsuario(request);

  //validacao
  assertThat(usuario).hasNoNullFieldsOrPropertiesExcept("senha");
  assertThat(usuario.getNome()).isEqualTo(nome);
  assertThat(usuario.getEmail()).isEqualTo(email);
  assertThat(usuario.getSenha()).isNull();
  assertThat(usuario.getMatricula()).isNotNull();

 }

 @Test
 void criarUsuario_quandoJaExistirEmailCadastrado_deveLancarExcecao() {
  //cenario
  String email = "usuario2@teste.com";
  String nome = "Usuario teste";
  UsuarioRequest request = UsuarioRequest
		  .builder()
		  .nome(nome)
		  .email(email)
		  .senha("12345")
		  .build();

  //acao e validacao
  assertThrows(EmailJaCadastradoException.class, () -> service.criarUsuario(request));
 }
}